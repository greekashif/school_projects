package rater;


import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author Clint
 * Test class for the Season object.
 */
public class SeasonTest extends TestCase {

	/**
	 * Setup method for the test.
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Tests the constructor for the SeasonTest object.
	 */
	@Test
	public void testConstructor() {
		Season season = new Season(1, "title", 2, 0, null, 3, "synopsis");
		assertEquals (1, season.getId());
		assertEquals ("title", season.getTitle());
		assertEquals (2, season.getSeason());
		assertEquals (0, season.getEpisodeNumber());
		assertEquals (null, season.getEpisodeName());
		assertEquals (3, season.getRating());
		assertEquals ("synopsis", season.getSynopsis());
	}

}
