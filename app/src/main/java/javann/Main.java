package javann;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javann.structs.DataStruct;


public class Main {
    public static void main(String[] args) {
        String filePath = "C:/Users/gordo/OneDrive/Desktop/py_ds/NeuralNetwork/data/train.csv";

        DataStruct data = getCSVData(filePath);

        int trainingDataSize = 10_000;
        DataStruct trainingData = getDataSection(0, trainingDataSize, data);
        DataStruct testingData = getDataSection(trainingDataSize, data.labels.length, data);

        NeuralNetwork neuralNetwork = new NeuralNetwork();
        neuralNetwork.train(trainingData.images, trainingData.labels, 250);
    }

    public static DataStruct getCSVData(String filePath) {
        DataStruct dataStruct;
        try {
            FileReader path = new FileReader(filePath);
            BufferedReader csvReader = new BufferedReader(path);
            
            // Skip csv headers.
            csvReader.readLine();
        
            ArrayList<double[]> unprocessedImages = new ArrayList<double[]>();
            ArrayList<Double> unprocessedLabels = new ArrayList<Double>();

            String line;
            while ((line = csvReader.readLine()) != null) {
                String[] strValues = line.split(",");
                
                double[] values = new double[strValues.length - 1];
                for (int i = 0; i < strValues.length; i++) {
                    if (i == 0) {
                        unprocessedLabels.add(Double.valueOf(strValues[i]));
                    }
                    else {
                        values[i - 1] = (double) (Double.valueOf(strValues[i])) / 255.0;
                    }
                }
                unprocessedImages.add(values);
            }

            csvReader.close();

            // Convert to double arrays.
            double[] labels = new double[unprocessedLabels.size()];
            for (int i = 0; i < unprocessedLabels.size(); i++) {
                labels[i] = unprocessedLabels.get(i).doubleValue();
            }

            double[][] images = new double[unprocessedImages.size()][unprocessedImages.get(0).length];
            for (int row = 0; row < unprocessedImages.size(); row++) {
                images[row] = unprocessedImages.get(row);
            }

            dataStruct = new DataStruct(images, labels);
        }
        catch (IOException e) {
            System.out.println(e);
            dataStruct = new DataStruct(new double[1][1], new double[1]);
        }
        
        return dataStruct;
    }

    public static DataStruct getDataSection(int dataStart, int dataEnd, DataStruct dataStruct) {
        double[][] images = dataStruct.images;
        double[] labels = dataStruct.labels;

        int dataLength = dataEnd - dataStart;

        double[][] sectionImages = new double[dataLength][images[0].length];
        double[] sectionLabels = new double[dataLength];

        for (int i = 0; i < dataLength; i++) {
            sectionImages[i] = images[dataStart + i];
            sectionLabels[i] = labels[dataStart + i];
        }

        return new DataStruct(sectionImages, sectionLabels);
    }
}
