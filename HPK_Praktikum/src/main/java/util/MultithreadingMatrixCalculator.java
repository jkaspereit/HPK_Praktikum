package util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultithreadingMatrixCalculator extends MatrixCalculator {
	
	public double[][] mathParallel(double[][] matrixEins, double[][] matrixZwei) {
		
		Matrix m1 = new Matrix(matrixEins); // init m1
		Matrix m2 = new Matrix(matrixZwei); // init m2
		
		if(m1.width()!=m2.height()) { // check size 
			throw new IllegalArgumentException("The number of columns must be equal to the number of rows!");
		}
		
		// init result matrix
		double[][] result = new double[m1.height()][m2.width()]; 
		
		return multithreadingIndizeMultiplication(result, m1, m2);
	}
	
	public double[][] multithreadingIndizeMultiplication(double[][] result, Matrix m1, Matrix m2){
		// Init threadPool
		ExecutorService threadPool = Executors.newCachedThreadPool(); 
		// multiplication
		for (int j = 0; j < result.length; j++) {
			final int fj = j;
			for (int i = 0; i < result[j].length; i++) {
				final int fi = i;
				// init a thread for every indize 
				Callable<Double> statThread = new Callable<Double>() {
					@Override
					public Double call() throws Exception {
						return mult(m1.row(fj),m2.column(fi));
					}
				};
				// start thread
				Future<Double> threadResult = threadPool.submit(statThread);
				// get Value
				try {
					result[j][i] = threadResult.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
