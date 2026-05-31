package Dp.knapSack;

public class MinCoinsNoOfItemBasedApprochRecursion {

    /*
     * Represents an impossible state.
     *
     * Why not Integer.MAX_VALUE?
     *
     * Because later we do:
     *
     * 1 + solve(...)
     *
     * If solve(...) returns Integer.MAX_VALUE,
     * adding 1 will cause integer overflow.
     *
     * Therefore we use MAX_VALUE - 1.
     */
    static final int INF = Integer.MAX_VALUE - 1;

    public static int solve(int[] coins,
                            int sum,
                            int index){

        /*
         * Meaning of function:
         *
         * solve(sum, index)
         *
         * Returns the minimum number of coins
         * required to create 'sum'
         * using the first 'index' coins.
         *
         * IMPORTANT:
         *
         * Here index does NOT represent
         * an array index.
         *
         * It represents:
         *
         * Number of coins available.
         *
         * Example:
         *
         * index = 3
         *
         * Means we can use:
         *
         * coins[0], coins[1], coins[2]
         *
         * index = 1
         *
         * Means we can use only:
         *
         * coins[0]
         */

        /*
         * ==========================
         * BASE CASE 1
         * ==========================
         *
         * Target sum has become 0.
         *
         * This means we have already
         * successfully formed the amount.
         *
         * Question:
         *
         * Minimum coins needed to form
         * amount = 0 ?
         *
         * Answer:
         *
         * 0 coins.
         *
         * Therefore return 0.
         */
        if(sum == 0){
            return 0;
        }

        /*
         * ==========================
         * BASE CASE 2
         * ==========================
         *
         * No coins left.
         *
         * index = 0 means
         * we have exhausted all coins.
         *
         * But sum is still greater than 0
         * because Base Case 1 did not execute.
         *
         * Example:
         *
         * sum = 5
         * index = 0
         *
         * We need to form 5
         * using no coins.
         *
         * Clearly impossible.
         *
         * Therefore return INF.
         */
        if(index == 0){
            return INF;
        }

        /*
         * Current coin available:
         *
         * coins[index - 1]
         *
         * Why index - 1 ?
         *
         * Because index represents
         * number of available coins.
         *
         * Example:
         *
         * index = 3
         *
         * Current coin is:
         *
         * coins[2]
         */
        if(coins[index - 1] <= sum){

            /*
             * ==========================
             * CHOICE 1 : TAKE
             * ==========================
             *
             * Take current coin.
             *
             * Since one coin is used,
             * add 1 to answer.
             *
             * Remaining sum:
             *
             * sum - currentCoin
             *
             * IMPORTANT:
             *
             * We stay at the SAME index.
             *
             * Why?
             *
             * Coin Change is an
             * Unbounded Knapsack problem.
             *
             * We can use the same coin
             * unlimited number of times.
             *
             * Therefore current coin
             * remains available.
             */
            int take =
                    1 +
                    solve(coins,
                          sum - coins[index - 1],
                          index);

            /*
             * ==========================
             * CHOICE 2 : SKIP
             * ==========================
             *
             * Ignore current coin.
             *
             * Move to smaller set of coins.
             *
             * Since current coin is discarded,
             * available coins decrease by 1.
             */
            int skip =
                    solve(coins,
                          sum,
                          index - 1);

            /*
             * We need minimum number
             * of coins.
             *
             * Therefore choose the
             * smaller answer.
             */
            return Math.min(take, skip);
        }

        /*
         * ==========================
         * COIN CANNOT BE TAKEN
         * ==========================
         *
         * Current coin is larger
         * than remaining sum.
         *
         * Example:
         *
         * coin = 7
         * sum  = 4
         *
         * Cannot take coin 7.
         *
         * Only option:
         *
         * Skip current coin.
         */
        else{

            return solve(coins,
                         sum,
                         index - 1);
        }
    }

    public static void main(String[] args) {

        int[] coins = {1, 2, 3};

        int n = coins.length;

        int sum = 5;

        /*
         * Start recursion with:
         *
         * sum = target amount
         *
         * index = total number of coins
         *
         * Here we send:
         *
         * n
         *
         * not n-1
         *
         * because this approach uses
         * "number of items available"
         * instead of actual array index.
         */
        int result =
                solve(coins,
                      sum,
                      n);

        /*
         * If answer is INF,
         * target cannot be formed.
         */
        if(result == INF){
            System.out.println(-1);
        }
        else{
            System.out.println(result);
        }
    }
}