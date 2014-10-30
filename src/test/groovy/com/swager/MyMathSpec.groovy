package com.swager

import org.junit.Rule
import org.junit.rules.TestName
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static spock.util.matcher.HamcrestMatchers.closeTo

/**
 * @author Joseph Swager
 * Created by josephswager on 10/3/14.
 * Basic Unit testing of the MyMath class to show spock testing
 */
class MyMathSpec extends Specification {

    @Shared
    MyMath math = new MyMath()

    @Rule
    TestName name

    // very junit like in out test just backwards we right our assertion first then our arrangements
    @Unroll
    def "test doubleNumber method when #number is doubled with the result of #result"() {

        expect:
        number + number == result

        where:
        number || result
        1      || math.doubleNumber(1)
        2      || math.doubleNumber(2)
        10     || math.doubleNumber(10)
        100    || math.doubleNumber(100)


    }

    def "test doubleNumber when valid input is given and then correct output is expected"() {
        when:
        int number = 4
        int numberExpected = 8
        def result = math.doubleNumber(number)

        then:
        result == numberExpected
    }

    def "test doubleNumber method  when valid number is given, but valid invalid double exception is then expected to prove method is doubling number correctly"() {
        when:
        int number = 4
        int wrongNumberExpected = 9
        def result = math.doubleNumber(number)

        then:
        result != wrongNumberExpected


    }

    // you can easily catch thrown exception messages and check message is whats expected
    def "test doubleNumber method when null is passed then resulting in IllegalArgumentException thrown"() {

        when:
        def result = math.doubleNumber(null)

        then:
        IllegalArgumentException ex = thrown()

        ex.message == 'Value is null or not an instance of a Number or its inherited classes'


    }

    def "test doubleNumber when method is given an Argument that is not of a Number class or its inheritance then resulting in an IllegalArgumentException thrown"() {
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
    def "test prettyPrintPercentageOfDivision when given 3 divided by 9 then the expected print is 33.33%"() {
        given:
        Double num = 3
        Double dom = 9
        when:
        String percentage = math.prettyPrintPercentageOfDivision(num, dom)
        then:
        percentage == "33.33%"
    }


    def "test prettyPrintPercentageOfDivision when given large numerator divided by small denominator then the result still rounds off to the second decimal"() {
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

    def "given default of typesOfMath then should have init to 0(empty)"() {
        expect:
        math.typesOfMath.size() == 0
    }

    def "given default of typesOfMath and no added values to the stack then run a pop method call should throw EmptyStackException"() {
        when:
        math.typesOfMath.pop()
        then:
        EmptyStackException ex = thrown()
        // thrown(EmptyStackException) is the equivalent as above I suggest you pick the one that matches your style and stick to it, It really is a style preference
    }

    def "given default of typesOfMath and no added values to the stack then run a peek method call should throw EmptyStackException"() {
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

    def "given a default setup with Trigonometry only when we run size method on typesOfMath then the result is 1"() {
        expect:
        math.typesOfMath.size() == 1
    }

    def "given a default setup with Trigonometry only when we run the pop method typesOfMath size is now 0"() {
        when:
        def mathType = math.typesOfMath.pop()

        then:
        mathType == "Trigonometry"
        math.typesOfMath.size() == 0
    }

    def "given a default setup with Trigonometry only when peek at typesOfMath it will be Trigonometry"() {
        when:
        def mathType = math.typesOfMath.peek()

        then:
        mathType == "Trigonometry"
        math.typesOfMath.size() == 1
    }

    def "given a default setup with Trigonometry only when we adds math type Algebra which brings size of typesOfMath to 2 when we then run peek and pop methods and it brings the size of typesOfMath back to 1 then math type Trigonometry is the only math type left and we have a size of one"() {
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