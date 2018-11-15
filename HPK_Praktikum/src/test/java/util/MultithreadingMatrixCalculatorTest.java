package util;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;

public class MultithreadingMatrixCalculatorTest extends AbstractMatrixCalculatorTest{


	@Override
	protected double[][] calculate(double[][] m1, double[][] m2) {
		return matrixCalculator.mathParallel(m1, m2);
	}
	
}
