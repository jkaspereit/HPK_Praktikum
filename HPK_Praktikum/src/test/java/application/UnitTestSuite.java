package application;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import calculator.ParallelMatrixCalculatorTest;
import calculator.SeriellMatrixCalculatorTest;
import util.MatrixTest;

@RunWith(Suite.class)
//@SuiteClasses( {MatrixTest.class, MatrixServiceTest.class, WRBScriptTest.class })
@SuiteClasses( {MatrixTest.class, SeriellMatrixCalculatorTest.class, ParallelMatrixCalculatorTest.class} )
public class UnitTestSuite {
	
}
