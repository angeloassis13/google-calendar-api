package assis.calendar.app.controller.advice;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class RestControllerExceptionHandler {

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	@ResponseBody
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	protected String handleDataIntegrityViolationException(DataIntegrityViolationException e) {
		log.error("DataIntegrityViolationException error", e);
		return "This value already exists on database";
	}
	
	@ExceptionHandler(value = { Exception.class })
	protected void handleGenericException(Exception e) {
		log.error("Unexpected error", e);
	}
	
	
}
