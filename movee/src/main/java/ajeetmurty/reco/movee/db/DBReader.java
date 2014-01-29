package ajeetmurty.reco.movee.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ajeetmurty.reco.movee.objs.movieObj;
import ajeetmurty.reco.movee.objs.userObj;

public class DBReader {
	private static Connection conn;
	private static Statement s;

	public static void setConnection(Connection connTemp, Statement stemp) {
		if (connTemp != null && stemp != null) {
			conn = connTemp;
			s = stemp;
		}
	}

	public static ArrayList getRecommendations(String genre) {
		if (genre != null) {
			try {
				s.execute("select * from MOVIE where " + genre + " = 1 and RATING = 99 and PREDICTION >2");
				ResultSet rs = s.getResultSet();
				ArrayList alstMovies = new ArrayList();
				while (rs.next()) {
					int x;
					String str = rs.getString(24);
					if (str == "99") {
						x = 99;
					} else {
						x = Integer.valueOf(str).intValue();
					}
					movieObj mov = new movieObj(rs.getString(2), rs.getInt(1), rs.getString(4), rs.getString(3), x, genre);
					mov.setPrediction(rs.getInt(25));
					alstMovies.add(mov);
				}
				return alstMovies;
			} catch (SQLException e) {
				System.out.println("this is the error::: " + e);
				e.printStackTrace();
			}
		}
		return null;
	}

	public static ArrayList getMovies(String category) {
		if (category != null) {
			try {
				s.execute("select * from MOVIE where " + category + " = 1");
				ResultSet rs = s.getResultSet();
				ArrayList alstMovies = new ArrayList();
				while (rs.next()) {
					int x;
					String str = rs.getString(24);
					if (str == "99") {
						x = 99;
					} else {
						x = Integer.valueOf(str).intValue();
					}
					movieObj mov = new movieObj(rs.getString(2), rs.getInt(1), rs.getString(4), rs.getString(3), x, category);
					alstMovies.add(mov);
				}
				return alstMovies;
			} catch (SQLException e) {
				System.out.println("this is the error::: " + e);
				e.printStackTrace();
			}
		}
		return null;
	}

	public static ArrayList getMovies(int userId, String genre) {
		if (genre != null) {
			try {
				s.execute("select PRS.MOVIEID, PRS.RATING from PRS, MOVIE where USERID = " + userId + " and PRS.MOVIEID = MOVIE.MOVIEID and MOVIE." + genre + "=1");
				ResultSet rs = s.getResultSet();
				ArrayList alstMovies = new ArrayList();
				while (rs.next()) {
					int x;
					String str = rs.getString(2);
					if (str == "99") {
						x = 99;
					} else {
						x = Integer.valueOf(str).intValue();
					}
					movieObj mov = new movieObj(null, rs.getInt(1), null, null, x, genre);
					alstMovies.add(mov);
				}
				return alstMovies;
			} catch (SQLException e) {
				System.out.println("this is the error::: " + e);
				e.printStackTrace();
			}
		}
		return null;
	}

	public static ArrayList getUsers(int movieId) {
		ArrayList alstUsers = new ArrayList();
		try {
			s.execute("select * from PRS where MOVIEID = " + movieId + "");
			ResultSet rs = s.getResultSet();
			while (rs.next()) {
				userObj usr = new userObj(rs.getInt(1), rs.getInt(3));
				alstUsers.add(usr);
			}
		} catch (SQLException e) {
			System.out.println("this is the error::: " + e);
			e.printStackTrace();
		}
		return alstUsers;
	}

	public static int getAvgRating(int userId) {
		try {
			s.execute("select * from PRS where USERID = " + userId + "");
			ResultSet rs = s.getResultSet();
			int average = 0;
			int count = 0;
			while (rs.next()) {
				average = average + rs.getInt(3);
				count = count + 1;
			}
			average = average / count;
			return average;
		} catch (SQLException e) {
			System.out.println("this is the error::: " + e);
			e.printStackTrace();
		}
		return 99;
	}

	public static float getCorrCoff(int userId, String genre) {
		try {
			s.execute("select * from PROFILE where USERID = " + userId + "");
			ResultSet rs = s.getResultSet();
			while (rs.next()) {
				return Float.valueOf(rs.getString(2)).floatValue();
				// return rs.getFloat(2);
			}
		} catch (SQLException e) {
			System.out.println("this is the error::: " + e);
			e.printStackTrace();
		}
		return 99;
	}

	public static int getAvgUserRating() {
		try {
			s.execute("select * from MOVIE");
			ResultSet rs = s.getResultSet();
			int avg = 0;
			int count = 0;
			while (rs.next()) {
				String str = rs.getString(24);
				if (str != "99") {
					avg = avg + Integer.valueOf(str).intValue();
					count = count + 1;
				}
			}
			return avg = avg / count;
		} catch (SQLException e) {
			System.out.println("this is the error::: " + e);
			e.printStackTrace();
		}
		return 99;
	}

	public static int getMoviesRateCount() {
		try {
			s.execute("select * from MOVIE where RATING <> " + 99 + "");
			ResultSet rs = s.getResultSet();
			int count = 0;
			while (rs.next()) {
				count = count + 1;
			}
			return count;
		} catch (SQLException e) {
			System.out.println("this is the error::: " + e);
			e.printStackTrace();
		}
		return 0;
	}
}
