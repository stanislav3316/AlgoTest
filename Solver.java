package seminar1;

import seminar1.collections.LinkedStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) ) ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 *
 * Считаем, что операции деления на ноль отсутствуют
 */
public class Solver {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN   = '(';
    private static final char RIGHT_PAREN  = ')';
    private static final char PLUS         = '+';
    private static final char MINUS        = '-';
    private static final char TIMES        = '*';
    private static final char DIVISION     = '/';

    private static double evaluate(String[] values) {
        LinkedStack<String> operations = new LinkedStack<>();
        LinkedStack<Double> nums = new LinkedStack<>();

        for (int i = 0; i < values.length; i++) {
            switch (values[i]) {
                case "+":
                    operations.push(values[i]);
                    break;
                case "-":
                    operations.push(values[i]);
                    break;
                case "(":
                    operations.push(values[i]);
                    break;
                case ")":
                    nums.push(doMath(nums.pop(), nums.pop(), operations.pop()));
                    operations.pop();
                    break;
                case "*":
                    operations.push(values[i]);
                    break;
                case "/":
                    operations.push(values[i]);
                    break;
                default:
                    nums.push(Double.parseDouble(values[i]));
            }
        }

        return nums.pop();
    }

    public static Double doMath(Double a, Double b, String op) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
        }
        return 0d;
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