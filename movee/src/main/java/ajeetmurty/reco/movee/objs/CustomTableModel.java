package ajeetmurty.reco.movee.objs;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class CustomTableModel extends DefaultTableModel {
	public CustomTableModel() {
		super();
	}

	public void addColumnToTable(String ColumnName) {
		if (ColumnName != null)
			addColumn(ColumnName);
	}

	public boolean isCellEditable(int row, int column) {
		return true;
	}
}
