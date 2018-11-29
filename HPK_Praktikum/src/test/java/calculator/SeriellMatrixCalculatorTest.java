package calculator;

import java.util.concurrent.ExecutionException;

import calculator.SeriellMatrixCalculator;
import util.AbstractMatrixCalculator;
import util.AbstractMatrixCalculatorTest;

public class SeriellMatrixCalculatorTest extends AbstractMatrixCalculatorTest {

	@Override
	protected AbstractMatrixCalculator getCalculator() {
		return new SeriellMatrixCalculator();
	}
	

}
