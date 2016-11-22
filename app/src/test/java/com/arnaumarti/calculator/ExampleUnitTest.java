package com.arnaumarti.calculator;

import android.util.Log;

import org.junit.Test;

import com.arnaumarti.calculator.helpers.MathUtil;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testMaxNumberB12() throws Exception {
        final int BASE12 = 12;
        final int MAX_DIGIT_INT_BASE12 = BASE12 - 1;
        final String MAX_DIGIT_STRING_BASE12 = MathUtil.convertIntB10toStringB12(MAX_DIGIT_INT_BASE12);// = b
        //Max numDigits=7->
        final int MAX_NUM_DIGITS_FOR_NUMBERS = 7;

        final String MAX_NUM_STRING_B12 = getMaxNumStringB12(MAX_DIGIT_STRING_BASE12, MAX_NUM_DIGITS_FOR_NUMBERS);

        final int MAX_NUM_INT_B12 = MathUtil.convertStringB12ToIntB10(MAX_NUM_STRING_B12);

        Log.e("MAX_NUM_INT_B12", String.valueOf(MAX_NUM_INT_B12));

        assertEquals(MAX_NUM_STRING_B12, "bbbbbbb");
    }

    // max number B10 = 9999999 -> max digit B12 = bbbbbbb = εεεεεεε
    private String getMaxNumStringB12(String maxDigitStringBase12, int maxNumDigits) {
        String result ="";
        for (int i = 0; i < maxNumDigits; i++) {
            result = result.concat(maxDigitStringBase12);
        }
        return result;
    }
}