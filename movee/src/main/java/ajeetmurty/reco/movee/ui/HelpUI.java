package ajeetmurty.reco.movee.ui;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class HelpUI extends JPanel {
	private GridBagLayout gbLayout = null;
	private GridBagConstraints gbcConstraints = null;
	private Connection conn;
	private JComboBox jCombo;

	public HelpUI(Connection connTemp) {
		if (connTemp != null) {
			conn = connTemp;
			buildUI();
		}
	}

	public void buildUI() {
		gbLayout = new GridBagLayout();
		gbcConstraints = new GridBagConstraints();
		this.setLayout(gbLayout);

		JLabel jlbRate = new JLabel("<html><body><font color = red> Q: I get a error message asking me to rate more movie?</font></body></html>");
		setContainerAttributes(0, 0, 1, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbRate);

		JTextPane jlbRateAnswer = answerPanel("A: To provide the best possible recommendations, it is important that moVee understands your tastes. For this happen, you haveto rate more than 15 movies, as this will enable moVee to build a better profile of your intrests, and suggest recommendations accordingly.");
		setContainerAttributes(0, 1, 1, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbRateAnswer);

		JLabel jlbError = new JLabel("<html><body><font color = red> Q: moVee just hangs up when u click on 'recompute recommendations?'</font></body></html>");
		setContainerAttributes(0, 2, 1, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbError);

		JTextPane jtaError = answerPanel("A: moVee's recommendation engine has to compute your prefernces using a database of over 1500+ movies. the process can take upto 30-40 minutes. Please be patient!!!");
		setContainerAttributes(0, 3, 1, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0, GridBagConstraints.FIRST_LINE_START, jtaError);

		JLabel jlbWhy = new JLabel("<html><body><font color = red> Q: Why do i need to re-compute my recommendations?</font></body></html>");
		setContainerAttributes(0, 4, 1, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0, GridBagConstraints.FIRST_LINE_START, jlbWhy);

		JTextPane jtaWhy = answerPanel("A: When u rate movies or change pre-existing ratings, these new ratings impact the way recommendations are calcualted. hence, from time to tim, it is important for you to recompute your recommendations to reflect your choices better.");
		setContainerAttributes(0, 5, 1, 1, GridBagConstraints.HORIZONTAL, 1.0, 0.0, GridBagConstraints.FIRST_LINE_START, jtaWhy);

	}

	public JTextPane answerPanel(String explanation) {
		StyledDocument doc;
		int s = 15;
		JTextPane jtaPool = new JTextPane();
		jtaPool.setBorder(null);
		jtaPool.setBackground(null);
		jtaPool.setEditable(false);
		doc = jtaPool.getStyledDocument();
		MutableAttributeSet standard = new SimpleAttributeSet();
		StyleConstants.setAlignment(standard, StyleConstants.ALIGN_JUSTIFIED);
		StyleConstants.setForeground(standard, Color.BLUE);
		StyleConstants.setFontSize(standard, s);
		StyleConstants.setBold(standard, true);
		StyleConstants.setItalic(standard, true);
		doc.setParagraphAttributes(0, 0, standard, true);
		jtaPool.setText("" + explanation + "");
		return jtaPool;
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
