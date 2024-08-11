import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public int evalRPN(String[] tokens) {
        List<String> operators = Arrays.asList("+", "-", "*", "/");
        Stack<String> s = new Stack<>();
        for (String el : tokens) {
            if (operators.contains(el)) {
                if (s.size() < 2) {
                    int n = Integer.valueOf(s.pop());
                    int b = Integer.valueOf(el+"1");
                    return n * b;
                }
                int a = Integer.valueOf(s.pop());
                int b = Integer.valueOf(s.pop());
                int result = perform(a, b, el);
                s.push(String.valueOf(result));
            } else {
                s.push(el);
            }
        }
        return Integer.valueOf(s.pop());
    }

    int perform(int a, int b, String el) {
        switch (el) {
            case "+":
                return b + a;
            case "-":
                return b - a;
            case "*":
                return b * a;
            case "/":
                return b / a;

            default:
                return 0;
        }
    }

    public int calculate(String s) {
        List<String> out = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.push("(");
        List<String> t = tokens(s);
        t.add(")");
        while (t.size() > 0) {
            String input = t.get(0);
            if (input.equals(" ")) {
                t.remove(0);
                continue;
            }
            if (isDigit(input)) {
                out.add(input);
                t.remove(0);
                continue;
            }
            String top = stack.peek();
            if (input.equals(")") && !top.equals("(")) {
                top = stack.pop();
                out.add(top);
                continue;
            }
            if (input.equals(")") && top.equals("(")) {
                top = stack.pop();
                t.remove(0);
                continue;
            }
            if (input.equals("(")) {
                stack.push(input);
                t.remove(0);
                continue;
            }
            if (getP(top) >= getP(input)) {
                top = stack.pop();
                out.add(top);
                continue;
            } else {
                stack.push(input);
                t.remove(0);
            }
        }
        System.out.println(out);
        int result = evalRPN(out.toArray(new String[0]));
        System.out.println(result);
        return result;
    }

    int getP(String c) {
        if (c.equals("+") || c.equals("-")) {
            return 2;
        } else if (c.equals("*") || c.equals("/")) {
            return 4;
        }
        return 0;
    }

    boolean isDigit(String s) {
        Pattern pattern = Pattern.compile("[\\d]+");
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    List<String> tokens(String a) {
        // String a = "((10 * (6 / ((9 + 3) * -11))) + 17) + 5";

        // Define the regex pattern to match numbers (including negative), operators,
        // and brackets
        // Pattern pattern = Pattern.compile("\\s-?\\d+(\\.\\d+)?|[+\\-*/()]");
        Pattern pattern = Pattern.compile("(?<=\\s|^)-\\d+|(\\d+)|[()+\\-*/]");
        Matcher matcher = pattern.matcher(a);

        // Create a list to hold the tokens
        List<String> tokens = new ArrayList<>();

        // Find all matches and add them to the list
        while (matcher.find()) {
            tokens.add(matcher.group());
        }

        // Print the tokens
        for (String token : tokens) {
            System.out.println(token);
        }
        return tokens;
    }
}
