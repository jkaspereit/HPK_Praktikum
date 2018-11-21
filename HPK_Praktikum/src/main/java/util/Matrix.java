package util;

import java.util.Arrays;

/**
 * a simple implementation of a matrix
 * 
 * @author jkasp
 */
public class Matrix {
	
    public static enum MATRIX {
        A,B,C,D;
    } 
	
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
	 * @return height
	 */
	public int height() {
		return matrix.length;
	}
	
	/**
	 * half height
	 * @return height/2
	 */
	public int halfHeight() {
		if (height()%2==1) return (height()+1)/2;
		return height()/2;
	}
	
	/**
	 * height of the width
	 * 
	 * @return width
	 */
	public int width(){
		if(height()==0) return 0;
		return matrix[0].length;
	}
	
	/**
	 * half width
	 * @return width/2
	 */
	public int halfWidth() {
		if (width()%2==1) return (width()+1)/2;
		return width()/2;
	}
	
	/**
	 * is empty method
	 * @return true if its empty
	 */
	public boolean isEmpty() {
		return width()==0;
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
	 * Matrix as 2-dim double array.
	 * @return double[][] matrix
	 */
	public double[][] toArray(){
		return matrix;
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
	
	/**
	 * Splits a matrix into 4 parts.
	 * @param part MATRIX A / B / C / D
	 * @return Matrix 
	 */
	public Matrix split(MATRIX part){
		return splitHeight(part).splitWidth(part);
	}
	
	/**
	 * Simple addition with matrix2
	 * @param matrix2
	 * @return new matrix
	 */
	public Matrix add(Matrix matrix2) {
		if(height()!=matrix2.height()||width()!=matrix2.width()) {
			throw new IllegalArgumentException("Wrong arguments for an addition.");
		}
		double[][] result = new double[height()][width()];
		for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
			for (int index = 0; index < matrix.length; index++) {
				result[rowIndex][index] = matrix[rowIndex][index] + matrix2.toArray()[rowIndex][index];
			}
		}
		return new Matrix(result);
	}
	
	/**
	 * Splits a matrix by width
	 * @param part MATRIX A/C or B/D
	 * @return new Matrix
	 */
	private Matrix splitWidth(MATRIX part) {
		
		double[][] result = new double[height()][width()/2];
		
		for (int column = 0; column < height(); column++) {
			switch (part) {
			case A:
			case C:
				result[column] = Arrays.copyOfRange(matrix[column], 0, halfWidth());
				break;
			case B:
			case D:
				result[column] = Arrays.copyOfRange(matrix[column], halfWidth(), width());
				break;
			default:
				return null;
			}
		}
		
		return new Matrix(result);
		
	}
	
	/**
	 * Splits a matrix by height.
	 * @param part MATRIX A/B or C/D
	 * @return new Matrix
	 */
	private Matrix splitHeight(MATRIX part) {
		switch (part) {
		case A:
		case B:
			return new Matrix( Arrays.copyOfRange(matrix, 0, halfHeight()));
		case C:
		case D:
			return new Matrix( Arrays.copyOfRange(matrix, halfHeight(), height()) );
		default:
			return null;
		}
	}

	/**
	 * Concatenate with matrix2
	 * @param matrix2
	 * @return Matrix
	 */
	public Matrix concate(Matrix matrix2) {
		double[][] result = new double[height()+matrix2.height()][width()];
		int resultRowIndex = 0;
		for (int rowIndex = 0; rowIndex < height(); resultRowIndex++,rowIndex++) {
			result[resultRowIndex] = row(rowIndex);
		}
		for (int rowIndex = 0; rowIndex < matrix2.height();resultRowIndex++, rowIndex++) {
			result[resultRowIndex] = matrix2.row(rowIndex);
		}
		return new Matrix(result);
	}
	
	/**
	 * Concatenate rows with matrix2.
	 * @param matrix2
	 * @return Matrix
	 */
	public Matrix concateRows(Matrix matrix2) {
		double[][] result = new double[height()][width()+matrix2.width()];
		for (int rowIndex = 0; rowIndex < height(); rowIndex++) {
			int resultIndex = 0;
			for (int index = 0; index < width();resultIndex++, index++) {
				result[rowIndex][resultIndex] = matrix[rowIndex][index];
			}
			for (int index = 0; index < matrix2.width();resultIndex++, index++) {
				result[rowIndex][resultIndex] = matrix2.toArray()[rowIndex][index];
			}
		}
		return new Matrix(result);
	}
	

}
