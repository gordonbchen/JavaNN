package javann;

import org.junit.Test;
import static org.junit.Assert.*;


public class FunctionsTest extends ArrayTest {
    @Test
    public void ReLU() {
        double[][] result = Functions.ReLU(m3);
        double[][] expected = {{0, 0, 0}, {0, 1, 2}};
        assertDeepArrayEquals(expected, result);
    }

    @Test
    public void ReLU_Deriv() {
        double[][] result = Functions.ReLU_Deriv(m3);
        double[][] expected = {{0, 0, 0}, {0, 1, 1}};
        assertDeepArrayEquals(expected, result);
    }

    @Test
    public void softmax() {
        double[][] result = Functions.softmax(m4);
        double[][] expected = {
            {6.68679417e-03, 3.33106430e-04, 5.89975040e-03},
            {9.04959183e-04, 6.69062149e-03, 1.18499655e-01},
            {9.92408247e-01, 9.92976272e-01, 8.75600595e-01}
        };
        assertDeepArrayEquals(expected, result);
    }

    @Test
    public void softmaxDeriv() {
        double[][] result = Functions.softmaxDeriv(m4);
        double[][] expected = {
            {0.00664208, 0.000333  , 0.00586494},
            {0.00090414, 0.00664586, 0.10445749},
            {0.00753412, 0.0069744 , 0.10892419}
        };
        assertDeepArrayEquals(expected, result);
    }

    @Test
    public void meanSquaredErrorDeriv() {
        double [][] result = Functions.meanSquaredErrorDeriv(m2, m4);
        double [][] expected = {
            { 0.        ,  3.33333333, -0.66666667},
            { 3.33333333,  3.33333333, -0.66666667},
            { 0.66666667,  2.        ,  0.        }
        };
        assertDeepArrayEquals(expected, result);
    }

    @Test
    public void oneHotEncodeDecode() {
        double[][] encodedExpected = {
            {0, 0},
            {1, 0},
            {0, 1},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0}
        };
        double[][] encodedResult = Functions.oneHotEncode(v1);
        assertDeepArrayEquals(encodedExpected, encodedResult);

        double[] decodedResult = Functions.oneHotDecode(encodedExpected);
        assertArrayEquals(v1, decodedResult, FLOAT_ERROR);
    }

    @Test
    public void getPercentAccuracy() {
        double result = Functions.getPercentAccuracy(v2, v3);
        double expected = 40.0;
        assertEquals(expected, result, FLOAT_ERROR);
    }
}
