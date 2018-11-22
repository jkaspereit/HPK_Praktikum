package calculator;

import java.util.concurrent.ExecutionException;

import calculator.AbstractMatrixCalculator;
import calculator.SeriellMatrixCalculator;
import util.AbstractMatrixCalculatorTest;

public class SeriellMatrixCalculatorTest extends AbstractMatrixCalculatorTest {

	@Override
	protected AbstractMatrixCalculator getCalculator() {
		return new SeriellMatrixCalculator();
	}
	
	@Override
	public void speedTest() throws InterruptedException, ExecutionException {
		// no speedUp Expected.
	}


}
