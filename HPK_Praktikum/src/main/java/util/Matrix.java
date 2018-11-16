package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * a simple implementation of a matrix
 * 
 * @author jkasp
 */
public class Matrix {
	
	double[][] matrix;
	
	/**
	 * Default Constructor
	 * 
	 * @param matrix values
	 */
	public Matrix(double[][] matrix) {
		this.matrix = matrix;
	}	
	
	/**
	 * height of the matrix
	 * 
	 * @return int height
	 */
	public int height() {
		return matrix.length;
	}
	
	/**
	 * height of the width
	 * 
	 * @return int width
	 */
	public int width(){
		return matrix[0].length;
	}
	
	/**
	 * row by index
	 * @param  index 
	 * @return double[] row
	 */
	public double[] row(int index) {
		return matrix[index];
	}
	
	/**
	 * column by index
	 * @param  index 
	 * @return double[] column
	 */
	public double[] column(int column) {
		double[] result = new double[height()];
		for (int j = 0; j < matrix.length; j++) {
			for (int i = 0; i < matrix[j].length; i++) {
				if(i == column) {
					result[j] = matrix[j][i];					
				}
			}
		}
		return  result;
	}
	
	/**
	 * prints the matrix.
	 */
	public void printMatrix () {
		for (int j = 0; j < matrix.length; j++) {
			for (int i = 0; i < matrix.length; i++) {
				System.out.print("|" + matrix[j][i]);
			}
			System.out.println("|");
		}
	}

}
