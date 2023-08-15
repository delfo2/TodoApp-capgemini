package util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import model.Tasks;

public class TableRenderFactory extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	private Color prazo = new Color(0x974EC3);
	private Color deadPrazo = new Color(0x313866);
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
             boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel jlabel;
		jlabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		jlabel.setHorizontalAlignment(CENTER);
		
		TaskTableModel taskModel = (TaskTableModel) table.getModel();
		Tasks task = taskModel.getTasks().get(row);
		
		if(task.getDeadLine().after(new Date())) {
			jlabel.setBackground(prazo);
			jlabel.setForeground(new Color(0x000000));
		} else {
			jlabel.setBackground(deadPrazo);
			jlabel.setForeground(new Color(0xFFFFFF));
		}
		
		return jlabel;
	}
}
