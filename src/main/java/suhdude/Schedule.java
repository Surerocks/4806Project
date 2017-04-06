package suhdude;


import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Entity
public class Schedule {
	
	
	private int id;
	private String stringRep;

	private int[] monday;
	
	private int[] tuesday;
	private int[] wednesday;
	private int[] thursday;
	private int[] friday;
	
	
	public Schedule(){
		
		int[] l = new int[17];
		for (int i = 0; i < 17; i++) {
			l[i]=0;
		}
		
		monday = l;
		tuesday = l;
		wednesday = l;
		thursday = l;
		friday = l;
		
		stringRep = "Monday: \nTuesday: \nWednesday: \nThursday: \nFriday";
	
	}
	
	public Schedule(String monday, String tuesday, String wednesday,String thursday, String friday ){
		this.monday = setList(monday);
		this.tuesday = setList(tuesday);
		this.wednesday = setList(wednesday);
		this.thursday = setList(thursday);
		this.friday = setList(friday);
		stringRep = "Monday: "+ monday+"\nTuesday: "+ tuesday+ "\nWednesday: "+ wednesday +"\nThursday: " + thursday + "\nFriday " + friday;
		
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int[] getMonday() {
		return monday;
	}
	
	
	public int[] getTuesday() {
		return tuesday;
	}
	
	public void setMonday(int[] monday) {
		this.monday = monday;
	}
	
	
	public void setTuesday(int[] tuesday) {
		this.tuesday = tuesday;
	}

	
	
	public int[] getWednesday() {
		return wednesday;
	}
	
	public void setWednesday(int[] wednesday) {
		this.wednesday = wednesday;
	}

	
	public int[] getThursday() {
		return thursday;
	}
	
	public void setThursday(int[] thursday) {
		this.thursday = thursday;
	}


	
	public int[] getFriday() {
		return friday;
	}
	
	public void setFriday(int[] friday) {
		this.friday = friday;
	}
	

	
	
	
	public int setPlacement(String time){
		String delims = "[:]+";
		String[] t = time.split(delims);
		
		int hour = Integer.parseInt(t[0]);
		int min = Integer.parseInt(t[1]);
		
		hour = 2*(hour - 8);
		if(min >= 30){
			hour++;
		}
		
		return hour;
	}
	
	public String findPresentationDay(List<Schedule> students){
		Boolean busy;
		String day="";
		int ts=-1;
		for(int i=0; i < 17; i++){
			busy = false;
			for(Schedule s:students){
				if(s.getMonday()[i]==1){
					busy = true;
				}
			}
			if(!busy){
				ts = i;
				day = "Monday";
				break;
			}
			
			busy = false;
			
			for(Schedule s:students){
				if(s.getTuesday()[i]==1){
					busy = true;
				}
			}
			
			if(!busy){
				ts = i;
				day = "Tuesday";
				break;
			}
			
			busy=false;
			
			for(Schedule s:students){
				if(s.getWednesday()[i]==1){
					busy = true;
				}
			}
			
			if(!busy){
				ts = i;
				day="Wednesday";
				break;
			}
			busy = false;
			for(Schedule s:students){
				if(s.getThursday()[i]==1){
					busy = true;
				}
			}
			
			if(!busy){
				ts = i;
				day= "Thursday";
				break;
			}
			busy = false;
			for(Schedule s:students){
				if(s.getFriday()[i]==1){
					busy = true;
				}
			}
			
			if(!busy){
				ts = i;
				day = "Friday";
				break;
			}
		}
		
		if(ts==-1){
			return "no time that works for everyone";
		}

		if((ts & 1) == 0){//even
			ts = (ts)/2 + 8;
			return day +" " +  ts + ":" + "00";
		} else{
			ts = (ts-1)/2 + 8;
			return day + " " + ts + ":" + "30";
			
		}
	}
	
	public String toString(){
		return stringRep;
	}
	
	public int[] setList(String times){
		
		
		int[] list = new int[17];
		String delims = "[ ,]+";
		String[] tokens = times.split(delims);
		String[] range;
		int start=0; 
		int end=0;
		delims = "[-]+";
		
		for(int i=0; i<17; i++){
			list[i]=0; 
		}
		
		if(times == ""){
			return list;
		}
		
		
		for(String s: tokens){
			range = s.split(delims);
			start = setPlacement(range[0]);
			end = setPlacement(range[1]);
			end = end -1;
			for(int i=0; i<17; i++){
				if(i<=end && i>=start){
					list[i]=1;	
				} 
			}
		}
		
		
		
		return list;
		
	}

}
