package arthematicCalculator;

import java.util.EmptyStackException;
import java.util.Stack;

public class ExpressionEvaluator {

    public static ExpressionEvaluator getInstance(){
        return new ExpressionEvaluator();
    }

    private static final String expression ="7+(67*5^(2+3-6)/2)/4";
    private static final String mathametical_operators_token="^/*+-";

    public String calculate(String expression) {
        String result=null;
        final Stack<Character> operator=new Stack<Character>();
        final Stack<String> operand=new Stack<String>();

        char[] tokens= expression.toCharArray();
        StringBuilder builder= new StringBuilder();
        int index=0;
        for(Character element:tokens) {
            index++;
            //System.out.println("operator :"+operator+" operand :"+operand);

            if(Character.isDigit(element)) {
                builder.append(element);
                if(expression.length()==index)
                    operand.push(builder.toString());
                continue;
            }
            //push double digit value if any
            if(builder.length()>0) {
                operand.push(builder.toString());
                builder = new StringBuilder();
            }

            Tokens currentToken = Tokens.getToken(element.toString());
            Tokens topMostToken=null;
            if(!operator.isEmpty()){
                topMostToken = Tokens.getToken(operator.peek().toString());

                //Check precedence
                if(mathametical_operators_token.contains(topMostToken.getOperation().toString()) && mathametical_operators_token.contains(currentToken.getOperation().toString())){
                    if(topMostToken.ordinal() > currentToken.ordinal()){
                        try{
                            operand.push(evaluate(operator.pop(),operand.pop(),operand.pop()));
                        }catch(Exception e){
                            return "Invalid Expression";
                        }
                    }
                }
            }
            //if closing closing braces
            if(currentToken == Tokens.CLOSING_BRACES)
            {
                while(!operator.peek().toString().equals("(")) {
                    try{
                        operand.push(evaluate(operator.pop(),operand.pop(),operand.pop()));
                    }catch(Exception e){
                        return "Invalid Expression";
                    }

                }
                //remove opening braces
                operator.pop();
                continue;
            }
            try{
                operator.push(currentToken.getOperation().charAt(0));
            }catch(Exception e){
                return "Invalid Expression";
            }
        }

        while((!operand.isEmpty() || !operator.isEmpty()) && !(operand.size()==1 && operator.isEmpty())){
            try{
                result = operand.push(evaluate(operator.pop(),operand.pop(),operand.pop()));
            }catch (EmptyStackException e) {
                return "Invalid Expression";
            }
        }

        return result;
    }

    private static String evaluate(Character pop, String pop2, String pop3) {
        Double operand1 = Double.parseDouble(pop2);
        Double operand2 = Double.parseDouble(pop3);

        double result=0.0;
        switch (pop) {
            case '+':
                result = addition(operand2, operand1);
                break;
            case '-':
                result = substraction(operand2, operand1);
                break;
            case '*':
                result = multiplication(operand2, operand1);
                break;
            case '/':
                result = division(operand2, operand1);
                break;
            case '^':
                result = power(operand2, operand1);
                break;
            default:
                break;
        }
        return String.valueOf(result);
    }

    private static double addition(Double operand2, Double operand1) {
        return operand2+operand1;
    }

    private static double substraction(Double operand2, Double operand1) {
        return operand2-operand1;
    }

    private static double multiplication(Double operand2, Double operand1)  {
        return operand2*operand1;
    }

    private static double division(Double operand2, Double operand1)  {
        return operand2/operand1;
    }

    private static double power(Double operand2, Double operand1) {
        return Math.pow(operand2, operand1);
    }


    public static void main(String args[]) {
        try{
            System.out.println(ExpressionEvaluator.getInstance().calculate(expression));
        }catch (Exception e) {
            System.out.println("Invalid Expression");
        }

    }
}
