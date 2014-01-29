package ajeetmurty.reco.movee.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class RateMainPanel extends JPanel {
	private GridBagLayout gbLayout = null;
	private GridBagConstraints gbcConstraints = null;
	private Connection conn;
	private JSplitPane jspSplit;
	private MovieInfoPanel info;
	private ratePanel rate;

	public RateMainPanel(Connection con) {
		conn = con;
		buildUI();
	}

	public void buildUI() {
		gbLayout = new GridBagLayout();
		gbcConstraints = new GridBagConstraints();
		this.setLayout(gbLayout);

		rate = new ratePanel(conn);
		GUIManager.registerRatePanel(rate);

		info = new MovieInfoPanel(conn);
		GUIManager.registerMovieInfo(info);

		jspSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, rate, info);
		jspSplit.setDividerLocation(GUIManager.getScreenHeight() / 3);
		jspSplit.setContinuousLayout(true);
		jspSplit.setOneTouchExpandable(true);
		setContainerAttributes(0, 0, 1, 1, GridBagConstraints.BOTH, 1.0, 1.0, GridBagConstraints.FIRST_LINE_START, jspSplit);
	}

	public void setContainerAttributes(int xpos, int ypos, int width, int height, int fill, double wtx, double wty, int ancr, JComponent jcmp) {
		if (jcmp != null) {
			gbcConstraints.insets = new Insets(5, 5, 5, 5);
			gbcConstraints.gridx = xpos;
			gbcConstraints.gridy = ypos;
			gbcConstraints.gridwidth = width;
			gbcConstraints.gridheight = height;
			gbcConstraints.fill = fill;
			gbcConstraints.weightx = wtx;
			gbcConstraints.weighty = wty;
			gbcConstraints.anchor = ancr;
			gbLayout.setConstraints(jcmp, gbcConstraints);
			add(jcmp);
		}
	}
}
