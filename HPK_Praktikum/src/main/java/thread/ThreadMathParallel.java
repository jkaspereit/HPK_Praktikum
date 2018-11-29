package thread;

import calculator.MathParallelCalculator;
import util.Matrix;

public class ThreadMathParallel extends Thread {

	private double[] vektorA;
	private double[][] matrixB;
	private double[] result;
	int i;
	
	MathParallelCalculator calculator;

	
	public ThreadMathParallel(int i, double[] vektorA, double[][] matrixB, MathParallelCalculator mathParallelCalculator) {
		this.vektorA = vektorA;
		this.matrixB = matrixB;
		this.i= i;
		calculator = mathParallelCalculator;
	}
	
	@Override
	public void run() {
		result = new double[matrixB.length];
		for (int j = 0; j < matrixB.length; j++) {
			result[j] = vektorMultiplikation(vektorA, matrixB[j]);
		}	
		calculator.setResultRow(i, result);
	}
	
	public double[] get() throws InterruptedException{
		join();
		return result;
	}
	
	/**
	 * vektor multiplication method 
	 * 
	 * @param vektorA
	 * @param vektorB
	 * @return vektorA * vektorB
	 */
	protected double vektorMultiplikation(double[] vektorA, double[] vektorB) {		
		double result = 0; 
		for (int i = 0; i < vektorA.length; i++) {
			result += vektorA[i] * vektorB[i];
		}
		return result;
	}
	
	
}
