package main.java.javann;


public class LinearAlgebra {

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
        double[] answer = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            double[] product = multiply(matrix[i], vector);
            answer[i] = sum(product);
        }
        return answer;
    }

    public static double[][] dot(double[][] m1, double[][] m2) {
        double[][] answer = new double[m1.length][m2[0].length];
        for (int col = 0; col < m2[0].length; col++) {
            double[] product = dot(m1, getColumn(m2, col));
            setColumn(answer, col, product);
        }
        return answer;
    }
}
