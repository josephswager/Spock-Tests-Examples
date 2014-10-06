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
    public void setup(){
        math = new MyMath();
    }

    @Test
    public void testDoubleNumber() throws Exception {
        // Arrange
        Integer numberExpected = new Integer(20);

        // Act
        Integer numberDoubled = (Integer) math.doubleNumber(10);

        // Assert
        assertEquals(numberExpected, numberDoubled);
    }


    @Test
    public void testDoubleNumber_expected_number_is_wrong_to_prove_method_works() throws Exception {
        // Arrange
        Integer numberExpected = new Integer(100);

        // Act
        Integer numberDoubled = (Integer) math.doubleNumber(10);

        // Assert
        assertNotEquals(numberExpected, numberDoubled);
        // numberDoubled is 20 not the mistaken 100 expected
    }




//    @Test
//    public void testPrettyPrintPercentageOfDivision() throws Exception {
//
//    }
}