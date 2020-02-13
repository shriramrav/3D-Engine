
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

@SuppressWarnings("serial")
public class Window extends JFrame {

	public Window() {
		super();
		//add update here
	}

	public void init() {
		setTitle("Render");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ArrayList<Point3D> p = new ArrayList<Point3D>( Arrays.asList(new Point3D[] { new Point3D(0, 0, 0),
				new Point3D(1, 0, 0), new Point3D(0, 1, 0), new Point3D(0, 0, 1), }));
		
		getContentPane().add(new ViewModel(p));

	}

	public void Run() {
		setVisible(true);
	}

//	@Override
//	public void setSize(int x, int y) {
//		super.setSize(x, y);
//	}
}
