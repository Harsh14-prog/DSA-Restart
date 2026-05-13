package Dp.knapSack;

public class CoinChangeWaysTopDown {

// dp[n][sum] = number of ways to form 'sum'
// using first n coins
static int[][] dp;

public static int solve(int[] coins, int sum, int n){

    /*
     * Base Case 1:
     * If sum becomes 0
     * → found one valid way
     */
    if(sum == 0){
        return 1;
    }

    /*
     * Base Case 2:
     * No coins left but sum still not formed
     */
    if(n == 0){
        return 0;
    }

    /*
     * Memoization check
     */
    if(dp[n][sum] != -1){
        return dp[n][sum];
    }

    /*
     * If current coin can be used
     */
    if(coins[n-1] <= sum){

        /*
         * Choice 1:
         * Take current coin
         *
         * IMPORTANT:
         * Stay at same n
         * because coin can be reused
         */
        int take = solve(coins,
                         sum - coins[n-1],
                         n);

        /*
         * Choice 2:
         * Skip current coin
         */
        int skip = solve(coins,
                         sum,
                         n-1);

        /*
         * Total ways = take + skip
         */
        int ways = take + skip;

        dp[n][sum] = ways;

        return ways;
    }

    /*
     * Current coin too large
     */
    else{

        int ways = solve(coins,
                         sum,
                         n-1);

        dp[n][sum] = ways;

        return ways;
    }
}

public static void main(String[] args) {

    int[] coins = {1,2,3};

    int sum = 5;

    int n = coins.length;

    /*
     * DP table initialization
     */
    dp = new int[n+1][sum+1];

    // Fill with -1 (not computed)
    for(int i = 0; i <= n; i++){
        for(int j = 0; j <= sum; j++){
            dp[i][j] = -1;
        }
    }

    int ways = solve(coins, sum, n);

    System.out.println(ways);
}
}
