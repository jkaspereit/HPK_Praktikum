package calculator;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import antlr.LibExprParser.StatContext;
import util.Matrix;

public class SeriellMatrixCalculator extends AbstractMatrixCalculator{
	
	public double[][] math(double[][] matrixA, double[][] matrixB) {
	
		if(matrixA.length==0||matrixB.length==0) {
			return new double[0][0];
		}
		
		if(matrixA[0].length != matrixB.length) { // check size 
			throw new IllegalArgumentException("The number of columns must be equal to the number of rows!");
		}
		
		// init math
		double[][] result = initMath(matrixA, matrixB);
		
		double[][] tB = transposeMatrix(matrixB);
		
		// multiplication 
		for (int j = 0; j < result.length; j++) {
			for (int i = 0; i < result[j].length; i++) {
				// ( m1 row j )  * ( m2 column i )
				result[j][i] = mult(matrixA[j], tB[i]);
			}
		}
		
		return result;
	}
	
}
