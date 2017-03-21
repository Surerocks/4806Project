package suhdude;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student extends User{

	
	private int studentNo;
	private String name;
	private String email;
	
	public Student(){
		
	}
	
	public Student(int studentNo, String name, String email){
		this.studentNo = studentNo;
		this.name = name;
		this.email = email;
	}

	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
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
