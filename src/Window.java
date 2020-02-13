import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JPanel {
	private Engine3D t;

	public Window() {
		setBackground(Color.white);
		t = new Engine3D();
	}
	
	public Window(Color backColor) {
		setBackground(backColor);
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
