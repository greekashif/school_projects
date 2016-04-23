package rater;

/**
 * @author Clint
 * An episode object.
 */
public class Episode {

	private int id;
	private String title;
	private int season;
	private int episodeNumber;
	private String episodeName;
	private String synopsis;
	
	/**
	 * The episode constructor.
	 * @param id The id of the episode.
	 * @param title The title for the show that the episode is in.
	 * @param season The number for the season that the episode is in.
	 * @param episodeNumber The number of the episode,
	 * @param episodeName The name of the episode.
	 * @param synopsis A synopsis of the episode.
	 */
	public Episode(int id, String title, int season, int episodeNumber, String episodeName, String synopsis) {
		this.id = id;
		this.title = title;
		this.season = season;
		this.episodeNumber = episodeNumber;
		this.episodeName = episodeName;
		this.synopsis = synopsis;
	}
	
	/**
	 * Gets the id.
	 * @return id The id of the episode.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the title.
	 * @return title The title of the show that the episode is a part of.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the season
	 * @return season The number for the season that the episode is a part of.
	 */
	public int getSeason() {
		return season;
	}

	/**
	 * Gets the episode number.
	 * @return episodeNumber The number of the episode.
	 */
	public int getEpisodeNumber() {
		return episodeNumber;
	}

	/**
	 * Gets the name of the episode.
	 * @return episodeName The name of the episode.
	 */
	public String getEpisodeName() {
		return episodeName;
	}

	/**
	 * Gets the synopsis for the episode.
	 * @return synopsis The synopsis or the episode.
	 */
	public String getSynopsis() {
		return synopsis;
	}
	
	
}
