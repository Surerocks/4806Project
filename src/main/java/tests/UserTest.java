package tests;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import suhdude.Professor;
import suhdude.User;
/** 
 * @author marcteboekhorst
 * Test Cases for the User Class
 */

public class UserTest {

	User u;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUserName() {
		String username = "username";
		u = new Professor("name","email");
		u.setUsername(username);
		assertTrue("Get Username method incorrect", u.getUsername().equals("username"));
	}
	
	@Test
	public void testSetUserName(){
		String username = "username";
		u = new Professor("name","email");
		assertTrue("Get Username method incorrect (Set)", u.getUsername()==null);
		u.setUsername(username);
		assertTrue("Set Username method incorrect", u.getUsername().equals("username"));
	}
	
	@Test
	public void testGetPassword() {
		String password = "password";
		u = new Professor("name","email");
		u.setPassword(password);
		assertTrue("Get Password method incorrect", u.getPassword().equals("password"));
	}
	
	@Test
	public void testSetPassword(){
		String password = "password";
		u = new Professor("name","email");
		assertTrue("Get Password method incorrect (Set)", u.getPassword()==null);
		u.setPassword(password);
		assertTrue("Get Password method incorrect", u.getPassword().equals("password"));
	}
	
	@Test
	public void testGetSessionId() {
		String session = "session";
		u = new Professor("name","email");
		u.setSessionId(session);
		assertTrue("Get Session Id method incorrect", u.getSessionId().equals("session"));
	}
	
	@Test
	public void testSetSessionId(){
		String session = "session";
		u = new Professor("name","email");
		assertTrue("Get Session Id method incorrect (Set)", u.getSessionId()==null);
		u.setSessionId(session);
		assertTrue("Get Session Id method incorrect", u.getSessionId().equals("session"));

	}

}
