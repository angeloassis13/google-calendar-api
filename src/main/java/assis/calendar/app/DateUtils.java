package assis.calendar.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.google.api.client.util.DateTime;

public class DateUtils {

	private DateUtils() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static DateTime localDateTimeToDateTime(LocalDateTime value) {
		return new DateTime(value.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000);
	}

	public static LocalDateTime googleDateTimeToLocalDateTime(DateTime value) {
		return ZonedDateTime.parse(value.toStringRfc3339()).toLocalDateTime();
	}

	public static LocalDateTime googleDateToLocalDateTime(DateTime value) {
		return LocalDateTime.of(LocalDate.parse(value.toStringRfc3339()), LocalTime.of(0, 0));
	}

	public static String formatLocalDate(LocalDate currentDate) {
		return currentDate.format(FORMATTER);
	}

}
