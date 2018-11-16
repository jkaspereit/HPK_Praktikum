package application;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import util.SeriellMatrixCalculatorTest;
import util.MatrixTest;
import util.ParallelMatrixCalculatorTest;

@RunWith(Suite.class)
//@SuiteClasses( {MatrixTest.class, MatrixServiceTest.class, WRBScriptTest.class })
@SuiteClasses( {MatrixTest.class, SeriellMatrixCalculatorTest.class, ParallelMatrixCalculatorTest.class} )
public class UnitTestSuite {
	
}
