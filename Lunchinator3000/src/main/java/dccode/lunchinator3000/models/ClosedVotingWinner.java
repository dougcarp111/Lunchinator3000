package dccode.lunchinator3000.models;

import java.util.Date;

/**
 * 
 * @author Doug
 *
 */
public class ClosedVotingWinner {

	private long id;
	private Date datetime;
	private String name;
	private int votes;
	
	public ClosedVotingWinner() {
		super();
	}
	
	public ClosedVotingWinner(Restaurant restaurant, Date datetime) {
		super();
		this.id = restaurant.getId();
		this.name = restaurant.getName();
		this.votes = restaurant.getVotes();
	}
	
	// Getters and Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
}
