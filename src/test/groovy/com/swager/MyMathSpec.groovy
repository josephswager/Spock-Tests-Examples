package com.swager

import org.junit.Rule
import org.junit.rules.TestName
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static spock.util.matcher.HamcrestMatchers.closeTo

/**
 * Created by josephswager on 10/3/14.
 */
class MyMathSpec extends Specification {

    @Shared
    MyMath math = new MyMath()

    @Rule
    TestName name

    // very junit like in out test just backwards we right our assertion first then our arrangements
    @Unroll
    def "test doubleNumber method when #number doubled  result is #result"() {

        expect:
        number + number == result

        where:
        number || result
        1      || math.doubleNumber(1)
        2      || math.doubleNumber(2)
        10     || math.doubleNumber(10)
        100    || math.doubleNumber(100)


    }

    def "test doubleNumber method where invalid expected is not doubled to prove method is returning double of number given"() {
        when:
        int number = 4
        int wrongNumberExpected = 9
        def result = math.doubleNumber(number)

        then:
        result != wrongNumberExpected


    }

    // you can easily catch thrown exception messages and check message is whats expected
    def "test doubleNumber method where null is passed throwing IllegalArgumentException"() {

        when:
        def result = math.doubleNumber(null)

        then:
        IllegalArgumentException ex = thrown()

        ex.message == 'Value is null or not an instance of a Number or its inherited classes'


    }

    def "test doubleNumber where method is given an Argument that is not of a Number class or its inhearantence throwing IllegalArgumentException"() {
        given:
        def notNumber = "This is not a number!"
        when:
        def result = math.doubleNumber(notNumber)

        then:
        IllegalArgumentException ex = thrown()

        ex.message == 'Value is null or not an instance of a Number or its inherited classes'
    }

    // shows how HamcrestMatchers are heavily woven into Spock assertions
    def "comparing two decimal numbers"() {

        def myPI = 3.14

        expect:
        myPI closeTo(Math.PI, 0.01)
    }

    // Complete BDD Style of spec testing and the way I personally prefer
    def "test prettyPrintPercentageOfDivision when given 3 divided by 9"() {
        given:
        Double num = 3
        Double dom = 9
        when:
        String percentage = math.prettyPrintPercentageOfDivision(num, dom)
        then:
        percentage == "33.33%"
    }


    def "test prettyPrintPercentageOfDivision when given large numerator  divided by small denominator"() {
        given:
        Double num = 44321
        Double dom = 12
        when:
        String percentage = math.prettyPrintPercentageOfDivision(num, dom)
        then:
        percentage == "3693.42%"
    }

    // just showing you can capture the test method name for logging purposes with spock easily
    def "retrieve test name at runtime"() {
        println "entering '$name.methodName'"

        expect:
        math.doubleNumber(1) == 2

        println "leaving '$name.methodName'"
    }

    // this portion of the test will show how easy stack testing work in spock

    def "size of typesOfMath should have init to 0(empty)"() {
        expect:
        math.typesOfMath.size() == 0
    }

    def "when we pop on typesOfMath should and no maths have been added since init should throw EmptyStackException"() {
        when:
        math.typesOfMath.pop()
        then:
        EmptyStackException ex = thrown()
        // thrown(EmptyStackException) is the equivalent as above I suggest you pick the one that matches your style and stick to it
    }

    def "when we peek on typesOfMath should and no maths have been added since init should throw EmptyStackException"() {
        when:
        math.typesOfMath.peek()
        then:
        EmptyStackException ex = thrown()
    }

    def "when we push one math type onto typesOfMath we get a stack size of 1 and can now peek at the value of the math type"() {
        when:
        math.typesOfMath.push("Algebra")

        then:
        math.typesOfMath.size() == old(math.typesOfMath.size()) + 1
        math.typesOfMath.peek() == "Algebra"
    }
}
/**
 * This spec class shows off the @Setup  which is just like junit instead of @Shared
 * with the Setup you will get a new instance each time where shared is initialized just once for the entire test class
 * this also shows the power of groovy having many classes in one file nd not having to deal with messy inner classes
 * which i find very convenient to test a class in many different ways
 */
class MyMathWithOneElementInTypesOfMathSpec extends Specification {
    def math = new MyMath()

    def setup() {
        math.typesOfMath.push("Trigonometry")
    }

    def "size of typesOfMath should have one"() {
        expect:
        math.typesOfMath.size() == 1
    }

    def "pop Trigonometry off typesOfMath to bring its size to 0"() {
        when:
        def mathType = math.typesOfMath.pop()

        then:
        mathType == "Trigonometry"
        math.typesOfMath.size() == 0
    }

    def "peek at typesOfMath and it will be Trigonometry"() {
        when:
        def mathType = math.typesOfMath.peek()

        then:
        mathType == "Trigonometry"
        math.typesOfMath.size() == 1
    }

    def "adds math type Algebra which brings size of typesOfMath to 2 then we peek and pop and it brings the size of typesOfMath back to 1 and math type Trigonometry is the only math type left"() {
        when:
        math.typesOfMath.push("Algebra")

        then:
        math.typesOfMath.size() == 2
        math.typesOfMath.peek() == "Algebra"

        // show you that you can have as many when and then statements in a test as long as they are followed in that order
        when:
        math.typesOfMath.pop()

        then:
        math.typesOfMath.size() == 1
        math.typesOfMath.peek() == "Trigonometry"
    }
}