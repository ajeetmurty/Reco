package ajeetmurty.reco.movee;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import ajeetmurty.reco.movee.db.ConvertDataToMDB;
import ajeetmurty.reco.movee.db.DBConnectionManager;
import ajeetmurty.reco.movee.db.DBReader;
import ajeetmurty.reco.movee.db.DBWriter;
import ajeetmurty.reco.movee.ui.GUIManager;
import ajeetmurty.reco.movee.ui.mainScreen;

public class PRSmain {
	private DBConnectionManager connMgr;
	private ConvertDataToMDB converter;
	private mainScreen screen;
	private Statement s;
	private Connection conn;

	public PRSmain() {
		// use the logic below to establish connection with text files and
		// transfer data to database
		connMgr = new DBConnectionManager();
		// establish connection with the MDB file
		connMgr.establishConnection();
		// if connection is good, then start mOvEEEEE
		if (connMgr.testConnection()) {

			// //databse readers and writers intialized
			conn = connMgr.getConnectionObject();
			try {
				s = conn.createStatement();
			} catch (SQLException e) {
				System.out.println("problem with creating connection in PRSmain class");
				e.printStackTrace(); // To change body of catch statement use
										// File | Settings | File Templates.
			}

			// used for data conversion - feeding text files into database
			// converter = new DBUtils.ReadDataset.ConvertDataToMDB(conn, s);

			DBReader.setConnection(conn, s);
			DBWriter.setConnection(conn, s);

			// //user interface initialization
			screen = new mainScreen(connMgr.getConnectionObject());
			GUIManager.registerMainScreen(screen);

			screen.setVisible(true);
			screen.setLocationRelativeTo(null);
			// screen.setAlwaysOnTop(true);
		}
	}

	public static void main(String args[]) {
		new PRSmain();
	}
}
