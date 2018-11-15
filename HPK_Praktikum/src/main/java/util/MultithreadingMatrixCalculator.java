package util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.ibm.icu.util.InitialTimeZoneRule;

public class MultithreadingMatrixCalculator extends MatrixCalculator {
	
	public double[][] mathParallel(double[][] matrixEins, double[][] matrixZwei) {
		// init math
		double[][] result = initMath(matrixEins, matrixZwei); 
		
		// Init threadPool
		ExecutorService threadPool = Executors.newCachedThreadPool(); 
		
		for(int j = 0; j < result.length;j++) {
			final int column = j;
			Callable<Void> statThread = new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					for (int i = 0; i < result[column].length; i++) {
						result[column][i] = mult(m1.row(column),m2.column(i));
					}
					return null;
				}
			};
			
			Future<Void> threadResult = threadPool.submit(statThread);
		}
		
		return result;
	}
	
	public double[][] mathDivideConquer(double[][] matrixEins, double[][] matrixZwei) {	
		// init math
		double[][] result = initMath(matrixEins, matrixZwei);
		
		return multithreadingIndizeMultiplication(result, m1, m2);
	}
	
	private double[][] multithreadingIndizeMultiplication(double[][] result, Matrix m1, Matrix m2){
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
