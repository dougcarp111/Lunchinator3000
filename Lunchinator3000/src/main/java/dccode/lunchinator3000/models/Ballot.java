package dccode.lunchinator3000.models;

import java.util.Date;
import java.util.List;

/**
 * This class represents a ballot.
 * @author Doug
 *
 */
public class Ballot {
	
	private String ballotId;
	private Date endTime;
	
	private List<Restaurant> restaurants;
	private List<Voter> voters;
	

	
	// Getters and Setters
	public String getBallotId() {
		return ballotId;
	}

	public void setBallotId(String ballotId) {
		this.ballotId = ballotId;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public List<Voter> getVoters() {
		return voters;
	}

	public void setVoters(List<Voter> voters) {
		this.voters = voters;
	}

}
