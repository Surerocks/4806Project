package suhdude;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Professor extends User{
	private String name;
	private String email;
	
	public Professor(){
		
	}
	
	public Professor(String name, String email){
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
