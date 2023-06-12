package javann;

import static org.junit.Assert.assertArrayEquals;

public class ArrayTest {
    public static final double FLOAT_ERROR = 1e-6;

    public static final double[][] m1 = {{0, 1, 2}, {3, 4, 5}};
    public static final double[][] m2 = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};

    public static final double[][] m3 = {{-2, -1, 0}, {0, 1, 2}};
    public static final double[][] m4 = {{0, -4, 3}, {-2, -1, 6}, {5, 4, 8}};

    public static final double[] v1 = {1, 2};

    public static final double[] v2 = {0, 1, 2, 3, 4};
    public static final double[] v3 = {-1, 1, 2, 4, 3};

    public static final double s = 2.0;

    public static void assertDeepArrayEquals(double[][] expected, double[][] actual) {
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], FLOAT_ERROR);
        }
    }
}
