package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import suhdude.Professor;
import suhdude.Project;

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
}
