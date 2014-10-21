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
    public void testDoubleNumber() throws Exception {
        Integer numberExpected = new Integer(20);

        Integer numberDoubled = (Integer) math.doubleNumber(10);

        assertEquals(numberExpected, numberDoubled);
    }


    @Test
    public void testDoubleNumber_expected_number_is_wrong_to_prove_method_works() throws Exception {
        Integer numberExpected = new Integer(100);

        Integer numberDoubled = (Integer) math.doubleNumber(10);

        assertNotEquals(numberExpected, numberDoubled);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testDoubleNumber_passed_null_throws_illegalArgumentException() throws Exception {
        Integer numberDoubled = (Integer) math.doubleNumber(null);
        assertNull(numberDoubled);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoubleNumber_passed_not_a_number_throws_illegalArgumentException() throws Exception {
        Integer numberDoubled = (Integer) math.doubleNumber("one");
        assertNull(numberDoubled);
    }

}