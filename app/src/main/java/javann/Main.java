package main.java.javann;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        double[][] a = {{1, 2}, {3, 4}};
        double[][] b = {{1, 3, 5}, {2, 4, 6}};
        double[][] c = LinearAlgebra.dot(a, b);
        System.out.println(Arrays.deepToString(c));
    }
}
