package suhdude;


import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Schedule {
	private int id;
	//private Student student;
	private Map<String, String> timeTable;
	
	public Schedule(){
		timeTable = new HashMap<String, String>();
		timeTable.put("monday", "");
		timeTable.put("tuesday", "");
		timeTable.put("wednesday", "");
		timeTable.put("thursday", "");
		timeTable.put("friday", "");
		
	}
	
	public Schedule(String monday, String tuesday, String wednesday,String thursday, String friday ){
		timeTable = new HashMap<String, String>();
		timeTable.put("monday", monday);
		timeTable.put("tuesday", tuesday);
		timeTable.put("wednesday", wednesday);
		timeTable.put("thursday", thursday);
		timeTable.put("friday", friday);
		//List<Integer> availible;
		//availible = setList(monday);
	
		
	}
	
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	@ElementCollection
	public Map<String,String> getStudents() {
		return timeTable;
	}
	
	public void setStudents(Map<String, String> timeTable) {
		this.timeTable = timeTable;
	}
	
	public ArrayList<Integer> setList(String times){
		ArrayList<Integer> list = new ArrayList();
		String delims = "[ ,]+";
		String[] tokens = times.split(delims);
		int range;
		for(String s: tokens){
			//TODO add functionality for converting a string key ("8:30-9:00" into a arraylist int repsentation  [0,1,1,0,0,0......]
		}
		
		return list;
		
	}
	

}
