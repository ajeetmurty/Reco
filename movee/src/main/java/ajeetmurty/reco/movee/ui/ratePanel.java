package ajeetmurty.reco.movee.ui;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableColumn;
import ajeetmurty.reco.movee.db.DBReader;
import ajeetmurty.reco.movee.objs.PRSTable;
import ajeetmurty.reco.movee.objs.movieObj;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.util.ArrayList;

public class ratePanel extends JPanel implements ActionListener {
    private GridBagLayout gbLayout = null;
    private GridBagConstraints gbcConstraints = null;
    private Connection conn;
    private JComboBox jCombo;
    private PRSTable tblMovies;
    private ArrayList alstMovies;
    private String currentCategory;

    public ratePanel(Connection connTemp){
        if(connTemp != null){
            conn = connTemp;
            alstMovies = new ArrayList();
            buildUI();
        }
    }

    public void buildUI(){
        gbLayout = new GridBagLayout();
        gbcConstraints = new GridBagConstraints();
        this.setLayout(gbLayout);

        JLabel jlbSelect = new JLabel("Select a Genre: ");
        setContainerAttributes(0,0,1,1, GridBagConstraints.NONE, 0.0,0.0,GridBagConstraints.FIRST_LINE_START, jlbSelect);

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
        setContainerAttributes(1,0,1,1, GridBagConstraints.HORIZONTAL, 0.0,0.0,GridBagConstraints.FIRST_LINE_START, jCombo);

        ArrayList tableHeader2 = new ArrayList();
        tableHeader2.add("<html><body><b>#</b></body></html>");
        tableHeader2.add("<html><body><b>Movie Name</b></body></html>");
        tableHeader2.add("<html><body><b>Genre</b></body></html>");
        tableHeader2.add("<html><body><b>Release Date</b></body></html>");
        tblMovies = new PRSTable(tableHeader2);
        //tblMovies.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblMovies.setFillsViewportHeight(true);
        tblMovies.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                System.out.println("this is the selection from table ::: "+tblMovies.getSelectedRow());
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
        setContainerAttributes(0,1,2,1, GridBagConstraints.BOTH, 1.0,1.0,GridBagConstraints.FIRST_LINE_START, jsp);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jCombo){
            String strName = (String) jCombo.getSelectedItem();
            if(!strName.equalsIgnoreCase("Choose a category ...")){
                currentCategory = strName;
                alstMovies = DBReader.getMovies(currentCategory);
                manageTable(alstMovies);
            }else{
                manageTable(alstMovies);
            }
        }
    }

    public void refreshTable(){
        if(currentCategory != null){
            alstMovies = DBReader.getMovies(currentCategory);
            manageTable(alstMovies);
        }
    }

    public void populateInfoPanel(int x){
        if(alstMovies != null && x != -1){
            movieObj obj = (movieObj) alstMovies.get(x);
            GUIManager.setMovieInfo(obj);
        }
    }

    public void manageTable(ArrayList alstMovies){
        if(alstMovies != null){
            tblMovies.clearTableRows();
            for(int i=0; i<alstMovies.size(); i++){
                movieObj objTemp = (movieObj) alstMovies.get(i);
                Object obj[] = new Object[4];
                obj[0]= Integer.valueOf(i);
                obj[1]= objTemp.getName();
                obj[2]= objTemp.getGenre();
                obj[3]= objTemp.getDate();
                tblMovies.addRowValue(obj);
                updateUI();
            }
        }else if(alstMovies.size() == 0){
            tblMovies.clearTableRows();
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
