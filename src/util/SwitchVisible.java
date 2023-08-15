package util;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SwitchVisible {
	public static void change(JScrollPane element, boolean view) {
		if(element != null) {
			element.setVisible(view);			
		}
	}

	public static void change(JTable element, boolean view) {
		if(element != null) {
			element.setVisible(view);			
		}
	}

	public static void change(JPanel element, boolean view) {
		if(element != null) {
			element.setVisible(view);			
		}
	}
	
}
