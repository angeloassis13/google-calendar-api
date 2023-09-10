package assis.calendar.app.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import assis.calendar.app.dto.EventBasicInfo;
import assis.calendar.app.dto.EventDetails;
import assis.calendar.app.service.GoogleIntegrationService;

@RestController
@RequestMapping("/calendar")
public class CalendarController {

	@Autowired
	private GoogleIntegrationService googleService;

	@GetMapping("/events")
	public List<EventBasicInfo> events(@RequestParam(required = false, defaultValue = "#{T(java.time.LocalDateTime).now()}") LocalDateTime startDate,
			@RequestParam(required = false) LocalDateTime endDate)
			throws IOException {
		return googleService.getEvents(startDate, endDate);
	}

	@GetMapping("/events/{eventId}")
	public EventDetails event(@PathVariable String eventId) throws IOException {
		return googleService.getEventDetails(eventId);
	}

}
