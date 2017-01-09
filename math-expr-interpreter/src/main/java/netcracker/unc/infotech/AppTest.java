package netcracker.unc.infotech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.System.out;

public class AppTest {

    private static final String EMPTY_STRING = "";
    private static final String HELP_INFO_KEY = "-h";
    private static final ArrayList<String> EXIT_COMMANDS = new ArrayList<String>(Arrays.asList(new String[]{"-q", "q", "quit", "exit"}));

    /**
     * Тестирует методы.
     *
     * @param args список аргументов командной строки.
     */
    public static void main(String[] args) {
        String expression;

        showUsage();
        while (true) {
            out.flush();
            out.print("~./math-expr-interpreter$");
            expression = getString();
            if (EXIT_COMMANDS.contains(expression)) {
                break;
            }

            if (EMPTY_STRING.equals(expression) || HELP_INFO_KEY.equals(expression)) {
                showUsage();
                continue;
            } else {
                try {
                    String rpn = ExpressionUtils.sortingStation(expression, ExpressionUtils.MAIN_MATH_OPERATIONS);
                    out.println("[INFO]:Reverse Polish Notation: " + rpn);
                    out.println("[INFO]: Result " + ExpressionUtils.calculateExpression(expression));
                } catch(IllegalArgumentException iaex) {
                    out.println("[ERROR]: Expression syntax error.");
                } catch(IllegalStateException isex) {
                    out.println("[ERROR]: Expression isn't specified or operations aren't specified.");
                }
            }
        }
    }

    public static String getString() {
        String input = null;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            input = br.readLine().trim();
        } catch (IOException ex) {
            out.println("System I/O exception!");
        }

        return input;
    }

    public static void showUsage() {
        out.println("This is Infix to Postfix Interpreter\n" +
                "\tYou can type expression in infix notation and then it will return it in postfix notation (RPN)\n" +
                "\tHelp Information:\n" +
                "\t-q: exit()\n" +
                "\t-h: help");
    }
}
