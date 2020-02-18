
public class Point3D extends Point2D {
	private double z;

	public Point3D(double x, double y, double z) {
		super(x, y);
		this.z = z;
	}

	public double getZ() {
		return z;
	}
	
	public String toString()
	{
		String[] temp = super.toString().split(" ");
		String str = "";
		for (int i = 0 ; i < temp.length - 1; i++) {
			str += temp[i] + " ";
		}
		return (str + ", " + getZ() + " )");
	}
}
