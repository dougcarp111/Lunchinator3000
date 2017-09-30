package dccode.lunchinator3000.models;

public class CreateBallotResponse {
	
	private String ballotId;
	
	public CreateBallotResponse() {
		super();
	}

	public CreateBallotResponse(String ballotId) {
		this.ballotId = ballotId;
	}
	
	// Getters and Setters
	public String getBallotId() {
		return ballotId;
	}

	public void setBallotId(String ballotId) {
		this.ballotId = ballotId;
	}
}
