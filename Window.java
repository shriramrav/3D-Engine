
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

@SuppressWarnings("serial")
public class Window extends JFrame {

	public Window() {
		super();
	}

	public void init() {
		setTitle("Render");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().add(new ViewModel(new ArrayList<Point3D>(Arrays.asList(new Point3D[] { new Point3D(-1, -1, -1),
				new Point3D(1, -1, -1), new Point3D(-1, 1, -1), new Point3D(1, 1, -1), new Point3D(-1, -1, 1),
				new Point3D(-1, -1, 1), new Point3D(1, -1, 1), new Point3D(-1, 1, 1), new Point3D(1, 1, 1) }))));

	}

	public void Run() {
		setVisible(true);
	}

	public void setSize(int x, int y) {
		super.setSize(x, y);
	}

}
