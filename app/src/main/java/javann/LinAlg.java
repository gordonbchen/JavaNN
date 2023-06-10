package javann;


public class LinAlg {

    public static double[] createRandomDoubleArray(int cols) {
        double[] array = new double[cols];
        for (int col = 0; col < cols; col++) {
            array[col] = Math.random() - 0.5;
        }
        return array;
    }

    public static double[][] createRandomDoubleArray(int rows, int cols) {
        double[][] array = new double[rows][cols];
        for (int row = 0; row < rows; row++) {
            array[row] = createRandomDoubleArray(cols);
        }
        return array;
    }


    public static double sum(double[] vector) {
        double sum = 0.0;
        for (double num : vector) {
            sum += num;
        }
        return sum;
    }

    public static double[] multiply(double[] v1, double[] v2) {
        double[] answer = new double[v1.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = v1[i] * v2[i];
        }
        return answer;
    }

    public static double[][] multiply(double[][] m1, double[][] m2) {
        double[][] answer = new double[m1.length][m1[0].length];
        for (int row = 0; row < m1.length; row++) {
            answer[row] = multiply(m1[row], m2[row]);
        }
        return answer;
    }

    public static double[] multiply(double s, double[] vector) {
        double[] answer = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            answer[i] = vector[i] * s;
        }
        return answer;
    }

    public static double[][] multiply(double s, double[][] matrix) {
        double[][] answer = new double[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            answer[row] = multiply(s, matrix[row]);
        }
        return answer;
    }


    public static double[] getColumn(double[][] matrix, int col) {
        double[] column = new double[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            column[row] = matrix[row][col];
        }
        return column;
    }

    public static void setColumn(double[][] matrix, int col, double[] vector) {
        for (int row = 0; row < vector.length; row++) {
            matrix[row][col] = vector[row];
        }
    }


    public static double[] dot(double[][] matrix, double[] vector) {
        double[] answer = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            double[] product = multiply(matrix[i], vector);
            answer[i] = sum(product);
        }
        return answer;
    }

    public static double[][] dot(double[][] m1, double[][] m2) {
        double[][] answer = new double[m1.length][m2[0].length];
        for (int ncol = 0; ncol < m2[0].length; ncol++) {
            double[] col = getColumn(m2, ncol);
            double[] product = dot(m1, col);
            setColumn(answer, ncol, product);
        }
        return answer;
    }


    public static double[][] colPlus(double[][] matrix, double[] columnVector) {
        double[][] answer = new double[matrix.length][matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                answer[row][col] = matrix[row][col] + columnVector[row];
            }
        }
        return answer;
    }

    
    public static double[][] transpose(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        double[][] answer = new double[cols][rows];
        for (int oldRow = 0; oldRow < rows; oldRow++) {
            for (int oldCol = 0; oldCol < cols; oldCol++) {
                answer[oldCol][oldRow] = matrix[oldRow][oldCol];
            }
        }
        return answer;
    }

    public static double[] colMean(double[][] matrix) {
        double[] answer = new double[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            double sum = 0.0;
            for (int col = 0; col < matrix[0].length; col++) {
                sum += matrix[row][col];
            }
            answer[row] = sum / matrix[0].length;
        }
        return answer;
    }

    public static double[] minus(double[] v1, double[] v2) {
        double[] answer = new double[v1.length];

        for (int i = 0; i < v1.length; i++) {
            answer[i] = v1[i] - v2[i];
        }
        return answer;
    }

    public static double[][] minus(double[][] m1, double[][] m2) {
        int rows = m1.length;
        int cols = m1[0].length;
        double[][] answer = new double[rows][cols];

        for (int row = 0; row < rows; row++) {
            answer[row] = minus(m1[row], m2[row]);
        }
        return answer;
    }

    public static double[][] exp(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] answer = new double[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                answer[row][col] = Math.exp(matrix[row][col]);
            }
        }
        return answer;
    }


    public static String showShape(double[] vector) {
        String shape = "(" + vector.length + ")";
        return shape;
    }

    public static String showShape(double[][] matrix) {
        int rows = matrix.length;
        int cols =  matrix[0].length;

        String shape = "(" + rows + ", " + cols + ")";
        return shape;
    }
}
