package javann.structs;

public class DerivStruct {
    public double[] db1;
    public double[][] dW1;
    public double[] db2;
    public double[][] dW2;

    public DerivStruct(double[] db1, double[][] dW1, double[] db2, double[][] dW2) {
        this.db1 = db1;
        this.dW1 = dW1;
        this.db2 = db2;
        this.dW2 = dW2;
    }
}