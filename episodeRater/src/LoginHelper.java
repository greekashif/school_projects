package rater;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Clint
 * The class that helps the LoginServlet.
 */
public class LoginHelper {

	protected PreparedStatement login;
	protected PreparedStatement findRole;

	/**
	 * The constructor for the login helper.  It sets up the databse connection.
	 */
	public LoginHelper(){

		String JDBC_URL = "jdbc:mysql://localhost:3306/episodeRaterDB";
		String DB_USER = "rateradmin";
		String DB_PASS = "rater";
		Connection conn = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			System.out.println("Connected to rater DB");

			login = conn.prepareStatement("SELECT username, password FROM User WHERE `username` = ? AND `password` = ?");
			findRole = conn.prepareStatement("SELECT role FROM User WHERE `username` = ?");
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
	 * Checks to see if someone's login information is correct.
	 * @param username The username someone enters in a login attempt.
	 * @param password The password someone enters in a login attempt.
	 * @return authorized A boolean depending on if the login attempt is made by an authorized user.
	 */
	public boolean isAuthorizedUser(String username, String password) {

		try
		{
			login.setString(1, username);
			try {
				MessageDigest hashed = MessageDigest.getInstance("MD5");
				hashed.update(password.getBytes());
				byte[] bytes = hashed.digest();
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < bytes.length; i++)
				{
					builder.append(String.format("%02X ", bytes[i]));
				}
				password = builder.toString();
				login.setString(2, password);
			} catch (NoSuchAlgorithmException e) {
				System.out.println("Hashed password failed. " + e.getMessage());
			}
			ResultSet results = login.executeQuery();
			Boolean authorized = results.first();
			return authorized;	
		}
		catch (SQLException e)
		{
			System.out.println("Error closing the connection isAuthorizedUser " + e.getMessage());
		}
		return false;
	}

	/**
	 * If there is an error logging in, it checks to see what that error was.
	 * @param username The username that the user entered.
	 * @param password The password that the user entered.
	 * @return error A string of the error.
	 */
	public String isAuthorizedUserError(String username, String password) {
		String error;
		if (username.equals(""))
		{
			error = "Login Error: No username was entered.  Please try again.";
			return error;
		}

		if (password.equals(""))
		{
			error = "Login Error: Password was not entered.  Please try again.";
			return error;
		}
		error = "Login Error: The username or password entered was incorrect.  Please try to login again.";
		return error;
	}

	/**
	 * Gets the role of the user when they sign in.
	 * @param username The username someone enters in a login attempt.
	 * @return role The role of the user.
	 */
	public String getRole(String username) {

		try
		{
			findRole.setString(1, username);
			ResultSet result = findRole.executeQuery();
			if (result.next() == true)
			{
				String role = result.getString(1);
				return role;
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error closing the connection getRole " + e.getMessage());
		}
		return("Guest");
	}
}
