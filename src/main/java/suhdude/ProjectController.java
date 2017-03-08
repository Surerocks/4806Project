package suhdude;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProjectController {
	private ProjectRepository repo;
	private StudentRepository studentRepo;
	private ProfessorRepository profRepo;

    @Autowired
    public void setRepo(ProjectRepository repo){
        this.repo = repo;
    }
    
    @Autowired
    public void setStudentRepo(StudentRepository studentRepo){
        this.studentRepo = studentRepo;
    }
    
    @Autowired
    public void setProfRepo(ProfessorRepository profRepo){
        this.profRepo = profRepo;
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getHome(Model model){
    	// TODO: Just return the home page (which is static)
        return "hello";
    }
    
 
    @RequestMapping(value="/createProject",method=RequestMethod.GET)
    public String createProject(@RequestParam(value="name") String name,
    				@RequestParam(value="max") int maxStudents,
    				@RequestParam(value="profName") String profName,
    				Model model){
    	// Get the prof that is referenced or create a new one
    	List<Professor> profs = profRepo.findByName(profName);
    	if(profs.isEmpty()){
    		return "error";
    	}
    	Professor prof = profs.get(0);
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
				@RequestParam(value="studentNo") int studentNo,
				Model model){
    	List<Student> students = studentRepo.findByStudentNo(studentNo);
    	if(students.isEmpty()){
    		// TODO: return Error message
    		return "error";
    	}
    	Student st = students.get(0);
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
    
    
}
