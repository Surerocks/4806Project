package suhdude;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project,Integer>{
	List<Project> findById(Integer id);
}
