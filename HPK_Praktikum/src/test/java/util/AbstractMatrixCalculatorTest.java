package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;

import calculator.AbstractMatrixCalculator;
import calculator.ParallelMatrixCalculator;
import calculator.SeriellMatrixCalculator;

public abstract class AbstractMatrixCalculatorTest {
	
	final double eps = 1.E-8;
    protected AbstractMatrixCalculator matrixCalculator;

    @Before
    public void setUp() throws Exception {
    	matrixCalculator = getCalculator();
        assertNotNull("no script implementation", matrixCalculator);
    }
    
    protected abstract AbstractMatrixCalculator getCalculator();

	protected double[][] calculate(double[][] m1, double[][] m2) throws InterruptedException, ExecutionException{
    	return matrixCalculator.math(m1, m2);
    }
    
    /**
     * Get the actual implementation for the MatrixService.
     * 
     * @return service implementation
     */
    protected SeriellMatrixCalculator getMatrixService() {
        return new SeriellMatrixCalculator();
    }
    
    @Test 
    public final void testMathDim0() throws Exception{
        double[][] m1 = {{5}};
        double[][] m2 = {{5}};
        double[][] result = {{25}};
        assertArrayEquals(result, calculate(m1, m2),eps);
    }
    
    @Test
    public final void testMathSeriellDim1() throws Exception {
        double[][] m1 = {{-14,7,-2}};
        double[][] m2 = {{1},{2},{7}};
        double[][] result = {{-14}};
        assertArrayEquals(result, calculate(m1, m2),eps);
    }
    @Test
    public final void testMathSeriellDim2() throws Exception {
        double[][] m1 = {{5,5},{5,5}};
        double[][] m2 = {{5,5},{5,5}};
        double[][] result = {{50,50},{50,50}};
        assertArrayEquals(result, calculate(m1, m2),eps);
    }
    @Test
    public final void testMathSeriellDim3() throws Exception {
        double[][] m1 = {{4,2,1},{0,-2,4}};
        double[][] m2 = {{1,2,3},{0,4,6},{2,-1,8}};
        double[][] result = {{6,15,32},{8,-12,20}};
        assertArrayEquals(result, calculate(m1, m2),eps);
    }
    @Test
    public final void testMathSeriellDim4() throws Exception {
        double[][] m1 = {{1,-1,1,-1},{2,-2,2,-2},{3,-3,3,-3},{-4,4,4,-4}};
        double[][] m2 = {{5,5,5,5},{5,5,5,5},{5,5,5,5},{5,5,5,5}};
        double[][] result = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        assertArrayEquals(result, calculate(m1, m2),eps);
    }
    @Test
    public final void testMathSeriellDim5() throws Exception {
        double[][] m1 = {{1},{2},{3},{4},{-10}};
        double[][] m2 = {{5,5,5,5,5}};
        double[][] result = {{5,5,5,5,5},{10,10,10,10,10},{15,15,15,15,15},{20,20,20,20,20},{-50,-50,-50,-50,-50}};
        assertArrayEquals(result, calculate(m1, m2),eps);
    }
    
    @Test
    public final void testMathSeriellFloatedNumbers() throws Exception {
        double[][] m1 = {{1.7},{2.4},{-3.1}};
        double[][] m2 = {{0,0.4,2.1}};
        double[][] result = {{0,17.0/25,357.0/100},{0,24.0/25,126.0/25},{0,-31.0/25,-651.0/100}};
        assertArrayEquals(result, calculate(m1, m2),eps);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public final void testMathColumnsNotEqualRows() throws Exception {
        double[][] m1 = {{1},{1},{1}};
        double[][] m2 = {{0,0,0},{0,0,0}};
        double[][] result = {{0,17.0/25,357.0/100},{0,24.0/25,126.0/25},{0,-31.0/25,-651.0/100}};
        assertArrayEquals(result, calculate(m1, m2),eps);
    }
    
    @Test
    public final void testMathAddAZero() throws Exception {
        double[][] m1 = {{5,5},{5,5}};
        double[][] m2 = {{5,5},{5,5,5}};
        double[][] result = {{50,50,25},{50,50,25}};
        assertArrayEquals(result, calculate(m1, m2),eps);
    }
    

    /**
     * Assert Method 
     * 
     * @param result expected double[][]
     * @param mathSeriell input double[][]
     * @param eps the maximum delta between expected and input
     */
	protected void assertArrayEquals(double[][] result, double[][] mathSeriell, double eps) {		
		for (int j = 0; j < mathSeriell.length; j++) {
			for (int i = 0; i < mathSeriell.length; i++) {
				assertEquals(result[j][i], mathSeriell[j][i], eps);
			}
		}		
	}
}
