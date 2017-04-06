package suhdude;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
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
	private File report;
	private Calendar reportDeadline;

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

	public File getReport() {
		return report;
	}

	public void setReport(File report) {
		this.report = report;
	}

	public Calendar getReportDeadline() {
		return reportDeadline;
	}

	public void setReportDeadline(Calendar reportDeadline) {
		this.reportDeadline = reportDeadline;
	}
	public boolean addApplicant(Student s){
		if(s.getGroup() != null) {
		    if (students.size() + s.getGroup().size() <= maxStudents) {
                for (Student member : s.getGroup()) {
                    applicants.add(member);
                }
                return true;
            }
        }
	    if(students.size() < maxStudents){
			applicants.add(s);
			return true;
		}
		return false;
	}
	
	public boolean approveApplicant(Student s){
		if(applicants.contains(s)){
		    if(s.getGroup() != null) {
                for (Student member : s.getGroup()) {
                	applicants.remove(member);
                    students.add(member);
                }
                return true;
            }
			applicants.remove(s);
			students.add(s);
			return true;
		}
		return false;
	}

	public boolean withdrawApplicant(Student s) {
		if(applicants.contains(s)) {
		    if(s.getGroup() != null) {
		        for (Student member : s.getGroup()) {
		            applicants.remove(member);
                }
                return true;
            }
			applicants.remove(s);
			return true;
		}
		return false;
	}
	
	public boolean submitReport(Student s, File file){
		if(students.contains(s)){
			Calendar now = Calendar.getInstance();
			if(now.compareTo(reportDeadline)<=0){
				setReport(file);
				return true;
			}
		}
		return false;
	}

}
