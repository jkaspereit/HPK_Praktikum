package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import thread.MultiplicationThread;
import thread.ThreadMatrixAddition;
import thread.ThreadMatrixMultiplication;
import thread.ThreadSelfmadeMath;
import util.Matrix;

public class SelfmadeCalculator extends AbstractDVCalculator {
	@Override
	public double[][] math(double[][] matrixA, double[][] matrixB) throws InterruptedException, ExecutionException {
		
		if(matrixA.length == 0 || matrixB.length == 0) {
			throw new IllegalArgumentException("Empty matrix not accepted for multiplication.");
		}
		
		if (matrixA[0].length != matrixB.length) { // check size
			throw new IllegalArgumentException("The number of columns must be equal to the number of rows!");
		}
		

		
		for (int rowIndex = 0; rowIndex < matrixA.length; rowIndex++) {
			if(matrixA[0].length!=matrixA[rowIndex].length) {
				throw new IllegalArgumentException("Missing arguments in the matrix A");
			}
		}
		
		for (int rowIndex = 0; rowIndex < matrixB.length; rowIndex++) {
			if(matrixB[0].length!=matrixB[rowIndex].length) {
				throw new IllegalArgumentException("Missing arguments in the matrix B");
			}
		}
		
		return math(new Matrix(matrixA), new Matrix(matrixB)).toArray();
	}
	
	/**
	 * Recursive multithreaded multiplication of matrices.
	 * @param matrixA
	 * @param matrixB
	 * @return matrixA * matrixB
	 * @throws InterruptedException
	 */
	public Matrix math(Matrix matrixA, Matrix matrixB) throws InterruptedException {
		List<Matrix> splitA = split(matrixA);
		List<Matrix> splitB = split(matrixB);
		
		// Pool of thread multiplications
		List<MultiplicationThread> multPool;

		if(matrixA.height()>64) { // when the dim > 64 call math again. Split up further before multiplication. 
			multPool = doMathAgain(splitA, splitB);
		} else { // now do the last multiplication
			multPool = endMath(splitA, splitB);
		}
		
		// start multPool
		for (MultiplicationThread thread : multPool) {
			thread.start();
		}
		
		// Pool of thread additions
		List<ThreadMatrixAddition> addPool = doAddition(multPool);
		
		// start addPool
		for (ThreadMatrixAddition thread : addPool) {
			thread.start();
		}
		
		List<Matrix> mergeList = getMergeList(addPool);
		
		return merge(mergeList);
	}
	
	private List<Matrix> getMergeList(List<ThreadMatrixAddition> addPool) throws InterruptedException{
		List<Matrix> mergeList = new ArrayList<>();
		mergeList.add(addPool.get(0).get());
		mergeList.add(addPool.get(1).get());
		mergeList.add(addPool.get(2).get());
		mergeList.add(addPool.get(3).get());
		return mergeList;
	}
	
	/**
	 * Calls an addition on the products. 
	 * @param multPool products
	 * @return List<ThreadMatrixAddition>
	 */
	private List<ThreadMatrixAddition> doAddition(List<MultiplicationThread> multPool){
		List<ThreadMatrixAddition> addPool = new ArrayList<>();
		addPool.add(new ThreadMatrixAddition(multPool.get(0),multPool.get(1)));
		addPool.add(new ThreadMatrixAddition(multPool.get(2),multPool.get(3)));
		addPool.add(new ThreadMatrixAddition(multPool.get(4),multPool.get(5)));
		addPool.add(new ThreadMatrixAddition(multPool.get(6),multPool.get(7)));
		return addPool;
	}
	
	
	/**
	 * Calls math again. 
	 * @param splitA 4 parts of Matrix A
	 * @param splitB 4 parts of Matrix B
	 * @return List<MultiplicationThread>
	 */
	private List<MultiplicationThread> doMathAgain(List<Matrix> splitA, List<Matrix> splitB) {
		ArrayList<MultiplicationThread> multPool = new ArrayList<>();
		multPool.add(new ThreadSelfmadeMath(splitA.get(0), splitB.get(0)));
		multPool.add(new ThreadSelfmadeMath(splitA.get(1), splitB.get(2)));
		multPool.add(new ThreadSelfmadeMath(splitA.get(0), splitB.get(1)));
		multPool.add(new ThreadSelfmadeMath(splitA.get(1), splitB.get(3)));
		multPool.add(new ThreadSelfmadeMath(splitA.get(2), splitB.get(0)));
		multPool.add(new ThreadSelfmadeMath(splitA.get(3), splitB.get(2)));
		multPool.add(new ThreadSelfmadeMath(splitA.get(2), splitB.get(1)));
		multPool.add(new ThreadSelfmadeMath(splitA.get(3), splitB.get(3)));
		return multPool;
	}
	
	/**
	 * Calls math a last time. 
	 * @param splitA 4 parts of Matrix A
	 * @param splitB 4 parts of Matrix B
	 * @return List<MultiplicationThread>
	 */
	private List<MultiplicationThread> endMath(List<Matrix> splitA, List<Matrix> splitB) {
		ArrayList<MultiplicationThread> multPool = new ArrayList<>();
		multPool.add(new ThreadMatrixMultiplication(splitA.get(0), splitB.get(0)));
		multPool.add(new ThreadMatrixMultiplication(splitA.get(1), splitB.get(2)));
		multPool.add(new ThreadMatrixMultiplication(splitA.get(0), splitB.get(1)));
		multPool.add(new ThreadMatrixMultiplication(splitA.get(1), splitB.get(3)));
		multPool.add(new ThreadMatrixMultiplication(splitA.get(2), splitB.get(0)));
		multPool.add(new ThreadMatrixMultiplication(splitA.get(3), splitB.get(2)));
		multPool.add(new ThreadMatrixMultiplication(splitA.get(2), splitB.get(1)));
		multPool.add(new ThreadMatrixMultiplication(splitA.get(3), splitB.get(3)));
		return multPool;
	}

}
