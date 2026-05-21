

import java.util.Arrays;
import java.util.Stack;

public class NGE2 {

    public static void main(String[] args) {

        // Input circular array
        int[] nums = {2, 10, 12, 1, 11};

        // Size of array
        int n = nums.length;

        // Stack stores potential next greater elements
        Stack<Integer> st = new Stack<>();

        // Array to store final answers
        int[] nge = new int[n];

        /*
           We traverse from right to left.

           Why 2*n times?

           Because array is circular.

           Example:
           Original array:
           [2,10,12,1,11]

           Virtual circular traversal:
           [2,10,12,1,11,2,10,12,1,11]  assuming 2 arrays

           Using:
           i % n

           converts indexes back into valid range.
        */
        for (int i = 2 * n - 1; i >= 0; i--) {

            // Current element in circular manner
            int curr = nums[i % n];

            /*
               Remove all smaller or equal elements.

               Why?

               Because they can never become
               next greater element for current
               or future left-side elements.

               We want stack to maintain
               useful greater candidates only.
            */
            while (!st.isEmpty() && st.peek() <= curr) {
                st.pop();
            }

            /*
               We store answers only during
               the FIRST pass.

               Why?

               Second pass is only used to
               simulate circular traversal
               and build proper stack state.
            */
            if (i < n) {

                /*
                   If stack becomes empty,
                   no greater element exists.
                */
                if (st.isEmpty()) {
                    nge[i] = -1;
                }

                /*
                   Otherwise top element
                   is the next greater element.
                */
                else {
                    nge[i] = st.peek();
                }
            }

            /*
               Push current element into stack.

               Current element may become
               next greater element for
               future left-side elements.
            */
            st.push(curr);
        }

        // Print final Next Greater Elements array
        System.out.println(Arrays.toString(nge));
    }
}