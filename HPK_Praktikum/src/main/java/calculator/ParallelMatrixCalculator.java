package calculator;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.ibm.icu.util.InitialTimeZoneRule;

import util.Matrix;

public class ParallelMatrixCalculator extends AbstractMatrixCalculator {

    ExecutorService threadPool = null;
//	final double[][] m1 = null;
//	final double[][] m2 = null;


    public void init() {
        if (threadPool == null) {
            threadPool = Executors.newCachedThreadPool();
        }
    }

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

        if (matrixA[0].length != matrixB.length) { // check size
            throw new IllegalArgumentException("The number of columns must be equal to the number of rows!");
        }

        // init math
        double[][] result = initMath(matrixA, matrixB);

        ArrayList<Future<double[]>> data = new ArrayList<>();

        //more threads != better speedup, set max?
        threadPool = Executors.newCachedThreadPool();

        double[][] tb = transposeMatrix(matrixB);
        
        for (int j = 0; j < result.length; j++) {
            final int column = j;
            final double[] row = result[column].clone();

            Callable<double[]> startThread = () -> {

                for (int i = 0; i < row.length; i++) {
                    row[i] = mult(matrixA[column], tb[i]);
                }
                return row;

            };

            //run this thread and go on...
			data.add(threadPool.submit(startThread));
        }

        // ...until you get the values
        for (int i = 0; i < data.size(); i++) {
            result[i] = data.get(i).get();
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
