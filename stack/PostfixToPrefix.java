

import java.util.Stack;

public class PostfixToPrefix {

    public static void main(String[] args) {

        // Postfix expression
        String s = "82+3*42/-5+";

        // Stack for prefix expressions
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
                 Prefix format:

                 operator + left + right
                */

                String ans = ch + v1 + v2;


                // Push generated prefix expression
                st.push(ans);
            }
        }


        // Final prefix expression
        System.out.println("Prefix Expression = " + st.peek());
    }
}