package rater;

import java.sql.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * This class will test the integrity of the
 * User record in the database.
 * @author Kashif Ahmadi
 */

public class UserHelperTest extends TestCase {

	private static String JDBC_URL = 
        "jdbc:mysql://localhost:3306/episodeRaterDB";
	private static String DB_USER = "rateradmin";
	private static String DB_PASS = "rater";

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		Connection conn = TVShowHelperTest.getConn();
		try 
		{
			PreparedStatement clearTableStatement =
					conn.prepareStatement("delete from User");
			clearTableStatement.execute();
			
		} catch(SQLException e) {
			System.err.println("setUp threw " + e);
		} 
	}
    /**
     * Tests if the preparedstatements are
     * properly initialized
     */
	@Test
	public void testConstructor() {
		UserHelper uh = new UserHelper();
		assertNotNull(uh.addUser);
		assertNotNull(uh.changePass);
		assertNotNull(uh.changeRole);
		assertNotNull(uh.removeUser);
		assertNotNull(uh.checkAdded);
	}
    /**
     * Tests if the user was successfully added
     * to the database records.
     */
	@Test
	public void testAddUSer() {
		UserHelper uh = new UserHelper();
		try {
            uh.addUser.setString(1,"user");
            uh.addUser.setString(2,"pass");
            uh.addUser.setString(3,"Reg");
            uh.addUser.executeUpdate();
			uh.checkAdded.setString(1, "user");
			ResultSet rs = uh.checkAdded.executeQuery();
			rs.next();
			assertEquals("instance 1 username", "user", rs.getString(1));
			assertEquals("instance 1 password", "pass", rs.getString(2));
			assertEquals("instance 1 role", "Reg", rs.getString(3));
		} catch (SQLException e) {
            System.err.println("testAddUser threw " + e);
		}
	}

    /**
     * Tests if the password was successfully changed
     */
    @Test
    public void testChangePass() {
        UserHelper uh = new UserHelper();
        try {
            uh.addUser.setString(1,"user");
            uh.addUser.setString(2,"pass");
            uh.addUser.setString(3,"Reg");
            uh.addUser.executeUpdate();

            
            uh.changePass.setString(1, "newpass");
            uh.changePass.setString(2, "user");
            uh.changePass.executeUpdate();
            uh.checkAdded.setString(1,"user");
            ResultSet rs = uh.checkAdded.executeQuery();
            rs.next();
            assertEquals("instance 2 passowrd","newpass",rs.getString(2));
        } catch(SQLException e) {
            System.err.println("testChangePass threw " + e);
        }
    }

    /**
     * Tests if the role was successfully changed
     */
    @Test
    public void testChangeRole() {
        UserHelper uh = new UserHelper();
        try {
            uh.addUser.setString(1,"user");
            uh.addUser.setString(2,"pass");
            uh.addUser.setString(3,"Reg");
            uh.addUser.executeUpdate();

            uh.changeRole.setString(1, "Mod");
            uh.changeRole.setString(2, "user");
            uh.changeRole.executeUpdate();

            uh.checkAdded.setString(1,"user");
            ResultSet rs = uh.checkAdded.executeQuery();
            rs.next();
            assertEquals("instance 3 role","Mod",rs.getString(3));
        } catch(SQLException e) {
            System.err.println("testChangeRole threw " + e);
        }
    }
	
    /**
     * Tests if the user was successfully removed 
     */
    @Test
    public void testRemoveUser() {
        UserHelper uh = new UserHelper();
        try {
            uh.addUser.setString(1,"user");
            uh.addUser.setString(2,"pass");
            uh.addUser.setString(3,"Reg");
            uh.addUser.executeUpdate();

            uh.removeUser.setString(1, "user");
            uh.removeUser.executeUpdate();
            uh.getUsers.setString(1, "user");
            ResultSet rs = uh.getUsers.executeQuery();
            assertEquals("instance 3 role", false, rs.next());
        } catch(SQLException e) {
            System.err.println("testRemoveUser threw " + e);
        }
    }
	
}//class
