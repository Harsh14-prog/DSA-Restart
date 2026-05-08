package Dp;

public class MinCoinsBottomUp {

    public static void main(String[] args) {

        // Available coins
        int[] coins = {1, 2, 3};

        // Target sum
        int sum = 5;

        int n = coins.length;

        /*
         * Large value representing impossible case
         *
         * We use MAX_VALUE - 1
         * to avoid overflow during:
         * 1 + dp[i][...]
         */
        int INF = Integer.MAX_VALUE - 1;

        /*
         * dp[i][j] =
         * minimum coins needed to form sum j
         * using first i coins
         */
        int[][] dp = new int[n+1][sum+1];

        /*
         * Step 1:
         * Initialize first row
         *
         * With 0 coins:
         * impossible to form positive sums
         */
        for(int j = 1; j <= sum; j++){
            dp[0][j] = INF;
        }

        /*
         * Step 2:
         * Initialize first column
         *
         * Sum = 0 needs 0 coins
         */
        for(int i = 0; i <= n; i++){
            dp[i][0] = 0;
        }

        /*
         * Step 3:
         * Fill DP table
         */
        for(int i = 1; i <= n; i++){

            for(int j = 1; j <= sum; j++){

                /*
                 * If current coin can be used
                 */
                if(coins[i-1] <= j){

                    /*
                     * Choice 1:
                     * Take current coin
                     *
                     * IMPORTANT:
                     * Use dp[i][...]
                     * because coins are reusable
                     */
                    int take = 1 + dp[i][j - coins[i-1]];

                    /*
                     * Choice 2:
                     * Skip current coin
                     */
                    int skip = dp[i-1][j];

                    /*
                     * Take minimum
                     */
                    dp[i][j] = Math.min(take, skip);
                }

                /*
                 * Current coin too large
                 */
                else{

                    /*
                     * Cannot take coin
                     * Only option: skip
                     */
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        /*
         * Final Answer
         */
        if(dp[n][sum] == INF){
            System.out.println(-1);
        }
        else{
            System.out.println(dp[n][sum]);
        }
    }
}