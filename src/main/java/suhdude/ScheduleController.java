package suhdude;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScheduleController {
	
	private ScheduleRepository repo;
	private UserRepository userRepo;
	private AuthenticationController auth;
	
	@Autowired
	public void setAuth(AuthenticationController auth){
		this.auth = auth;
	}
	
	@Autowired
    public void setRepo(ScheduleRepository repo){
        this.repo = repo;
    }
	
	@Autowired
    public void setUserRepo(UserRepository userRepo){
        this.userRepo = userRepo;
    }
	
	
	@RequestMapping(value="/schedule", method= RequestMethod.GET)
    public String schduleHome(@CookieValue(value="sessionId",defaultValue="") String sessionId, Model model){
		if(!auth.isAuthenticated(sessionId)){
    		model.addAttribute("message", "User not authenticated");
    		return "error";
    	}
		/*
		List<Schedule> s = repo.findById(0);
		Schedule schedule = new Schedule();
		if(!s.isEmpty()){
			schedule = s.get(0);
		}
		
		model.addAttribute("schedule", schedule);
		*/
		return "schedule";
    }
	
	@RequestMapping(value="/addSchedule",method=RequestMethod.GET)
    public String createSchedule(@RequestParam(value="monday") String monday,
    				@RequestParam(value="tuesday") String tuesday,
    				@RequestParam(value="wednesday") String wednesday,
    				@RequestParam(value="thursday") String thursday,
    				@RequestParam(value="friday") String friday, 
    				@CookieValue(value="sessionId",defaultValue="") String sessionId,
    				Model model){
		if(!auth.isAuthenticated(sessionId)){
    		model.addAttribute("message", "User not authenticated");
    		return "error";
    	}
		
		Schedule schedule = new Schedule(monday, tuesday, wednesday, thursday, friday);
    	model.addAttribute("schedule",schedule);
    	repo.save(schedule);
    	return "displayAvailibility";
    }
	
	@RequestMapping(value="/displayAvailibility", method= RequestMethod.GET)
    public String getSchedule(@CookieValue(value="sessionId",defaultValue="") String sessionId, Model model){
		if(!auth.isAuthenticated(sessionId)){
    		model.addAttribute("message", "User not authenticated");
    		return "error";
    	}
		Iterator<Schedule> is = repo.findAll().iterator();

		if(!is.hasNext()){
			return "schedule";
		}
		Schedule s = is.next();
		System.out.println(s.getStringRep());
    	model.addAttribute("schedule",s);
    	return "displayAvailibility";
    }

}
