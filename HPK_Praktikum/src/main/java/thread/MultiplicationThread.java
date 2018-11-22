package thread;

public abstract class MultiplicationThread extends Thread{

	public abstract double[][] get() throws InterruptedException;
	
}
