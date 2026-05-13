package Dp.knapSack;

public class CoinChangeWaysBottomUp {
public static void main(String[] args) {

    // Available coin denominations
    int[] coins = {1, 2, 3};

    // Target sum
    int sum = 5;

    int n = coins.length;

    /*
     * dp[i][j] =
     * number of ways to form sum j
     * using first i coins
     */
    int[][] dp = new int[n+1][sum+1];

    /*
     * Base Case:
     *
     * There is exactly 1 way to form sum = 0
     * → by choosing no coins
     */
    for(int i = 0; i <= n; i++){
        dp[i][0] = 1;
    }

    /*
     * Note:
     * dp[0][j] = 0 for j > 0
     *
     * Meaning:
     * With 0 coins, we cannot form positive sums
     *
     * Java initializes int arrays with 0 by default
     */

    /*
     * Fill DP table
     */
    for(int i = 1; i <= n; i++){              // i → number of coins considered

        for(int j = 1; j <= sum; j++){        // j → current target sum

            /*
             * If current coin can be used
             */
            if(coins[i-1] <= j){

                /*
                 * Choice 1: Take current coin
                 *
                 * IMPORTANT:
                 * Use dp[i][...]
                 * because same coin can be reused
                 */
                int take = dp[i][j - coins[i-1]];

                /*
                 * Choice 2: Skip current coin
                 */
                int skip = dp[i-1][j];

                /*
                 * Total ways = take + skip
                 */
                dp[i][j] = take + skip;
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
     * Final Answer:
     * number of ways to form given sum
     */
    System.out.println(dp[n][sum]);
}
}
