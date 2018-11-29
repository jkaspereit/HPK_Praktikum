package calculator;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import calculator.DivideAndConquerCalculator;
import util.AbstractMatrixCalculator;
import util.AbstractMatrixCalculatorTest;
import util.MatrixSpeedUpTest;
import util.MatrixSpeedUpTest.TEST_MODE;

public class DivideAndConquerMatrixCalculatorTest extends AbstractMatrixCalculatorTest {

	@Override
	protected AbstractMatrixCalculator getCalculator() {
		return new DivideAndConquerCalculator();
	}
	
	@Test
    public final void testSplit() throws Exception {
        Method method = DivideAndConquerCalculator.class.getDeclaredMethod("split", double[][].class);
        method.setAccessible(true);
        double[][] matrix = {{1,1,2,2},{1,1,2,2},{3,3,4,4},{3,3,4,4}};
        Object[] parameters = {matrix};

        List<double[][]> split =  (List<double[][]>) method.invoke(matrixCalculator, parameters);

        double[][] m1 = {{1,1},{1,1}};
        double[][] m2 = {{2,2},{2,2}};
        double[][] m3 = {{3,3},{3,3}};
        double[][] m4 = {{4,4},{4,4}};
        assertArrayEquals(split.get(0), m1,eps);
        assertArrayEquals(split.get(1), m2,eps);
        assertArrayEquals(split.get(2), m3,eps);
        assertArrayEquals(split.get(3), m4,eps);
    }


	@Test
	public void speedTest() throws InterruptedException, ExecutionException {
		MatrixSpeedUpTest test = new MatrixSpeedUpTest(matrixCalculator);
		test.speedUpTest(TEST_MODE.RUN_SERIELL,  "div&con");
	}
	
	
}
