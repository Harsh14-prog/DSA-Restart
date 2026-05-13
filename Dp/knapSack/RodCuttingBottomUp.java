package Dp.knapSack;

public class RodCuttingBottomUp {

    public static void main(String[] args) {

        // Possible cut lengths
        int[] length = {1,2,3,4,5,6,7,8};

        // Profit for each cut length
        int[] price = {1,5,8,9,10,17,17,20};

        // Total rod length
        int rodLength = 8;

        int n = length.length;

        /*
         * dp[i][j] =
         * maximum profit using first i cut lengths
         * for rod size j
         */
        int[][] dp = new int[n+1][rodLength+1];

        /*
         * Base Case:
         * dp[0][j] = 0 → no cuts available
         * dp[i][0] = 0 → rod length 0
         *
         * Java initializes arrays with 0 by default
         */

        /*
         * Fill DP table
         */
        for(int i = 1; i <= n; i++){

            for(int j = 1; j <= rodLength; j++){

                /*
                 * If current cut can be used
                 */
                if(length[i-1] <= j){

                    /*
                     * Choice 1: Take current cut
                     *
                     * IMPORTANT:
                     * Use dp[i][...]
                     * because cuts can be reused
                     */
                    int take = price[i-1]
                             + dp[i][j - length[i-1]];

                    /*
                     * Choice 2: Skip current cut
                     */
                    int skip = dp[i-1][j];

                    /*
                     * Store maximum profit
                     */
                    dp[i][j] = Math.max(take, skip);
                }

                /*
                 * Current cut too large
                 */
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        /*
         * Final Answer
         */
        System.out.println(dp[n][rodLength]);
    }
}