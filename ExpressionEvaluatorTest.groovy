package arthematicCalculator

import spock.lang.Specification

class ExpressionEvaluatorTest extends Specification {

    ExpressionEvaluator evaluator;
    void setup() {
        evaluator = ExpressionEvaluator.instance
    }

    def "should not throw error for valid expression"() {
        when:
            def result = evaluator.calculate(EXPRESSION);
        then:
            !result.equals("Invalid Expression")
        where:
        EXPRESSION                          |_
        "7+(67*5^(2+3-6)/2)/4"              |_
        "(8*5/8)-(3/1)-5"                   |_
    }

    def "should throw error when invalid expression"() {
        when:
            def result = evaluator.calculate(EXPRESSION);
        then:
            result.equals("Invalid Expression")
        where:
        EXPRESSION                          |_
        "7+67(56*2)"                        |_
        "-(8+7"                             |_
        "8*+7"                              |_
        "(8*5/8)-((3/1)-5"                  |_
    }

}
