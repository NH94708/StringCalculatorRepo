package arthematicCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public static void main(String args[]) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> expressions = new ArrayList<String>();
        ExpressionEvaluator evaluator = ExpressionEvaluator.getInstance();

        try{
            System.out.println("Please enter number of test cases");
            Integer noOfTestCases = Integer.parseInt(br.readLine());
            System.out.println("Please enter expression to evaluate");
            for(int i = 0; i < noOfTestCases; i++){
                String exp = br.readLine();
                expressions.add(exp);
            }
            for(String expression: expressions){
                try{
                    System.out.println(ExpressionEvaluator.getInstance().calculate(expression));
                }catch (Exception e) {
                    System.out.println("Invalid Expression");
                }

            }
        } catch (IOException e) {
            System.out.println("Please enter valid input for number of test cases");
            e.printStackTrace();
        }


    }

}
