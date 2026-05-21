

import java.util.Stack;

public class PrefixEvaluation {

    // Function to perform operation
    public static int solve(int v1, int v2, char sign) {

        if(sign == '+')
            return v1 + v2;

        if(sign == '-')
            return v1 - v2;

        if(sign == '*')
            return v1 * v2;

        if(sign == '/')
            return v1 / v2;

        if(sign == '%')
            return v1 % v2;

        if(sign == '^')
            return (int)Math.pow(v1, v2);

        return 0;
    }

    public static void main(String[] args) {

        // Prefix expression
        String s = "+-*+823/425";

        // Stack for operands/results
        Stack<Integer> st = new Stack<>();


        /*
         Prefix evaluation is done from:

         RIGHT ---> LEFT

         because operator comes before operands
        */

        for(int i = s.length() - 1; i >= 0; i--) {

            char ch = s.charAt(i);


            // ================= OPERAND =================
            // If current character is digit,
            // push into stack
            if(Character.isDigit(ch)) {

                st.push(ch - '0');
            }


            // ================= OPERATOR =================
            else {

                /*
                 VERY IMPORTANT

                 In prefix:

                 First pop  = left operand
                 Second pop = right operand

                 because traversal direction is reversed
                */

                int v1 = st.pop(); // left operand
                int v2 = st.pop(); // right operand


                // Solve expression
                int ans = solve(v1, v2, ch);


                // Push result back into stack
                st.push(ans);
            }
        }


        // Final answer remains at top of stack
        System.out.println("Answer = " + st.peek());
    }
}