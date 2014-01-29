package ajeetmurty.reco.movee.objs;

public class movieObj {
	private String movieName;
	private int movieId;
	private String imdbLink;
	private String date;
	private int rating;
	private String genre;
	private int prediction;

	public movieObj(String name, int id, String Link, String dt, int rate, String genreTemp) {
		movieName = name;
		movieId = id;
		imdbLink = Link;
		date = dt;
		rating = rate;
		genre = genreTemp;
	}

	public void setPrediction(int pred) {
		prediction = pred;
	}

	public int getPredcition() {
		return prediction;
	}

	public String getName() {
		return movieName;
	}

	public int getId() {
		return movieId;
	}

	public String getLink() {
		return imdbLink;
	}

	public String getDate() {
		return date;
	}

	public int getRating() {
		return rating;
	}

	public String getGenre() {
		return genre;
	}
}
