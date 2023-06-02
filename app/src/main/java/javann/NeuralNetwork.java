package main.java.javann;


public class NeuralNetwork {
    // Layer 1
    private final int LAYER_1_INPUTS = 784;
    private final int LAYER_1_NODES = 12;

    private double[][] layer1Weights;
    private double[] layer1Biases;

    // Layer 2
    private final int LAYER_2_INPUTS = 12;
    private final int LAYER_2_NODES = 10;

    private double[][] layer2Weights;
    private double[] layer2Biases;

    public NeuralNetwork() {
        // Initialize weights and biases.
        layer1Weights = LinearAlgebra.createRandomDoubleArray(LAYER_1_NODES, LAYER_1_INPUTS);
        layer1Biases = LinearAlgebra.createRandomDoubleArray(LAYER_1_NODES);

        layer2Weights = LinearAlgebra.createRandomDoubleArray(LAYER_2_NODES, LAYER_2_INPUTS);
        layer2Biases = LinearAlgebra.createRandomDoubleArray(LAYER_2_NODES);
    }
}
