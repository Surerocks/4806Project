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
    public String getCreatePage(){
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
    
    @RequestMapping(value="/deleteProject",method=RequestMethod.GET)
    public String deleteProject(@RequestParam(value="projectId") int projectId,
			Model model){
    	List<Project> projects = repo.findById(projectId);
    	if(projects.isEmpty()){
    		return "error";
    	}
    	Project p = projects.get(0);
    	repo.delete(p);
    	return "hello";
    }
    
    
}
