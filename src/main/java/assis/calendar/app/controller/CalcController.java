package assis.calendar.app.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import assis.calendar.app.dto.CalcDto;
import assis.calendar.app.service.CalculationService;

@RestController
@RequestMapping("")
public class CalcController {

	@Autowired
	private CalculationService calcService;

	@GetMapping("/calc")
	public CalcDto events(
			@RequestParam(required = false, defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate startDate,
			@RequestParam(required = false) LocalDate endDate) throws IOException {
		return calcService.calculate(startDate, endDate);
	}

}
