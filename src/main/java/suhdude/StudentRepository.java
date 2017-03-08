package suhdude;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Integer>{
	List<Student> findByStudentNo(Integer studentNo);
}
