package suhdude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {
	private StudentRepository repo;
	
	@Autowired
    public void setRepo(StudentRepository repo){
        this.repo = repo;
    }
	
	@RequestMapping(value="/createStudent",method=RequestMethod.GET)
	public String createStudent(@RequestParam("studentName") String studentName,
				@RequestParam("email") String email,
				@RequestParam("studentNo") int studentNo){
		Student s = new Student();
		s.setEmail(email);
		s.setName(studentName);
		s.setStudentNo(studentNo);
		repo.save(s);
		return "hello";
	}
}