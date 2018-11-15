package util;

public class MatrixService {

	public double[][] mathSeriell(double[][] matrixEins, double[][] matrixZwei) {
		
		Matrix m1 = new Matrix(matrixEins); // init m1
		Matrix m2 = new Matrix(matrixZwei); // init m2
		
		if(m1.width()!=m2.height()) { // check size 
			throw new IllegalArgumentException("matrixsize doesnt match.");
		}
		
		// init result matrix
		double[][] result = new double[m1.height()][m2.width()]; 
		
		// multiplication 
		for (int j = 0; j < result.length; j++) {
			for (int i = 0; i < result[j].length; i++) {
				result[j][i] = mult(m1.row(j),m2.column(i));
			}
		}
		
		return result;
	}
	
	private double mult(double[] row, double[] column) {
		double result = 0; 
		for (int i = 0; i < row.length; i++) {
			result += row[i] * column[i];
		}
		return result;
	}
	
}
