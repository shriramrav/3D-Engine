import java.awt.Color;

import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class ViewModel extends JPanel {
	private Object3D t;

	int ctr = 0;
	
	public ViewModel() {
		setBackground(Color.white);
		t = new Object3D();
	}

	public ViewModel(ArrayList<Point3D> p) {
		setBackground(Color.white);
		t = new Object3D(p);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		addMouseListener(new PanelListener());
		System.out.println("here");
		t.update(new Vector3D(0, 0, 0), new Vector3D(0, 0, 0), new Point2D(0.03, 0.02),
				new Point2D(150, 100), 0.1, 0);
		for (Point2D p : t.toList()) {
			System.out.println("In List : " + p.toString());
			g.drawOval((int) p.getX(), (int) p.getY(), 5, 5);
		}
		g.drawOval(ctr, ctr, 5, 5);
		ctr++;
	}

	public class PanelListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			t.update(new Vector3D(1, 0, 0), new Vector3D(0, 0, 0), new Point2D(0.03, 0.02),
					new Point2D(150, 100), 0.2, 0);
			repaint();
		}
	}

}
