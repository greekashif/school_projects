package rater;

/** 
 * @author Clint
 * The show object.
 */
public class Show {
	
	private int id;
	private String title;
	private int season;
	private int rating;
	private String genre;
	private String synopsis;
	
	public Show(int id, String title, int season, int rating, String genre, String synopsis) {
		this.id = id;
		this.title = title;
		this.season = season;
		this.rating = rating;
		this.genre = genre;
		this.synopsis = synopsis;
	}
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getSeason() {
		return season;
	}

	public int getRating() {
		return rating;
	}

	public String getGenre() {
		return genre;
	}

	public String getSynopsis() {
		return synopsis;
	}
	

}
