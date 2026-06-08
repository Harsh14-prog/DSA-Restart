import java.util.Stack;

public class LargestRectangleHistogram {

    public static void main(String[] args) {

        int[] heights = {2, 1, 5, 6, 2, 3};

        int n = heights.length;

        // Stores maximum rectangle area found so far
        int maxArea = 0;

        /*
         * Monotonic Increasing Stack
         *
         * Stores indices, not heights.
         *
         * Example:
         * heights = [2,1,5,6]
         *
         * Stack may contain:
         * [1,2,3]
         *
         * meaning:
         * heights = [1,5,6]
         */
        Stack<Integer> st = new Stack<>();

        /*
         * Notice: i goes till n (not n-1)
         *
         * Why?
         *
         * We want one extra iteration that acts like
         * a dummy bar of height 0.
         *
         * This forces all remaining bars in the stack
         * to get processed.
         *
         * Example:
         *
         * heights = [1,2,3,4]
         *
         * Without i == n:
         * Stack = [0,1,2,3]
         *
         * No smaller element ever comes,
         * so areas are never calculated.
         *
         * The extra iteration flushes the stack.
         */
        for (int i = 0; i <= n; i++) {

            /*
             * Pop while:
             *
             * 1. Stack is not empty
             * 2. Current height is smaller than
             *    stack top height
             *
             * OR
             *
             * 3. i == n
             *
             * When i == n, we pretend a height 0 arrived,
             * forcing all remaining elements to be popped.
             */
            while (!st.isEmpty()
                    && (i == n || heights[st.peek()] >= heights[i])) {

                /*
                 * This bar's rectangle is finalized now.
                 */
                int mid = st.pop();

                /*
                 * Current index i is the
                 * Next Smaller Element (NSE)
                 *
                 * Why?
                 *
                 * Because current height is the first
                 * height smaller than heights[mid].
                 */
                int nse = i;

                /*
                 * After popping,
                 * the new stack top becomes
                 * Previous Smaller Element (PSE)
                 */
                int pse = st.isEmpty() ? -1 : st.peek();

                /*
                 * Width formula:
                 *
                 * width = NSE - PSE - 1
                 *
                 * Example:
                 *
                 * PSE = 1
                 * MID = 2
                 * NSE = 4
                 *
                 * Width = 4 - 1 - 1 = 2
                 */
                int width = nse - pse - 1;

                /*
                 * Area using the popped bar
                 * as the minimum height
                 */
                int area = heights[mid] * width;

                maxArea = Math.max(maxArea, area);
            }

            /*
             * IMPORTANT:
             *
             * Do not push when i == n
             *
             * Because:
             * heights[n] does not exist.
             *
             * The iteration i == n is only used
             * to flush the stack.
             */
            if (i < n) {
                st.push(i);
            }
        }

        System.out.println("Largest Area = " + maxArea);
    }
}