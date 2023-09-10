package assis.calendar.app;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.google.api.services.calendar.model.Event;

import assis.calendar.app.dto.EventBasicInfo;
import assis.calendar.app.dto.EventDetails;
import assis.calendar.app.dto.ValuesConfigDto;
import assis.calendar.app.entity.ValuesConfig;

@Mapper(componentModel = "spring")
public interface Mappers {

	@Mapping(source = "summary", target = "title")
	EventBasicInfo googleEventToEventBasicInfo(Event source);

	@InheritConfiguration(name = "googleEventToEventBasicInfo")
	@Mapping(source = "organizer.email", target = "organizerEmail")
	EventDetails googleEventToEventDetails(Event source);

	ValuesConfig dtoToEntity(ValuesConfigDto source);

}
