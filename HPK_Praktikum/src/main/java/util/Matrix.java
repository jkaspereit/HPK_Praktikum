package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Matrix {
	
	double[][] matrix;
	
	public Matrix(double[][] matrix) {
		this.matrix = matrix;
	}	
	
	public int height() {
		return matrix.length;
	}
	
	public int width(){
		return matrix[0].length;
	}
	
	public double[] column(int column) {
		return matrix[column];
	}
	
	public double[] row(int row) {
		double[] result = new double[width()];
		for (int j = 0; j < matrix.length; j++) {
			for (int i = 0; i < matrix[j].length; i++) {
				result[j] = matrix[j][i];
			}
		}
		return result;
	}

}
