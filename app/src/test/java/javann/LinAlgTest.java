/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package javann;

import org.junit.Test;
import static org.junit.Assert.*;


public class LinAlgTest extends ArrayTest {
    @Test
    public void transpose() {
        double[][] result = LinAlg.transpose(m1);
        double[][] expected = {{0, 3}, {1, 4}, {2, 5}};
        assertDeepArrayEquals(expected, result);
    }

    @Test
    public void matrixDotMatrix() {
        double[][] result = LinAlg.dot(m1, m2);
        double[][] expected = {{15, 18, 21}, {42, 54, 66}};
        assertDeepArrayEquals(expected, result);
    }

    @Test
    public void colPlus() {
        double[][] result = LinAlg.colPlus(m1, v1);
        double[][] expected = {{1, 2, 3}, {5, 6, 7}};
        assertDeepArrayEquals(expected, result);
    }

    @Test
    public void scalarMultiplyMatrix() {
        double[][] result = LinAlg.multiply(s, m1);
        double[][] expected = {{0, 2, 4}, {6, 8, 10}};
        assertDeepArrayEquals(expected, result);
    }

    @Test
    public void matrixMultiplyMatrix() {
        double[][] result = LinAlg.multiply(m1, m3);
        double[][] expected = {{0, -1, 0}, {0, 4, 10}};
        assertDeepArrayEquals(expected, result);
    }

    @Test
    public void colMean() {
        double[] result = LinAlg.colMean(m1);
        double[] expected = {1, 4};
        assertArrayEquals(expected, result, FLOAT_ERROR);
    }


    @Test
    public void minus() {
        double[][] result = LinAlg.minus(m1, m3);
        double[][] expected = {{2, 2, 2}, {3, 3, 3}};
        assertDeepArrayEquals(expected, result);
    }
}
