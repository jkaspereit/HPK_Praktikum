package util;

import static org.junit.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatrixSpeedUpTest {

	MultithreadingMatrixCalculator calculator;
	
	double[][] matrixA;
	double[][] matrixB;
	
	final double eps = 1.E-8;
	
    public static enum MATH_FLAG {
        SERIELL, PARALLEL, DIVIDE_CONQUERE;
    } 
	
	@Before
	public void setUp() {
		calculator = new MultithreadingMatrixCalculator();
	}
	
	@Test
	public final void speedUpTest() throws InterruptedException, ExecutionException {
		System.out.println("repetitions\t| dimension\t| seriell\t| parallel\t| speedup");
		System.out.println("----------------+---------------+---------------+---------------+---------------");
		
		doSpeedTest(64, 100, MATH_FLAG.PARALLEL);
		doSpeedTest(128, 50, MATH_FLAG.PARALLEL);
		doSpeedTest(256, 25, MATH_FLAG.PARALLEL);
		doSpeedTest(256, 12, MATH_FLAG.PARALLEL);
		
	}
	
	/**
	 * Runs a Speed Test and prints the result. 
	 * 
	 * @param dim matrix dimension
	 * @param repetition number of calculations
	 * @param parallelFlag Defines the parallel calculation model.
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	private void doSpeedTest(int dim, int repetition, MATH_FLAG parallelFlag) throws InterruptedException, ExecutionException{
		long timeSeriell = runSingleSpeedTest(dim, repetition, MATH_FLAG.SERIELL);
		long timeParallel = runSingleSpeedTest(dim, repetition, parallelFlag);
		System.out.println(repetition +"\t\t|" +  dim +"\t\t| " + timeSeriell + "\t\t|" + timeParallel +"\t\t|" + ((double) timeSeriell/timeParallel));
	}
	
	/**
	 * Runs repetitions times a near (size)x(size) matrices specified calculation.
	 * 
	 * @param size Defines the sizes of the matrices to calculate. 
	 * @param repetitions Defines the number of repetition. 
	 * @param flag Defines the calculation model. 
	 * 
	 * @return run-time (speed)
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	private long runSingleSpeedTest(int size, int repetitions, MATH_FLAG flag) throws InterruptedException, ExecutionException{
		
		long finalTime = 0; 
		
		for (int i = 0; i < repetitions; i++) {
			double[][] expected = initrun(size);
			long launchRunTime = System.currentTimeMillis();
			assertArrayEquals(expected, calculate(flag), eps);
			finalTime += System.currentTimeMillis() - launchRunTime;
		}
		
		return finalTime;
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
			return calculator.mathSeriell(matrixA, matrixB);
		case PARALLEL:
			return calculator.mathParallel(matrixA, matrixB);
		case DIVIDE_CONQUERE:
			return calculator.mathDivideConquer(matrixA, matrixB);
		default:
			return null;
		}
	}

	/**
	 * Initializing SpeedRun.
	 * 
	 * @param size Defines MatrixA (size-1)x(size), MatrixB (n)x(n+1).
	 * @return double[][] expectedValue of the Matrix calculation.
	 */
	private double[][] initrun(int size) {
		matrixA = createRndMatrix(size-1, size);
		matrixB = createRndMatrix(size, size+1);	
		return calculator.mathSeriell(matrixA, matrixB);
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
		return new Random().doubles().findAny().getAsDouble();
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
	
}
