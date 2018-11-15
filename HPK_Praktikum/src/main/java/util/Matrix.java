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
	
	public double[] row(int row) {
		return matrix[row];
	}
	
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

}
