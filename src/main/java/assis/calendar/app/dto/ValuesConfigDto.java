package assis.calendar.app.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ValuesConfigDto {

	@NonNull
	private LocalDate startTime;

	@NonNull
	private Double value;

}
