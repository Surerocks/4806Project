package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import suhdude.Student;

/** 
 * @author marcteboekhorst
 * Test Cases for the Student Class
 */
public class StudentTest {
	
	Student s;
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testStudent() {
		int num = 1;
		String name = "student";
		String email = "student@mail.ca";
		s = new Student(num,name,email);
		assertTrue("Student's Name, Email, or Student Number not correct",
				s.getName().equals("student")&&s.getEmail().equals("student@mail.ca")
				&& s.getStudentNo()==1);
	}
	
	@Test
	public void testGetStudentNo(){
		int num = 1;
		s = new Student(num,"name","email");
		assertTrue("Student's Student Number is not correct", s.getStudentNo()==1);
	}
	
	@Test
	public void testSetStudentNo(){
		int num = 1;
		s = new Student(num,"name","email");
		assertTrue("Student's Student Number is not correct", s.getStudentNo()==1);
		num = 2;
		s.setStudentNo(2);
		assertTrue("Student's Student Number is not correct", s.getStudentNo()==2);
	}
	@Test
	public void testGetName(){
		String name = "stu";
		s = new Student(1,name,"email");
		assertTrue("Student's Name not correct", s.getName().equals("stu"));
	}
	
	@Test
	public void testSetName(){
		String name = "stu";
		s = new Student(1,name,"email");
		assertTrue("Student's Name not correct", s.getName().equals("stu"));
		name = "stu2";
		s.setName(name);
		assertTrue("Student's Name not correct", s.getName().equals("stu2"));
	}
	
	@Test
	public void testGetEmail(){
		String email = "email";
		s = new Student(1,"name",email);
		assertTrue("Student's Email not correct", s.getEmail().equals("email"));
	}
	
	@Test
	public void testSetEmail(){
		String email = "email";
		s = new Student(1,"name",email);
		assertTrue("Student's Email not correct", s.getEmail().equals("email"));
		email = "email2";
		s.setEmail(email);
		assertTrue("Student's Email not correct", s.getEmail().equals("email2"));
	}
	
	@Test
	public void testIsGroupLeader(){
		s = new Student(1,"name","email");
		s.setGroupLeader(true);
		assertTrue("Group Leader Method not correct", s.isGroupLeader());
	}
	
	@Test
	public void testSetGroupLeader(){
		s = new Student(1,"name","email");
		assertFalse("Group Leader Method not correct", s.isGroupLeader());
		s.setGroupLeader(true);
		assertTrue("Group Leader Method not correct", s.isGroupLeader());
	}
	
	@Test
	public void testCreateGroup(){
		s = new Student(1,"name","email");
		Student s2 = new Student(2,"name2","email2");
		s.createGroup(s2);
		assertTrue("Group not created properly", s.getGroup().contains(s2));
	}
	
	@Test
	public void testAddtoGroup(){
		s = new Student(1,"name","email");
		Student s2 = new Student(2,"name2","email2");
		s.createGroup(s2);
		Student s3 = new Student(3,"name3","email3");
		s.addToGroup(s3);
		assertTrue("Student not added properly", s.getGroup().contains(s3));
	}
	
	@Test
	public void testDisbandGroup(){
		s = new Student(1,"name","email");
		Student s2 = new Student(2,"name2","email2");
		s.createGroup(s2);
		assertTrue("Group not created properly", s.getGroup().contains(s2));
		s.disbandGroup();
		assertTrue("Group not disbanded properly", s.getGroup().isEmpty());
	}
	
	@Test
	public void testGetGroup(){
		s = new Student(1,"name","email");
		Student s2 = new Student(2,"name2","email2");
		s.createGroup(s2);
		assertTrue("Get Group method not correct",
				s.getGroup().contains(s)&&s.getGroup().contains(s2));
	}
	
	@Test
	public void testSetGroup(){
		s = new Student(1,"name","email");
		Student s2 = new Student(2,"name2","email2");
		List<Student> students = new ArrayList<Student>();
		students.add(s);
		students.add(s2);
		s.setGroup(students);
		assertTrue("Set Group method not correct",
				s.getGroup().contains(s)&&s.getGroup().contains(s2));
	}
}
