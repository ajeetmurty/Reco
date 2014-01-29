package ajeetmurty.reco.movee.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import ajeetmurty.reco.movee.objs.PRSTable;

@SuppressWarnings("serial")
public class recommendationsList extends JPanel implements ActionListener {
	private GridBagLayout gbLayout = null;
	private GridBagConstraints gbcConstraints = null;
	private JComboBox jCombo;
	private PRSTable tblMovies;
	private JSplitPane jsp;
	private recomTopPanel recomTop;
	private recomBottomPanel recomBottom;

	public recommendationsList() {
		buildUI();
	}

	public void buildUI() {
		gbLayout = new GridBagLayout();
		gbcConstraints = new GridBagConstraints();
		this.setLayout(gbLayout);

		recomTop = new recomTopPanel();
		GUIManager.registerRecomTopPanel(recomTop);
		recomBottom = new recomBottomPanel();
		GUIManager.registerRecomBottomPanel(recomBottom);

		jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, recomTop, recomBottom);
		jsp.setDividerLocation(GUIManager.getScreenHeight() / 3);
		jsp.setContinuousLayout(true);
		jsp.setOneTouchExpandable(false);
		setContainerAttributes(0, 1, 1, 1, GridBagConstraints.BOTH, 1.0, 1.0, GridBagConstraints.FIRST_LINE_START, jsp);
	}

	public void refreshTopPanel() {
		GUIManager.refreshTopPanel();
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

	public void actionPerformed(ActionEvent e) {
		// To change body of implemented methods use File | Settings | File
		// Templates.
	}
}
