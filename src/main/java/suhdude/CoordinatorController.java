package suhdude;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CoordinatorController {
	
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
    
    @RequestMapping(value="/studentsWithoutProjects",method=RequestMethod.GET)
    public String getStudentsWithoutProject(Model model,
    		@CookieValue(value="sessionId",defaultValue="") String sessionId){
    	// Verify that the coordinator is logged in
    	if(!auth.isAuthenticated(sessionId)){
    		model.addAttribute("message", "User not authenticated");
    		return "error";
    	}
    	// Get the coordinator that is referenced
    	List<User> coords = userRepo.findBySessionId(sessionId);
    	if(coords.isEmpty()){
    		model.addAttribute("message", "User not authenticated");
    		return "error";
    	}
    	if(!(coords.get(0) instanceof ProjectCoordinator)){
    		model.addAttribute("message", "Only project coordinators can view students without projects");
    		return "error";
    	}
    	
    	// Get a set of all students that have applied or are approved for projects
    	Set<Student> studentsInProjects = new HashSet<Student>();
    	Iterator<Project> projects = repo.findAll().iterator();
    	while(projects.hasNext()){
    		Project p = projects.next();
    		for(Student s : p.getApplicants()){
    			studentsInProjects.add(s);
    		}
    		for(Student s : p.getStudents()){
    			studentsInProjects.add(s);
    		}
    	}
    	
    	// Find all students that exist
    	Set<Student> exists = new HashSet<Student>();
    	Iterator<User> users = userRepo.findAll().iterator();
    	while(users.hasNext()){
    		User u = users.next();
    		if(u instanceof Student){
    			Student s = (Student) u;
    			exists.add(s);
    		}
    	}
    	
    	// Set difference of exist that are not in the previously found set
    	exists.removeAll(studentsInProjects);
    	
    	// Return the list to be displayed
    	model.addAttribute("students", exists);
    	
    	// TODO: Make a studentsWithoutProject.html file to display list
    	return "studentsWithoutProject";
    }
    
    
    @RequestMapping(value="/createCoordinator",method=RequestMethod.GET)
	public String createCoordinator(@RequestParam("name") String name,
				@RequestParam("password") String password,
				@RequestParam("username") String username){
		ProjectCoordinator p = new ProjectCoordinator();
		p.setName(name);
		p.setPassword(password);
		p.setUsername(username);
		userRepo.save(p);
		return "login";
	}
	
	@RequestMapping(value="/createCoordinators",method=RequestMethod.GET)
	public String createStudents(){
		// TODO: Make a createCoordinator.html page
		return "createCoordinator";
	}
	
	
}	
