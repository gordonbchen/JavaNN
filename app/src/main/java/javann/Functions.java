package javann;


public class Functions {

    public static double[][] ReLU(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] answer = new double[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                answer[row][col] = Math.max(matrix[row][col], 0.0);
            }
        }
        return answer;
    }

    public static double[][] ReLU_Deriv(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] answer = new double[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double deriv = 0.0;
                if (matrix[row][col] > 0.0) {
                    deriv = 1.0;
                }

                answer[row][col] = deriv;
            }
        }
        return answer;
    }


    public static double[][] softmax(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] answer = new double[rows][cols];

        double[][] safeMatrix = getSafeMatrix(matrix);
        double[][] e_X = LinAlg.exp(safeMatrix);

        for (int col = 0; col < cols; col++) {
            // Find column sum.
            double colSum = 0.0;
            for (int row = 0; row < rows; row++) {
                colSum += e_X[row][col];
            }

            for (int row = 0; row < rows; row++) {
                answer[row][col] = e_X[row][col] / colSum;
            }
        }

        return answer;
    }

    public static double[][] softmaxDeriv(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] answers = new double[rows][cols];

        double[][] safeMatrix = getSafeMatrix(matrix);
        double[][] e_X = LinAlg.exp(safeMatrix);

        double[] colSums = colSum(e_X);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double a = colSums[col] - e_X[row][col];
                
                answers[row][col] = (a * e_X[row][col]) / (Math.pow(colSums[col], 2));
            }
        }

        return answers;
    }


    public static double[][] getSafeMatrix(double[][] matrix) {
        double max = max(matrix);

        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] answer = new double[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                answer[row][col] = matrix[row][col] - max;
            }
        }
        return answer;
    }


    public static double max(double[][] matrix) {
        double[] colMaxVals = colMax(matrix);

        double maxVal = colMaxVals[0];
        for (double colMaxVal : colMaxVals) {
            maxVal = Math.max(maxVal, colMaxVal);
        }
        return maxVal;
    }

    public static double[] colMax(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        double[] colMaxVals = new double[cols];
        for (int col = 0; col < cols; col++) {
            double colMaxVal = matrix[0][col];
            for (int row = 0; row < rows; row++) {
                colMaxVal = Math.max(colMaxVal, matrix[row][col]);
            }
            colMaxVals[col] = colMaxVal;
        }
        return colMaxVals;
    }

    public static double[] colSum(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        double[] colSums = new double[cols];
        for (int col = 0; col < cols; col++) {
            double colSum = 0.0;
            for (int row = 0; row < rows; row++) {
                colSum += matrix[row][col];
            }
            colSums[col] = colSum;
        }
        return colSums;
    }


    public static double[][] meanSquaredErrorDeriv(double[][] pred, double[][] actual) {
        int rows = pred.length;
        int cols = pred[0].length;
        double[][] answer = new double[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double error = pred[row][col] - actual[row][col];
                answer[row][col] = (2.0 / pred.length) * error;
            }
        }
        return answer;
    }


    public static double[][] oneHotEncode(double[] Y) {
        double[][] encoded = new double[10][Y.length];
        for (int col = 0; col < Y.length; col++) {
            int num = (int) Y[col];
            encoded[num][col] = 1.0;
        }
        return encoded;
    }

    public static double[] oneHotDecode(double[][] Z) {
        double[] decoded = new double[Z[0].length];
        for (int col = 0; col < Z[0].length; col++) {
            int maxRow = 0;
            double maxValue = Z[0][col];

            for (int row = 1; row < Z.length; row++) {
                if (Z[row][col] > maxValue) {
                    maxValue = Z[0][col];
                    maxRow = row;
                }
            }

            decoded[col] = maxRow;
        }

        return decoded;
    }


    public static double getPercentAccuracy(double[] actual, double[] guess) {
        double correct = 0;
        for (int i = 0; i < actual.length; i++) {
            if (((int) (actual[i])) == ((int) (guess[i]))) {
                correct++;
            }
        }

        double correctDecimal = correct / ((double) (actual.length));
        return correctDecimal * 100.0;
    }
}
