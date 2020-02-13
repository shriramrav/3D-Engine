public class Matrix {
	private double[][] matrix;

	public Matrix(int x, int y) {
		matrix = new double[x][y];
	}

	public Matrix(double[][] temp) {
		matrix = temp;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}

	public void init() {
		for (int i = 0; i < matrix.length; i++)
			for (int u = 0; u < matrix[0].length; u++)
				setNum(i, u, 0);
	}

	public double[][] getMatrix() {
		return matrix;
	}

	public int getX() {
		return matrix.length;
	}

	public int getY() {
		return matrix[0].length;
	}

	public double getNum(int x, int y) {
		return matrix[x][y];
	}

	public void setNum(int x, int y, double t) {
		matrix[x][y] = t;
	}

	public boolean chkMultiply(Matrix m2) {
		if (getX() == m2.getY())
			return true;
		else
			return false;
	}

	public Matrix findMultiply(Matrix m2) {// rows is y & columns is X
		Matrix product = new Matrix(m2.getX(), getY());
		for (int i = 0; i < getY(); i++)
			for (int j = 0; j < m2.getX(); j++) {
				double tempDouble = 0;
				for (int k = 0; k < getX(); k++)
					tempDouble += getNum(k, i) * m2.getNum(j, k);
				product.setNum(j, i, tempDouble);
			}
		return product;
	}

	public double formatDouble(double temp) {
		return (Double.valueOf(String.format("%.2f", temp)));
	}
}
