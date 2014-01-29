package ajeetmurty.reco.movee.objs;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;

@SuppressWarnings("serial")
public class CustomTableCellRenderer extends DefaultTableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		String strPropType = table.getModel().getValueAt(row, 0).toString();

		super.setHorizontalAlignment(JLabel.CENTER);

		if (strPropType != null) {
			if (strPropType.equalsIgnoreCase("(S)")) {
				cell.setBackground(Color.CYAN);
			} else {
				cell.setBackground(Color.white);
			}
		}
		return cell;
	}

	public void setCellAlignment(int align) {

	}
}
