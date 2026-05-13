package Dp.knapSack;

public class CountSubsetSumBottomUp {
    public static void main(String[] args) {

        // Input array
        int[] nums = { 2, 3, 5, 6, 8, 10 };
        int target = 10;
        int n = nums.length;

        /*
         * dp[i][j] = number of ways to form sum j
         * using first i elements
         */
        int[][] dp = new int[n + 1][target + 1];

        /*
         * Base Case:
         * dp[i][0] = 1
         *
         * Meaning:
         * For any number of elements, there is exactly 1 way
         * to make sum 0 → by choosing no elements (empty subset)
         */
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        /*
         * Note:
         * dp[0][j] = 0 (for j > 0) by default
         * Meaning:
         * With 0 elements, we cannot form any positive sum
         */

        /*
         * Fill the DP table
         */
        for (int i = 1; i <= n; i++) { // i → number of elements considered
            for (int j = 1; j <= target; j++) { // j → current target sum

                /*
                 * Case 1: If current element can be included
                 */
                if (nums[i - 1] <= j) {

                    /*
                     * Two choices:
                     * 1. Skip current element
                     * → dp[i-1][j]
                     *
                     * 2. Take current element
                     * → dp[i-1][j - nums[i-1]]
                     *
                     * Total ways = sum of both
                     */
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    /*
                     * Case 2: Current element is too large
                     * → cannot take it
                     * → only option is to skip
                     */
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        /*
         * Final Answer:
         * number of ways to form 'target' using all elements
         */
        System.out.println(dp[n][target]);
    }
}
