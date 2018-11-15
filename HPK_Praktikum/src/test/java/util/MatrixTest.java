package util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.management.RuntimeErrorException;

import org.junit.Before;
import org.junit.Test;

import util.Matrix;
import util.MatrixCalculator;

public class MatrixTest {

	final double eps = 1.E-8; 
    
	/**
	 * Method Test: Matrix.row(int row) 
	 */
    @Test 
    public final void testMatrixRow() {
    	double[][] init = {{4,2,1},{0,-2,4}};
    	double[] row0 = {4,2,1};
    	double[] row1 = {0,-2,4};
    	Matrix matrix = new Matrix(init);
    	assertArrayEquals(row0, matrix.row(0), eps);
    	assertArrayEquals(row1, matrix.row(1), eps);
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
        
    
}
