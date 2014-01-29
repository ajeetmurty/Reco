package ajeetmurty.reco.movee.ui;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;

@SuppressWarnings("serial")
public class mainScreen extends JFrame implements ActionListener {
	private JPanel jpMain;
	private GridBagLayout gbLayout = null;
	private GridBagConstraints gbcConstraints = null;
	private JButton jbtNew;
	private Connection conn;
	private JTabbedPane jtabOptions;

	public mainScreen(Connection conTemp) {
		if (conTemp != null) {
			conn = conTemp;
			buildUI();
		}
	}

	public void buildUI() {
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("moVee");
		jpMain = new JPanel();
		jpMain.setVisible(true);
		jpMain.setBorder(BorderFactory.createTitledBorder(""));
		gbLayout = new GridBagLayout();
		gbcConstraints = new GridBagConstraints();
		jpMain.setLayout(gbLayout);

		// jbtNew = new JButton("new moveee");
		// setContainerAttributes(0,0,1,1, GridBagConstraints.BOTH,
		// 1.0,1.0,GridBagConstraints.FIRST_LINE_START, jbtNew);

		ImageIcon imgMoveeee = new ImageIcon("images/movie.gif");
		this.setIconImage(imgMoveeee.getImage());

		JLabel l = new JLabel(new ImageIcon("images/reel.gif"));
		setContainerAttributes(0, 0, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, l);

		jtabOptions = new JTabbedPane();

		ImageIcon img02 = new ImageIcon("images/img01.gif");
		RateMainPanel ratePnl = new RateMainPanel(conn);
		GUIManager.registerRateMainPanel(ratePnl);
		jtabOptions.addTab("Browse & Rate Movies", img02, ratePnl, "Rate movies you have already seen");

		ImageIcon img01 = new ImageIcon("images/img03.gif");
		recomPanel recom = new recomPanel(conn);
		GUIManager.registerRecomPanel(recom);
		jtabOptions.addTab("My Recommendations", img01, recom, "movies recommended based on your preferences");

		ImageIcon img03 = new ImageIcon("images/help.gif");
		HelpUI help = new HelpUI(conn);
		GUIManager.registerHelpUI(help);
		jtabOptions.addTab("Help !!!", img03, help, "Need help?? Come on in");

		jtabOptions.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JTabbedPane pane = (JTabbedPane) e.getSource();
				if (pane.getSelectedIndex() == 0) {

				} else if (pane.getSelectedIndex() == 1) {

				} else if (pane.getSelectedIndex() == 2) {

				}
			}
		});

		setContainerAttributes(0, 1, 1, 1, GridBagConstraints.BOTH, 1.0, 1.0, GridBagConstraints.FIRST_LINE_START, jtabOptions);

		jpMain.setPreferredSize(new Dimension(800, 700));
		add(jpMain);
		pack();
	}

	public void actionPerformed(ActionEvent e) {
		// To change body of implemented methods use File | Settings | File
		// Templates.
	}

	public void setDefaulLookandFeel() {
		try {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}

		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this.getContentPane());
	}

	protected void setContainerAttributes(int xpos, int ypos, int width, int height, int fill, double wtx, double wty, int ancr, JComponent jcmp) {
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
			jpMain.add(jcmp);
		}
	}
}
