package ajeetmurty.reco.movee.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;
import ajeetmurty.reco.movee.db.DBReader;
import ajeetmurty.reco.movee.objs.PRSTable;
import ajeetmurty.reco.movee.objs.movieObj;

public class recomTopPanel extends JPanel implements ActionListener {
	private GridBagLayout gbLayout = null;
	private GridBagConstraints gbcConstraints = null;
	private Connection conn;
	private JComboBox jCombo;
	private PRSTable tblMovies;
	private ArrayList alstMovies;
	private String currentGenre;

	public recomTopPanel() {
		buildUI();
	}

	public void buildUI() {
		gbLayout = new GridBagLayout();
		gbcConstraints = new GridBagConstraints();
		this.setLayout(gbLayout);

		JLabel jlbSelect = new JLabel("Select a Genre: ");
		setContainerAttributes(0, 0, 1, 1, GridBagConstraints.NONE, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbSelect);

		currentGenre = "Choose a category ...";
		jCombo = new JComboBox();
		jCombo.addItem("Choose a category ...");
		jCombo.addItem("ACTION");
		jCombo.addItem("ADVENTURE");
		jCombo.addItem("ANIMATION");
		jCombo.addItem("CHILDREN");
		jCombo.addItem("COMEDY");
		jCombo.addItem("CRIME");
		jCombo.addItem("DOCUMENTARY");
		jCombo.addItem("DRAMA");
		jCombo.addItem("FANTASY");
		jCombo.addItem("FILMNOIR");
		jCombo.addItem("HORROR");
		jCombo.addItem("MUSICAL");
		jCombo.addItem("ROMANCE");
		jCombo.addItem("SCIFI");
		jCombo.addItem("THRILLER");
		jCombo.addItem("WAR");
		jCombo.addItem("WESTERN");
		jCombo.addActionListener(this);
		setContainerAttributes(1, 0, 1, 1, GridBagConstraints.HORIZONTAL, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, jCombo);

		ArrayList tableHeader2 = new ArrayList();
		tableHeader2.add("<html><body><b>#</b></body></html>");
		tableHeader2.add("<html><body><b>Movie Name</b></body></html>");
		tableHeader2.add("<html><body><b>Genre</b></body></html>");
		tableHeader2.add("<html><body><b>Release Date</b></body></html>");
		tblMovies = new PRSTable(tableHeader2);
		// tblMovies.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblMovies.setFillsViewportHeight(true);
		tblMovies.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				System.out.println("this is the selection from table ::: " + tblMovies.getSelectedRow());
				populateInfoPanel(tblMovies.getSelectedRow());
			}
		});
		TableColumn col = tblMovies.getColumnModel().getColumn(0);
		col.setPreferredWidth(40);
		col = tblMovies.getColumnModel().getColumn(1);
		col.setPreferredWidth(505);
		col = tblMovies.getColumnModel().getColumn(2);
		col.setPreferredWidth(100);
		col = tblMovies.getColumnModel().getColumn(3);
		col.setPreferredWidth(100);

		JScrollPane jsp = new JScrollPane(tblMovies);
		jsp.setBorder(BorderFactory.createTitledBorder("Movie List"));
		setContainerAttributes(0, 1, 2, 1, GridBagConstraints.BOTH, 1.0, 1.0, GridBagConstraints.FIRST_LINE_START, jsp);
	}

	public void populateInfoPanel(int index) {
		if (alstMovies != null && index != -1) {
			movieObj obj = (movieObj) alstMovies.get(index);
			GUIManager.refreshRecommMovieInfo(obj);
		}
	}

	public void refreshMovieRecommendations() {
		if (currentGenre != null) {
			if (currentGenre.equalsIgnoreCase("Choose a category ...")) {
				tblMovies.clearTableRows();
			} else {
				alstMovies = DBReader.getRecommendations(currentGenre);
				tblMovies.clearTableRows();
				for (int i = 0; i < alstMovies.size(); i++) {
					movieObj objTemp = (movieObj) alstMovies.get(i);
					Object obj[] = new Object[4];
					obj[0] = Integer.valueOf(i);
					obj[1] = objTemp.getName();
					obj[2] = objTemp.getGenre();
					obj[3] = objTemp.getDate();
					tblMovies.addRowValue(obj);
					updateUI();
				}
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

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jCombo) {
			String strName = (String) jCombo.getSelectedItem();
			currentGenre = strName;
			refreshMovieRecommendations();
			updateUI();
		}
	}
}
