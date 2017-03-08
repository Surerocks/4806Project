package suhdude;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Project {
	
	private int id;
	private String name;
	private List<Student> students;
	private List<Student> applicants;
	private Professor prof;
	private int maxStudents;
	
	public Project(){
		
	}
	
	public Project(String name){
		this.name = name;
	}
	
	@Id
	@GeneratedValue
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
	public int getMaxStudents() {
		return maxStudents;
	}
	@OneToOne
	public Professor getProf(){
		return prof;
	}
	public void setProf(Professor prof){
		this.prof = prof;
	}
	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public List<Student> getStudents() {
		return students;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	public List<Student> getApplicants() {
		return applicants;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void setApplicants(List<Student> applicants) {
		this.applicants = applicants;
	}

	public boolean addApplicant(Student s){
		if(students.size() < maxStudents){
			applicants.add(s);
			return true;
		}
		return false;
	}
	
	public boolean approveApplicant(Student s){
		if(applicants.contains(s)){
			applicants.remove(s);
			students.add(s);
			return true;
		}
		return false;
	}	

}
