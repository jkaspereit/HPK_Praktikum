package application;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import util.MatrixCalculatorTest;
import util.MatrixTest;
import util.MultithreadingMatrixCalculatorTest;

@RunWith(Suite.class)
//@SuiteClasses( {MatrixTest.class, MatrixServiceTest.class, WRBScriptTest.class })
@SuiteClasses( {MatrixTest.class, MatrixCalculatorTest.class, MultithreadingMatrixCalculatorTest.class} )
public class UnitTestSuite {
	
}
