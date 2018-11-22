package thread;

import calculator.SelfmadeCalculator;
import util.Matrix;

public class ThreadSelfmadeMath extends MultiplicationThread{

	private Matrix matrixA;
	private Matrix matrixB;
	private Matrix result;
	
	public ThreadSelfmadeMath(Matrix matirxA, Matrix matrixB) {
		this.matrixA = matirxA;
		this.matrixB = matrixB;
	}
	
	@Override
	public void run() {
		SelfmadeCalculator calculator = new SelfmadeCalculator();	
		try {
			result = calculator.math(matrixA, matrixB);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public double[][] get() throws InterruptedException{
		join();
		return result.toArray();
	}
	
}
