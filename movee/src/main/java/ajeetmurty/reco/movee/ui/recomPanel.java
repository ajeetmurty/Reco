package ajeetmurty.reco.movee.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import ajeetmurty.reco.movee.db.DBReader;

@SuppressWarnings("serial")
public class recomPanel extends JPanel implements ActionListener {
	private GridBagLayout gbLayout = null;
	private GridBagConstraints gbcConstraints = null;
	private Connection conn;
	private JComboBox jCombo;
	private JButton jbtCompute;
	private JSplitPane jsp;
	private recommendationsList recom;

	public recomPanel(Connection connTemp) {
		if (connTemp != null) {
			conn = connTemp;
			buildUI();
		}
	}

	public void buildUI() {
		gbLayout = new GridBagLayout();
		gbcConstraints = new GridBagConstraints();
		this.setLayout(gbLayout);

		jbtCompute = new JButton("Re-compute Recommendations", new ImageIcon("images/compute.gif"));
		setContainerAttributes(0, 0, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jbtCompute);
		jbtCompute.addActionListener(this);

		jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, null, null);
		jsp.setDividerLocation(200);
		jsp.setContinuousLayout(true);
		jsp.setOneTouchExpandable(false);
		setContainerAttributes(0, 1, 1, 1, GridBagConstraints.BOTH, 1.0, 1.0, GridBagConstraints.FIRST_LINE_START, jsp);

		recom = new recommendationsList();
		GUIManager.registerRecommendationsList(recom);
		showRecommendations();
	}

	public void showRecommendations() {
		if (DBReader.getMoviesRateCount() < 15) {
			noRecomScreen error = new noRecomScreen(DBReader.getMoviesRateCount());
			jsp.setTopComponent(error);
			System.out.println("total movies rated ::: " + DBReader.getMoviesRateCount());
		} else {
			GUIManager.refreshRecommendations();
			jsp.setTopComponent(recom);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtCompute) {
			int n = JOptionPane.showConfirmDialog(this, "Would you like to Recompute your recommendations? This process might take a few minutes", "Recompute Recommendations", JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				GUIManager.recomputeRecommendations();
				GUIManager.refreshRecommendations();
				jsp.setTopComponent(recom);
			} else if (n == JOptionPane.NO_OPTION) {
			}
		}
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
