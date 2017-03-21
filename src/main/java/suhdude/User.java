package suhdude;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * A User of the system. They have a username (for Identification) and a password
 *
 */
@Entity
public abstract class User {
	private String username;
	private String password;
	private String sessionId;
	
	@Id
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
	
}
