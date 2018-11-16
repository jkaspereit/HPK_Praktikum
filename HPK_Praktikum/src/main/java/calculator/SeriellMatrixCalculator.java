package calculator;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import antlr.LibExprParser.StatContext;
import util.Matrix;

public class SeriellMatrixCalculator extends AbstractMatrixCalculator{
	
	public double[][] math(double[][] matrixEins, double[][] matrixZwei) {
		
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
	
}
