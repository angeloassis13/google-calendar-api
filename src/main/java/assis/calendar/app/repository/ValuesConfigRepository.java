package assis.calendar.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import assis.calendar.app.entity.ValuesConfig;

public interface ValuesConfigRepository extends JpaRepository<ValuesConfig, Long> {

	@Query("SELECT conf FROM ValuesConfig conf WHERE startTime > :start AND startTime < :end ORDER BY startTime ")
	List<ValuesConfig> findByDate(@Param(value = "start") LocalDate start, @Param(value = "end") LocalDate end);

	@Query("SELECT conf FROM ValuesConfig conf WHERE startTime <= :start ORDER BY startTime DESC LIMIT 1")
	ValuesConfig findLastBeforeDate(LocalDate start);

}
