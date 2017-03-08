package suhdude;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProjectController {
	private ProjectRepository repo;

    @Autowired
    public void setRepo(ProjectRepository repo){
        this.repo = repo;
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getMessages(Model model){
        model.addAttribute("hello","Hello World!1!!");
        return "hello";
    }
    
    @RequestMapping(value="/project", method= RequestMethod.GET)
    public String setProject(Model model){
    	Project p = new Project("A project");
        model.addAttribute("project",p);
        repo.save(p);
        return "project";
    }
    
    @RequestMapping(value="/project/view", method= RequestMethod.GET)
    public String getProject(Model model){
    	Iterator<Project> list = repo.findAll().iterator();
    	if(list.hasNext()){
    		model.addAttribute("project",list.next());
    	}
        return "project";
    }
}
