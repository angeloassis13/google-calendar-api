package assis.calendar.app.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import assis.calendar.app.DateUtils;
import assis.calendar.app.Mappers;
import assis.calendar.app.dto.ValuesConfigDto;
import assis.calendar.app.entity.ValuesConfig;
import assis.calendar.app.repository.ValuesConfigRepository;

@Service
public class ValuesConfigService {

	private static final String DEFAULT_SORT_BY = "startTime";

	@Autowired
	private Mappers mapper;

	@Autowired
	private ValuesConfigRepository valuesConfigRepository;

	public List<ValuesConfig> findAll() {
		return valuesConfigRepository.findAll(Sort.by(DEFAULT_SORT_BY));
	}

	public ValuesConfig createOrUpdate(ValuesConfig config) {
		return valuesConfigRepository.save(config);
	}

	public ValuesConfig createConfig(ValuesConfigDto valuesConfigDto) {
		ValuesConfig valuesConfig = mapper.dtoToEntity(valuesConfigDto);
		return createOrUpdate(valuesConfig);
	}

	public ValuesConfig updateConfig(Long id, ValuesConfigDto valuesConfigDto) {
		ValuesConfig valuesConfig = mapper.dtoToEntity(valuesConfigDto);
		valuesConfig.setId(id);
		return createOrUpdate(valuesConfig);
	}

	public void deleteConfig(Long id) {
		valuesConfigRepository.deleteById(id);
	}

	public Map<String, Double> getValuesMap(LocalDate start, LocalDate end) {

		Map<String, Double> valuesByDate = new LinkedHashMap<>();

		ValuesConfig currentConfig = valuesConfigRepository.findLastBeforeDate(start);
		LocalDate currentDate = start;

		List<ValuesConfig> intervalDates = valuesConfigRepository.findByDate(start, end);
		LocalDate firstDate = intervalDates.get(0).getStartTime();

		while (currentDate.isBefore(firstDate)) {
			valuesByDate.put(DateUtils.formatLocalDate(currentDate), currentConfig.getValue());
			currentDate = currentDate.plusDays(1L);
		}

		currentConfig = intervalDates.get(0);
		for (ValuesConfig nextConfig : intervalDates.subList(1, intervalDates.size())) {
			LocalDate nextDate = nextConfig.getStartTime();

			while (currentDate.isBefore(nextDate)) {
				valuesByDate.put(DateUtils.formatLocalDate(currentDate), currentConfig.getValue());
				currentDate = currentDate.plusDays(1L);
			}
			currentConfig = nextConfig;
		}

		while (currentDate.isBefore(end)) {
			valuesByDate.put(DateUtils.formatLocalDate(currentDate), currentConfig.getValue());
			currentDate = currentDate.plusDays(1L);
		}
		return valuesByDate;
	}

}
