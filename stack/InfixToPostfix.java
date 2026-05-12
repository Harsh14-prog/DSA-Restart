package stack;

import java.util.Stack;

public class InfixToPostfix {

    // Function to return precedence of operators
    public static int precedence(char ch) {

        // Lowest precedence
        if(ch == '+' || ch == '-')
            return 1;

        // Higher precedence
        if(ch == '*' || ch == '/' || ch == '%')
            return 2;

        // Highest precedence
        if(ch == '^')
            return 3;

        return 0;
    }

    public static void main(String[] args) {

        // Infix expression
        String s = "8+(3*2)-(4/2)+5";

        // Stack for operands/postfix expressions
        Stack<String> st1 = new Stack<>();

        // Stack for operators/brackets
        Stack<Character> st2 = new Stack<>();


        // Traverse expression character by character
        for(char ch : s.toCharArray()) {

            // ================= OPERAND =================
            // If digit, push directly into operand stack
            if(Character.isDigit(ch)) {

                st1.push(String.valueOf(ch));
            }


            // ================= OPENING BRACKET =================
            // Opening bracket acts as boundary/wall
            else if(ch == '(') {

                st2.push(ch);
            }


            // ================= CLOSING BRACKET =================
            // Solve everything until '(' appears
            else if(ch == ')') {

                while(!st2.isEmpty() && st2.peek() != '(') {

                    // Right operand
                    String v1 = st1.pop();

                    // Left operand
                    String v2 = st1.pop();

                    // Operator
                    char op = st2.pop();

                    /*
                     Postfix format:
                     left + right + operator

                     v1 = right operand
                     v2 = left operand
                    */

                    String ans = v2 + v1 + op;

                    // Push generated postfix expression
                    st1.push(ans);
                }

                // Remove opening bracket '('
                st2.pop();
            }


            // ================= OPERATOR =================
            else {

                /*
                 Before pushing current operator,
                 solve all operators having:

                 1. Higher precedence
                 2. Equal precedence
                */

                while(!st2.isEmpty() &&
                        st2.peek() != '(' &&
                        precedence(st2.peek()) >= precedence(ch)) {

                    // Right operand
                    String v1 = st1.pop();

                    // Left operand
                    String v2 = st1.pop();

                    // Operator
                    char op = st2.pop();

                    // Create postfix expression
                    String ans = v2 + v1 + op;

                    // Push result back
                    st1.push(ans);
                }

                // Push current operator
                st2.push(ch);
            }
        }


        // ================= REMAINING OPERATORS =================
        // Process operators left in stack
        while(!st2.isEmpty()) {

            // Right operand
            String v1 = st1.pop();

            // Left operand
            String v2 = st1.pop();

            // Operator
            char op = st2.pop();

            // Create postfix expression
            String ans = v2 + v1 + op;

            // Push back into stack
            st1.push(ans);
        }


        // Final postfix expression
        System.out.println("Postfix Expression = " + st1.peek());
    }
}