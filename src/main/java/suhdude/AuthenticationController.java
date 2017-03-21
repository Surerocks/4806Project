package suhdude;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {
	
	// TODO: This class will be used to handle login for a user
	
	// Will need a UserRepository, which means existing Prof and Student repos
		// will no longer be needed, but replaced with User repo and getProfs/getStudents
		// methods within that repository
	private UserRepository repo;
	
	@Autowired
	public void setRepo(UserRepository repo){
		this.repo = repo;
	}
	
	// Our new landing page will be a page that makes calls to this to login,
		// which then returns the appropriate homepage based on the type of User
		// For example, Profs will see a different view than Students
	
	// Our new landing page will also have a register as student or register as prof,
		// Which will require backend support
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String createProf(@RequestParam("username") String username,
				@RequestParam("password") String password,
				Model model){
		List<User> users = repo.findByUsername(username);
		if(users.isEmpty()){
			// TODO: Return nice message about username/password invalid
			model.addAttribute("message", "User not found");
			return "error";
		}
		User u = users.get(0);
		if(u.getPassword().equals(password)){
			// TODO: Return a view that is specific to the type of User
			return "hello";
		} else {
			// TODO: Return nice message about username/password invalid
			model.addAttribute("message", "Password incorrect");
			return "error";
		}
	}
	
}
