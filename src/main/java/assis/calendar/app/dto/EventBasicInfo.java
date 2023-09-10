package assis.calendar.app.dto;

import java.time.LocalDateTime;

import com.google.api.client.util.DateTime;

import assis.calendar.app.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class EventBasicInfo {

	@NonNull
	private String id;

	@NonNull
	private String title;

	@NonNull
	private LocalDateTime eventTime;

	public void setEventTime(DateTime eventTime) {
		if (eventTime.isDateOnly())
			this.eventTime = DateUtils.googleDateToLocalDateTime(eventTime);
		else
			this.eventTime = DateUtils.googleDateTimeToLocalDateTime(eventTime);
	}
}
