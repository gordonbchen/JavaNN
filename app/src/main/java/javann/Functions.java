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
        for (int col = 0; col < cols; col++) {
            // Find column sum.
            double colSum = 0.0;
            for (int row = 0; row < rows; row++) {
                colSum += Math.exp(safeMatrix[row][col]);
            }

            for (int row = 0; row < rows; row++) {
                answer[row][col] = Math.exp(safeMatrix[row][col]) / colSum;
            }
        }

        return answer;
    }

    public static double[][] softmaxDeriv(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] answers = new double[rows][cols];

        double[][] safeMatrix = getSafeMatrix(matrix);

        double[] colMaxVals = colMax(safeMatrix);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double e_X = Math.exp(safeMatrix[row][col]);
                double a = colMaxVals[col] - e_X;
                
                answers[row][col] = (a * e_X) / (Math.pow(colMaxVals[col], 2));
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


    public static double[][] meanSquaredErrorDeriv(double[][] pred, double[][] actual) {
        int rows = pred.length;
        int cols = pred[0].length;
        double[][] answer = new double[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double error = pred[row][col] - actual[row][col];
                answer[row][col] = (2.0 / pred[0].length) * error;
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
            if (actual[i] == guess[i]) {
                correct++;
            }
        }

        double correctDecimal = correct / ((double) (actual.length));
        return correctDecimal * 100.0;
    }
}
