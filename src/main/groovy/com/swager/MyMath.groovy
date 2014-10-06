package com.swager

import java.math.RoundingMode

/**
 * Basic math operations for testing examples
 * @author joseph swager
 * Created by josephswager on 10/3/14.
 */
class MyMath {

    def typesOfMath = new Stack()

    /**
     * Doubles a number
     * @param number - Number to be doubled
     * @return - Number object doubled
     */
    def doubleNumber(number) {
        if (number instanceof Number) {
            number + number
        } else {
            throw new IllegalArgumentException("Value is null or not an instance of a Number or its inherited classes")
        }
    }

    /**
     * gives a nice string in percentage of the division
     * @param numerator - BigDecimal dividend
     * @param denominator - BigDecimal divisor
     * @return - String of formatted percentage to the max of second decimal
     */
    String prettyPrintPercentageOfDivision(BigDecimal numerator, BigDecimal denominator) {
        if (numerator == null || denominator == null) {
            throw new IllegalArgumentException("Argument 'dividend' nor 'divisor' can be null")
        }

        if (denominator == 0) {
            throw new IllegalArgumentException("Argument 'divisor' is 0")
        }

        def quotient
        // this method division exactness(precession) is off a little,
        // but covers what we need to show a spock testing for a percentage string
        if (denominator >= numerator) {
            quotient = numerator.divide(denominator, new java.math.MathContext(4))
            return (quotient * 100).stripTrailingZeros() + "%"
        } else {
            quotient = (numerator / denominator).setScale(2, RoundingMode.CEILING)
            return quotient + "%"
        }


    }
}
