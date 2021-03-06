package suhdude;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student extends User{

	private int studentNo;
	private String name;
	private String email;
	private List<Student> group = new ArrayList<Student>();
	private boolean groupLeader = false;

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

	public boolean isGroupLeader() {
		return groupLeader;
	}

	public void createGroup(Student s) {
		group = new ArrayList<Student>();
		this.groupLeader = true;
		group.add(this);
		group.add(s);
		s.group = group;
	}

	public void createGroup() {
		group = new ArrayList<>();
		this.groupLeader = true;
		group.add(this);
	}

	public void setGroupLeader(boolean b) {
		this.groupLeader = b;
	}
	public boolean addToGroup(Student s) {
		if(!group.contains(s)) {
			group.add(s);
			s.group = group;
			return true;
		}
		return false;
	}

	public void disbandGroup() {
		for(Student s: group) {
			s.group = null;
		}
		group.clear();
		groupLeader = false;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public List<Student> getGroup() {
		return group;
	}

	public void setGroup(List<Student> g) { this.group = g; }
}
