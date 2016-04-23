package rater;

/**
 * @author Clint
 * The season object.
 */
public class Season {

	private int id;
	private String title;
	private int season;
	private int episodeNumber;
	private String episodeName;
	private int rating;
	private String synopsis;
	
	public Season(int id, String title, int season, int episodeNumber, String episodeName, int rating, String synopsis) {
		this.id = id;
		this.title = title;
		this.season = season;
		this.episodeNumber = episodeNumber;
		this.episodeName = episodeName;
		this.rating = rating;
		this.synopsis = synopsis;
	}

	public int getId() {
		return id;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public String getTitle() {
		return title;
	}

	public int getSeason() {
		return season;
	}

	public int getEpisodeNumber() {
		return episodeNumber;
	}

	public String getEpisodeName() {
		return episodeName;
	}

	public int getRating() {
		return rating;
	}

	
	
}
