package calculator;

import java.util.concurrent.ExecutionException;

import util.Matrix;

public abstract class AbstractMatrixCalculator {

	protected Matrix m1;
	
	protected Matrix m2;
	
	public abstract double[][] math(double[][] matrixA, double[][] matrixB) throws InterruptedException, ExecutionException ;
	

	/**
	 * Optional multiplication method
	 * 
	 * @param row
	 * @param column
	 * @return row * column
	 */
	protected double mult(double[] row, double[] column) {
		double result = 0; 
		for (int i = 0; i < row.length; i++) {
			result += row[i] * column[i];
		}
		return result;
	}
	
	/**
	 * Initialize method for math(double[][] matrixA, double[][] matrixB)
	 * @param matrixA
	 * @param matrixB
	 * @return double[][] result
	 */
	protected double[][] initMath(double[][] matrixA, double[][] matrixB){
		if(matrixA == null || matrixB == null) {
			throw new IllegalArgumentException("Null is not valid argument!");
		}
		
		m1 = new Matrix(matrixA); // init m1
		m2 = new Matrix(matrixB); // init m2
		
		if(m1.width()!=m2.height()) { // check size 
			throw new IllegalArgumentException("The number of columns must be equal to the number of rows!");
		}
		
		// init result matrix
		return new double[m1.height()][m2.width()]; 
	}
	
	
	
}
