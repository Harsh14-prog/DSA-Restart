package stack;

import java.util.Stack;

public class PostfixToInfix {

    public static void main(String[] args) {

        // Postfix expression
        String s = "82+3*42/-5+";

        // Stack for infix expressions
        Stack<String> st = new Stack<>();


        /*
         Postfix is processed from:

         LEFT ---> RIGHT

         because operator comes after operands
        */

        for(char ch : s.toCharArray()) {


            // ================= OPERAND =================
            // Push operand directly into stack
            if(Character.isDigit(ch)) {

                st.push(String.valueOf(ch));
            }


            // ================= OPERATOR =================
            else {

                /*
                 VERY IMPORTANT

                 In postfix:

                 First pop  = right operand
                 Second pop = left operand
                */

                String v2 = st.pop(); // right operand
                String v1 = st.pop(); // left operand


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