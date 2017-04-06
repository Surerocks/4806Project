package suhdude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
	private UserRepository repo;
	private AuthenticationController auth;

	@Autowired
    public void setRepo(UserRepository repo){
        this.repo = repo;
    }

	@Autowired
	public void setAuth(AuthenticationController auth){
		this.auth = auth;
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
	
	@RequestMapping(value="/manageGroups",method=RequestMethod.GET)
	public String manageGroups(){
		return "manageGroup";
	}

	@RequestMapping(value="/createGroup", method=RequestMethod.GET)
	public String createGroup(@CookieValue(value="sessionId", defaultValue="") String sessionId,
							  @RequestParam(value = "username", defaultValue = "") String username, Model model) {
		if(!auth.isAuthenticated(sessionId)){
			model.addAttribute("message", "User not authenticated");
			return "error";
		}
		List<User> students = repo.findBySessionId(sessionId);
		if(students.isEmpty()){
			model.addAttribute("message", "User not authenticated");
			return "error";
		}
		if(!(students.get(0) instanceof Student)){
			model.addAttribute("message", "Only students can create & join groups");
			return "error";
		}
		Student st = (Student) students.get(0);
		if(username == "") {
			st.createGroup();
		}
		else {
			students = repo.findByUsername(username);
			if(students.isEmpty()){
				model.addAttribute("message", "User not authenticated");
				return "error";
			}
			Student st2 = (Student) students.get(0);
			if (st.getGroup().isEmpty() && st2.getGroup().isEmpty()) {
				st.createGroup(st2);
			} else if (st.getGroup().isEmpty() && !st2.getGroup().isEmpty()) {
				st2.addToGroup(st);
			} else if (!st.getGroup().isEmpty() && st2.getGroup().isEmpty()) {
				st.addToGroup(st2);
			} else {
				model.addAttribute("message", "Both students are in a group");
				return "error";
			}
			repo.save(st2);
		}
		repo.save(st);
		model.addAttribute("group",st.getGroup());
		return "manageGroup";
	}

	@RequestMapping(value="/disbandGroup", method=RequestMethod.GET)
	public String disbandGroup(@CookieValue(value="sessionId",defaultValue="") String sessionId, Model model) {
		if(!auth.isAuthenticated(sessionId)){
    		model.addAttribute("message", "User not authenticated");
    		return "error";
    	}
    	List<User> students = repo.findBySessionId(sessionId);
    	if(students.isEmpty()){
    		model.addAttribute("message", "User not authenticated");
    		return "error";
    	}
    	if(!(students.get(0) instanceof Student)){
    		model.addAttribute("message", "Only students that are group leaders can disband a group");
    		return "error";
    	}
    	Student st = (Student) students.get(0);
    	if(!st.isGroupLeader()) {
    		model.addAttribute("message", "Only students that are group leaders can disband a group");
    		return "error";
    	}
    	st.disbandGroup();
    	return "manageGroup";
	}
}