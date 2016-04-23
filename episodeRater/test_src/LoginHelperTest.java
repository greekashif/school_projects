package rater;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author Clint
 * Test class for the LoginHelper.
 */
public class LoginHelperTest extends TestCase {

	UserHelper uh = new UserHelper();
	LoginHelper lh = new LoginHelper();

	/**
	 * Setup method for the tests.
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		String JDBC_URL = "jdbc:mysql://localhost:3306/episodeRaterDB";
		String DB_USER = "rateradmin";
		String DB_PASS = "rater";
		Connection conn = null;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			System.out.println("Connected to rater DB");
			PreparedStatement clearUser = conn.prepareStatement("delete from User");
			clearUser.execute();
			PreparedStatement truncateUser = conn.prepareStatement("TRUNCATE TABLE User");
			truncateUser.execute();

		} catch(ClassNotFoundException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch(SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	/**
	 * Tests the login process.
	 */
	@Test
	public void testLogin() {
		boolean authorized;

		uh.addUser("user1", "pass1", "pass1");
		authorized = lh.isAuthorizedUser("user1", "pass1");
		assertEquals (true, authorized);

		uh.addUser("user2", "pass1", "pass1");
		authorized = lh.isAuthorizedUser("user2", "pass1");
		assertEquals (true, authorized);

		uh.addUser("user3", "pass1", "pass1");
		authorized = lh.isAuthorizedUser("", "pass1");
		assertEquals (false, authorized);
		assertEquals ("Login Error: No username was entered.  Please try again.", lh.isAuthorizedUserError("", "pass1"));

		uh.addUser("user4", "pass1", "pass1");
		authorized = lh.isAuthorizedUser("user4", "");
		assertEquals (false, authorized);
		assertEquals ("Login Error: Password was not entered.  Please try again.", lh.isAuthorizedUserError("user4", ""));

		uh.addUser("user5", "pass2", "pass2");
		authorized = lh.isAuthorizedUser("user5", "pass1");
		assertEquals (false, authorized);
		assertEquals ("Login Error: The username or password entered was incorrect.  Please try to login again.", lh.isAuthorizedUserError("user5", "pass1"));

		authorized = lh.isAuthorizedUser("user2", "pass2");
		assertEquals (false, authorized);
		assertEquals ("Login Error: The username or password entered was incorrect.  Please try to login again.", lh.isAuthorizedUserError("user2", "pass2"));
	}

	/**
	 * Test getting the role.
	 */
	@Test
	public void testGetRole() {
		String role;

		uh.addUser("user1", "pass1", "pass1");
		role = lh.getRole("user1");
		assertEquals ("Reg", role);

		uh.addUser("user2", "pass1", "pass1");
		System.out.println(lh.getRole("user2"));
		uh.changeRole("user2");
		System.out.println(lh.getRole("user2"));
		role = lh.getRole("user2");
		assertEquals ("Mod", role);

		role = lh.getRole("");
		assertEquals ("Guest", role);
	}
	
	/**
	 * Teardown method for after the tests.
	 */
	@After
	protected void tearDown() throws Exception {
		String JDBC_URL = "jdbc:mysql://localhost:3306/episodeRaterDB";
		String DB_USER = "rateradmin";
		String DB_PASS = "rater";
		Connection conn = null;
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			System.out.println("Connected to rater DB");
			PreparedStatement clearUser = conn.prepareStatement("delete from User");
			clearUser.execute();
			PreparedStatement truncateUser = conn.prepareStatement("TRUNCATE TABLE User");
			truncateUser.execute();
			PreparedStatement addUser = conn.prepareStatement("INSERT INTO User (`username`, `password`, `role`) Values ('mod', '83 87 8C 91 17 13 38 90 2E 0F E0 FB 97 A8 C4 7A', 'Mod'");
			addUser.executeUpdate();
			System.out.println("Initial user added to the database.");
		} catch(ClassNotFoundException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch(SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}
}