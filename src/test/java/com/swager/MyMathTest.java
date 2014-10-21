package com.swager;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test class is provided to compare spock to junit tests
 * NOTE: not all test are represented in this class that are in the spock spec test class
 */
public class MyMathTest {

    MyMath math;

    @Before
    public void setup() {
        math = new MyMath();
    }

    @Test
    public void givenNumberToMethodDoubleNumberExpectedOutcomeIsANumberDoubled() {
        Integer numberExpected = new Integer(20);
        Integer numberDoubled = (Integer) math.doubleNumber(10);

        assertEquals(numberExpected, numberDoubled);
    }


    @Test
    public void givenNumberToMethodDoubleNumberExpectedNumberToBeWrongOutcomeToProveMethodWorks() {
        Integer numberExpected = new Integer(100);
        Integer numberDoubled = (Integer) math.doubleNumber(10);

        assertNotEquals(numberExpected, numberDoubled);
    }


    @Test(expected = IllegalArgumentException.class)
    public void GivenANullValueTotDoubleNumberMethodAndExpectExceptionOfIllegalArgumentExceptionToBeThrown() {
        Integer numberDoubled = (Integer) math.doubleNumber(null);
        assertNull(numberDoubled);
    }

    @Test(expected = IllegalArgumentException.class)
    public void GivenANonValidNumberValueTotDoubleNumberMethodAndExpectExceptionOfIllegalArgumentExceptionToBeThrown() {
        Integer numberDoubled = (Integer) math.doubleNumber("one");
        assertNull(numberDoubled);
    }

}