package suhdude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {
	private UserRepository repo;
	
	@Autowired
    public void setRepo(UserRepository repo){
        this.repo = repo;
    }
	
	@RequestMapping(value="/createStudent",method=RequestMethod.GET)
	public String createStudent(@RequestParam("studentName") String studentName,
				@RequestParam("email") String email,
				@RequestParam("studentNo") int studentNo,
				@RequestParam("password") String password,
				@RequestParam("username") String username){
		Student s = new Student();
		s.setEmail(email);
		s.setName(studentName);
		s.setStudentNo(studentNo);
		s.setPassword(password);
		s.setUsername(username);
		repo.save(s);
		return "login";
	}
	
	@RequestMapping(value="/createStudents",method=RequestMethod.GET)
	public String createStudents(){
		return "createStudent";
	}
}