package dccode.lunchinator3000.models;

/**
 * Class to store voter information.
 * @author Doug
 *
 */
public class Voter {
	
	private String name;
	private String emailAddress;
	
	public Voter() {
		super();
	}

	// Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
