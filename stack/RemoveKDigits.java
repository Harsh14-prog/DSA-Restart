
import java.util.Stack;

public class RemoveKDigits {

    public static String removeKdigits(String num, int k) {

        // Stack stores digits
        Stack<Character> st = new Stack<>();

        /*
         * Traverse every digit
         */
        for (int i = 0; i < num.length(); i++) {

            // Current digit
            char curr = num.charAt(i);

            /*
             * Remove larger previous digits.
             * 
             * Why?
             * 
             * Smaller digit appearing earlier
             * creates smaller number overall.
             * 
             * Maintain monotonic increasing stack.
             */
            while (!st.isEmpty()
                    && k > 0
                    && st.peek() > curr) {

                // Remove larger digit
                st.pop();

                // One removal completed
                k--;
            }

            // Push current digit into stack
            st.push(curr);
        }

        /*
         * If removals still remain,
         * 
         * remove digits from END.
         * 
         * Example:
         * 12345, k = 2
         * 
         * Remove:
         * 4 and 5
         */
        while (k > 0) {

            st.pop();

            k--;
        }

        /*
         * Build final number from stack.
         * 
         * Stack gives reverse order,
         * so later we reverse string.
         */
        StringBuilder sb = new StringBuilder();

        while (!st.isEmpty()) {

            sb.append(st.pop());
        }

        // Reverse to restore correct order
        sb.reverse();

        /*
         * Remove leading zeros.
         * 
         * Example:
         * 0200 -> 200
         */
        while (sb.length() > 0
                && sb.charAt(0) == '0') {

            sb.deleteCharAt(0);
        }

        /*
         * If all digits removed
         * or only zeros remain
         */
        if (sb.length() == 0) {

            return "0";
        }

        // Final smallest number
        return sb.toString();
    }

    public static void main(String[] args) {

        // Input number
        String num = "1432219";

        // Number of digits to remove
        int k = 3;

        // Stores final smallest number
        String ans = removeKdigits(num, k);

        System.out.println("Smallest Number = " + ans);
    }
}