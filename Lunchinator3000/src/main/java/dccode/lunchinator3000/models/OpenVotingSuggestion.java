package dccode.lunchinator3000.models;

/**
 * 
 * @author Doug
 *
 */
public class OpenVotingSuggestion {

	private long id;
	private String name;
	private String averageReview;
	private String topReviewer;
	private String review;
	
	public OpenVotingSuggestion() {
		super();
	}
	
	public OpenVotingSuggestion(Restaurant restaurant) {
		super();
		this.id = restaurant.getId();
		this.name = restaurant.getName();
		this.averageReview = restaurant.getAverageReview();
		this.topReviewer = restaurant.getTopReviewer();
		this.review = restaurant.getReview();
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
	public String getTopReviewer() {
		return topReviewer;
	}
	public void setTopReviewer(String topReviewer) {
		this.topReviewer = topReviewer;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
}
