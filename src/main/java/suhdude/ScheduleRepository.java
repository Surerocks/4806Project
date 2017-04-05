package suhdude;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<Schedule,Integer>{
	List<Schedule> findById(Integer id);

}
