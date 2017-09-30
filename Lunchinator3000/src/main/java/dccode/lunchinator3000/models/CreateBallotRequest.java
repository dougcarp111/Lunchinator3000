package dccode.lunchinator3000.models;

import java.util.List;

public class CreateBallotRequest {

	private String endTime;
	private List<Voter> voters;
	
	public CreateBallotRequest() {
		super();
	}
	
	// Getters and Setters
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endtime) {
		this.endTime = endtime;
	}
	public List<Voter> getVoters() {
		return voters;
	}
	public void setVoters(List<Voter> voters) {
		this.voters = voters;
	}
	
}
