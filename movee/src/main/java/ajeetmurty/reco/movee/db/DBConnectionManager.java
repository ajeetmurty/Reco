package ajeetmurty.reco.movee.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionManager {
	public static Connection con;
	public static Statement s;

	public DBConnectionManager() {
	}

	public static Connection getConnectionObject() {
		return con;
	}

	public static void establishConnection() {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// set this to a MS Access DB you have on your machine
			String filename = "data/PRS.mdb";
			String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
			database += filename.trim() + ";DriverID=22;READONLY=false}";
			// now we can get the connection from the DriverManager
			con = DriverManager.getConnection(database, "", "");
			s = con.createStatement();
			// Get the metadata regarding this connection's database
		} catch (Exception e) {
			System.out.println("Error with establishing connection: " + e);
		}
	}

	public static boolean testConnection() {
		if (con != null) {
			try {
				// create a table
				s.execute("create table TEST ( column_name integer )");
				// insert some data into the table
				s.execute("insert into TEST values(1)");
				// select the data from the table
				s.execute("select column_name from TEST");
				// get any ResultSet that came from our query
				ResultSet rs = s.getResultSet();
				// if rs == null, then there is no ResultSet to view this will
				// step through our data row-by-row
				if (rs != null)
					while (rs.next()) {
						/*
						 * the next line will get the first column in our
						 * current row's ResultSet as a String ( getString(
						 * columnNumber) ) and output it to the screen
						 */
						// rs.getString(1);
						// System.out.println("Test Data from test DB: " +
						// rs.getString(1) );
					}
				s.execute("drop table TEST");
				return true;
			}

			catch (Exception e) {
				System.out.println("Error with testing connection: " + e);
				return false;
			}
		}
		return false;
	}

	public static void closeConenction() {
		try {
			con.close(); // close the Connection to let the database know we're
							// done with it
		} catch (SQLException e) {
			System.out.println("Error with closing connection: " + e);
		}
	}
}
