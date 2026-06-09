import java.util.Stack;

public class MaximumRectangle {

    /*
     * Finds the largest rectangle area in a histogram.
     *
     * Example:
     * Heights = [2,1,5,6,2,3]
     *
     * Uses Monotonic Increasing Stack.
     *
     * Time Complexity  : O(n)
     * Space Complexity : O(n)
     */
    public static int largestAreaHistogram(int[] heights) {

        int n = heights.length;

        int maxArea = 0;

        // Stores indices of histogram bars
        Stack<Integer> stack = new Stack<>();

        /*
         * Traverse all bars.
         * We go till n (one extra iteration)
         * to flush out remaining stack elements.
         */
        for (int i = 0; i <= n; i++) {

            /*
             * Current bar is smaller than stack top.
             * Means stack top has found its
             * Next Smaller Element (NSE).
             */
            while (!stack.isEmpty()
                    && (i == n || heights[stack.peek()] >= heights[i])) {

                // Current bar becomes NSE
                int mid = stack.pop();

                // Previous Smaller Element (PSE)
                int pse = stack.isEmpty() ? -1 : stack.peek();

                int nse = i;

                int width = nse - pse - 1;

                int area = heights[mid] * width;

                maxArea = Math.max(maxArea, area);
            }

            if (i < n) {
                stack.push(i);
            }
        }

        return maxArea;
    }

    /*
     * Finds maximum rectangle containing only 1's
     * in a binary matrix.
     *
     * Idea:
     * Convert each row into a histogram.
     *
     * Example:
     *
     * Matrix:
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     *
     * Histogram after row 1:
     * [1,0,1,0,0]
     *
     * Histogram after row 2:
     * [2,0,2,1,1]
     *
     * Histogram after row 3:
     * [3,1,3,2,2]
     *
     * For every histogram,
     * calculate Largest Rectangle in Histogram.
     */
    public static int maximalRectangle(char[][] matrix) {

        // Edge Case
        if (matrix == null
                || matrix.length == 0
                || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        /*
         * height[j]
         *
         * Stores consecutive count of 1's
         * ending at current row.
         */
        int[] height = new int[cols];

        int maxArea = 0;

        // Process every row
        for (int row = 0; row < rows; row++) {

            for (int col = 0; col < cols; col++) {

                /*
                 * If current cell is 1,
                 * extend histogram height.
                 */
                if (matrix[row][col] == '1') {
                    height[col]++;
                }

                /*
                 * If current cell is 0,
                 * histogram breaks.
                 */
                else {
                    height[col] = 0;
                }
            }

            /*
             * Current row converted into histogram.
             * Find largest rectangle in it.
             */
            int currentArea = largestAreaHistogram(height);

            maxArea = Math.max(maxArea, currentArea);
        }

        return maxArea;
    }

    public static void main(String[] args) {

        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

        int answer = maximalRectangle(matrix);

        System.out.println("Maximum Rectangle Area = " + answer);
    }
}