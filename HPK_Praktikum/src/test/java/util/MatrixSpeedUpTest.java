package util;

import static org.junit.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import calculator.AbstractMatrixCalculator;
import calculator.DivideAndConquerMatrixCalculator;
import calculator.ParallelMatrixCalculator;
import calculator.SelfmadeCalculator;
import calculator.SeriellMatrixCalculator;

public class MatrixSpeedUpTest {

	AbstractMatrixCalculator parallelCalculator;
	SeriellMatrixCalculator seriellCalculator;
	TEST_MODE mode;
	
	double[][] matrixA;
	double[][] matrixB;
	
	final double eps = 1.E-8;

	public static enum TEST_MODE{
		RUN_SERIELL, SKIP_SERIELL;
	}
	
    public static enum MATH_FLAG {
        SERIELL, PARALLEL;
    } 
	

	public MatrixSpeedUpTest(AbstractMatrixCalculator calculator, TEST_MODE mode) {
		this.parallelCalculator = calculator;
		this.mode = mode;
		seriellCalculator = new SeriellMatrixCalculator();
	}
	
	public final void speedUpTest() throws InterruptedException, ExecutionException {		
		System.out.println("repetitions\t| dimension\t| seriell\t| parallel\t| speedup");
		System.out.println("----------------+---------------+---------------+---------------+---------------");

		runSingleSpeedTest(64, 100);
		runSingleSpeedTest(128, 50);
		runSingleSpeedTest(256, 25);
		runSingleSpeedTest(512, 12);

	}


	/**
	 * Runs a Speed Test and prints the result. 
	 * 
	 * @param dim Defines the dimension of the matrix
	 * @param repetitions Defines the number of calculations. 
	 * @param flag Defines the calculation model. 
	 * 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	private void runSingleSpeedTest(int dim, int repetitions) throws InterruptedException, ExecutionException{
		
		long finalTimeSeriell = 0;
		long finalTimeParallel = 0;
		
		for (int i = 0; i < repetitions; i++) {
			initrun(dim);

			long launchRunTime; 
			
			// Parallel
			launchRunTime = System.currentTimeMillis();
			double[][] value = calculate(MATH_FLAG.PARALLEL);
			finalTimeParallel += System.currentTimeMillis() - launchRunTime;

			if(mode == TEST_MODE.RUN_SERIELL) {
				// Seriell
				launchRunTime = System.currentTimeMillis();
				double[][] expected = calculate(MATH_FLAG.SERIELL);
				finalTimeSeriell += System.currentTimeMillis() - launchRunTime;				
				assertArrayEquals(expected, value, eps);
			}
		}
		
		if(mode == TEST_MODE.SKIP_SERIELL) {
			finalTimeSeriell = getSeriellByDim(dim);
		}
		
		System.out.println(repetitions +"\t\t|" +  dim + "\t\t| " + finalTimeSeriell+ "\t\t|" + finalTimeParallel +"\t\t|" + ((double) finalTimeSeriell/finalTimeParallel));
	}
	
	/**
	 * Calculates the matrices A and B. 
	 * 
	 * @param flag	Calculation model
	 * @return result of the calculation 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	private double[][] calculate(MATH_FLAG flag) throws InterruptedException, ExecutionException {
		switch (flag) {
		case SERIELL:
			return seriellCalculator.math(matrixA, matrixB);
		case PARALLEL:
			return parallelCalculator.math(matrixA, matrixB);
		default:
			return null;
		}
	}

	/**
	 * Initializing SpeedRun.
	 * 
	 * @param size Defines MatrixA (size-1)x(size), MatrixB (n)x(n+1).
	 * @return double[][] expectedValue of the Matrix calculation.
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	private void initrun(int size) throws InterruptedException, ExecutionException {
		matrixA = createRndMatrix(size-1, size);
		matrixB = createRndMatrix(size, size+1);	
	}

	/**
	 * creates a matrix filled with random values.
	 * 
	 * @param height int coulumn.length
	 * @param width  int row.length
	 * @param matrix double[][] 
	 */
	private double[][] createRndMatrix(int height, int width) {
		double[][] matrix = new double[height][width];
		for (int j = 0; j < matrix.length; j++) {
			for (int i = 0; i < matrix[0].length ; i++) {
				matrix[j][i] = getRndDouble();
			}
		}
		return matrix;
	}

	/**
	 * @return Random double value
	 */
	private double getRndDouble() {
		return 1; //TODO
	}
	
    /**
     * Assert Method 
     * 
     * @param result expected double[][]
     * @param mathSeriell input double[][]
     * @param eps the maximum delta between expected and input
     */
	private void assertArrayEquals(double[][] result, double[][] mathSeriell, double eps) {
		for (int j = 0; j < mathSeriell.length; j++) {
			for (int i = 0; i < mathSeriell.length; i++) {
				assertEquals(result[j][i], mathSeriell[j][i], eps);
			}
		}		
	}
	
	/**
	 * Gets a default long time by the dimension for a seriell calculation.
	 * @param dim Defines Dimension. 
	 * @return long time
	 */
	private long getSeriellByDim(int dim) {
		switch (dim) {
		case 64: return 1500;
		case 128: return 10000;
		case 256: return 90000;
		case 512: return 650000;
		default:
			return 0;
		}
	}
}
