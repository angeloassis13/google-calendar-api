package assis.calendar.app.dto;

import java.util.List;

import lombok.Data;
import lombok.NonNull;

@Data
public class CalcDto {

	@NonNull
	private List<EventBasicInfo> events;

	@NonNull
	private Double result;

}
