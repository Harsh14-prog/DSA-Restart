package Practice;

import java.util.Stack;

public class fifth {

    public static int precendance(char sign) {

        if (sign == '+' || sign == '-') {
            return 1;
        } 
        else if (sign == '*' || sign == '/' || sign == '%') {
            return 2;
        } 
        else if (sign == '^') {
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

        Stack<Integer> st1 = new Stack<>();

        Stack<Character> st2 = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                st1.push(ch - '0');
            } 
            else if (st2.isEmpty() || ch == '(') {
                st2.push(ch);
            } 
            else if (ch == ')') {

                while (!st2.isEmpty() && st2.peek() != '(') {

                    int v2 = st1.pop();
                    int v1 = st1.pop();
                    int sign = st2.pop();

                    int ans = solve(v1, v2, sign);

                    st1.push(ans);
                }

                st2.pop();

            } 
            else {

                while (!st2.isEmpty() && st2.peek() != '(' && precendance(st2.peek()) >= precendance(ch)) {

                    int v2 = st1.pop();
                    int v1 = st1.pop();
                    int sign = st2.pop();

                    int ans = solve(v1, v2, sign);

                    st1.push(ans);
                }
                st2.push(ch);
            }
        }

        while (!st2.isEmpty()) {

            int v2 = st1.pop();
            int v1 = st1.pop();
            int sign = st2.pop();

            int ans = solve(v1, v2, sign);

            st1.push(ans);
        }

        System.out.println(st1.peek());
    }
}
