package calculator;

import java.util.ArrayList;
import java.util.List;

import util.Matrix;
import util.Matrix.MATRIX;

public abstract class AbstractDVCalculator extends AbstractMatrixCalculator{

	private static int A = 0;
	private static int B = 1;
	private static int C = 2;
	private static int D = 3;
	
	protected List<Matrix> split(Matrix matrix){
		List<Matrix> result = new ArrayList<>();
		result.add(matrix.split(MATRIX.A));
		result.add(matrix.split(MATRIX.B));
		result.add(matrix.split(MATRIX.C));
		result.add(matrix.split(MATRIX.D));
		return result;
	}
	
	protected Matrix merge(List<Matrix> matrixList) {
		Matrix concate = matrixList.get(A).concate(matrixList.get(C));
		Matrix concate2 = matrixList.get(B).concate(matrixList.get(D));
		return concate.concateRows(concate2);
	}
	
	
	
}
