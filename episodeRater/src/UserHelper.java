package rater;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * @author Clint
 * The class that helps the User Servlet.
 */
public class UserHelper {

	protected PreparedStatement addUser;
	protected PreparedStatement changeRole;
	protected PreparedStatement changePass;
	protected PreparedStatement removeUser;
	protected PreparedStatement getUsers;
	protected PreparedStatement checkAdded;
	protected PreparedStatement checkPass;

	/**
	 * The constructor for the UserHelper class.  It sets up the database connection.
	 */
	public UserHelper (){

		String JDBC_URL = "jdbc:mysql://localhost:3306/episodeRaterDB";
		String DB_USER = "rateradmin";
		String DB_PASS = "rater";
		Connection conn = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			System.out.println("Connected to rater DB");

			addUser = conn.prepareStatement("INSERT INTO User (username, password, role) values(?, ?, ?)");
			changeRole = conn.prepareStatement("UPDATE User SET `role` = ? WHERE `username` = ?");
			changePass = conn.prepareStatement("UPDATE User SET `password` = ? WHERE `username` = ?");
			removeUser = conn.prepareStatement("DELETE FROM User WHERE username = ?");
			getUsers = conn.prepareStatement("SELECT username from User WHERE username = ?");
			checkAdded = conn.prepareStatement("SELECT * from User WHERE username = ?");
			checkPass = conn.prepareStatement("SELECT password from User WHERE username = ?");
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
	 * Adds a user to the database.
	 * @param user The username of the user being added.
	 * @param pass The password of the user being added.
	 * @param pass2 Confirmation of the password of the user being added.
	 * @return error A string of the the error message or a success message if successful.
	 */
	public String addUser(String user, String pass, String pass2) {
		String error;
		if (user.equals(""))
		{
			error = "Error Creating User: No username was entered.  Please try again.";
			return error;
		}

		if (pass.equals(""))
		{
			error = "Error Creating User: Password was not entered.  Please try again.";
			return error;
		}

		if (pass2.equals(""))
		{
			error = "Error Creating User: Confirmation of the password was not entered.  Please try again.";
			return error;
		}

		try {
			getUsers.setString(1, user);
			ResultSet results;
			results = getUsers.executeQuery();
			if (results != null)
			{
				addUser.setString(1, user);
				if (pass.equals(pass2))
				{
					try {
						MessageDigest hashed = MessageDigest.getInstance("MD5");
						hashed.update(pass.getBytes());
						byte[] bytes = hashed.digest();
						StringBuilder builder = new StringBuilder();
						for (int i = 0; i < bytes.length; i++)
						{
							builder.append(String.format("%02X ", bytes[i]));
						}
						pass = builder.toString();
						addUser.setString(2, pass);
					}
					catch (NoSuchAlgorithmException e) {
						System.out.println("Hashed password failed. " + e.getMessage());
					}
				}
				else
				{
					error = "Error Creating User: Password and password confirmation do not match.  Please try again.";
					return error;
				}
				addUser.setString(3, "Reg");
				addUser.executeUpdate();
				error = "User successfully added.";
				return error;
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error closing the connection" + e.getMessage());
		}

		error = "Error Creating User: No idea what happened.  Please try again.  If that does not work please contact support at episoderatersupport@episoderater.com or 1-800-episoderatersupport.";
		return error;
	}

	/**
	 * Changes the password of a user.
	 * @param user The username whose password is being chanbged.
	 * @param pass The old password of the user.
	 * @param newPass The new password of the user.
	 * @param newPass2 Confirmation of the new password for the user.
	 * @return error A string of the error message or a success message if successful.
	 */
	public String changePassword(String user, String pass, String newPass, String newPass2)
	{
		String error;
		boolean authorized;
		if (pass.equals(""))
		{
			error = "Error Changing Password: Current password was not entered.  Please try again.";
			return error;
		}

		if (newPass.equals(""))
		{
			error = "Error Changing Password: New password was not entered.  Please try again.";
			return error;
		}

		if (newPass2.equals(""))
		{
			error = "Error Changing Password: Confirmation of the new password was not entered.  Please try again.";
			return error;
		}
		LoginHelper lh = new LoginHelper();
		authorized = lh.isAuthorizedUser(user, pass);
		if (authorized == false)
		{
			error = "Error Changing Password: Current password was not entered.  Please try again.";
			return error;
		}

		try
		{
			if (newPass.equals(newPass2))
			{
				try {
					MessageDigest hashed = MessageDigest.getInstance("MD5");
					hashed.update(newPass.getBytes());
					byte[] bytes = hashed.digest();
					StringBuilder builder = new StringBuilder();
					for (int i = 0; i < bytes.length; i++)
					{
						builder.append(String.format("%02X ", bytes[i]));
					}
					newPass = builder.toString();
					changePass.setString(1, newPass);

				} catch (NoSuchAlgorithmException e) {
					System.out.println("Hashed password failed. " + e.getMessage());
				}
			}
			else
			{
				error = "Error Changing Password: New password and new password confirmation do not match. Please try again.";
				return error;
			}
			changePass.setString(2, user);
			changePass.executeUpdate();
			error = "Password changed successfully.";
			return error;

		}
		catch (SQLException e)
		{
			System.out.println("Error closing the connection" + e.getMessage());
		}
		error = "Error Changing Password: No idea what happened.  Please try again.  If that does not work please contact support at episoderatersupport@episoderater.com or 1-800-episoderatersupport.";
		return error;
	}

	/**
	 * Makes a user a moderator.
	 * @param user The username of the user being made a mod.
	 * @return error A strinng of the error message, or a success message if successful.
	 */
	public String changeRole(String user)
	{
		String error;
		if (user.equals(""))
		{
			error = "Error Changing Role: No user was selected.  Please try again.";
			return error;
		}
		try
		{
			String mod= "Mod";
			changeRole.setString(1, mod);			
			changeRole.setString(2, user);
			changeRole.executeUpdate();
			error = "Role changed successfully.";
			return error;

		}
		catch (SQLException e)
		{
			System.out.println("Error closing the connection" + e.getMessage());
		}
		error = "Error Changing Role: No idea what happened.  Please try again.  If that does not work please contact support at episoderatersupport@episoderater.com or 1-800-episoderatersupport.";
		return error;
	}
}
