package rater;

/**
 * @author Kashif Ahmadi
 */

import org.junit.Test;
import junit.framework.TestCase;

public class ReviewTest extends TestCase {

	@Test
	/**
	 * Tests the empty constructor for the correct values
	 */
	public void testConstructor() {
		Review instance1 = new Review(1,"review",2,4,"now","user");
		assertEquals("instance 1 userID", "user", instance1.getUsername());
		assertEquals("instance 1 tvshowID", 2, instance1.getTvShowID());
		assertEquals("instance 1 rating", 4, instance1.getRating());
		assertEquals("instance 1 review", "review", instance1.getReview());
		assertEquals("instance 1 timeCreated", "now", instance1.getTime());
        assertEquals("instance 1 reviewID", 1, instance1.getReviewID());
	}
}
