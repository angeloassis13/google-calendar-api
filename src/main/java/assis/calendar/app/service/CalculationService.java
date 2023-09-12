package assis.calendar.app.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import assis.calendar.app.dto.CalcDto;
import assis.calendar.app.dto.EventBasicInfo;

@Service
public class CalculationService {

	@Autowired
	private GoogleIntegrationService googleIntegrationService;

	@Autowired
	private ValuesConfigService valuesConfigService;

	public CalcDto calculate(LocalDate startDate, LocalDate endDate) throws IOException {

		if (endDate == null) {
			endDate = startDate.plusMonths(1L);
		}

		List<EventBasicInfo> events = googleIntegrationService.getEvents(startDate, endDate);

		Map<String, Double> configValuesMap = valuesConfigService.getValuesMap(startDate, endDate);

		Double totalValue = 0.0;
		for (EventBasicInfo event : events) {
			String eventTimeKey = event.getEventTime().toLocalDate().toString();
			totalValue += configValuesMap.get(eventTimeKey);

		}

		return new CalcDto(events, totalValue);

	}

}
