package suhdude;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

	
	private int studentNo;
	private String name;
	private String email;
	private List<Student> group;
	private boolean groupLeader = false;

	public Student(){
		
	}
	
	public Student(int studentNo, String name, String email){
		this.studentNo = studentNo;
		this.name = name;
		this.email = email;
	}

	@Id
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

	public boolean isGroupLeader() {
		return groupLeader;
	}

	public void createGroup(Student s) {
		group = new ArrayList<>();
		groupLeader = true;
		group.add(this);
		group.add(s);
	}

	public boolean addToGroup(Student s) {
		if(!group.contains(s)) {
			group.add(s);
			return true;
		}
		return false;
	}

	public void disbandGroup() {
		group.clear();
		groupLeader = false;
	}

	public List<Student> getGroup() {
		return group;
	}

	public void setGroup(List<Student> g) { group = g; }
}
