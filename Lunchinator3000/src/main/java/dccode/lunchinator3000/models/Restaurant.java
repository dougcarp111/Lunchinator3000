package dccode.lunchinator3000.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

	private long id;
	private String name;
	private String description;
	private String averageReview;
	private String topReviewer;
	private String review;
	private int votes;
	private int waitTimeMinutes;
	private String image;
	private List<String> types = new ArrayList<String>();
	
	/**
	 * 
	 */
	public Restaurant() {
		super();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}


	public int getWaitTimeMinutes() {
		return waitTimeMinutes;
	}


	public void setWaitTimeMinutes(int waitTimeMinutes) {
		this.waitTimeMinutes = waitTimeMinutes;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public List<String> getTypes() {
		return types;
	}


	public void setTypes(List<String> types) {
		this.types = types;
	}
	
}
