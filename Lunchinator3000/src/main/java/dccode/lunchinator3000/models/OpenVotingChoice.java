package dccode.lunchinator3000.models;


/**
 * This class is used for JSON binding.
 * @author Doug
 *
 */
public class OpenVotingChoice {

	private long id;
	private String name;
	private String averageReview;
	private String description;
	
	
	public OpenVotingChoice() {
		super();
	}
	
	
	public OpenVotingChoice(Restaurant restaurant) {
		super();
		this.id = restaurant.getId();
		this.name = restaurant.getName();
		this.averageReview = restaurant.getAverageReview();
		this.description = restaurant.getDescription();
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
	public String getAverageReview() {
		return averageReview;
	}
	public void setAverageReview(String averageReview) {
		this.averageReview = averageReview;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
