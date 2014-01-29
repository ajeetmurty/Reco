package ajeetmurty.reco.movee.objs;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class PRSTable extends JTable {
	private CustomTableModel customDataModel;

	public PRSTable() {
		buildUI();
	}

	public PRSTable(ArrayList headers) {
		buildUI();
		addTableHeadres(headers);
	}

	private void buildUI() {
		customDataModel = new CustomTableModel();
		setModel(customDataModel);
		setDefaultRenderer(Object.class, new CustomTableCellRenderer());
		packColumns(2);
		packRows(this, 0);
		setTableAttributes();
		updateUI();
	}

	/**
	 * @param headers
	 */
	private void addTableHeadres(ArrayList headers) {
		String strHead = "";

		if (headers != null) {
			for (int i = 0; i < headers.size(); i++) {
				strHead = (String) headers.get(i);
				if (strHead != null) {
					customDataModel.addColumnToTable(strHead);
				}
			}
		}
	}

	public void addTableHeader(String strHead) {
		if (strHead != null) {
			customDataModel.addColumnToTable(strHead);
		}
	}

	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}

	/**
	 * Set default set of attributes to the table
	 */
	private void setTableAttributes() {
		// setShowHorizontalLines( false );
		setRowSelectionAllowed(true);
		setColumnSelectionAllowed(false);

		// Configure some of JTable's paramters
		setShowHorizontalLines(true);

		// Enable cell selection
		// setColumnSelectionAllowed(true);
		// setRowSelectionAllowed(true);

		// setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// setAutoCreateColumnsFromModel(true);
		// setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
	}

	/**
	 * Clears all date in the table
	 */
	public void clearTableRows() {
		if (getModel() instanceof DefaultTableModel) {
			((DefaultTableModel) getModel()).setNumRows(0);
		} else {
			int cols = getModel().getColumnCount();
			setModel(new DefaultTableModel(0, cols));
		}
	}

	private int getPreferredRowHeight(JTable table, int rowIndex, int margin) {
		// Get the current default height for all rows
		int height = table.getRowHeight();

		// Determine highest cell in the row
		for (int c = 0; c < table.getColumnCount(); c++) {
			TableCellRenderer renderer = table.getCellRenderer(rowIndex, c);
			Component comp = table.prepareRenderer(renderer, rowIndex, c);
			int h = comp.getPreferredSize().height + 2 * margin;
			height = Math.max(height, h);
		}
		return height;
	}

	private void packRows(JTable table, int margin) {
		packRows(table, 0, table.getRowCount(), margin);
	}

	private void packRows(JTable table, int start, int end, int margin) {
		for (int r = 0; r < table.getRowCount(); r++) {
			// Get the preferred height
			int h = getPreferredRowHeight(table, r, margin);

			// Now set the row height using the preferred height
			if (table.getRowHeight(r) != h) {
				table.setRowHeight(r, h);
			}
		}
	}

	private void packColumns(int margin) {
		for (int c = 0; c < getColumnCount(); c++) {
			packColumn(c, 2);
		}
	}

	/**
	 * Sets the preferred width of the visible column specified by vColIndex.
	 * The column will be just wide enough to show the column head and the
	 * widest cell in the column. margin pixels are added to the left and right
	 * (resulting in an additional width of 2*margin pixels).
	 */
	private void packColumn(int vColIndex, int margin) {
		TableModel model = getModel();
		DefaultTableColumnModel colModel = (DefaultTableColumnModel) getColumnModel();
		TableColumn col = colModel.getColumn(vColIndex);
		int width = 0;

		// Get width of column header
		TableCellRenderer renderer = col.getHeaderRenderer();
		if (renderer == null) {
			renderer = getTableHeader().getDefaultRenderer();
		}
		Component comp = renderer.getTableCellRendererComponent(this, col.getHeaderValue(), false, false, 0, 0);
		width = comp.getPreferredSize().width;

		// Get maximum width of column data
		for (int r = 0; r < getRowCount(); r++) {
			renderer = getCellRenderer(r, vColIndex);
			comp = renderer.getTableCellRendererComponent(this, getValueAt(r, vColIndex), false, false, r, vColIndex);
			width = Math.max(width, comp.getPreferredSize().width);
		}

		// Add margin
		width += 2 * margin;

		// Set the width
		col.setPreferredWidth(width);
	}

	/**
	 * Regardless of sort order (ascending or descending), null values always
	 * appear last.
	 * 
	 * @param model
	 *            table model
	 * @param colIndex
	 *            colIndex specifies a column in model.
	 * @param ascending
	 *            true if ascending order
	 */
	/*
	 * private void sortColumn(DefaultTableModel model, int colIndex, boolean
	 * ascending) { Vector data = model.getDataVector(); Object[] colData = new
	 * Object[model.getRowCount()];
	 * 
	 * // Copy the column data in an array for (int i = 0; i < colData.length;
	 * i++) { colData[i] = ((Vector) data.get(i)).get(colIndex); }
	 * 
	 * // Sort the array of column data Arrays.sort(colData, new
	 * ColumnSorter(ascending));
	 * 
	 * // Copy the sorted values back into the table model for (int i = 0; i <
	 * colData.length; i++) { ((Vector) data.get(i)).set(colIndex, colData[i]);
	 * } model.fireTableStructureChanged(); }
	 */

	public void sortAllRowsBy(DefaultTableModel model, int colIndex, boolean ascending) {
		Vector data = model.getDataVector();
		Collections.sort(data, new ColumnSorter(colIndex, ascending));
		model.fireTableStructureChanged();
	}

	public void addRowValue(Object[] row) {
		setAutoCreateColumnsFromModel(false);
		customDataModel.addRow(row);
		int mColIndex = 0;
		boolean ascending = true;
		sortAllRowsBy(customDataModel, mColIndex, ascending);
		updateUI();
	}

	public ArrayList getAddedProperties() {
		ArrayList alst = new ArrayList();
		String strNm = "";

		int intRowCount = customDataModel.getRowCount();
		for (int i = 0; i < intRowCount; i++) {
			strNm = (String) customDataModel.getValueAt(i, 1);
			if (strNm != null) {
				alst.add(strNm);
			}
		}
		return alst;
	}

	public ArrayList deleteSelectRows() {
		String strProName = "";
		int[] selectedIx = getSelectedRows();
		ArrayList alstDel = new ArrayList();
		for (int i = selectedIx.length; i > 0; i--) {
			strProName = (String) customDataModel.getValueAt(selectedIx[i - 1], 1);
			customDataModel.removeRow(selectedIx[i - 1]);
			if (strProName != null) {
				alstDel.add(strProName);
			}
		}
		return alstDel;
	}

	public ArrayList deleteSelectedRelations() {
		String strFrom = "";
		String strTo = "";
		int[] selectedIx = getSelectedRows();
		ArrayList alstDel = new ArrayList();

		for (int i = selectedIx.length; i > 0; i--) {
			strFrom = (String) customDataModel.getValueAt(selectedIx[i - 1], 0);
			strTo = (String) customDataModel.getValueAt(selectedIx[i - 1], 1);

			customDataModel.removeRow(selectedIx[i - 1]);

			if (strFrom != null && strTo != null) {
				alstDel.add(new String[] { strFrom, strTo });
			}
		}
		return alstDel;
	}

	public String getSelectedRow(int column) {
		String strProName = "";
		int selectedIx = getSelectedRow();
		if (selectedIx != -1)
			strProName = (String) customDataModel.getValueAt(selectedIx, column);
		if (strProName != null) {
			return strProName;
		}

		return null;

	}

	public void setCellAlignment(int align) {

	}

	public void updateColOfSelectedRow(String value) {
		if (value != null) {
			customDataModel.setValueAt(value, getSelectedRow(), 4);
		}
	}
}
