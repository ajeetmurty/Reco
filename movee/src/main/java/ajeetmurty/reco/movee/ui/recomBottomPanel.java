package ajeetmurty.reco.movee.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import ajeetmurty.reco.movee.objs.movieObj;

public class recomBottomPanel extends JPanel implements ActionListener {
	private GridBagLayout gbLayout = null;
	private GridBagConstraints gbcConstraints = null;
	private Connection conn;
	private JLabel jlbname, jlbRelease, jlbIMDB, jlbRating, jlbGen;
	private JToolBar jtb;
	private int rating;
	private JRadioButton birdButton, catButton, dogButton, rabbitButton, pigButton;
	private movieObj movie;

	public recomBottomPanel() {
		buildUI();
	}

	public void buildUI() {
		gbLayout = new GridBagLayout();
		gbcConstraints = new GridBagConstraints();
		this.setLayout(gbLayout);
		this.setBorder(BorderFactory.createTitledBorder("Movie Info"));

		JLabel jlbTitle = new JLabel("Title : ");
		setContainerAttributes(0, 0, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbTitle);
		jlbname = new JLabel("-");
		setContainerAttributes(1, 0, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbname);

		JLabel jlbDate = new JLabel("Release Date : ");
		setContainerAttributes(0, 1, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbDate);
		jlbRelease = new JLabel("-");
		setContainerAttributes(1, 1, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbRelease);

		JLabel jlbGenre = new JLabel("Genre : ");
		setContainerAttributes(0, 2, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbGenre);
		jlbGen = new JLabel("-");
		setContainerAttributes(1, 2, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbGen);

		JLabel jlbInfo = new JLabel("Info : ");
		setContainerAttributes(0, 3, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbInfo);
		jlbIMDB = new JLabel("-");
		setContainerAttributes(1, 3, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbIMDB);
		MouseAdapter ml_label = new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				try {
					final String launch = "cmd /c start " + movie.getLink();
					(Runtime.getRuntime()).exec(launch);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Error in launching Web-browser");
				}
			}
		};
		jlbIMDB.addMouseListener(ml_label);

		JLabel jlbRate = new JLabel("Recommended rating : ");
		setContainerAttributes(0, 4, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbRate);
		jlbRating = new JLabel("-");
		setContainerAttributes(1, 4, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbRating);

	}

	public void setMovieInfo(movieObj obj) {
		if (obj != null) {
			movie = obj;
			jlbname.setText(obj.getName());
			jlbRelease.setText(obj.getDate());
			jlbIMDB.setText("<html><body><a href = \"" + obj.getLink() + "\">" + "IMDB" + "</a>");
			jlbGen.setText(obj.getGenre());
			jlbRating.setText("" + obj.getPredcition());
			updateUI();
		}
	}

	public void actionPerformed(ActionEvent e) {
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
