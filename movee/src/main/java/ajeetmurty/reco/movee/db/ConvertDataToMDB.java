package ajeetmurty.reco.movee.db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.StringTokenizer;
import java.io.*;

public class ConvertDataToMDB {
	Connection con;
	Statement s;

	public ConvertDataToMDB(Connection conTemp, Statement stemp) {
		if (conTemp != null && stemp != null) {
			con = conTemp;
			s = stemp;
			try {
				insertData();
			} catch (Exception e) {
				System.out.println("Error with creating PRS database & table: " + e);
			}
		}
	}

	public void insertData() {
		if (con != null) {
			readTextData();
			readGenres();
			createProfile();
		}
	}

	public void createProfile() {
		// use the below to create the profile table
		try {
			// create a table
			s.execute("create table PROFILE ( USERID INTEGER, ACTION FLOAT , ADVENTURE FLOAT, ANIMATION FLOAT, CHILDREN FLOAT, COMEDY FLOAT, CRIME FLOAT, DOCUMENTARY FLOAT, DRAMA FLOAT, FANTASY FLOAT, FILMNOIR FLOAT, HORROR FLOAT, MUSICAL FLOAT, ROMANCE FLOAT, SCIFI FLOAT, THRILLER FLOAT, WAR FLOAT, WESTERN FLOAT)");
			for (int i = 1; i < 944; i++) {
				s.execute("insert into PROFILE values(" + i + ", 99, 99, 99, 99, 99, 99 ,99 ,99 ,99 ,99, 99, 99, 99, 99, 99, 99, 99)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void readTextData() {
		try {
			// create a table
			s.execute("CREATE TABLE prs ( userid integer , movieid integer  , rating integer , rate_date integer )");
			try {
				FileInputStream fin = new FileInputStream("u.data");
				DataInputStream dis = new DataInputStream(fin);
				String data = "";
				String user;
				String movie;
				String rating;
				String rate_date;

				while ((data = dis.readLine()) != null) {
					StringTokenizer stoken = new StringTokenizer(data, "\t");
					user = stoken.nextToken();
					movie = stoken.nextToken();
					rating = stoken.nextToken();
					rate_date = stoken.nextToken();

					// System.out.println("user ::: "+user);
					// System.out.println("movie ::: "+movie);
					// System.out.println("rating ::: "+rating);
					// System.out.println("date ::: "+rate_date);
					// System.out.println("///////////// ");
					String query = "insert into prs values(" + user + "," + movie + "," + rating + "," + rate_date + ")";
					System.out.println("query is ::: " + query);
					s.execute(query); // create a table
				}

			} catch (FileNotFoundException e) {
				System.out.println("Error with reading test Netflix File: " + e);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readGenres() {
		try {
			// create a table
			s.execute("CREATE TABLE movie ( movieid integer , moviename char(100) , releasedate char(100) , imdblink char(200) , unknown integer , action integer , adventure integer , animation integer , children integer , comedy integer , crime integer , documentary integer , drama integer , fantasy integer , filmnoir integer , horror integer , musical integer , mystery integer , romance integer , scifi integer , thriller integer , war integer , western integer, rating integer, prediction integer)");
			try {
				FileInputStream fin = new FileInputStream("u.item");
				DataInputStream dis = new DataInputStream(fin);
				String data = "";
				String movieid, moviename, releasedate, videorelease, imdblink, unknown, action, adventure, animation, children, comedy, crime, documentary, drama, fantasy, filmnoir, horror, musical, mystery, romance, scifi, thriller, war, western;

				while ((data = dis.readLine()) != null) {
					StringTokenizer stoken = new StringTokenizer(data, "|");

					movieid = stoken.nextToken();
					moviename = stoken.nextToken();
					moviename = moviename.replaceAll("'", "");
					releasedate = stoken.nextToken();
					releasedate = releasedate.replaceAll("'", "");
					// videorelease = stoken.nextToken();
					imdblink = stoken.nextToken();
					imdblink = imdblink.replaceAll("'", "");
					unknown = stoken.nextToken();
					action = stoken.nextToken();
					adventure = stoken.nextToken();
					animation = stoken.nextToken();
					children = stoken.nextToken();
					comedy = stoken.nextToken();
					crime = stoken.nextToken();
					documentary = stoken.nextToken();
					drama = stoken.nextToken();
					fantasy = stoken.nextToken();
					filmnoir = stoken.nextToken();
					horror = stoken.nextToken();
					musical = stoken.nextToken();
					mystery = stoken.nextToken();
					romance = stoken.nextToken();
					scifi = stoken.nextToken();
					thriller = stoken.nextToken();
					war = stoken.nextToken();
					western = stoken.nextToken();

					System.out.println("movieid ::: " + movieid);
					System.out.println("movie ::: " + moviename);
					System.out.println("date ::: " + releasedate);
					System.out.println("imdblink ::: " + imdblink);
					System.out.println("unknown ::: " + unknown);
					System.out.println("action ::: " + action);
					System.out.println("adventure ::: " + adventure);
					System.out.println("///////////// ");
					String query = "insert into movie values(" + movieid + ",'" + moviename + "','" + releasedate + "','" + imdblink + "'," + unknown + "," + action + "," + adventure + "," + animation + "," + children + "," + comedy + "," + crime + "," + documentary + "," + drama + "," + fantasy + "," + filmnoir + "," + horror + "," + musical + "," + mystery + "," + romance + "," + scifi + ","
							+ thriller + "," + war + "," + western + ", 99, 99)";
					System.out.println("query is ::: " + query);
					s.execute(query); // create a table
				}
				System.out.println("its done >>><<<");
			} catch (FileNotFoundException e) {
				System.out.println("Error with reading test Netflix File: " + e);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}