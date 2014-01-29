package ajeetmurty.reco.movee.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ajeetmurty.reco.movee.objs.movieObj;

public class DBWriter {

	private static Connection conn;
	private static Statement s;

	public static void setConnection(Connection connTemp, Statement stemp) {
		if (connTemp != null && stemp != null) {
			conn = connTemp;
			s = stemp;
		}
	}

	public static void saveRating(int rating, movieObj obj) {
		if (conn != null && obj != null) {
			int movieId = obj.getId();
			try {
				s.execute("UPDATE MOVIE SET RATING = " + rating + " WHERE MOVIEID = " + movieId + " ");
				ResultSet rs = s.getResultSet();
			} catch (SQLException e) {
				System.out.println("this is the error::: " + e);
				e.printStackTrace();
			}
		}
	}

	public static void savePrediction(int movieId, int prediction) {
		if (conn != null) {
			try {
				s.execute("UPDATE MOVIE SET PREDICTION = " + prediction + " WHERE MOVIEID = " + movieId + " ");
				ResultSet rs = s.getResultSet();
			} catch (SQLException e) {
				System.out.println("this is the error::: " + e);
				e.printStackTrace();
			}
		}
	}

	public static void saveCorrelation(double corr, int userId, String genre) {
		if (conn != null && genre != null) {
			try {
				s.execute("UPDATE PROFILE SET " + genre + " = " + corr + " WHERE USERID = " + userId + " ");
				ResultSet rs = s.getResultSet();
			} catch (SQLException e) {
				System.out.println("this is the error::: " + e);
				e.printStackTrace();
			}
		}
	}
}
