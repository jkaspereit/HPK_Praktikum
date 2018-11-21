package calculator;

import java.util.List;
import java.util.concurrent.ExecutionException;

import util.Matrix;

public class SelfmadeCalculator extends AbstractDVCalculator {

	@Override
	public double[][] math(double[][] matrixA, double[][] matrixB) throws InterruptedException, ExecutionException {
		
		if (matrixA[0].length != matrixB.length) { // check size
			throw new IllegalArgumentException("The number of columns must be equal to the number of rows!");
		}
		
		return math(new Matrix(matrixA), new Matrix(matrixB)).toArray();
	}
	
	public Matrix math(Matrix matrixA, Matrix matrixB) {
		List<Matrix> splitA = split(matrixA);
		List<Matrix> splitB = split(matrixB);
		new Thread(createRunable(splitA.get(0), splitB.get(0))).start();
		new Thread(createRunable(splitA.get(1), splitB.get(2))).start();
		new Thread(createRunable(splitA.get(0), splitB.get(1))).start();
		new Thread(createRunable(splitA.get(1), splitB.get(3))).start();
		new Thread(createRunable(splitA.get(2), splitB.get(0))).start();
		new Thread(createRunable(splitA.get(3), splitB.get(2))).start();
		new Thread(createRunable(splitA.get(2), splitB.get(1))).start();
		new Thread(createRunable(splitA.get(3), splitB.get(3))).start();

		
	}

	private Runnable createRunable(Matrix matrix, Matrix matrix2) {
		new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		};
		return null;
	}
	

}
