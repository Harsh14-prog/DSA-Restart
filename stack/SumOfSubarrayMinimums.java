

import java.util.Stack;

public class SumOfSubarrayMinimums {

    public static void main(String[] args) {

        // Input array
        int[] nums = {3, 1, 2, 4};

        int n = nums.length;

        // Stack stores INDICES
        Stack<Integer> st = new Stack<>();

        // Final answer
        long sum = 0;

        /*
           Traverse entire array

           We use monotonic increasing stack.

           Stack property:
           nums[stack top] <= current element
        */
        for (int i = 0; i < n; i++) {

            /*
               If current element is smaller,
               then current element becomes
               NSE (Next Smaller Element)
               for popped elements.
            */
            while (!st.isEmpty() && nums[st.peek()] > nums[i]) {

                /*
                   Element whose contribution
                   we are calculating
                */
                int mid = st.pop();

                /*
                   Previous Smaller Element index

                   After popping:
                   new stack top becomes PSE
                */
                int pse;

                if (st.isEmpty()) {
                    pse = -1;
                } else {
                    pse = st.peek();
                }

                /*
                   Current index becomes NSE
                   because current element
                   is smaller
                */
                int nse = i;

                /*
                   Number of choices on LEFT side
                   start point of subarr whose min is curr ele
                */
                int left = mid - pse;

                /*
                   Number of choices on RIGHT side
                   end point of subarr whose min is curr ele
                */
                int right = nse - mid;

                /*
                   Contribution formula:

                   arr[i] × leftChoices × rightChoices
                */
                sum += (long) nums[mid] * left * right;
            }

            // Push current index into stack
            st.push(i);
        }

        /*
           Some elements may still remain in stack.

           For them:
           NSE = n
           because no smaller element exists on right.
        */
        while (!st.isEmpty()) {

            int mid = st.pop();

            int pse;

            if (st.isEmpty()) {
                pse = -1;
            } else {
                pse = st.peek();
            }

            // No smaller element on right side
            int nse = n;

            int left = mid - pse;

            int right = nse - mid;

            sum += (long) nums[mid] * left * right;
        }

        // Print final answer
        System.out.println("Sum of Subarray Minimums = " + sum);
    }
}