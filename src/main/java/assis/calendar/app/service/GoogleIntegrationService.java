package assis.calendar.app.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import assis.calendar.app.DateUtils;
import assis.calendar.app.Mappers;
import assis.calendar.app.dto.EventBasicInfo;
import assis.calendar.app.dto.EventDetails;

@Service
public class GoogleIntegrationService {

	private static final String DEFAULT_CALENDAR_ID = "primary";
	private static final String DEFAULT_ORDER_BY = "startTime";

	@Autowired
	private Mappers mapper;

	@Autowired
	private Calendar calendarService;

	public List<EventBasicInfo> getEvents(LocalDate startDate, LocalDate endDate) throws IOException {

		if (endDate == null) {
			endDate = startDate.plusMonths(1L);
		}

		DateTime timeMin = DateUtils.localDateToDateTime(startDate);
		DateTime timeMax = DateUtils.localDateToDateTime(endDate);

		Events events = calendarService.events()
				.list(DEFAULT_CALENDAR_ID)
				.setTimeMin(timeMin)
				.setTimeMax(timeMax)
				.setOrderBy(DEFAULT_ORDER_BY)
				.setSingleEvents(true)
				.execute();
		List<Event> items = events.getItems();

		List<EventBasicInfo> eventsBasicInfo = new ArrayList<>();

		if (!items.isEmpty()) {

			for (Event event : items) {

				EventBasicInfo eventBasicInfo = mapper.googleEventToEventBasicInfo(event);

				DateTime start = event.getStart().getDateTime();
				if (start == null) {
					start = event.getStart().getDate();
				}

				eventBasicInfo.setEventTime(start);
				eventsBasicInfo.add(eventBasicInfo);
			}
		}

		return eventsBasicInfo;

	}

	public EventDetails getEventDetails(String eventId) throws IOException {
		Event event = calendarService.events().get(DEFAULT_CALENDAR_ID, eventId).execute();
		return mapper.googleEventToEventDetails(event);
	}

}
