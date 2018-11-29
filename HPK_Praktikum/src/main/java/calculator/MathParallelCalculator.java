package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import thread.ThreadMathParallel;
import util.AbstractMatrixCalculator;
import util.Matrix;

public class MathParallelCalculator extends AbstractMatrixCalculator {

	double[][] matrixC;
	
	List<Thread> pool;
	
	int threadCount = 0;
	
	@Override
	public synchronized double[][] math(double[][] matrixA, double[][] matrixB) throws InterruptedException, ExecutionException {
		matrixC = createResultMatrix(matrixA, matrixB);
		double[][] matrixD = transposeMatrix(matrixB);
		
		 pool = new ArrayList<>();
		
		for (int i = 0; i < matrixA.length; i++) {
			ThreadMathParallel thread = new ThreadMathParallel(i,matrixA[i], matrixD, this);
			pool.add(thread);
			thread.start();
			
		}
		
		for (Thread thread : pool) {
			thread.join();
		}
		
		return matrixC;
	}
	
	public void setResultRow(int i, double[] value) {
		matrixC[i] = value;
	}

	
}
