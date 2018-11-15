package util;

public class MatrixCalculatorTest extends AbstractMatrixCalculatorTest {

	@Override
	protected double[][] calculate(double[][] m1, double[][] m2) {
		return matrixCalculator.mathSeriell(m1, m2);
	}

}
