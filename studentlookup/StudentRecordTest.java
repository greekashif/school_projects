package assn1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;
/**
 * Tests a student record to verify that it holds the supplied data.
 * @author drdan
 *
 */
public class StudentRecordTest {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	/**
	 * Verify that a StudentRecord object contains the data supplied
	 * to the constructor
	 */
	public void testConstructor() {
		StudentRecord instance = new StudentRecord("Fred","Flintstone","fred@uga.edu",
				"fred.jpg");
		assertEquals("First name","Fred",instance.getFname());
		assertEquals("Last name","Flintstone",instance.getLname());
		assertEquals("Email addr","fred@uga.edu",instance.getEmailAddr());
		assertEquals("Image file path","images/fred.jpg",instance.getImageLocation());
	}

}
