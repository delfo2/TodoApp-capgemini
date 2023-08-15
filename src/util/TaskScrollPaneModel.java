package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JScrollPane;

public class TaskScrollPaneModel extends JScrollPane {
	private static final long serialVersionUID = 1L;
	private Color backgroundColor;

    public TaskScrollPaneModel(Component view, Color backgroundColor) {
        super(view);
        this.backgroundColor = backgroundColor;
        setOpaque(true);
        getViewport().setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}