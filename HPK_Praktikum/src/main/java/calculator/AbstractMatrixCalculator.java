package calculator;

import java.util.concurrent.ExecutionException;

import util.Matrix;

public abstract class AbstractMatrixCalculator {
	
	public abstract double[][] math(double[][] matrixA, double[][] matrixB) throws InterruptedException, ExecutionException;
	

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
	
	protected double[][] add(double[][] matrixA, double[][] matrixB) {
		
		double[][] result = initMath(matrixA, matrixB);
		
		if(matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length ){
			throw new IllegalArgumentException("Addiotion failed.!");
		}
		
		
		for (int j = 0; j < matrixA.length; j++) {
			for (int i = 0; i < matrixA[0].length; i++) {
				result[j][i] = matrixA[j][i] + matrixB[j][i];
			}
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
		
		// init result matrix
		return new double[matrixA.length][matrixB[0].length];
	}
	
	/**
	 * Checks if a matrix is empty
	 * @param matrix
	 * @return
	 */
	protected boolean isEmptyMatrix(double[][] matrix) {
		return matrix.length==0 || matrix[0].length == 0;
	}
	
	
	/**
	 * column by index
	 * @param  index 
	 * @return double[] column
	 */
	protected double[] column(double[][] matrix, int column) {
		double[] result = new double[matrix.length];
		for (int j = 0; j < matrix.length; j++) {
			for (int i = 0; i < matrix[j].length; i++) {
				if(i == column) {
					result[j] = matrix[j][i];					
				}
			}
		}
		return  result;
	}
	
}
