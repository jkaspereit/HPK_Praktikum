package thread;

import util.Matrix;

public class ThreadMatrixMultiplication extends Thread{

	private Matrix matrixA;
	private Matrix matrixB;
	private double[][] result;
	
	public ThreadMatrixMultiplication(Matrix matirxA, Matrix matrixB) {
		this.matrixA = matirxA;
		this.matrixB = matrixB;
		result = new double[matirxA.height()][matrixB.width()];
	}
	
	@Override
	public void run() {
		for (int rowIndex = 0; rowIndex < matrixA.height(); rowIndex++) {
			for (int index = 0; index < matrixB.width(); index++) {
				// ( A row  )  * ( B column  )
				result[rowIndex][index] = mult(matrixA.row(rowIndex),matrixB.column(index));
			}
		}		
	}
	
	public double[][] get() throws InterruptedException{
		join();
		return result;
	}
	
	
	/**
	 * multiplication method
	 * 
	 * @param row
	 * @param column
	 * @return row * column
	 */
	protected double mult(double[] row, double[] column) {
		double result = 0; 
		for (int i = 0; i < row.length; i++) {
			result += row[i] * column[i];
		}
		return result;
	}

}
