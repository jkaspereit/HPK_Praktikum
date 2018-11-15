package application;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import util.MatrixService;
import util.MatrixServiceTest;
import util.MatrixTest;

@RunWith(Suite.class)
//@SuiteClasses( {MatrixTest.class, MatrixServiceTest.class, WRBScriptTest.class })
@SuiteClasses( {MatrixTest.class, MatrixServiceTest.class} )
public class UnitTestSuite {
	
}
