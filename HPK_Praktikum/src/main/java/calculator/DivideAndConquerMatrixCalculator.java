package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.plaf.SliderUI;

import org.stringtemplate.v4.compiler.CodeGenerator.region_return;

import com.ibm.icu.util.BytesTrie.Result;

import util.Matrix;

public class DivideAndConquerMatrixCalculator extends AbstractMatrixCalculator {

	public double[][] math(double[][] matrixA, double[][] matrixB) throws InterruptedException, ExecutionException {

		if (matrixA[0].length != matrixB.length) { // check size
			throw new IllegalArgumentException("The number of columns must be equal to the number of rows!");
		}

		// init math
		double[][] result = initMath(matrixA, matrixB);

		List<double[][]> splitA = split(matrixA);
		List<double[][]> splitB = split(matrixB);

		List<double[][]> splitC = new ArrayList<>();

		ExecutorService threadPool = Executors.newCachedThreadPool();

		System.out.println("Start 1");
		double[][] c11 = startThreadMult(splitA.get(0), splitB.get(0), threadPool,1);
		System.out.println("Start 2");
		double[][] c12 = startThreadMult(splitA.get(1), splitB.get(2), threadPool,2);
		System.out.println("Start 3");
		double[][] c21 = startThreadMult(splitA.get(0), splitB.get(1), threadPool,3);
		System.out.println("Start 4");
		double[][] c22 = startThreadMult(splitA.get(1), splitB.get(3), threadPool,4);
		System.out.println("Start 5");
		double[][] c31 = startThreadMult(splitA.get(2), splitB.get(0), threadPool,5);
		System.out.println("Start 6");
		double[][] c32 = startThreadMult(splitA.get(3), splitB.get(2), threadPool,6);
		System.out.println("Start 7");
		double[][] c41 = startThreadMult(splitA.get(2), splitB.get(1), threadPool,7);
		System.out.println("Start 8");
		double[][] c42 = startThreadMult(splitA.get(3), splitB.get(3), threadPool,8);

		splitC.add(startThreadAddition(c11, c12, threadPool).get());
		System.out.println("Startet ADD 1");
		splitC.add(startThreadAddition(c21, c22, threadPool).get());
		System.out.println("Startet ADD 2");
		splitC.add(startThreadAddition(c31, c32, threadPool).get());
		splitC.add(startThreadAddition(c41, c42, threadPool).get());

		return merge(splitC, result);
	}

	private double[][] merge(List<double[][]> splitC, double[][] result) {
		int column = 0;
		int row = 0;
		// C1
		double[][] c1 = splitC.get(0);
		if (!isEmptyMatrix(c1)) {
			for (int j = 0; j < c1.length; j++) {
				row = 0;
				for (int i = 0; i < c1[0].length; i++) {
					result[column][row] = c1[j][i];
					row++;
				}
				column++;
			}
		}
		// C2
		column = 0;
		int tempRow = row;
		double[][] c2 = splitC.get(1);
		if (!isEmptyMatrix(c2)) {
			for (int j = 0; j < c2.length; j++) {
				row = tempRow;
				for (int i = 0; i < c2[0].length; i++) {
					result[column][row] = c2[j][i];
					row++;
				}
				column++;
			}
		}
		int tempColumn = column;
		// C3
		double[][] c3 = splitC.get(2);
		if (!isEmptyMatrix(c3)) {
			for (int j = 0; j < c3.length; j++) {
				row = 0;
				for (int i = 0; i < c3[0].length; i++) {
					result[column][row] = c3[j][i];
					row++;
				}
				column++;
			}
		}
		// C4
		row = tempRow;
		column = tempColumn;
		double[][] c4 = splitC.get(3);
		if (!isEmptyMatrix(c4)) {
			for (int j = 0; j < c4.length; j++) {
				row = tempRow;
				for (int i = 0; i < c4[0].length; row++, i++) {
					result[column][row] = c4[j][i];
				}
				column++;
			}
		}
		return result;
	}

