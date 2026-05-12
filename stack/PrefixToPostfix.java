package stack;

import java.util.Stack;

public class PrefixToPostfix {

    public static void main(String[] args) {

        // Prefix expression
        String s = "+-*+823/425";

        // Stack for postfix expressions
        Stack<String> st = new Stack<>();


        /*
         Prefix is processed from:

         RIGHT ---> LEFT
        */

        for(int i = s.length() - 1; i >= 0; i--) {

            char ch = s.charAt(i);


            // ================= OPERAND =================
            // Push operand directly
            if(Character.isDigit(ch)) {

                st.push(String.valueOf(ch));
            }


            // ================= OPERATOR =================
            else {

                /*
                 In prefix traversal:

                 First pop  = left operand
                 Second pop = right operand
                */

                String v1 = st.pop(); // left operand
                String v2 = st.pop(); // right operand


                /*
                 Postfix format:

                 left + right + operator
                */

                String ans = v1 + v2 + ch;


                // Push generated postfix expression
                st.push(ans);
            }
        }


        // Final postfix expression
        System.out.println("Postfix Expression = " + st.peek());
    }
}