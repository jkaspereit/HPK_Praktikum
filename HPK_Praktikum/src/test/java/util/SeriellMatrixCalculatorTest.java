package util;

import calculator.AbstractMatrixCalculator;
import calculator.SeriellMatrixCalculator;

public class SeriellMatrixCalculatorTest extends AbstractMatrixCalculatorTest {

	@Override
	protected AbstractMatrixCalculator getCalculator() {
		return new SeriellMatrixCalculator();
	}


}
