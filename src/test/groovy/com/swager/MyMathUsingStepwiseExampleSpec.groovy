package com.swager

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise


/**
 * Created by josephswager on 10/6/14.
 */
@Stepwise
class MyMathUsingStepwiseExampleSpec extends Specification {

    @Shared
    MyMath math = new MyMath()

    @Shared
    def num = new BigDecimal(0)

    @Shared
    def den = new BigDecimal(0)

    def "Step 1: give the denominator a value of 5.5"(){

        when:
        den = 5.5

        then:
        den > 0
        den == 5.5
    }

    def "Step 2: double denominator"(){
        when:
        den = math.doubleNumber(den)

        then:
        5.5 == old(den)
        den == 11

    }

    def "Step 3: create the numerator"(){
        when:
        num = 2

        then:
        num > 0
        num == 2

    }

    def "Step 4: run prettyPrintPercentageOfDivision using #num as the numerator and #den as denominator"(){

        when:
        def percentage = math.prettyPrintPercentageOfDivision(num, den)

        then:
        percentage == '18.18%'

    }

    def "Step 5: set denominator to 0"(){
        when:
        den = 0

        then:
        11 == old(den)
        den == 0

    }

    def "Step 6: run prettyPrintPercentageOfDivision using #num as the numerator and #den as denominator throws an illegalArgumentException"(){
        when:
        def percentage = math.prettyPrintPercentageOfDivision(num, den)

        then:
        IllegalArgumentException ex = thrown()

        ex.message == "Argument 'divisor' is 0"
    }

}