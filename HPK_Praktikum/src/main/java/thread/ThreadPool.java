//package thread;
//
//import java.util.List;
//
//import calculator.MathParallelCalculator;
//
//public class ThreadPool {
//	
//	private int size;
//	
//	private ThreadMathParallel[] pool;
//	
//	public ThreadPool(int initSize, MathParallelCalculator calculator) {
//		for (int i = 0; i < size; i++) {
//			pool[size++] = new ThreadMathParallel(calculator);
//		}
//	}
//	
//	public synchronized void release(ThreadMathParallel thread) {
//		pool[size++] = thread;
//		notify();
//	}
//	
//	public synchronized ThreadMathParallel require() {
//		try {
//			while(size==0) {
//				wait();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return pool[--size];
//	}
//	
//	public int getSize() {
//		return size;
//	}
//	
//}
