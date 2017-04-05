package suhdude;

import javax.persistence.Entity;

@Entity
public class ProjectCoordinator extends User {
	
	public String name;
	
	public ProjectCoordinator(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
