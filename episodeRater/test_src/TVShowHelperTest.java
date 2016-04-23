
package rater;

import java.sql.*;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

/**
 * Tests the functionality of the TVShowHelper class
 *
 * @author Kashif Ahmadi
 */

public class TVShowHelperTest extends TestCase {
    
        private static String JDBC_URL = 
            "jdbc:mysql://localhost:3306/episodeRaterDB";
        private static String DB_USER = "rateradmin";
        private static String DB_PASS = "rater";


    /** Utility class to get a connection
     *  to the database
     */
    public static Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, DB_USER,
                                                DB_PASS);
            return conn;
        } catch(SQLException e) {
            System.err.println("Could not connect to DB");
            System.err.println(e);
        } catch(ClassNotFoundException e) {
            System.err.println("Could not load DB driver");
            System.err.println(e);
        }
        return null;
    }

    /**
     * Clear the database entries before running
     * subsequent tests
     */

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        Connection conn = null;
        try {
            conn = getConn();
            PreparedStatement clearTableStatement =
                conn.prepareStatement("delete from TVShow");
            clearTableStatement.execute();
        } catch(SQLException e) {
            System.err.println(e);
        } finally {
            if(conn != null) conn.close();
        }
    }

    /**
     * Tests the constructor for initializing the 
     * prepared statements and not null
     */
    @Test
    public void testConstructor() {
        TVShowHelper tsh = new TVShowHelper();
        assertNotNull(tsh.addShow);
        assertNotNull(tsh.addSeason);
        assertNotNull(tsh.addEpisode);
        assertNotNull(tsh.listSeasons);
        assertNotNull(tsh.getSeason);
        assertNotNull(tsh.listEpisodes);
        assertNotNull(tsh.getEpisode);
        assertNotNull(tsh.browse);
        assertNotNull(tsh.search);
    }

    /**
     * Tests whether the show has been added
     * to the database
     */
    @Test
    public void testAddShow() {
        TVShowHelper th = new TVShowHelper();
        try {
            th.addShow.setString(1,"Seinfeld");
            th.addShow.setInt(2, 0);
            th.addShow.setInt(3, 1);
            th.addShow.setString(4,"Soup Nazi");
            th.addShow.setInt(5, 5);
            th.addShow.setString(6, "Comedy");
            th.addShow.setString(
                7, "Soup kitchen tyrant terrorizes Manhattan!");
            th.addShow.executeUpdate();
            Connection conn = getConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT title, season,"+
                "episodeNumber, episodeName, rating, genre, synopsis "+
                "from TVShow");
            rs.next();

            assertEquals("instance 1 title", "Seinfeld", rs.getString(1));
            assertEquals("instance 1 season", 0, rs.getInt(2));
            assertEquals("instance 1 episode", 1, rs.getInt(3));
            assertEquals("instance 1 episodeName", "Soup Nazi", 
                          rs.getString(4));
            assertEquals("instance 1 rating", 5, rs.getInt(5));
            assertEquals("instance 1 genre", "Comedy", rs.getString(6));
            assertEquals("instance 1 synopsis",
                         "Soup kitchen tyrant terrorizes Manhattan!",
                          rs.getString(7));
        } catch(SQLException e) {
            System.err.println("testAddShow threw " + e);
        }
    }

    /**
     * Tests whether the season has been added
     * to the database successfully
     */
    @Test
    public void testAddSeason() {
        TVShowHelper th = new TVShowHelper();
        try {
            th.addSeason.setString(1,"Season 1");
            th.addSeason.setInt(2, 1);
            th.addSeason.setInt(3, 2);
            th.addSeason.setString(4,"Soup Nazi");
            th.addSeason.setInt(5, 5);
            th.addSeason.setString(6, "Comedy");
            th.addSeason.setString(
                7, "Soup kitchen tyrant terrorizes Manhattan!");
            th.addSeason.executeUpdate();
            Connection conn = getConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT title, season,"+
                "episodeNumber, episodeName, rating, genre, synopsis "+
                "from TVShow");
            rs.next();

            assertEquals("instance 2 title", "Season 1", rs.getString(1));
            assertEquals("instance 2 season", 1, rs.getInt(2));
            assertEquals("instance 2 episode", 2, rs.getInt(3));
            assertEquals("instance 2 episodeName", "Soup Nazi", 
                          rs.getString(4));
            assertEquals("instance 2 rating", 5, rs.getInt(5));
            assertEquals("instance 2 genre", "Comedy", rs.getString(6));
            assertEquals("instance 2 synopsis",
                         "Soup kitchen tyrant terrorizes Manhattan!",
                          rs.getString(7));
        } catch(SQLException e) {
            System.err.println("testAddSeason threw" + e);
        }
    }

    /**
     * Tests whether the episode has been added
     * to the database successfully 
     */
    @Test            
    public void testAddEpisode() {
        TVShowHelper th = new TVShowHelper();
        try {
            th.addEpisode.setString(1,"Season 7");
            th.addEpisode.setInt(2, 1);
            th.addEpisode.setInt(3, 2);
            th.addEpisode.setString(4,"Soup Nazi");
            th.addEpisode.setInt(5, 5);
            th.addEpisode.setString(6, "Comedy");
            th.addEpisode.setString(
                7, "Soup kitchen tyrant terrorizes Manhattan!");
            th.addEpisode.executeUpdate();
            Connection conn = getConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT title, season,"+
                "episodeNumber, episodeName, rating, genre, synopsis "+
                "from TVShow");
            rs.next();

            assertEquals("instance 2 title", "Season 7", rs.getString(1));
            assertEquals("instance 2 season", 1, rs.getInt(2));
            assertEquals("instance 2 episode", 2, rs.getInt(3));
            assertEquals("instance 2 episodeName", "Soup Nazi", 
                          rs.getString(4));
            assertEquals("instance 2 rating", 5, rs.getInt(5));
            assertEquals("instance 2 genre", "Comedy", rs.getString(6));
            assertEquals("instance 2 synopsis",
                         "Soup kitchen tyrant terrorizes Manhattan!",
                          rs.getString(7));
        } catch(SQLException e) {
            System.err.println("testAddEpisode threw " + e);
        }
    }

}

            
            
        

