package dccode.lunchinator3000.models;

public class Review {

	private long Id;
	
	private String restaurant;
	private String reviewer;
	private String rating;
	private String review;
	private String reviewerImage;
	
	public Review() {
		super();
	}

	// Getters and Setters
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		this.Id = id;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getReviewerImage() {
		return reviewerImage;
	}

	public void setReviewerImage(String reviewerImage) {
		this.reviewerImage = reviewerImage;
	}
}
