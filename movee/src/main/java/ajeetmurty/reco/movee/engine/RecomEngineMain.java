package ajeetmurty.reco.movee.engine;

import java.util.ArrayList;
import ajeetmurty.reco.movee.db.DBReader;
import ajeetmurty.reco.movee.db.DBWriter;
import ajeetmurty.reco.movee.objs.movieObj;
import ajeetmurty.reco.movee.objs.userObj;

public class RecomEngineMain {
	public static ArrayList alstRecommendations;

	public static ArrayList getRecommendations() {
		return null;
	}

	public static void recomputeRecommendations() {
		recomputerCorrleations();
		recomputePredictions();
	}

	public static void recomputePredictions() {
		calcualtePredictions("ACTION");
		calcualtePredictions("ADVENTURE");
		calcualtePredictions("ANIMATION");
		calcualtePredictions("CHILDREN");
		calcualtePredictions("COMEDY");
		calcualtePredictions("CRIME");
		calcualtePredictions("DOCUMENTARY");
		calcualtePredictions("DRAMA");
		calcualtePredictions("FANTASY");
		calcualtePredictions("FILMNOIR");
		calcualtePredictions("HORROR");
		calcualtePredictions("MUSICAL");
		calcualtePredictions("ROMANCE");
		calcualtePredictions("SCIFI");
		calcualtePredictions("THRILLER");
		calcualtePredictions("WAR");
		calcualtePredictions("WESTERN");
	}

	public static void recomputerCorrleations() {
		calculateCorrelations("ACTION");
		calculateCorrelations("ADVENTURE");
		calculateCorrelations("ANIMATION");
		calculateCorrelations("CHILDREN");
		calculateCorrelations("COMEDY");
		calculateCorrelations("CRIME");
		calculateCorrelations("DOCUMENTARY");
		calculateCorrelations("DRAMA");
		calculateCorrelations("FANTASY");
		calculateCorrelations("FILMNOIR");
		calculateCorrelations("HORROR");
		calculateCorrelations("MUSICAL");
		calculateCorrelations("ROMANCE");
		calculateCorrelations("SCIFI");
		calculateCorrelations("THRILLER");
		calculateCorrelations("WAR");
		calculateCorrelations("WESTERN");
	}

	public static void calculateCorrelations(String genreMain) {
		//get all movies for a specific genre X.
		ArrayList listMoviesPerGenre = DBReader.getMovies(genreMain);
		
		//there are 943 users in the movielens dataset. lets start with user A.
		for (int i = 1; i < 944; i++) {
			ArrayList alstUser = new ArrayList();
			ArrayList alstFetch = new ArrayList();
			float user = 0;
			float fetch = 0;
			
			//get all movies for user A belonging to a genre X. 
			ArrayList listMoviesPerUserPerGenre = DBReader.getMovies(i, genreMain);

			// find similar ratings between user and database
			for (int x = 0; x < listMoviesPerGenre.size(); x++) {
				movieObj moviePerGenre = (movieObj) listMoviesPerGenre.get(x);
				for (int y = 0; y < listMoviesPerUserPerGenre.size(); y++) {
					movieObj moviePerUserPerGenre = (movieObj) listMoviesPerUserPerGenre.get(y);
					if (moviePerGenre.getId() == moviePerUserPerGenre.getId() && moviePerGenre.getRating() != 99) {
						alstUser.add(Integer.valueOf(moviePerGenre.getRating()));
						user = user + moviePerGenre.getRating();
						alstFetch.add(Integer.valueOf(moviePerUserPerGenre.getRating()));
						fetch = fetch + moviePerUserPerGenre.getRating();
					}
				}
			}

			// find average
			user = user / alstUser.size();
			fetch = fetch / alstFetch.size();

			// calculate numerator
			float numerator = 0, denominator1 = 0, denominator2 = 0;
			for (int a = 0; a < alstUser.size(); a++) {
				Integer x = (Integer) alstUser.get(a);
				Integer y = (Integer) alstFetch.get(a);
				numerator = numerator + ((x.floatValue() - user) * (y.floatValue() - fetch));
				denominator1 = denominator1 + ((x.floatValue() - user) * (x.floatValue() - user));
				denominator2 = denominator2 + ((y.floatValue() - fetch) * (y.floatValue() - fetch));
			}

			float correlation = (float) (numerator / (Math.sqrt(denominator1) * Math.sqrt(denominator2)));
			System.out.println("correlation is ::: " + correlation);
			if (Double.isNaN(correlation)) {
				correlation = 99;
			}
			DBWriter.saveCorrelation(correlation, i, genreMain);
		}
	}

	public static void calcualtePredictions(String genreMain) {
		System.out.println(">>> " + DBReader.getAvgUserRating());
		if (genreMain != null) {
			ArrayList alstMovies = DBReader.getMovies(genreMain);
			for (int i = 1; i < alstMovies.size(); i++) {
				movieObj obj = (movieObj) alstMovies.get(i);
				ArrayList alstUsers = DBReader.getUsers(obj.getId());
				float prediction = 99;
				float numerator = 0;
				float denominator = 0;
				int avg = DBReader.getAvgUserRating();

				for (int j = 0; j < alstUsers.size(); j++) {
					userObj usr = (userObj) alstUsers.get(j);
					float corr = DBReader.getCorrCoff(usr.getId(), genreMain);
					if (!Double.isNaN(corr)) {
						float usrAvg = DBReader.getAvgRating(usr.getId());
						numerator = numerator + ((usr.getRate() - usrAvg) * corr);
						if (corr < 0) {
							denominator = denominator + (-1 * corr);
						} else {
							denominator = denominator + (1 * corr);
						}
						prediction = avg + (numerator / denominator);
					} else {
						prediction = 99;
					}
				}
				DBWriter.savePrediction(obj.getId(), (int) prediction);
				System.out.println(obj.getId() + " prediciton is >>>  " + (int) prediction);
			}
		}
	}
}
