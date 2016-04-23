package rater;

/**
 * @author Clint
 * The review object.
 */
public class Review {

	private int reviewID;
	private String review;
	private int tvShowID;
	private int rating;
	private String time;
	private String username;
	
	public Review(int reviewID, String review, int tvShowID, int rating, String time, String username) {
		this.reviewID = reviewID;
		this.review = review;
		this.tvShowID = tvShowID;
		this.rating = rating;
		this.time = time;
		this.username = username;;
	}

	public int getReviewID() {
		return reviewID;
	}

	public String getReview() {
		return review;
	}

	public int getTvShowID() {
		return tvShowID;
	}

	public int getRating() {
		return rating;
	}

	public String getTime() {
		return time;
	}

	public String getUsername() {
		return username;
	}
	
	
}
