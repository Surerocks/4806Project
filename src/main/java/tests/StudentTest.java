package tests;

import static org.junit.Assert.*;

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

}
