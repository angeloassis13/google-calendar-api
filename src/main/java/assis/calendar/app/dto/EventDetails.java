package assis.calendar.app.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EventDetails extends EventBasicInfo {

	private String location;
	private String description;
	private String eventType;
	private String hangoutLink;
	private String organizerEmail;
	private String status;

}
