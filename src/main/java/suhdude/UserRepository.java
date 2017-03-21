package suhdude;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String>{
	List<User> findByUsername (String username);
	List<User> findBySessionId (String sessionId);
}
