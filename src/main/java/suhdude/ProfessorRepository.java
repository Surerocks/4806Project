package suhdude;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepository extends CrudRepository<Professor,Integer>{
	List<Professor> findByName(String name);
}
