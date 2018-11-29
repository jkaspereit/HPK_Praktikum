package util;

import java.util.concurrent.ExecutionException;

public abstract class AbstractMatrixCalculator {
	
	public abstract double[][] math(double[][] matrixA, double[][] matrixB) throws InterruptedException, ExecutionException;
	
	@Override
	public String toString() {
		return "calculator";
	}
	
	
	/**
	 * vektor multiplication method 
	 * 
	 * @param vektorA
	 * @param vektorB
	 * @return vektorA * vektorB
	 */
	protected double vektorMultiplikation(double[] vektorA, double[] vektorB) {		
		double result = 0; 
		for (int i = 0; i < vektorA.length; i++) {
			result += vektorA[i] * vektorB[i];
		}
		return result;
	}
	
	/**
	 * Addition of matrixA and matrixB
	 * @param matrixA
	 * @param matrixB
	 * @return matrixA + matrixB
	 */
	protected double[][] add(double[][] matrixA, double[][] matrixB) {
		
		double[][] result = new double[matrixA.length][matrixA[0].length];
		
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
	protected double[][] createResultMatrix(double[][] matrixA, double[][] matrixB){
		if(matrixA == null || matrixB == null) {
			throw new IllegalArgumentException("Null is not valid argument!");
		}
		
		if(isEmptyMatrix(matrixA) || isEmptyMatrix(matrixB)) {
			throw new IllegalArgumentException("Empty Matrix not allowed!");
		}
		
        if (matrixA[0].length != matrixB.length) { // check size
            throw new IllegalArgumentException("The number of columns must be equal to the number of rows!");
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
	 * Transpose Matrix 
	 * @param matrix
	 * @return matrix transposed
	 */
    public static double[][] transposeMatrix(double [][] matrix){
        double[][] temp = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                temp[j][i] = matrix[i][j];
        return temp;
    }
	
}
