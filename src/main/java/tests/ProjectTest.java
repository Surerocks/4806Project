package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import suhdude.Professor;
import suhdude.Project;
import suhdude.Student;

/** 
 * @author marcteboekhorst
 * Test Cases for the Project Class
 */
public class ProjectTest {
	
	Project pro;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testProject() {
		String name = "project";
		pro = new Project(name);
		pro.setMaxStudents(4);
		pro.setProf(new Professor("prof","profemail"));
		
		assertTrue("Project information incorrect",
				pro.getName().equals("project") 
				&& pro.getMaxStudents()==4
				&& pro.getProf().getName().equals("prof")
				&& pro.getProf().getEmail().equals("profemail"));
	}

	@Test
	public void testGetId(){
		int id = 1;
		pro = new Project("project");
		pro.setId(id);
		assertTrue("Project id not correct", pro.getId()==1);
	}
	
	@Test
	public void testSetId(){
		int id = 1;
		pro = new Project("project");
		assertTrue("Project id is not correct", pro.getId()==0);
		pro.setId(id);
		assertTrue("Project id is not correct", pro.getId()==1);
	}
	
	@Test
	public void testGetName(){
		String name = "project";
		pro = new Project(name);
		assertTrue("Project name is not correct", pro.getName().equals("project"));
	}
	
	@Test
	public void testSetName(){
		String name = "project";
		pro = new Project(name);
		assertTrue("Project name is not correct", pro.getName().equals("project"));
		name = "project2";
		pro.setName(name);
		assertTrue("Project name is not correct", pro.getName().equals("project2"));
		
	}
	
	@Test
	public void testGetMaxStudents(){
		pro = new Project("project");
		assertTrue("Get max students is not correct",pro.getMaxStudents()==0);
	}
	
	@Test
	public void testSetMaxStudents(){
		pro = new Project("project");
		pro.setMaxStudents(2);
		assertTrue("Get max students is not correct",pro.getMaxStudents()==2);
	}
	
	@Test
	public void testGetProf(){
		pro = new Project("project");
		Professor prof = new Professor("prof","profemail");
		pro.setProf(prof);
		assertTrue("Get prof method is not correct",pro.getProf().getName().equals("prof")
				&& pro.getProf().getEmail().equals("profemail"));
	}
	
	@Test
	public void testSetProf(){
		pro = new Project("project");
		assertTrue("Set prof method is not correct", pro.getProf()==null);
		Professor prof = new Professor("prof","profemail");
		pro.setProf(prof);
		assertTrue("Get prof method is not correct",pro.getProf().getName().equals("prof")
				&& pro.getProf().getEmail().equals("profemail"));
	}
	
	@Test
	public void testSetStudents(){
		pro = new Project("project");
		assertTrue("Student list should be null",pro.getStudents()==null);
		Student s = new Student(1,"name","email");
		Student s2 = new Student(2,"name2","email2");
		List<Student> students = new ArrayList<Student>();
		students.add(s);
		students.add(s2);
		pro.setStudents(students);
		assertTrue("Set Students method is not correct",
				pro.getStudents().contains(s)&& pro.getStudents().contains(s2));
		
	}
	
	@Test
	public void testGetStudents(){
		pro = new Project("project");
		Student s = new Student(1,"name","email");
		List<Student> students = new ArrayList<Student>();
		students.add(s);
		pro.setStudents(students);
		assertTrue("Get students method incorrect",pro.getStudents().contains(s));
	}
	
	@Test
	public void testSetApplicants(){
		pro = new Project("project");
		assertTrue("Applicant list should be null",pro.getApplicants()==null);
		Student s = new Student(1,"name","email");
		Student s2 = new Student(2,"name2","email2");
		List<Student> applicants = new ArrayList<Student>();
		applicants.add(s);
		applicants.add(s2);
		pro.setApplicants(applicants);
		assertTrue("Set Applicants method is not correct",
				pro.getApplicants().contains(s)&& pro.getApplicants().contains(s2));
		
	}
	
	@Test
	public void testGetApplicants(){
		pro = new Project("project");
		Student s = new Student(1,"name","email");
		List<Student> applicants = new ArrayList<Student>();
		applicants.add(s);
		pro.setApplicants(applicants);
		assertTrue("Get applicants method incorrect",pro.getApplicants().contains(s));
	}
	
	@Test
	public void testAddApplicant(){
		pro = new Project("project");
		pro.setStudents(new ArrayList<Student>());
		pro.setApplicants(new ArrayList<Student>());
		pro.setMaxStudents(1);
		Student s = new Student(1,"name","email");
		pro.addApplicant(s);
		assertTrue("Add applicant method incorrect",pro.getApplicants().contains(s));
	}
	
	@Test
	public void testApproveApplicant(){
		pro = new Project("project");
		pro.setStudents(new ArrayList<Student>());
		pro.setApplicants(new ArrayList<Student>());
		pro.setMaxStudents(1);
		Student s = new Student(1,"name","email");
		pro.addApplicant(s);
		pro.approveApplicant(s);
		assertTrue("Approve applicant method incorrect",pro.getStudents().contains(s));
	}
	
	@Test
	public void testWithdrawApplicant(){
		pro = new Project("project");
		pro.setStudents(new ArrayList<Student>());
		pro.setApplicants(new ArrayList<Student>());
		pro.setMaxStudents(1);
		Student s = new Student(1,"name","email");
		pro.addApplicant(s);
		assertTrue("Add applicant method incorrect (withdraw)",
				pro.getApplicants().contains(s));
		pro.withdrawApplicant(s);
		assertTrue("Withdraw applicant method incorrect",pro.getApplicants().isEmpty());
		
	}
}
