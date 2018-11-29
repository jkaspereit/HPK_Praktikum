package calculator;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

import util.AbstractMatrixCalculator;
import util.AbstractMatrixCalculatorTest;
import util.MatrixSpeedUpTest;
import util.MatrixSpeedUpTest.TEST_MODE;

public class ParallelMatrixCalculatorTest extends AbstractMatrixCalculatorTest{

	@Override
	protected AbstractMatrixCalculator getCalculator() {
		return new MathParallelCalculator();
	}
	
	@Test
	public void speedTest() throws InterruptedException, ExecutionException {
		MatrixSpeedUpTest test = new MatrixSpeedUpTest(matrixCalculator);
		test.speedUpTest(TEST_MODE.RUN_SERIELL,  "parallel");
	}
	
}
