

import java.util.Stack;

public class PostfixEvaluation {

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

        return 0;
    }

    public static void main(String[] args) {

        // Postfix expression
        String s = "82+3*42/-5+";

        // Stack for operands/results
        Stack<Integer> st = new Stack<>();

        // Traverse expression character by character
        for(char ch : s.toCharArray()) {

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

                 In postfix:

                 First pop  = right operand
                 Second pop = left operand
                */

                int v2 = st.pop(); // right operand
                int v1 = st.pop(); // left operand


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