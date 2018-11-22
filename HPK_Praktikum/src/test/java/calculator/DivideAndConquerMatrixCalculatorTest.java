package calculator;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;

import calculator.AbstractMatrixCalculator;
import calculator.DivideAndConquerMatrixCalculator;
import util.AbstractMatrixCalculatorTest;

public class DivideAndConquerMatrixCalculatorTest extends AbstractMatrixCalculatorTest {

	@Override
	protected AbstractMatrixCalculator getCalculator() {
		return new DivideAndConquerMatrixCalculator();
	}
	
	@Test
    public final void testSplit() throws Exception {
        Method method = DivideAndConquerMatrixCalculator.class.getDeclaredMethod("split", double[][].class);
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
}
