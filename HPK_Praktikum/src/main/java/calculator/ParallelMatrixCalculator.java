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
        threadPool = Executors.newFixedThreadPool(result.length);

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

        threadPool.shutdown();

        return result;
    }
}
