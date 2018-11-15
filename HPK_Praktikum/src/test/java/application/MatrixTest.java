package application;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.management.RuntimeErrorException;

import org.junit.Before;
import org.junit.Test;

import util.Matrix;
import util.MatrixService;

public class MatrixTest {

	final double eps = 1.E-8;
    MatrixService service;

    @Before
    public final void setUp() throws Exception {
    	service = new MatrixService();
        assertNotNull("no script implementation", service);
    }
    
    /**
     * Get the actual implementation for the MatrixService.
     * 
     * @return service implementation
     */
    protected MatrixService getMatrixService() {
        return new MatrixService();
    }
    
    
    @Test // TODO teste ob die row() methode funktioniert
    public final void testMatrixRow() {
    	double[][] init = {{4,2,1},{0,-2,4}};
    	Matrix matrix = new Matrix(init);
    	matrix.row(0); 
    	matrix.row(1);
    	throw new RuntimeException("todo");
    }
    
    /**
     * Method Test:  Matrix.column(int column)
     */
    @Test 
    public final void testMatrixColumn() {
    	double[][] init = {{4,2,1},{0,-2,4}};
    	double[] col0 = {4,0};
    	double[] col1 = {2,-2};
    	double[] col2 = {1,4};
    	Matrix matrix = new Matrix(init);
    	assertArrayEquals(col0, matrix.column(0), eps);
    	assertArrayEquals(col1, matrix.column(1), eps);
    	assertArrayEquals(col2, matrix.column(2), eps);
    }
    
    /**
     * Method Test: Matrix.height()
     */
    @Test 
    public final void testMatrixHeight() {
    	double[][] init = {{0,2,1,4},{0,1,4,6}};
    	Matrix matrix = new Matrix(init);
    	assertEquals(2, matrix.height());
    }
    
    /**
     * Method Test: Matrix.width()
     */
    @Test 
    public final void testMatrixWidth() {
    	double[][] init = {{0,2,1,4},{0,1,4,6}};
    	Matrix matrix = new Matrix(init);
    	assertEquals(4, matrix.width());
    }
    
    
    @Test
    public final void testLongAdd() throws Exception {
        double[][] m1 = {{4,2,1},{0,-2,4}};
        double[][] m2 = {{1,2,3},{0,4,6},{2,-1,8}};
        double[][] result = {{6,15,32},{8,-12,20}};
        service.printMatrix(service.mathSeriell(m1, m2));
        assertEquals(result,service.mathSeriell(m1, m2));
        
    }

    
    
}
