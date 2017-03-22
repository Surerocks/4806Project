package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import suhdude.Professor;

/** 
 * @author marcteboekhorst
 * Test Cases for the Professor Class
 */
public class ProfessorTest {
	
	Professor p;
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testProfessor() {
		String name = "prof";
		String email = "prof@mail.ca";
		p = new Professor(name,email);
		assertTrue("Professor's Name or Email not correct",
				p.getName().equals("prof")&&p.getEmail().equals("prof@mail.ca"));
	}
	
	@Test
	public void testGetName(){
		String name = "prof";
		p = new Professor(name,"email");
		assertTrue("Professor's Name not correct", p.getName().equals("prof"));
	}
	
	@Test
	public void testSetName(){
		String name = "prof";
		p = new Professor(name,"email");
		assertTrue("Professor's Name not correct", p.getName().equals("prof"));
		name = "prof2";
		p.setName(name);
		assertTrue("Professor's Name not correct", p.getName().equals("prof2"));
	}
	
	@Test
	public void testGetEmail(){
		String email = "email";
		p = new Professor("name",email);
		assertTrue("Professor's Email not correct", p.getEmail().equals("email"));
	}
	
	@Test
	public void testSetEmail(){
		String email = "email";
		p = new Professor("name",email);
		assertTrue("Professor's Email not correct", p.getEmail().equals("email"));
		email = "email2";
		p.setEmail(email);
		assertTrue("Professor's Email not correct", p.getEmail().equals("email2"));
	}

}
