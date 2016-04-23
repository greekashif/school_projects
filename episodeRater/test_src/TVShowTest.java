package rater;

import org.junit.Test;
import junit.framework.TestCase;

/**
 * @author Kashif Ahmadi
 */

public class TVShowTest extends TestCase {

	@Test
	/**
	 * Tests the empty constructor for the correct values
	 */
	public void testConstructor() {
		Show instance1 = new Show(0, "Seinfeld", 6, 3, "comedy",
            "Show about nothing!");
		assertEquals("instance 1 id", 0, instance1.getId());
		assertEquals("instance 1 title", "Seinfeld", instance1.getTitle());
		assertEquals("instance 1 title", 6 , instance1.getSeason());
		assertEquals("instance 1 rating", 3, instance1.getRating());
		assertEquals("instance 1 rating", "comedy", instance1.getGenre());
		assertEquals("instance 1 rating", "Show about nothing!" ,
             instance1.getSynopsis());
	}
}
