

import java.util.Stack;

public class InfixEvaluation {

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

        return -1;
    }


    // Function to perform operation
    public static int solve(int v1, int v2, char op) {

        if(op == '+')
            return v1 + v2;

        if(op == '-')
            return v1 - v2;

        if(op == '*')
            return v1 * v2;

        if(op == '/')
            return v1 / v2;

        if(op == '%')
            return v1 % v2;

        if(op == '^')
            return (int)Math.pow(v1, v2);

        return 0;
    }


    public static void main(String[] args) {

        // Infix expression
        String s = "8+(3*2)-(4/2)+5";

        // Stack for operands/values
        Stack<Integer> val = new Stack<>();

        // Stack for operators/brackets
        Stack<Character> op = new Stack<>();


        // Traverse expression character by character
        for(char ch : s.toCharArray()) {

            // ================= OPERAND =================
            // If current character is digit
            // push it into value stack
            if(Character.isDigit(ch)) {

                val.push(ch - '0');
            }


            // ================= OPENING BRACKET =================
            // Opening bracket acts like a boundary/wall
            else if(ch == '(') {

                op.push(ch);
            }


            // ================= CLOSING BRACKET =================
            // Solve everything until opening bracket appears
            else if(ch == ')') {

                while(op.peek() != '(') {

                    // Right operand
                    int v2 = val.pop();

                    // Left operand
                    int v1 = val.pop();

                    // Operator
                    char operation = op.pop();

                    // Solve operation
                    int ans = solve(v1, v2, operation);

                    // Push result back
                    val.push(ans);
                }

                // Remove opening bracket '('
                op.pop();
            }


            // ================= OPERATOR =================
            else {

                /*
                 Before pushing current operator,
                 solve all operators having:

                 1. Higher precedence
                 2. Equal precedence

                 because they deserve execution first.
                */

                while(!op.isEmpty() &&
                        op.peek() != '(' &&
                        precedence(ch) <= precedence(op.peek())) {

                    // Right operand
                    int v2 = val.pop();

                    // Left operand
                    int v1 = val.pop();

                    // Operator
                    char operation = op.pop();

                    // Solve operation
                    int ans = solve(v1, v2, operation);

                    // Push result back
                    val.push(ans);
                }

                // Push current operator
                op.push(ch);
            }
        }


        // ================= REMAINING OPERATORS =================
        // Process operators left in stack
        while(!op.isEmpty()) {

            // Right operand
            int v2 = val.pop();

            // Left operand
            int v1 = val.pop();

            // Operator
            char operation = op.pop();

            // Solve operation
            int ans = solve(v1, v2, operation);

            // Push result back
            val.push(ans);
        }


        // Final answer
        System.out.println("Answer = " + val.peek());
    }
}