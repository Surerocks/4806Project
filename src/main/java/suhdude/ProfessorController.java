package suhdude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfessorController {
	private UserRepository repo;
	
	@Autowired
    public void setRepo(UserRepository repo){
        this.repo = repo;
    }
	
	@RequestMapping(value="/createProfessor",method=RequestMethod.GET)
	public String createProf(@RequestParam("profName") String profName,
				@RequestParam("email") String email,
				@RequestParam("password") String password,
				@RequestParam("username") String username){
		Professor p = new Professor(profName,email);
		p.setUsername(username);
		p.setPassword(password);
		repo.save(p);
		return "login";
	}
	
	@RequestMapping(value="/createProfessors",method=RequestMethod.GET)
	public String createProfs(){
		return "createProf";
	}
}
