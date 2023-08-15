package util;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import view.MainScreen;


public class TableRenderFactoryButton extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;
	private String iconName;
	
	public TableRenderFactoryButton (String iconName) {
		this.iconName = iconName;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
             boolean isSelected, boolean hasFocus, int row, int column) {
		JLabel jlabel;
		jlabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		jlabel.setHorizontalAlignment(CENTER);
		
		ImageIcon newIcon = new ImageIcon(MainScreen.class.getResource("/view/" + this.iconName + ".png"));
		Image tempImg = newIcon.getImage();
		Image icon = tempImg.getScaledInstance(13, 13, java.awt.Image.SCALE_SMOOTH);
		jlabel.setIcon(new ImageIcon(icon));
		
		return jlabel;
	}
}
