
package Dp;

public class knapSackWithBottomUpTabulation {
    public static void main(String[] args) {

        // weights of items
        int[] wt = { 2, 3, 4 };

        // values corresponding to weights
        int[] val = { 4, 5, 6 };

        // total capacity of knapsack
        int weight = 7;

        // number of items
        int n = wt.length;

        // DP table:
        // dp[i][j] = maximum value using first i items with capacity j
        int[][] dp = new int[n + 1][weight + 1];

        /*
         * Base Case:
         * dp[0][j] = 0 → no items → no profit
         * dp[i][0] = 0 → zero capacity → no profit
         * 
         * NOTE: Java automatically initializes array with 0,
         * so no need to explicitly fill base cases.
         */
         // base case -- okk if removed this block
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= weight; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }

        // Build the DP table row by row -- mappting n , weight -> i , j
        for (int i = 1; i <= n; i++) { // i → number of items considered
            for (int j = 1; j <= weight; j++) { // j → current capacity

                // Check if current item can be included
                if (wt[i - 1] <= j) {

                    /*
                     * Two choices:
                     * 1. Take the item:
                     * value = val[i-1] + dp[i-1][j - wt[i-1]]
                     * 
                     * 2. Skip the item:
                     * value = dp[i-1][j]
                     * 
                     * Take maximum of both
                     */
                    dp[i][j] = Math.max(
                            val[i - 1] + dp[i - 1][j - wt[i - 1]],
                            dp[i - 1][j]);

                } else {
                    /*
                     * Item cannot be included (too heavy)
                     * So we skip it
                     */
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Final answer:  always at right corner at dp[n][weight]
        // maximum value using all items and full capacity
        System.out.println(dp[n][weight]);
    }

}
