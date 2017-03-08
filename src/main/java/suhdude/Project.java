package suhdude;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project {
	
	private int id;
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;
	
	public Project(){
		
	}
	
	public Project(String name){
		this.name = name;
	}
	
	

}
