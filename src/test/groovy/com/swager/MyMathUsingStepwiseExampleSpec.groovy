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

    def "Step 1: create the denominator"(){

    }

    def "Step 2: double denominator"(){

    }

    def "Step 3: create the numerator"(){

    }

    def "Step 4: run prettyPrintPercentageOfDivision using #num as the numerator and #den as denominator"(){

    }

    def "Step 5: set denominator to 0"(){

    }

    def "Step 6: run prettyPrintPercentageOfDivision using #num as the numerator and #den as denominator throws an illegalArgumentException"(){

    }

}