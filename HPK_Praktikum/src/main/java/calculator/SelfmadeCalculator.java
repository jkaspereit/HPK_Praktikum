package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import thread.ThreadMatrixAddition;
import thread.ThreadMatrixMultiplication;
import util.Matrix;

public class SelfmadeCalculator extends AbstractDVCalculator {

	@Override
	public double[][] math(double[][] matrixA, double[][] matrixB) throws InterruptedException, ExecutionException {
		
		if (matrixA[0].length != matrixB.length) { // check size
			throw new IllegalArgumentException("The number of columns must be equal to the number of rows!");
		}
		
		return math(new Matrix(matrixA), new Matrix(matrixB)).toArray();
	}
	
	public Matrix math(Matrix matrixA, Matrix matrixB) throws InterruptedException {
		List<Matrix> splitA = split(matrixA);
		List<Matrix> splitB = split(matrixB);
		
		List<ThreadMatrixMultiplication> pool = new ArrayList<>();
		
		pool.add(new ThreadMatrixMultiplication(splitA.get(0), splitB.get(0)));
		pool.add(new ThreadMatrixMultiplication(splitA.get(1), splitB.get(2)));
		pool.add(new ThreadMatrixMultiplication(splitA.get(0), splitB.get(1)));
		pool.add(new ThreadMatrixMultiplication(splitA.get(1), splitB.get(3)));
		pool.add(new ThreadMatrixMultiplication(splitA.get(2), splitB.get(0)));
		pool.add(new ThreadMatrixMultiplication(splitA.get(3), splitB.get(2)));
		pool.add(new ThreadMatrixMultiplication(splitA.get(2), splitB.get(1)));
		pool.add(new ThreadMatrixMultiplication(splitA.get(3), splitB.get(3)));
		
		for (ThreadMatrixMultiplication thread : pool) {
			thread.start();
		}

		ThreadMatrixAddition add0 = new ThreadMatrixAddition(pool.get(0),pool.get(1));
		ThreadMatrixAddition add1 = new ThreadMatrixAddition(pool.get(2),pool.get(3));
		ThreadMatrixAddition add2 = new ThreadMatrixAddition(pool.get(4),pool.get(5));
		ThreadMatrixAddition add3 = new ThreadMatrixAddition(pool.get(6),pool.get(7));
		
		add0.start();
		add1.start();
		add2.start();
		add3.start();
		
		List<Matrix> mList = new ArrayList<>();
		
		mList.add(new Matrix(add0.get()));
		mList.add(new Matrix(add1.get()));
		mList.add(new Matrix(add2.get()));
		mList.add(new Matrix(add3.get()));
		
		return merge(mList);
	}
	

}
