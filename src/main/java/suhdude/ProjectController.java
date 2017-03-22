package suhdude;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProjectController {
	private ProjectRepository repo;
	private UserRepository userRepo;
	private AuthenticationController auth;
	
	@Autowired
	public void setAuth(AuthenticationController auth){
		this.auth = auth;
	}

    @Autowired
    public void setRepo(ProjectRepository repo){
        this.repo = repo;
    }
    
    @Autowired
    public void setUserRepo(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getHome(Model model,
    		@CookieValue(value="sessionId",defaultValue="") String sessionId){
    	// Just return the login page if not authenticated
    	if(!auth.isAuthenticated(sessionId)){
    		return "login";
    	}
        return "hello";
    }
    
    @RequestMapping(value="/displayProjects", method= RequestMethod.GET)
    public String getProjects(Model model){
    	Iterator<Project> projs = repo.findAll().iterator();
    	List<Project> projects = new ArrayList<Project>();
    	while(projs.hasNext()){
    		projects.add(projs.next());
    	}
    	model.addAttribute("projects",projects);
    	return "displayProject";
    }
    
    @RequestMapping(value="/createProjects", method= RequestMethod.GET)
    public String getCreatePage(@CookieValue(value="sessionId",defaultValue="") String sessionId,
    		Model model){
    	if(!auth.isAuthenticated(sessionId)){
    		model.addAttribute("message", "User not authenticated");
    		return "error";
    	}
    	
    	return "createProject";
    }
    
    @RequestMapping(value="/viewProject", method= RequestMethod.GET)
    public String getProjects(@RequestParam(value="projectId") Integer id,
					Model model){
    	List<Project> project = repo.findById(id);
    	if(project.isEmpty()){
    		return "error";
    	}
    	model.addAttribute("project",project.get(0));
    	return "project";
    }
    
    @RequestMapping(value="/createProject",method=RequestMethod.GET)
    public String createProject(@RequestParam(value="name") String name,
    				@RequestParam(value="max") int maxStudents,
    				@CookieValue(value="sessionId",defaultValue="") String sessionId,
    				Model model){
    	if(!auth.isAuthenticated(sessionId)){
    		model.addAttribute("message", "User not authenticated");
    		return "error";
    	}
    	// Get the prof that is referenced
    	List<User> profs = userRepo.findBySessionId(sessionId);
    	if(profs.isEmpty()){
    		model.addAttribute("message", "User not authenticated");
    		return "error";
    	}
    	if(!(profs.get(0) instanceof Professor)){
    		model.addAttribute("message", "Only professors can create projects");
    		return "error";
    	}
    	Professor prof = (Professor) profs.get(0);
    	Project p = new Project();
    	p.setName(name);
    	p.setProf(prof);
    	p.setMaxStudents(maxStudents);
    	model.addAttribute("project",p);
    	repo.save(p);
    	return "project";
    }

    
    @RequestMapping(value="/applyForProject",method=RequestMethod.GET)
    public String applyForProject(@RequestParam(value="projectId") int projectId,
				@CookieValue(value="sessionId",defaultValue="") String sessionId,
				Model model){
    	if(!auth.isAuthenticated(sessionId)){
    		model.addAttribute("message", "User not authenticated");
    		return "error";
    	}
    	List<User> students = userRepo.findBySessionId(sessionId);
    	if(students.isEmpty()){
    		model.addAttribute("message", "User not authenticated");
    		return "error";
    	}
    	if(!(students.get(0) instanceof Student)){
    		model.addAttribute("message", "Only students can apply for projects");
    		return "error";
    	}
    	
    	Student st = (Student) students.get(0);
    	List<Project> projects = repo.findById(projectId);
    	if(projects.isEmpty()){
    		return "error";
    	}
    	Project p = projects.get(0);
    	if(p.addApplicant(st)){
    		// Return success message
    		repo.save(p);
    		model.addAttribute("project",p);
    		return "project";
    	} else {
    		return "error";
    	}
    }
    
    @RequestMapping(value="/deleteProject",method=RequestMethod.GET)
    public String deleteProject(@RequestParam(value="projectId") int projectId,
    		@CookieValue(value="sessionId",defaultValue="") String sessionId,
			Model model){
    	if(!auth.isAuthenticated(sessionId)){
    		model.addAttribute("message", "User not authenticated");
    		return "error";
    	}
    	// Get the prof that is deleting the page
    	List<User> profs = userRepo.findBySessionId(sessionId);
    	if(profs.isEmpty() || !(profs.get(0) instanceof Professor)){
    		model.addAttribute("message", "User not authorized to delete the project");
    		return "error";
    	}
    	List<Project> projects = repo.findById(projectId);
    	if(projects.isEmpty()){
    		return "error";
    	}
    	Project p = projects.get(0);
    	repo.delete(p);
    	return "hello";
    }
    
    
}
