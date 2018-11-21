package util;

import static org.junit.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import calculator.DivideAndConquerMatrixCalculator;
import calculator.ParallelMatrixCalculator;
import calculator.SelfmadeCalculator;
import calculator.SeriellMatrixCalculator;

public class MatrixSpeedUpTest {

	ParallelMatrixCalculator parallelCalculator;
	SeriellMatrixCalculator seriellCalculator;
	DivideAndConquerMatrixCalculator divideAndConquereCalculator;
	SelfmadeCalculator selfmadeCalculator;
	
	double[][] matrixA;
	double[][] matrixB;
	
	final double eps = 1.E-8;

	
    public static enum MATH_FLAG {
        SERIELL, PARALLEL, DIVIDE_CONQUERE, SELFMADE;
    } 
	
	@Before
	public void setUp() {
		seriellCalculator = new SeriellMatrixCalculator();
		parallelCalculator = new ParallelMatrixCalculator();
		divideAndConquereCalculator = new DivideAndConquerMatrixCalculator();
		selfmadeCalculator = new SelfmadeCalculator();
		parallelCalculator.init();
	}
	
	@Test
	public final void speedUpTestS() throws InterruptedException, ExecutionException {
		System.out.println("repetitions\t| dimension\t| seriell\t| parallel\t| speedup");
		System.out.println("----------------+---------------+---------------+---------------+---------------");

		runSingleSpeedTest(64, 100, MATH_FLAG.SELFMADE);
		runSingleSpeedTest(128, 50, MATH_FLAG.SELFMADE);
		runSingleSpeedTest(256, 25, MATH_FLAG.SELFMADE);
		runSingleSpeedTest(256, 12, MATH_FLAG.SELFMADE);

	}

	@Test
	public final void speedUpTestCAC() throws InterruptedException, ExecutionException {
		System.out.println("repetitions\t| dimension\t| seriell\t| parallel\t| speedup");
		System.out.println("----------------+---------------+---------------+---------------+---------------");

		runSingleSpeedTest(64, 100, MATH_FLAG.DIVIDE_CONQUERE);
		runSingleSpeedTest(128, 50, MATH_FLAG.DIVIDE_CONQUERE);
		runSingleSpeedTest(256, 25, MATH_FLAG.DIVIDE_CONQUERE);
		runSingleSpeedTest(256, 12, MATH_FLAG.DIVIDE_CONQUERE);

	}

	@Test
	public final void speedUpTestP() throws InterruptedException, ExecutionException {
		System.out.println("repetitions\t| dimension\t| seriell\t| parallel\t| speedup");
		System.out.println("----------------+---------------+---------------+---------------+---------------");

		runSingleSpeedTest(64, 100, MATH_FLAG.PARALLEL);
		runSingleSpeedTest(128, 10, MATH_FLAG.PARALLEL);
		runSingleSpeedTest(256, 10, MATH_FLAG.PARALLEL);

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
	private void runSingleSpeedTest(int dim, int repetitions, MATH_FLAG flag) throws InterruptedException, ExecutionException{
		
		long finalTimeSeriell = 0;
		long finalTimeParallel = 0;
		
		for (int i = 0; i < repetitions; i++) {
			initrun(dim);

			// Seriell
			long launchRunTime = System.currentTimeMillis();
			double[][] expected = calculate(MATH_FLAG.SERIELL);
			finalTimeSeriell += System.currentTimeMillis() - launchRunTime;

			// Parallel
			launchRunTime = System.currentTimeMillis();
			double[][] value = calculate(flag);
			finalTimeParallel += System.currentTimeMillis() - launchRunTime;

			// Check
			assertArrayEquals(expected, value, eps);
		}
		
		System.out.println(repetitions +"\t\t\t\t|" +  dim + "\t\t\t\t| " + finalTimeSeriell+ "\t\t\t|" + finalTimeParallel +"\t\t\t|" + ((double) finalTimeSeriell/finalTimeParallel));
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
		case DIVIDE_CONQUERE:
			return divideAndConquereCalculator.math(matrixA, matrixB);
		case SELFMADE:
			return selfmadeCalculator.math(matrixA, matrixB);
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
		return 1;
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
