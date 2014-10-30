package com.swager

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise


/**
 * @author joseph swager
 * Created by josephswager on 10/6/14.
 * Used to show a gaunted order run on methods is available
 */
@Stepwise
class MyMathUsingStepwiseExampleSpec extends Specification {

    @Shared
    MyMath math = new MyMath()

    @Shared
    def num = new BigDecimal(0)

    @Shared
    def den = new BigDecimal(0)

    def "Step 1: give the denominator a value of 5.5 then it den will be greater than zero and equal 5.5"() {

        when:
        den = 5.5

        then:
        den > 0
        den == 5.5
    }

    def "Step 2: double denominator then the den value will be 11 also we can seee the old method shows dens value is 5.5 before me made any changes"() {
        when:
        den = math.doubleNumber(den)

        then:
        5.5 == old(den)
        den == 11

    }

    def "Step 3: create the numerator with the given value 2 then its value will be greater than x=zero and equal to 2"() {
        when:
        num = 2

        then:
        num > 0
        num == 2

    }

    def "Step 4: when run prettyPrintPercentageOfDivision using #num as the numerator and #den as denominator the output will be a correctly formatted percentage to the second decimal and no exceptions will be thrown "() {

        when:
        def percentage = math.prettyPrintPercentageOfDivision(num, den)

        then:
        percentage == '18.18%'
        notThrown IllegalArgumentException

    }

    def "Step 5: when we set  denominator to 0 then it will equal zero and we can run the old method to see before we changed it wr had it set to 11"() {
        when:
        den = 0

        then:
        11 == old(den)
        den == 0

    }

    def "Step 6: when we run prettyPrintPercentageOfDivision using #num as the numerator and #den as denominator throws an illegalArgumentException because we looking for divisors of zero which would cause and Arithmetic exception so we check this and then throw the exception already stated with the message 'Argument 'divisor' is 0'"() {
        when:
        def percentage = math.prettyPrintPercentageOfDivision(num, den)

        then:
        IllegalArgumentException ex = thrown()

        ex.message == "Argument 'divisor' is 0"
    }

}