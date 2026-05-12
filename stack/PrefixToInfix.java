package stack;

import java.util.Stack;

public class PrefixToInfix {

    public static void main(String[] args) {

        // Prefix expression
        String s = "+-*+823/425";

        // Stack for infix expressions
        Stack<String> st = new Stack<>();


        /*
         Prefix is processed from:

         RIGHT ---> LEFT

         because operator comes before operands
        */

        for(int i = s.length() - 1; i >= 0; i--) {

            char ch = s.charAt(i);


            // ================= OPERAND =================
            // Push operand directly into stack
            if(Character.isDigit(ch)) {

                st.push(String.valueOf(ch));
            }


            // ================= OPERATOR =================
            else {

                /*
                 VERY IMPORTANT

                 In prefix:

                 First pop  = left operand
                 Second pop = right operand
                */

                String v1 = st.pop(); // left operand
                String v2 = st.pop(); // right operand


                /*
                 Infix format:

                 (left operator right)

                 Brackets are important
                 to preserve precedence.
                */

                String ans = "(" + v1 + ch + v2 + ")";


                // Push generated infix expression
                st.push(ans);
            }
        }


        // Final infix expression
        System.out.println("Infix Expression = " + st.peek());
    }
}