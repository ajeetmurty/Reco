package ajeetmurty.reco.movee.ui;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class waitScreen extends JPanel {
	private GridBagLayout gbLayout = null;
	private GridBagConstraints gbcConstraints = null;

	public waitScreen() {
		gbLayout = new GridBagLayout();
		gbcConstraints = new GridBagConstraints();
		this.setLayout(gbLayout);

		ImageIcon imgViz = new ImageIcon("images/indicator.gif");
		JLabel jlbWait = new JLabel(imgViz);
		setContainerAttributes(0, 0, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.CENTER, jlbWait);

		JLabel jlbMssg = new JLabel("<html><body><font color= \"#0066FF\">Please Wait, Calculating Your Recommendations ... </font></body></html>");
		setContainerAttributes(0, 1, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.CENTER, jlbMssg);

		updateUI();
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
