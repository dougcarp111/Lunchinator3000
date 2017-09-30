package dccode.lunchinator3000.models;

public class Vote {

	private long id;
	private String ballotId;
	private String voterName;
	private String emalAddress;
	
	public Vote() {
		super();
	}
	
	public Vote(long id, String ballotId, String voterName, String emalAddress) {
		super();
		this.id = id;
		this.ballotId = ballotId;
		this.voterName = voterName;
		this.emalAddress = emalAddress;
	}
	
	// Getters and Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBallotId() {
		return ballotId;
	}
	public void setBallotId(String ballotId) {
		this.ballotId = ballotId;
	}
	public String getVoterName() {
		return voterName;
	}
	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}
	public String getEmalAddress() {
		return emalAddress;
	}
	public void setEmalAddress(String emalAddress) {
		this.emalAddress = emalAddress;
	}
	
}
