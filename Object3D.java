import java.util.ArrayList;
import java.util.stream.Collectors;

public class Object3D {

	private Matrix[] m;

	private ArrayList<Point3D> input_list = new ArrayList<Point3D>();
	private ArrayList<Point3D> buffer_list = new ArrayList<Point3D>();

	public Object3D() {
		m = new Matrix[5];
		for (int i = 0; i < m.length; i++) {
			m[i] = new Matrix(4, 4);
			m[i].init();
//			System.out.println(m[i].toString());
		}
	}

	public void addPoint(Point3D p) {
		input_list.add(p);
		buffer_list.add(p);
	}

	public void removePoint(int index) {
		input_list.remove(index);
		buffer_list.remove(index);
	}

	public void removePoint() {
		input_list.remove(input_list.size() - 1);
		buffer_list.remove(buffer_list.remove(buffer_list.size() - 1));
	}

	public Object3D(ArrayList<Point3D> arr) {
		this();
		input_list = arr;
		buffer_list = input_list;
	}

	public void reset() {
		buffer_list = input_list;
	}

	public Matrix config(Point3D p, Vector3D rot, Vector3D trans, Point2D axis, Point2D sens, double f, double s) {

		System.out.println("config");

//		System.out.println("f : " + f);
//		System.out.println("axis.getX() : " + axis.getX());
//		System.out.println("sens.getX() : " + sens.getX());
		System.out.println(m[0].toString());

		m[0].setNum(0, 0, (f * axis.getX()) / (2 * sens.getX()));
		m[0].setNum(1, 0, s);
		m[0].setNum(1, 1, (f * axis.getY()) / (2 * sens.getY()));
		m[0].setNum(1, 2, -1);
		m[0].setNum(2, 2, 1);
		m[0].setNum(3, 3, 1);
//		System.out.println("m[0]: \n" + m[0].toString());

		m[1].setNum(0, 0, 1);
		m[1].setNum(1, 1, Math.cos(rot.getX()));
		m[1].setNum(1, 2, Math.sin(rot.getX()));
		m[1].setNum(2, 1, -Math.sin(rot.getX()));
		m[1].setNum(2, 2, Math.cos(rot.getX()));
		m[1].setNum(3, 3, 1);
//		System.out.println("m[1]: \n" + m[1].toString());

		m[2].setNum(0, 0, Math.cos(rot.getY()));
		m[2].setNum(1, 0, Math.sin(rot.getY()));
		m[2].setNum(0, 1, 0);
		m[2].setNum(1, 1, 1);
		m[2].setNum(0, 2, -Math.sin(rot.getY()));
		m[2].setNum(1, 2, Math.cos(rot.getY()));
		m[2].setNum(2, 2, 1);
		m[2].setNum(3, 3, 1);
//		System.out.println("m[2]: \n" + m[2].toString());

		m[3].setNum(0, 0, Math.cos(rot.getZ()));
		m[3].setNum(1, 0, -Math.sin(rot.getZ()));
		m[3].setNum(0, 1, Math.sin(rot.getZ()));
		m[3].setNum(1, 1, Math.cos(rot.getZ()));
		m[3].setNum(2, 2, 1);
		m[3].setNum(3, 3, 1);
//		System.out.println("m[3]: \n" + m[3].toString());

		m[4].setNum(0, 0, 1);
		m[4].setNum(1, 1, 1);
		m[4].setNum(2, 2, 1);
		m[4].setNum(3, 3, 1);
		m[4].setNum(3, 0, -trans.getX());
		m[4].setNum(3, 1, -trans.getY());
		m[4].setNum(3, 2, -trans.getZ());
//		System.out.println("m[4]: \n" + m[4].toString());

//		System.out.println("this : \n" + new Matrix(new double[][] { { p.getX(), p.getY(), p.getZ(), 1 } }).toString());

		return (new Matrix(new double[][] { { p.getX(), p.getY(), p.getZ(), 1 } }));
	}

	public Point3D finalize(Matrix temp) {
		Matrix t = new Matrix(4, 4);
		t.init();
		t.setNum(0, 0, 1 / temp.getNum(0, 2));
		t.setNum(1, 1, 1 / temp.getNum(0, 2));
		t.setNum(2, 2, 1);
		t.setNum(3, 3, 1);
		Matrix q = (t.findMultiply(temp));
		return new Point3D(q.getNum(0, 0), q.getNum(0, 1), q.getNum(0, 2));
	}

	interface Lambda {
		Matrix func(Matrix matrix, int sIndex, int fIndex);
	}

	Lambda rec;
	{
		rec = (matrix, sIndex, fIndex) -> {
			System.out.println("sIndex : " + sIndex + ", fIndex : " + fIndex + ", matrix : \n" + matrix.toString());
			if (sIndex == fIndex) {
				return matrix;
			} else {
				return rec.func(m[sIndex].findMultiply(matrix), --sIndex, fIndex);
			}
		};
	}

	public Matrix perform(Matrix temp, int sIndex, int fIndex) {
		return rec.func(temp, sIndex, fIndex);
	}

	public void update(Vector3D rot, Vector3D trans, Point2D axis, Point2D sens, double f, double s) {
		System.out.println("listner");
		buffer_list = new ArrayList<Point3D>(buffer_list.stream().map(p -> config(p, rot, trans, axis, sens, f, s))
				.map(p -> perform(p, 4, 0)).map(p -> new Point3D(p.getNum(0, 0), p.getNum(0, 1), p.getNum(0, 2)))
				.collect(Collectors.<Point3D>toList()));
	}

	public ArrayList<Point2D> toList() {
		return new ArrayList<Point2D>(
				buffer_list.stream().map(p -> new Matrix(new double[][] { { p.getX(), p.getY(), p.getZ(), 1 } }))
						.map(p -> finalize(perform(p, 0, -1))).map(p -> new Point2D(p.getX(), p.getY()))
						.collect(Collectors.toList()));
	}

}
