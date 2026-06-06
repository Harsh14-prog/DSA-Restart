
import java.util.Stack;

public class SumOfSubarrayRanges {

    /*
     * ------------------------------------------------
     * FUNCTION TO FIND SUM OF SUBARRAY MINIMUMS
     * ------------------------------------------------
     */
    public static long sumOfSubarrayMinimums(int[] nums) {

        int n = nums.length;

        Stack<Integer> st = new Stack<>();

        long sum = 0;

        /*
         * Monotonic Increasing Stack
         * 
         * Stack stores indices.
         * 
         * Current smaller element becomes
         * NSE (Next Smaller Element)
         * for popped elements.
         */
        for (int i = 0; i < n; i++) {

            /*
             * If current element is smaller,
             * then popped element's contribution
             * can now be calculated.
             */
            while (!st.isEmpty() && nums[st.peek()] > nums[i]) {

                // Element whose contribution is calculated
                int mid = st.pop();

                /*
                 * Previous Smaller Element index
                 * 
                 * After popping,
                 * new stack top becomes PSE.
                 */
                int pse;

                if (st.isEmpty()) {
                    pse = -1;
                } else {
                    pse = st.peek();
                }

                /*
                 * Current index becomes NSE
                 */
                int nse = i;

                /*
                 * Number of choices on LEFT side
                 */
                int left = mid - pse;

                /*
                 * Number of choices on RIGHT side
                 */
                int right = nse - mid;

                /*
                 * Contribution:
                 * 
                 * element × leftChoices × rightChoices
                 */
                sum += 1L * nums[mid] * left * right;
            }

            // Push current index
            st.push(i);
        }

        /*
         * Elements remaining in stack
         * do not have smaller element on right.
         * 
         * So:
         * NSE = n
         */
        while (!st.isEmpty()) {

            int mid = st.pop();

            int pse;

            if (st.isEmpty()) {
                pse = -1;
            } else {
                pse = st.peek();
            }

            int nse = n;

            int left = mid - pse;

            int right = nse - mid;

            sum += 1L * nums[mid] * left * right;
        }

        return sum;
    }

    /*
     * ------------------------------------------------
     * FUNCTION TO FIND SUM OF SUBARRAY MAXIMUMS
     * ------------------------------------------------
     */
    public static long sumOfSubarrayMaximums(int[] nums) {

        int n = nums.length;

        Stack<Integer> st = new Stack<>();

        long sum = 0;

        /*
         * Monotonic Decreasing Stack
         * 
         * Current greater element becomes
         * NGE (Next Greater Element)
         * for popped elements.
         */
        for (int i = 0; i < n; i++) {

            /*
             * If current element is greater,
             * popped element's contribution
             * becomes finalized.
             */
            while (!st.isEmpty() && nums[st.peek()] < nums[i]) {

                // Element whose contribution is calculated
                int mid = st.pop();

                /*
                 * Previous Greater Element index
                 */
                int pge;

                if (st.isEmpty()) {
                    pge = -1;
                } else {
                    pge = st.peek();
                }

                /*
                 * Current index becomes NGE
                 */
                int nge = i;

                /*
                 * Number of left choices
                 */
                int left = mid - pge;

                /*
                 * Number of right choices
                 */
                int right = nge - mid;

                /*
                 * Contribution formula
                 */
                sum += 1L * nums[mid] * left * right;
            }

            // Push current index
            st.push(i);
        }

        /*
         * Remaining elements do not have
         * greater element on right.
         * 
         * So:
         * NGE = n
         */
        while (!st.isEmpty()) {

            int mid = st.pop();

            int pge;

            if (st.isEmpty()) {
                pge = -1;
            } else {
                pge = st.peek();
            }

            int nge = n;

            int left = mid - pge;

            int right = nge - mid;

            sum += 1L * nums[mid] * left * right;
        }

        return sum;
    }

    public static void main(String[] args) {

        int[] nums = { 1, 2, 3 };

        /*
         * Final answer:
         * 
         * Sum of all subarray maximums
         * -
         * Sum of all subarray minimums
         */
        long ans = sumOfSubarrayMaximums(nums)
                - sumOfSubarrayMinimums(nums);

        System.out.println("Sum of Subarray Ranges = " + ans);
    }

}