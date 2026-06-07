package Practice;

import java.util.Stack;

public class fifth {

    public static int precendance(char sign) {

        if (sign == '+' || sign == '-') {
            return 1;
        } else if (sign == '*' || sign == '/' || sign == '%') {
            return 2;
        } else if (sign == '^') {
            return 3;
        }

        return -1;
    }

    public static int solve(int v1, int v2, int sign) {

        if (sign == '+')
            return v1 + v2;
        if (sign == '-')
            return v1 - v2;
        if (sign == '*')
            return v1 * v2;
        if (sign == '/')
            return v1 / v2;
        if (sign == '%')
            return v1 % v2;
        if (sign == '^')
            return (int) Math.pow(v1, v2);

        return 0;
    }

    public static void main(String[] args) {

        // Infix expression
        String s = "8+(3*2)-(4/2)+5";

        Stack<String> st1 = new Stack<>();

        Stack<Character> st2 = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                st1.push(String.valueOf(ch));
            } else if (st2.isEmpty() || ch == '(') {
                st2.push(ch);
            } else if (ch == ')') {

                while (!st2.isEmpty() && st2.peek() != '(') {

                    String v2 = st1.pop();
                    String v1 = st1.pop();
                    int sign = st2.pop();

                    StringBuilder sb = new StringBuilder();
                    sb.append(sign).append(v1).append(v2);

                    st1.push(sb.toString());
                }

                st2.pop();

            } else {

                while (!st2.isEmpty() && st2.peek() != '(' && precendance(st2.peek()) >= precendance(ch)) {

                    String v2 = st1.pop();
                    String v1 = st1.pop();
                    int sign = st2.pop();

                    StringBuilder sb = new StringBuilder();
                    sb.append(sign).append(v1).append(v2);

                    st1.push(sb.toString());
                }
                st2.push(ch);
            }
        }

        while (!st2.isEmpty()) {

            String v2 = st1.pop();
            String v1 = st1.pop();
            int sign = st2.pop();

            StringBuilder sb = new StringBuilder();
            sb.append(sign).append(v1).append(v2);

            st1.push(sb.toString());
        }

        System.out.println(st1.peek());
    }
}
