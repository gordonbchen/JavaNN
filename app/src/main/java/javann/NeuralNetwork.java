package javann;

import java.util.*;

import javann.structs.*;


public class NeuralNetwork {
    // Layer 1
    private final int LAYER_1_INPUTS = 784;
    private final int LAYER_1_NODES = 12;

    private double[][] W1;
    private double[] b1;

    // Layer 2
    private final int LAYER_2_INPUTS = 12;
    private final int LAYER_2_NODES = 10;

    private double[][] W2;
    private double[] b2;

    // Hyper.
    private final double LEARNING_RATE = 0.5;
    private final int MINI_BATCH_SIZE = 256;

    public NeuralNetwork() {
        // Initialize weights, biases, and define activation functions.
        W1 = LinAlg.createRandomDoubleArray(LAYER_1_NODES, LAYER_1_INPUTS);
        b1 = LinAlg.createRandomDoubleArray(LAYER_1_NODES);

        W2 = LinAlg.createRandomDoubleArray(LAYER_2_NODES, LAYER_2_INPUTS);
        b2 = LinAlg.createRandomDoubleArray(LAYER_2_NODES);
    }

    public ActStruct forwardProp(double[][] X) {
        double[][] X_T = LinAlg.transpose(X);

        double[][] Z1 = LinAlg.colPlus(LinAlg.dot(W1, X_T), b1);
        double[][] A1 = Functions.ReLU(Z1);

        double[][] Z2 = LinAlg.colPlus(LinAlg.dot(W2, A1), b2);
        double[][] A2 = Functions.softmax(Z2);

        ActStruct actStruct = new ActStruct(Z1, A1, Z2, A2);
        return actStruct;
    }

    public DerivStruct backProp(ActStruct actStruct, double[][] X, double[] y) {
        double[][] Z1 = actStruct.Z1;
        double[][] A1 = actStruct.A1;
        double[][] Z2 = actStruct.Z2;
        double[][] A2 = actStruct.A2;

        double[][] oneHotY = Functions.oneHotEncode(y);

        double[][] dA2 = Functions.meanSquaredErrorDeriv(A2, oneHotY);
        double[][] dZ2 = LinAlg.multiply(dA2, Functions.softmaxDeriv(Z2));

        double[][] dW2 = LinAlg.multiply((1.0 / y.length), LinAlg.dot(dZ2, LinAlg.transpose(A1)));
        double[] db2 = LinAlg.colMean(dZ2);

        double[][] dA1 = LinAlg.dot(LinAlg.transpose(W2), dZ2);
        double[][] dZ1 = LinAlg.multiply(dA1, Functions.ReLU_Deriv(Z1));

        double[][] dW1 = LinAlg.multiply((1.0 / y.length), LinAlg.dot(dZ1, X));
        double[] db1 = LinAlg.colMean(dZ1);

        DerivStruct derivStruct = new DerivStruct(db1, dW1, db2, dW2);
        return derivStruct;
    }

    public void applyGradients(DerivStruct derivStruct) {
        double[] db1 = derivStruct.db1;
        double[][] dW1 = derivStruct.dW1;
        double[] db2 = derivStruct.db2;
        double[][] dW2 = derivStruct.dW2;

        b1 = LinAlg.minus(b1, LinAlg.multiply(LEARNING_RATE, db1));
        W1 = LinAlg.minus(W1, LinAlg.multiply(LEARNING_RATE, dW1));
        b2 = LinAlg.minus(b2, LinAlg.multiply(LEARNING_RATE, db2));
        W2 = LinAlg.minus(W2, LinAlg.multiply(LEARNING_RATE, dW2));
    }

    public void gradientDescent(double[][] X, double[] y) {
        ActStruct actStruct = forwardProp(X);
        DerivStruct derivStruct = backProp(actStruct, X, y);

        applyGradients(derivStruct);
    }

    public void train(double[][] X, double[] y, int epochs) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int startIndex = 0; startIndex < y.length; startIndex += MINI_BATCH_SIZE) {
                int miniBatchSize = Math.min(X.length - startIndex, MINI_BATCH_SIZE);
                double[][] miniBatchX = getMiniBatchX(X, startIndex, miniBatchSize);
                double[] miniBatchY = getMiniBatchY(y, startIndex, miniBatchSize);

                gradientDescent(miniBatchX, miniBatchY);
            }

            // Shuffle data.
            Integer[] shuffledInds = getShuffledInds(y.length);
            X = getShuffledX(X, shuffledInds);
            y = getShuffledY(y, shuffledInds);

            if (epoch % 10 == 0) {
                double[][] A2 = forwardProp(X).A2;
                double[] guesses = Functions.oneHotDecode(A2);
                double percentAccuracy = Functions.getPercentAccuracy(y, guesses);
                System.out.println("Epoch " + epoch + "    accuracy=" + percentAccuracy);
            }
        }
    }

    public double[][] getMiniBatchX(double[][] X, int startIndex, int miniBatchSize) {
        double[][] miniBatchX = new double[miniBatchSize][X[0].length];
        for (int i = 0; i < miniBatchSize; i++) {
            miniBatchX[i] = X[startIndex + i];
        }
        return miniBatchX;
    }

    public double[] getMiniBatchY(double[] y, int startIndex, int miniBatchSize) {
        double[] miniBatchY = new double[miniBatchSize];
        for (int i = 0; i < miniBatchSize; i++) {
            miniBatchY[i] = y[startIndex + i];
        }
        return miniBatchY;
    }

    public Integer[] getShuffledInds(int length) {
        Integer[] inds = new Integer[length];
        for (int i = 0; i < length; i++) {
            inds[i] = i;
        }

        List<Integer> indList = Arrays.asList(inds);
        Collections.shuffle(indList);

        indList.toArray(inds);
        return inds;
    }

    public double[][] getShuffledX(double[][] X, Integer[] shuffledInds) {
        double[][] shuffled = new double[X.length][X[0].length];

        for (int i = 0; i < X.length; i++) {
            shuffled[i] = X[shuffledInds[i].intValue()];
        }
        return shuffled;
    }

    public double[] getShuffledY(double[] y, Integer[] shuffledInds) {
        double[] shuffled = new double[y.length];

        for (int i = 0; i < y.length; i++) {
            shuffled[i] = y[shuffledInds[i].intValue()];
        }
        return shuffled;
    }
}
