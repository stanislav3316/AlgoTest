package seminar1;

import seminar1.collections.LinkedStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) )
 * ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 *
 * 1 + ( 2 + 3 ) * 4 * 5 = 101
 * 1 + 5 * 4 * 5 = 101
 * 1 + 5 * 20 = 101
 * 1 + 100 = 101
 * 20 / 4 = 5
 * (101 - 1) / 5 = 20
 *
 * Считаем, что операции деления на ноль отсутствуют
 */
public class SolverExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN   = '(';
    private static final char RIGHT_PAREN  = ')';
    private static final char PLUS         = '+';
    private static final char MINUS        = '-';
    private static final char TIMES        = '*';
    private static final char DIVISION     = '/';

    private static double evaluate(String[] values) {
        String postFix = getPostFixExpression(values);
        values = postFix.split(" ");
        LinkedStack<Double> operandStack = new LinkedStack<>();

        for (String token : values) {
            if (isNumber(token)) {
                operandStack.push(Double.parseDouble(token));
            } else {
                double operandB = operandStack.pop();
                double operandA = operandStack.pop();
                double result = doMath(token, operandA, operandB);
                operandStack.push(result);
            }
        }

        return operandStack.pop();
    }

    private static double doMath(String op, double a, double b) {
        switch (op) {
            case "*":
                return a * b;
            case "/":
                return a / b;
            case "+":
                return a + b;
            case "-":
                return a - b;
        }
        return 0;
    }

    public static boolean isNumber(String expr) {
        try {
            double a = Double.parseDouble(expr);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private static String getPostFixExpression(String[] values) {
        LinkedStack<String> opStack = new LinkedStack<>();
        LinkedStack<String> postFixList = new LinkedStack<>();

        for (String token : values) {
            //number
            if (isNumber(token)) {
                postFixList.push(token + " ");
            } else if (token.equals("(")) {
                opStack.push(token);
            } else if (token.equals(")")) {
                String topToken = opStack.pop();
                while (!topToken.equals("(")) {
                    postFixList.push(topToken + " ");
                    topToken = opStack.pop();
                }
            } else {
                while (!opStack.isEmpty() && (getPriority(opStack.peek()) >= getPriority(token))) {
                    postFixList.push(opStack.pop() + " ");
                }
                opStack.push(token);
            }
        }

        while (!opStack.isEmpty()) {
            postFixList.push(opStack.pop() + " ");
        }

        StringBuilder builder = new StringBuilder();
        while (!postFixList.isEmpty()) {
            builder.append(postFixList.pop());
        }

        builder.deleteCharAt(builder.length() - 1);
        return builder.reverse().toString();
    }

    private static int getPriority(String op) {
        switch (op) {
            case "*":
            case "/":
                return 3;
            case "+":
            case "-":
                return 2;
            case "(":
                return 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(evaluate(sequence.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}