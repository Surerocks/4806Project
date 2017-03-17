package suhdude;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScheduleController {
	
	private ScheduleRepository repo;
	
	
	@Autowired
    public void setRepo(ScheduleRepository repo){
        this.repo = repo;
    }
	
	
	@RequestMapping(value="/schedule", method= RequestMethod.GET)
    public String schduleHome(Model model){
        return "schedule";
    }
	
	@RequestMapping(value="/addSchedule",method=RequestMethod.GET)
    public String createProject(@RequestParam(value="monday") String monday,
    				@RequestParam(value="tuesday") String tuesday,
    				@RequestParam(value="wednesday") String wednesday,
    				@RequestParam(value="thursday") String thursday,
    				@RequestParam(value="friday") String friday,
    				Model model){
    	
    	Schedule schedule = new Schedule(monday, tuesday, wednesday, thursday, friday);
    	model.addAttribute("Schdule",schedule);
    	repo.save(schedule);
    	return "schedule";
    }

}
