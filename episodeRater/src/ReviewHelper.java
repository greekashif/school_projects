package rater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/** 
 * @author Clint
 * A helper class for the ReviewServlet.
 */
public class ReviewHelper {
	protected PreparedStatement addReview;
	protected PreparedStatement deleteReview;
	protected PreparedStatement getReviews;

	/**
	 *  The constructor for the ReviewHelper class.  It sets up the database connection.
	 */
	public ReviewHelper (){

		String JDBC_URL = "jdbc:mysql://localhost:3306/episodeRaterDB";
		String DB_USER = "rateradmin";
		String DB_PASS = "rater";
		Connection conn = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			System.out.println("Connected to rater DB");

			addReview = conn.prepareStatement("INSERT INTO Review (review, tvShowID, rating, time, username) values(?, ?, ?, NOW(), ?)");
			deleteReview = conn.prepareStatement("DELETE FROM Review WHERE `reviewID` = ?");
			getReviews = conn.prepareStatement("SELECT * FROM Review WHERE `tvShowID` = ?");
			System.out.println("created prepared statements");

		} catch(ClassNotFoundException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch(SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a review to the database.
	 * @param review The review being added.
	 * @param tvShowID The episode for which the review is being added.
	 * @param rating A rating between 1 and 5 to go along with the review. 
	 * @param username The username of the person adding the review.
	 * @return error A String of the error message, or a success message if successful.
	 */
	public String addReview(String review, int tvShowID, int rating, String username){
		String error;
		if (review.equals(""))
		{
			error = "Error Submitting Review: No review was entered.  Please try again.";
			return error;
		}

		if (rating!= 1 && rating!= 2 && rating!= 3 && rating!= 4 && rating!= 5)
		{
			error = "Error Submitting Review: No rating was entered.  Please try again.";
			return error;
		}

		try {
			addReview.setString(1, review);
			addReview.setInt(2, tvShowID);
			addReview.setInt(3, rating);
			addReview.setString(4, username);
			addReview.executeUpdate();
			error = "Review successfully added.";
			return error;
		} catch (SQLException e) {
            System.err.println("addReview threw " + e);
		}
		error = "Error Submitting Review: No idea what happened.  Please try again.  If that does not work please contact support at episoderatersupport@episoderater.com or 1-800-episoderatersupport.";
		return error;
	}
	
	/**
	 * Deletes a review.
	 * @param reviewID The review identified by an id number being deleted.
	 * @return error A String for an error message, or a success message if successful.
	 */
	public String deleteReview(int reviewID){
		String error;
		try {
			deleteReview.setInt(1, reviewID);
			deleteReview.executeUpdate();
			error = "Review successfully deleted.";
			return error;
		} catch (SQLException e) {
            System.err.println("deleteReview threw " + e);
		}
		error = "Error Deleting Review: No idea what happened.  Please try again.  If that does not work please contact support at episoderatersupport@episoderater.com or 1-800-episoderatersupport.";
		return error;
	}
	
	/**
	 * Gets an ArrayList of review objects.
	 * @param tvShowID The episode for which the review objects are retrieved.
	 * @return review A ArrayList of reviews for the specified episode. 
	 */
	public ArrayList<Review> getReviews(int tvShowID)
	{
		ResultSet results;
		try {
			getReviews.setInt(1, tvShowID);
			results = getReviews.executeQuery();
			ArrayList<Review> review = new ArrayList<Review>();
			while(results.next())
				{
					Review r = new Review(results.getInt("reviewID"), results.getString("review"), results.getInt("tvShowID"), results.getInt("rating"), results.getString("time"), results.getString("username"));
					review.add(r);
				}
				return review;
		} catch (SQLException e) {
            System.err.println("getReviews threw + " + e);
		}
		return null;
	}
}
