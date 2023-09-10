package assis.calendar.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assis.calendar.app.dto.ValuesConfigDto;
import assis.calendar.app.entity.ValuesConfig;
import assis.calendar.app.service.ValuesConfigService;

@RestController
@RequestMapping("/config")
public class ConfigController {

	@Autowired
	private ValuesConfigService valuesConfigService;

	@GetMapping
	public List<ValuesConfig> getConfig() {
		return valuesConfigService.findAll();
	}

	@PostMapping
	public ValuesConfig createConfig(@RequestBody ValuesConfigDto valuesConfigCreateDto) {
		return valuesConfigService.createConfig(valuesConfigCreateDto);
	}

	@PutMapping("/{id}")
	public ValuesConfig updateConfig(@PathVariable Long id, @RequestBody ValuesConfigDto valuesConfigDto) {
		return valuesConfigService.updateConfig(id, valuesConfigDto);
	}

	@DeleteMapping("/{id}")
	public void deleteConfig(@PathVariable Long id) {
		valuesConfigService.deleteConfig(id);
	}

}
