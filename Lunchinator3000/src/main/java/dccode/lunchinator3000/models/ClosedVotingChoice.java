package dccode.lunchinator3000.models;

/**
 * 
 * @author Doug
 *
 */
public class ClosedVotingChoice {

	private long id;
	private String name;
	private int votes;
	
	public ClosedVotingChoice() {
		super();
	}
	
	public ClosedVotingChoice(Restaurant restaurant) {
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
