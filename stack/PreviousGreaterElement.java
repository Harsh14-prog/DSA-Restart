package stack;

import java.util.Arrays;
import java.util.Stack;

public class PreviousGreaterElement {

    public static void main(String[] args) {

        // Input array
        int[] nums = {10, 4, 2, 20, 40, 12, 30};

        // Size of array
        int n = nums.length;

        // Array to store answers
        int[] pge = new int[n];

        // Stack stores potential previous greater elements
        Stack<Integer> st = new Stack<>();

        /*
           Traverse LEFT to RIGHT

           Why?

           Because we need previous greater elements,
           meaning answers should come from LEFT side.
        */
        for (int i = 0; i < n; i++) {

            int curr = nums[i];

            /*
               Remove all smaller or equal elements.

               Why?

               Because current element is bigger,
               so smaller elements become useless
               for future processing.
            */
            while (!st.isEmpty() && st.peek() <= curr) {
                st.pop();
            }

            /*
               If stack becomes empty,
               no previous greater element exists.
            */
            if (st.isEmpty()) {
                pge[i] = -1;
            }

            /*
               Otherwise stack top is
               nearest previous greater element.
            */
            else {
                pge[i] = st.peek();
            }

            /*
               Push current element.

               It may become previous greater
               for future elements.
            */
            st.push(curr);
        }

        // Print final answer
        System.out.println(Arrays.toString(pge));
    }
}