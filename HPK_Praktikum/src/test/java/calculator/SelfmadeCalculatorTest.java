package calculator;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import calculator.AbstractDVCalculator;
import calculator.AbstractMatrixCalculator;
import calculator.SelfmadeCalculator;
import util.AbstractMatrixCalculatorTest;
import util.Matrix;

public class SelfmadeCalculatorTest extends AbstractMatrixCalculatorTest{
	
	@Override
	protected AbstractMatrixCalculator getCalculator() {
		return new SelfmadeCalculator();
	}

	@Test
	public final void testSplit() throws Exception {
		Method method = AbstractDVCalculator.class.getDeclaredMethod("split", Matrix.class);
		method.setAccessible(true);			
		double[][] matrix = {{1,1,2,2},{1,1,2,2},{3,3,4,4},{3,3,4,4}};
        List<Matrix> split =  (List<Matrix>) method.invoke(matrixCalculator, new Matrix(matrix));
        double[][] m0 = {{1,1},{1,1}};
        double[][] m1 = {{2,2},{2,2}};
        double[][] m2 = {{3,3},{3,3}};
        double[][] m3 = {{4,4},{4,4}};
        assertArrayEquals(m0,split.get(0).toArray(),eps);
        assertArrayEquals(m1,split.get(1).toArray(),eps);
        assertArrayEquals(m2,split.get(2).toArray(),eps);
        assertArrayEquals(m3,split.get(3).toArray(),eps);
	}
	
	@Test
	public final void testSplitEmptyMatrix() throws Exception {
		Method method = AbstractDVCalculator.class.getDeclaredMethod("split", Matrix.class);
		method.setAccessible(true);			
		double[][] matrix = {{1}};
        List<Matrix> split =  (List<Matrix>) method.invoke(matrixCalculator, new Matrix(matrix));
        double[][] expectedMatrix = {{1}};
        double[][] emptyMatrix = new double[0][0];
        assertArrayEquals(expectedMatrix,split.get(0).toArray(),eps);
        assertArrayEquals(emptyMatrix,split.get(1).toArray(),eps);
        assertArrayEquals(emptyMatrix,split.get(2).toArray(),eps);
        assertArrayEquals(emptyMatrix,split.get(3).toArray(),eps);
	}
	
	@Test
	public final void testSplitOddHeight() throws Exception {
		Method method = AbstractDVCalculator.class.getDeclaredMethod("split", Matrix.class);
		method.setAccessible(true);			
		double[][] matrix = {{1,2},{1,2},{3,4}};
        List<Matrix> split =  (List<Matrix>) method.invoke(matrixCalculator, new Matrix(matrix));
        double[][] m0 = {{1},{1}};
        double[][] m1 = {{2},{2}};
        double[][] m2 = {{3}};
        double[][] m3 = {{4}};
        assertArrayEquals(m0,split.get(0).toArray(),eps);
        assertArrayEquals(m1,split.get(1).toArray(),eps);
        assertArrayEquals(m2,split.get(2).toArray(),eps);
        assertArrayEquals(m3,split.get(3).toArray(),eps);
	}
	
	@Test
	public final void testSplitOddWith() throws Exception {
		Method method = AbstractDVCalculator.class.getDeclaredMethod("split", Matrix.class);
		method.setAccessible(true);			
		double[][] matrix = {{1,1,2},{3,3,4}};
        List<Matrix> split =  (List<Matrix>) method.invoke(matrixCalculator, new Matrix(matrix));
        double[][] m0 = {{1,1}};
        double[][] m1 = {{2}};
        double[][] m2 = {{3,3}};
        double[][] m3 = {{4}};
        assertArrayEquals(m0,split.get(0).toArray(),eps);
        assertArrayEquals(m1,split.get(1).toArray(),eps);
        assertArrayEquals(m2,split.get(2).toArray(),eps);
        assertArrayEquals(m3,split.get(3).toArray(),eps);
	}
	
