package calculator;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.ibm.icu.util.InitialTimeZoneRule;

import util.Matrix;

public class ParallelMatrixCalculator extends AbstractMatrixCalculator {
	
	/**
	 * Parallel calculation of a matrix product. 
	 * 
	 * @param matrixA
	 * @param matrixB
	 * @return result 
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public double[][] math(double[][] matrixA, double[][] matrixB) throws InterruptedException, ExecutionException {
		
		if(matrixA[0].length != matrixB.length) { // check size 
			throw new IllegalArgumentException("The number of columns must be equal to the number of rows!");
		}
		
		// init math
		double[][] result = initMath(matrixA, matrixB); 
		
		// Init threadPool
		ExecutorService threadPool = Executors.newCachedThreadPool(); 
		
		for(int j = 0; j < result.length;j++) {
			final int column = j;
			final double[] row = result[column].clone();
			final double[][] m1 = matrixA.clone();
			final double[][] m2 = matrixB.clone();
			if (m1==matrixA) {
				System.out.println("alhajshdfhasljdhflkhk");
			}
			Callable<double[]> startThread = new Callable<double[]>() {
				@Override
				public double[] call() throws Exception {
					for (int i = 0; i < row.length; i++) {
						row[i] = mult(m1[column],column(m2, i));
					}
					return row;
				}
			};
			Future<double[]> threadResult = threadPool.submit(startThread);
			result[column] = threadResult.get();
		}
		
		return result;
	}
	
	
//	/**
//	 * Creates Callable<double[]> for math() 
//	 * 
//	 * @param row
//	 * @param column
//	 * @return
//	 */
//	private Callable<double[]> createCallableMathParallel(double[] row, int column){
//		return new Callable<double[]>() {
//			@Override
//			public double[] call() throws Exception {
//				for (int i = 0; i < row.length; i++) {
//					row[i] = mult(m1.row(column),m2.column(i));
//				}
//				return row;
//			}
//		};
//	}
	

}
