package calculator;

public class MatrixCalc {

    private double[][] matrix1;
    private double[][] matrix2;
    private double[][] res;

    /**
     * Constructor
     *
     * @param matrix1
     * @param matrix2
     */
    public MatrixCalc(double[][] matrix1, double[][] matrix2) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
    }


    public void run() {
        //Multiplikation
        int rows = matrix1.length;
        int cols = matrix2[0].length;
        res = new double[rows][cols];
        //Zweite Matrix transponieren, für schnelleren Zugriff in der Schleife
        //double[][] trans = transpose(matrix2);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    //Matrixmultiplikation durchführen
                    res[i][j] += matrix1[i][k] * matrix2[k][j];//trans[j][k];
                }
            }
        }
    }

}