	@Test
	public final void testMerge() throws Exception {
		Method method = AbstractDVCalculator.class.getDeclaredMethod("merge", List.class);
		method.setAccessible(true);			
        double[][] m0 = {{1,1},{1,1}};
        double[][] m1 = {{2,2},{2,2}};
        double[][] m2 = {{3,3},{3,3}};
        double[][] m3 = {{4,4},{4,4}};
        List<Matrix> toMerge = new ArrayList<>();
        toMerge.add(new Matrix(m0));
        toMerge.add(new Matrix(m1));
        toMerge.add(new Matrix(m2));
        toMerge.add(new Matrix(m3));
        Matrix mergedMatrix =  (Matrix) method.invoke(matrixCalculator, toMerge);
        double[][] expectedMatrix = {{1,1,2,2},{1,1,2,2},{3,3,4,4},{3,3,4,4}};
        assertArrayEquals(expectedMatrix,mergedMatrix.toArray(),eps);
	}
	
	@Test
	public final void testMergeEmptyMatrix() throws Exception {
		Method method = AbstractDVCalculator.class.getDeclaredMethod("merge", List.class);
		method.setAccessible(true);	
        double[][] m0 = {{1}};
        double[][] emptyMatrix = new double[0][0];
        List<Matrix> toMerge = new ArrayList<>();
        toMerge.add(new Matrix(m0));
        toMerge.add(new Matrix(emptyMatrix));
        toMerge.add(new Matrix(emptyMatrix));
        toMerge.add(new Matrix(emptyMatrix));
		Matrix mergedMatrix =  (Matrix) method.invoke(matrixCalculator, toMerge);
        double[][] expectedMatrix = {{1}};
        assertArrayEquals(expectedMatrix,mergedMatrix.toArray(),eps);
	}
	
	@Test
	public final void testMergeOddHeight() throws Exception {
		Method method = AbstractDVCalculator.class.getDeclaredMethod("merge", List.class);
		method.setAccessible(true);			
        double[][] m0 = {{1},{1}};
        double[][] m1 = {{2},{2}};
        double[][] m2 = {{3}};
        double[][] m3 = {{4}};
        List<Matrix> toMerge = new ArrayList<>();
        toMerge.add(new Matrix(m0));
        toMerge.add(new Matrix(m1));
        toMerge.add(new Matrix(m2));
        toMerge.add(new Matrix(m3));
        Matrix mergedMatrix =  (Matrix) method.invoke(matrixCalculator, toMerge);
		double[][] expectedMatrix = {{1,2},{1,2},{3,4}};
        assertArrayEquals(expectedMatrix,mergedMatrix.toArray(),eps);
	}
	
	@Test
	public final void testMergeOddWith() throws Exception {
		Method method = AbstractDVCalculator.class.getDeclaredMethod("merge", List.class);
		method.setAccessible(true);			
        double[][] m0 = {{1,1}};
        double[][] m1 = {{2}};
        double[][] m2 = {{3,3}};
        double[][] m3 = {{4}};
        List<Matrix> toMerge = new ArrayList<>();
        toMerge.add(new Matrix(m0));
        toMerge.add(new Matrix(m1));
        toMerge.add(new Matrix(m2));
        toMerge.add(new Matrix(m3));
        Matrix mergedMatrix =  (Matrix) method.invoke(matrixCalculator, toMerge);
        double[][] expectedMatrix = {{1,1,2},{3,3,4}};
        assertArrayEquals(expectedMatrix,mergedMatrix.toArray(),eps);
	}

    @Test(expected = IllegalArgumentException.class)
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
     * @param math input double[][]
     * @param eps the maximum delta between expected and input
     */
	protected void assertArrayEquals(double[][] result, double[][] math, double eps) {		
		
		if (result.length == 0 || result[0].length == 0) {
			if (math.length == 0 || math[0].length == 0) {
				return;
			}
		}
		
		for (int j = 0; j < math.length; j++) {
			for (int i = 0; i < math[0].length; i++) {
				assertEquals(result[j][i], math[j][i], eps);
			}
		}		
	}

}
