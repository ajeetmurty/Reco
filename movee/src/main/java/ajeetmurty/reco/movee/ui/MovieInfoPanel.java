package ajeetmurty.reco.movee.ui;

import javax.swing.*;
import ajeetmurty.reco.movee.db.DBWriter;
import ajeetmurty.reco.movee.objs.movieObj;
import java.awt.event.*;
import java.awt.*;
import java.sql.Connection;
import java.io.IOException;

public class MovieInfoPanel extends JPanel implements ActionListener {
	private GridBagLayout gbLayout = null;
	private GridBagConstraints gbcConstraints = null;
	private Connection conn;
	private JLabel jlbname, jlbRelease, jlbIMDB, jlbRating, jlbGen;
	private JToolBar jtb;
	private int rating;
	private JRadioButton birdButton, catButton, dogButton, rabbitButton, pigButton;
	private movieObj movie;

	public MovieInfoPanel(Connection conTemp) {
		if (conTemp != null) {
			conn = conTemp;
			buildUI();
		}
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
					String tmpString = movie.getName().replace(' ', '%');
					// final String launch = "cmd /c start " +
					// "http://imdb.com/find?s=all&q=" +movie.getName();
					final String launch = "cmd /c start " + movie.getLink();
					System.out.println("url::: " + launch);
					(Runtime.getRuntime()).exec(launch);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Error in launching Web-browser");
				}
			}
		};
		jlbIMDB.addMouseListener(ml_label);

		JLabel jlbRate = new JLabel("You rated this movie : ");
		setContainerAttributes(0, 4, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbRate);
		jlbRating = new JLabel("-");
		setContainerAttributes(1, 4, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbRating);

		JLabel jlbNewRate = new JLabel("New Rating: ");
		setContainerAttributes(0, 5, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbNewRate);

		// Create the radio buttons.
		birdButton = new JRadioButton("1");
		birdButton.setMnemonic(KeyEvent.VK_B);
		birdButton.setActionCommand("1");

		catButton = new JRadioButton("2");
		catButton.setMnemonic(KeyEvent.VK_C);
		catButton.setActionCommand("2");

		dogButton = new JRadioButton("3");
		dogButton.setMnemonic(KeyEvent.VK_D);
		dogButton.setActionCommand("3");
		dogButton.setSelected(true);

		rabbitButton = new JRadioButton("4");
		rabbitButton.setMnemonic(KeyEvent.VK_R);
		rabbitButton.setActionCommand("4");

		pigButton = new JRadioButton("5");
		pigButton.setMnemonic(KeyEvent.VK_P);
		pigButton.setActionCommand("5");

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(birdButton);
		group.add(catButton);
		group.add(dogButton);
		group.add(rabbitButton);
		group.add(pigButton);

		// Register a listener for the radio buttons.
		birdButton.addActionListener(this);
		catButton.addActionListener(this);
		dogButton.addActionListener(this);
		rabbitButton.addActionListener(this);
		pigButton.addActionListener(this);

		rating = 3;
		jtb = new JToolBar();
		jtb.add(birdButton);
		jtb.add(catButton);
		jtb.add(dogButton);
		jtb.add(rabbitButton);
		jtb.add(pigButton);
		ImageIcon imgViz = new ImageIcon("images/ok.gif");
		Action actViz = new AbstractAction("", imgViz) {
			public void actionPerformed(ActionEvent e) {
				saveRating(rating);
				GUIManager.refreshInfo();
				JOptionPane.showMessageDialog(null, "New rating saved successfully !!!");
			}
		};

		jtb.setVisible(false);
		jtb.add(actViz);
		jtb.setBackground(null);
		jtb.setFloatable(false);
		setContainerAttributes(1, 5, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jtb);
	}

	public void saveRating(int rateNo) {
		if (movie != null) {
			DBWriter.saveRating(rating, movie);
		}
	}

	public void setMovieInfo(movieObj obj) {
		if (obj != null) {
			movie = obj;
			jtb.setVisible(true);
			jlbname.setText(obj.getName());
			jlbRelease.setText(obj.getDate());
			jlbIMDB.setText("<html><body><a href = \"" + obj.getLink() + "\">" + "IMDB" + "</a>");
			jlbGen.setText(obj.getGenre());
			String rate = "";
			if (obj.getRating() == 99) {
				rate = "Not Rated";
			} else {
				rate = "" + obj.getRating();
			}
			jlbRating.setText(rate);
			updateUI();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == birdButton) {
			rating = 1;
		} else if (e.getSource() == catButton) {
			rating = 2;
		} else if (e.getSource() == dogButton) {
			rating = 3;
		} else if (e.getSource() == rabbitButton) {
			rating = 4;
		} else if (e.getSource() == pigButton) {
			rating = 5;
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
