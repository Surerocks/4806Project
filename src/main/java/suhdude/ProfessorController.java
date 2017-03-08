package suhdude;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfessorController {
	private ProfessorRepository repo;
	
	@Autowired
    public void setRepo(ProfessorRepository repo){
        this.repo = repo;
    }
	
	@RequestMapping(value="/createProfessor",method=RequestMethod.GET)
	public String createProf(@RequestParam("profName") String profName,
				@RequestParam("email") String email){
		Professor p = new Professor(profName,email);
		repo.save(p);
		return "hello";
	}
	
	@RequestMapping(value="/createProfessors",method=RequestMethod.GET)
	public String createProfs(){
		return "createProf";
	}
}
