package util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import antlr.LibExprParser.StatContext;

public class MatrixCalculator {

	protected Matrix m1;
	
	protected Matrix m2;
	
	public double[][] mathSeriell(double[][] matrixEins, double[][] matrixZwei) {
		
		// init math
		double[][] result = initMath(matrixEins, matrixZwei);
		
		// multiplication 
		for (int j = 0; j < result.length; j++) {
			for (int i = 0; i < result[j].length; i++) {
				// ( m1 row j )  * ( m2 column i )
				result[j][i] = mult(m1.row(j),m2.column(i));
			}
		}
		
		return result;
	}
	
	public void printMatrix (double[][] matrix) {
		for (int j = 0; j < matrix.length; j++) {
			for (int i = 0; i < matrix.length; i++) {
				System.out.print("|" + matrix[j][i]);
			}
			System.out.println("|");
		}
	}
	
	protected double mult(double[] row, double[] column) {
		double result = 0; 
		for (int i = 0; i < row.length; i++) {
			result += row[i] * column[i];
		}
		return result;
	}
	
	protected double[][] initMath(double[][] matrixEins, double[][] matrixZwei){
		if(matrixEins == null || matrixZwei == null) {
			throw new IllegalArgumentException("Null is not valid argument!");
		}
		
		m1 = new Matrix(matrixEins); // init m1
		m2 = new Matrix(matrixZwei); // init m2
		
		if(m1.width()!=m2.height()) { // check size 
			throw new IllegalArgumentException("The number of columns must be equal to the number of rows!");
		}
		
		// init result matrix
		return new double[m1.height()][m2.width()]; 
	}
	
}
