package com.arnaumarti.calculator.helpers;

import java.util.Random;

/**
 * Created by arnau on 25/10/16.
 */

public class MathUtil {

    private final static String CHI = "\u03A7";
    private final static String EPSILON = "\u03B5";

    /**
     * Returns a psuedo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimim value
     * @param max Maximim value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {
        // Usually this can be a field rather than a method variable
        Random rand = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    public static String convertIntB10toStringB12(int intNumB10) {
        String strNumB12 = Integer.toString(intNumB10, 12);

        return strNumB12;
    }
    public static String convertIntB10toStringB12ChiEpsilon(int intNumB10) {
        String strNumB12 = convertIntB10toStringB12(intNumB10);
        String a10 = "a";
        String b11 = "b";
        if (strNumB12.contains(a10)) {

            strNumB12 = strNumB12.replaceAll(a10, CHI);
        }

        if (strNumB12.contains(b11)) {

            strNumB12 = strNumB12.replaceAll(b11, EPSILON);
        }
        return strNumB12;
    }
    public static int convertStringB12ToIntB10(String maxNumStringB12) {
        return Integer.valueOf(maxNumStringB12,12);
    }
    // max number B10 = 9999999 -> max digit B12 = bbbbbbb = εεεεεεε
    public static String getMaxNumStringB12(String maxDigitStringBase12, int maxNumDigits) {
        String result ="";
        for (int i = 0; i < maxNumDigits; i++) {
            result = result.concat(maxDigitStringBase12);
        }
        return result;
    }
}
