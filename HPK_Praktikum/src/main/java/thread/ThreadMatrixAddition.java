package thread;

import util.Matrix;

public class ThreadMatrixAddition extends Thread{

	private Matrix matrixA;
	private Matrix matrixB;
	
	private double[][] result;

	public ThreadMatrixAddition(MultiplicationThread matirxA, MultiplicationThread matrixB) {
		try {
			this.matrixA = new Matrix(matirxA.get());
			this.matrixB = new Matrix(matrixB.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		if (matrixA.isEmpty()&&matrixB.isEmpty()) {
			result = new double[0][0];
		}

		if (matrixA.isEmpty()) {
			result = matrixB.toArray();
		}

		if (matrixB.isEmpty()) {
			result = matrixA.toArray();
		}

		double[][] m1 = new double[Math.max(matrixA.halfHeight(), matrixB.height())][Math.max(matrixA.width(),
				matrixB.width())];

		double[][] m2 = new double[Math.max(matrixA.halfHeight(), matrixB.height())][Math.max(matrixA.width(),
				matrixB.width())];

		for (int j = 0; j < matrixA.height(); j++) {
			for (int i = 0; i < matrixA.width(); i++) {
				m1[j][i] = matrixA.toArray()[j][i];
			}
		}

		for (int j = 0; j < matrixB.height(); j++) {
			for (int i = 0; i < matrixB.width(); i++) {
				m2[j][i] = matrixB.toArray()[j][i];
			}
		}

		result = new Matrix(m1).add(new Matrix(m2)).toArray();
	}

	public Matrix get() throws InterruptedException {
		join();
		return new Matrix(result);
	}

}
