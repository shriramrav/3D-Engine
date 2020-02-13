import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class ViewModel extends JPanel {
	private Object3D t;

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
		System.out.println("here");
		for (Point2D p : t.toList()) {
//			System.out.println("here");
			g.drawOval((int) p.getX(), (int) p.getY(), 5, 5);
		}
	}

	public class PanelListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			t.update(new Vector3D(1, 0, 0), new Vector3D(0, 0, 0), new Point2D(getWidth() / 10000, getHeight() / 10000),
					new Point2D(getWidth() / 2, getHeight() / 2), 0.1, 0);
			repaint();
		}
	}

}
