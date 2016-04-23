package rater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Clint
 * A helper class for the TVShowServlet.
 */
public class TVShowHelper {

	protected PreparedStatement addShow;
	protected PreparedStatement checkShow;
	protected PreparedStatement addSeason;
	protected PreparedStatement checkSeason;
	protected PreparedStatement addEpisode;
	protected PreparedStatement checkEpisode;
	protected PreparedStatement getShow;
	protected PreparedStatement listSeasons;
	protected PreparedStatement getSeason;
	protected PreparedStatement listEpisodes;
	protected PreparedStatement getEpisode;
	protected PreparedStatement browse;
	protected PreparedStatement browseGenre;
	protected PreparedStatement search;

	/**
	 * The constructor for the TVShowHelper class.  Connects to the database.
	 */
	public TVShowHelper (){

		String JDBC_URL = "jdbc:mysql://localhost:3306/episodeRaterDB";
		String DB_USER = "rateradmin";
		String DB_PASS = "rater";
		Connection conn = null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			System.out.println("Connected to rater DB");

			addShow = conn.prepareStatement("INSERT INTO TVShow (title, season, episodeNumber, episodeName, rating, genre, synopsis)" +" values(?, ?, ?, ?, ?, ?, ?)");
			checkShow = conn.prepareStatement("SELECT title FROM TVShow WHERE title = ?");
			addSeason = conn.prepareStatement("INSERT INTO TVShow (title, season, episodeNumber, episodeName, rating, genre, synopsis)" +" values(?, ?, ?, ?, ?, ?, ?)");
			checkSeason = conn.prepareStatement("SELECT season FROM TVShow WHERE title = ? AND season = ?");
			addEpisode = conn.prepareStatement("INSERT INTO TVShow (title, season, episodeNumber, episodeName, rating, genre, synopsis)" +" values(?, ?, ?, ?, ?, ?, ?)");
			checkEpisode = conn.prepareStatement("SELECT episodeNumber FROM TVShow WHERE title = ? AND season = ? AND (episodeNumber = ? OR episodeName = ?)");
			getShow = conn.prepareStatement("SELECT id, title, season, rating, genre, synopsis FROM TVShow WHERE title = ?");
			listSeasons = conn.prepareStatement("SELECT id, title, season, episodeNumber, episodeName, rating, synopsis FROM TVShow WHERE title = ? AND episodeNumber = ? AND season > 0");
			getSeason = conn.prepareStatement("SELECT id, title, season, episodeNumber, episodeName, rating, synopsis FROM TVShow WHERE title = ? AND season = ?");
			listEpisodes = conn.prepareStatement("SELECT id, title, season, episodeNumber, episodeName, synopsis from TVShow WHERE title = ? AND season = ? AND episodeNumber > 0");
			getEpisode = conn.prepareStatement("SELECT id, title, season, episodeNumber, episodeName, synopsis from TVShow WHERE title = ? AND season = ? AND episodeNumber = ?");
			browse = conn.prepareStatement("SELECT id, title, season, rating, genre, synopsis FROM TVShow WHERE season = 0 ORDER BY `title` ASC");
			browseGenre = conn.prepareStatement("SELECT id, title, season, rating, genre, synopsis FROM TVShow WHERE season = ? AND genre = ? ORDER BY `title` ASC");
			search = conn.prepareStatement("SELECT id, title, season, rating, genre, synopsis FROM TVShow WHERE `title` LIKE CONCAT('%', ?, '%') AND season = ? ORDER BY `title` ASC");
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
	 * Adds a show to the database.
	 * @param title Name of the show.
	 * @param rating Rating of the show.
	 * @param genre Genre for the show.
	 * @param synopsis Synopsis of the show.
	 * @return error An error message, or a success message if successful.
	 */
	public String addShow(String title, int rating, String genre, String synopsis){
		String error;
		if (title.equals(""))
		{
			error = "Error Adding Show: No show name was entered.  Please try again.";
			return error;
		}

		if (rating != 1 && rating != 2 && rating != 3 && rating != 4 && rating != 5)
		{
			error = "Error Adding Show: No rating was entered.  Please try again.";
			return error;
		}

		if (genre.equals(""))
		{
			error = "Error Adding Show: No genre was entered.  Please try again.";
			return error;
		}

		if (synopsis.equals(""))
		{
			error = "Error Adding Show: No synopsis was entered.  Please try again.";
			return error;
		}

		try {
			checkShow.setString(1, title);
			ResultSet results;
			results = checkShow.executeQuery();
			if (results.first() == true)
			{
				error = "Error Adding TV Show: TV show already exists. Please try to add a new TV show.";
				return error;
			}
			addShow.setString(1, title);
			addShow.setInt(2, 0);
			addShow.setInt(3, 0);
			addShow.setString(4, null);
			addShow.setInt(5, rating);
			addShow.setString(6, genre);
			addShow.setString(7, synopsis);
			addShow.executeUpdate();
			error = "Show successfully added.";
			return error;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		error = "Error Adding Show: No idea what happened.  Please try again.  If that does not work please contact support at episoderatersupport@episoderater.com or 1-800-episoderatersupport.";
		return error;
	}

	/**
	 * Adds a season to the database.
	 * @param title The title of the show that contains the season.
	 * @param season The season number.
	 * @param rating The rating for the season.
	 * @param synopsis A synopsis of the season.
	 * @return error An error or success message depending on the outcome.
	 */
	public String addSeason(String title, int season, int rating, String synopsis){
		String error;

		if (season == 0)
		{
			error = "Error Adding Season: No season number was entered.  Please try again.";
			return error;
		}

		if (rating!= 1 && rating!= 2 && rating!= 3 && rating!= 4 && rating!= 5)
		{
			error = "Error Adding Season: No rating was entered.  Please try again.";
			return error;
		}

		if (synopsis.equals(""))
		{
			error = "Error Adding Season: No synopsis was entered.  Please try again.";
			return error;
		}

		try {
			checkSeason.setString(1, title);
			checkSeason.setInt(2, season);
			ResultSet results;
			results = checkSeason.executeQuery();
			if (results.first() == true)
			{
				error = "Error Adding Season: Season already exists. Please try to add a new season.";
				return error;
			}
			addSeason.setString(1, title);
			addSeason.setInt(2, season);
			addSeason.setInt(3, 0);
			addSeason.setString(4, null);
			addSeason.setInt(5, rating);
			addSeason.setString(6, null);
			addSeason.setString(7, synopsis);
			addSeason.executeUpdate();
			error = "Season successfully added.";
			return error;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		error = "Error Adding Season: No idea what happened.  Please try again.  If that does not work please contact support at episoderatersupport@episoderater.com or 1-800-episoderatersupport.";
		return error;
	}

	/**
	 * Adds an episode to the database.
	 * @param title The title of the show that contains the episode..
	 * @param season The number for the season that contains the episode.
	 * @param episodeNumber The number of the episode.
	 * @param episodeName The name of the episode.
	 * @param synopsis A synopsis of the episode.
	 * @return error An error or success message depending of the results.
	 */
	public String addEpisode(String title, int season, int episodeNumber, String episodeName, String synopsis){
		String error;

		if (episodeNumber == 0)
		{
			error = "Error Adding Episode: No episode number was entered.  Please try again.";
			return error;
		}

		if (episodeName.equals(""))
		{
			error = "Error Adding Episode: No episode name was entered.  Please try again.";
			return error;
		}

		if (synopsis.equals(""))
		{
			error = "Error Adding Episode: No synopsis was entered.  Please try again.";
			return error;
		}

		try {
			checkEpisode.setString(1, title);
			checkEpisode.setInt(2, season);
			checkEpisode.setInt(3, episodeNumber);
			checkEpisode.setString(4, episodeName);
			ResultSet results;
			results = checkEpisode.executeQuery();
			if (results.first() == true)
			{
				error = "Error Adding Episode: Episode already exists. Please try to add a new episode.";
				return error;
			}
			addEpisode.setString(1, title);
			addEpisode.setInt(2, season);
			addEpisode.setInt(3, episodeNumber);
			addEpisode.setString(4, episodeName);
			addEpisode.setInt(5, 0);
			addEpisode.setString(6,null);
			addEpisode.setString(7, synopsis);
			addEpisode.executeUpdate();
			error = "Episode successfully added.";
			return error;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		error = "Error Adding Episode: No idea what happened.  Please try again.  If that does not work please contact support at episoderatersupport@episoderater.com or 1-800-episoderatersupport.";
		return error;
	}

	/**
	 * Gets a show object for the title given.
	 * @param title The title of the show.
	 * @return show A show object.
	 */
	public Show getShow(String title){

		try {
			getShow.setString(1, title);
			ResultSet results;
			results = getShow.executeQuery();
			results.next();
			Show show = new Show(results.getInt("id"), results.getString("title"), results.getInt("season"), results.getInt("rating"), results.getString("genre"), results.getString("synopsis"));
			return show;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets list of the season for a particular show.
	 * @param title The name of the show for which a list of the seasons is being retrieved.
	 * @return Season An ArrayList of season objects.
	 */
	public ArrayList<Season> listSeasons(String title){

		try {
			listSeasons.setString(1, title);
			listSeasons.setInt(2, 0);
			ResultSet results;
			results = listSeasons.executeQuery();
			ArrayList<Season> seasons = new ArrayList<Season>();
			while(results.next())
			{
				Season se = new Season(results.getInt("id"), results.getString("title"), results.getInt("season"), results.getInt("episodeNumber"), results.getString("episodeName"), results.getInt("rating"), results.getString("synopsis"));
				seasons.add(se);
			}
			return seasons;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the season selected for the show that is selected.
	 * @param title The name of the show that is selected.
	 * @param season The number of the season being selected.
	 * @return season A season object for the season that is selected.
	 */
	public Season getSeasons(String title, int season){

		try {
			getSeason.setString(1, title);
			getSeason.setInt(2, season);
			ResultSet results;
			results = getSeason.executeQuery();
			results.next();
			Season seasons = new Season(results.getInt("id"), results.getString("title"), results.getInt("season"), results.getInt("episodeNumber"), results.getString("episodeName"), results.getInt("rating"), results.getString("synopsis"));
			return seasons;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets a list of the episodes in a season for a show.
	 * @param title The name of the show that the episodes are in.
	 * @param season The number for the season containing the episodes
	 * @return episode An ArrayList of episode objects for a particular season.
	 */
	public ArrayList<Episode> listEpisodes(String title, int season){

		try {
			listEpisodes.setString(1, title);
			listEpisodes.setInt(2, season);
			ResultSet results;
			results = listEpisodes.executeQuery();
			ArrayList<Episode> episode = new ArrayList<Episode>();
			while(results.next())
			{
				Episode e = new Episode(results.getInt("id"), results.getString("title"), results.getInt("season"), results.getInt("episodeNumber"), results.getString("episodeName"), results.getString("synopsis"));
				episode.add(e);
			}
			return episode;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets a particular episode.
	 * @param title The name of the show which the episode is from.
	 * @param season The season number that the episode is in.
	 * @param episodeNumber The number of the episode.
	 * @return episode An episode object for the episode that is selected.
	 */
	public Episode getEpisode(String title, int season, int episodeNumber){

		try {
			getEpisode.setString(1, title);
			getEpisode.setInt(2, season);
			getEpisode.setInt(3, episodeNumber);
			ResultSet results;
			results = getEpisode.executeQuery();
			results.next();
			Episode episode = new Episode(results.getInt("id"), results.getString("title"), results.getInt("season"), results.getInt("episodeNumber"), results.getString("episodeName"), results.getString("synopsis"));
			return episode;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * A method to browse tv shows.
	 * @return show An ArrayList of all the shows in alphabetical order.
	 */
	public ArrayList<Show> browse(String genre)
	{
		try {
			ResultSet results;
			results = browse.executeQuery();
			ArrayList<Show> show = new ArrayList<Show>();
			if (!genre.equals("a"))
			{
				browseGenre.setInt(1, 0);
				browseGenre.setString(2, genre);
				results = browseGenre.executeQuery();
				while(results.next())
				{
					Show sh = new Show(results.getInt("id"), results.getString("title"), results.getInt("season"), results.getInt("rating"), results.getString("genre"), results.getString("synopsis"));
					show.add(sh);
				}
				return show;
			}
			while(results.next())
			{
				Show sh = new Show(results.getInt("id"), results.getString("title"), results.getInt("season"), results.getInt("rating"), results.getString("genre"), results.getString("synopsis"));
				show.add(sh);
			}
			return show;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * A method to search for shows using their names and genres.
	 * @param searchQuery  The search query being entered.
	 * @param genre The genre of show being searched for.
	 * @return show An ArrayList of show objects the fit the search results.
	 */
	public ArrayList<Show> search (String searchQuery)
	{		
		try {
			ResultSet results;
			ArrayList<Show> show = new ArrayList<Show>();
			search.setString(1, searchQuery);
			search.setInt(2, 0);
			results = search.executeQuery();
			while(results.next())
			{
				Show sh = new Show(results.getInt("id"), results.getString("title"), results.getInt("season"), results.getInt("rating"), results.getString("genre"), results.getString("synopsis"));
				show.add(sh);
			}
			return show;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
