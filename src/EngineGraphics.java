import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class EngineGraphics extends JPanel {
	private Engine3D t;

	public EngineGraphics() {
		setBackground(Color.white);
		t = new Engine3D();
	}
	
	public EngineGraphics(ArrayList<Point3D> p) {
		t = new Engine3D(p);
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}

	

	public class PanelListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {

			repaint();
		}
	}

}