	private double[][] startThreadMult(double[][] partA, double[][] partB, ExecutorService threadPool, int c)
			throws InterruptedException, ExecutionException {
		Callable<double[][]> statThread = new Callable<double[][]>() {
			@Override
			public double[][] call() throws Exception {
				SeriellMatrixCalculator smc = new SeriellMatrixCalculator();
				return smc.math(partA, partB);
			}
		};
		// start thread
		Future<double[][]> submit = threadPool.submit(statThread);
		
		double[][] result = submit.get();
		
		System.out.println("Hat Ergebnis:" + c);
		
		return result;
	}

	private Future<double[][]> startThreadAddition(double[][] partA, double[][] partB, ExecutorService threadPool)
			throws InterruptedException, ExecutionException {
		Callable<double[][]> statThread = new Callable<double[][]>() {
			@Override
			public double[][] call() throws Exception {
				return mergeAddition(partA, partB);
			}
		};
		// start thread
		return threadPool.submit(statThread);
	}

	/**
	 * Splits a Matrix in 4 parts.
	 * 
	 * @param matrix
	 * @return List<double[][]> includes part1-4
	 */
	private List<double[][]> split(double[][] matrix) {
		int height = matrix.length;
		int width = matrix[0].length;

		List<double[][]> splitMatrix = initSplitMatrix(height, width);
		// M1
		double[][] m1 = splitMatrix.get(0);
		for (int j = 0; j < getHalf(height); j++) {
			for (int i = 0; i < getHalf(width); i++) {
				m1[j][i] = matrix[j][i];
			}
		}
		// M2
		double[][] m2 = splitMatrix.get(1);
		for (int j = 0; j < getHalf(height); j++) {
			for (int row = 0, i = getHalf(width); i < width; row++, i++) {
				m2[j][row] = matrix[j][i];
			}
		}
		// M3
		double[][] m3 = splitMatrix.get(2);
		for (int column = 0, j = getHalf(height); j < height; column++, j++) {
			for (int i = 0; i < getHalf(width); i++) {
				m3[column][i] = matrix[j][i];
			}
		}
		// M4
		double[][] m4 = splitMatrix.get(3);
		for (int column = 0, j = getHalf(height); j < height; column++, j++) {
			for (int row = 0, i = getHalf(width); i < width; row++, i++) {
				m4[column][row] = matrix[j][i];
			}
		}

		return splitMatrix;
	}

	private List<double[][]> initSplitMatrix(int height, int width) {
		int halfHeigth = getHalf(height);
		int halfWidth = getHalf(width);
		double[][] matrix1 = new double[halfHeigth][halfWidth];
		double[][] matrix2 = new double[halfHeigth][width - halfWidth];
		double[][] matrix3 = new double[height - halfHeigth][halfWidth];
		double[][] matrix4 = new double[height - halfHeigth][width - halfWidth];
		List<double[][]> splitMatrix = new ArrayList<>();
		splitMatrix.add(matrix1);
		splitMatrix.add(matrix2);
		splitMatrix.add(matrix3);
		splitMatrix.add(matrix4);
		return splitMatrix;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private int getHalf(int value) {
		if (value % 2 != 0)
			return ++value / 2;
		return value / 2;
	}

	private double[][] multithreadingIndizeMultiplication(double[][] result, Matrix m1, Matrix m2) {
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
						return mult(m1.row(fj), m2.column(fi));
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

	protected double[][] mergeAddition(double[][] matrixA, double[][] matrixB) {

		if (isEmptyMatrix(matrixA) && isEmptyMatrix(matrixB)) {
			return new double[0][0];
		}

		if (isEmptyMatrix(matrixA)) {
			return matrixB;
		}

		if (isEmptyMatrix(matrixB)) {
			return matrixA;
		}

		double[][] m1 = new double[Math.max(matrixA.length, matrixB.length)][Math.max(matrixA[0].length,
				matrixB[0].length)];

		double[][] m2 = new double[Math.max(matrixA.length, matrixB.length)][Math.max(matrixA[0].length,
				matrixB[0].length)];

		for (int j = 0; j < matrixA.length; j++) {
			for (int i = 0; i < matrixA[0].length; i++) {
				m1[j][i] = matrixA[j][i];
			}
		}

		for (int j = 0; j < matrixB.length; j++) {
			for (int i = 0; i < matrixB[0].length; i++) {
				m2[j][i] = matrixB[j][i];
			}
		}

		return add(m1, m2);
	}

}
