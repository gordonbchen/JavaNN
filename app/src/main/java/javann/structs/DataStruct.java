package javann.structs;

public class DataStruct {
    public double[][] images;
    public double[] labels;

    public DataStruct(double[][] images, double[] labels) {
        this.images = images;
        this.labels = labels;
    }
}
