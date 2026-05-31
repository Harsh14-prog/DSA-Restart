package Dp.knapSack;

public class MinCoinsIndexBasedApproachRecursive {

    /*
     * We use a very large number to represent
     * an impossible state.
     *
     * Why not Integer.MAX_VALUE?
     *
     * Because later we do:
     *
     * 1 + solve(...)
     *
     * and Integer.MAX_VALUE + 1
     * causes integer overflow.
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
         * required to form 'sum'
         * using coins from 0...index.
         */

        /*
         * ==========================
         * BASE CASE 1
         * ==========================
         *
         * Sum has become 0.
         *
         * We successfully formed the target.
         *
         * How many more coins are needed?
         *
         * Answer = 0
         */
        if(sum == 0){
            return 0;
        }

        /*
         * ==========================
         * BASE CASE 2
         * ==========================
         *
         * Only first coin remains.
         *
         * We must decide whether the
         * remaining sum can be formed
         * using only coins[0].
         */
        if(index == 0){

            /*
             * If sum is divisible by coin[0],
             * then we can form the sum
             * using only this coin.
             *
             * Example:
             *
             * coin = 2
             * sum  = 8
             *
             * 8/2 = 4 coins
             */
            if(sum % coins[0] == 0){
                return sum / coins[0];
            }

            /*
             * Example:
             *
             * coin = 2
             * sum  = 7
             *
             * Cannot form 7 using only 2s.
             *
             * Return impossible state.
             */
            return INF;
        }

        /*
         * ==========================
         * CHOICE 1 : TAKE COIN
         * ==========================
         *
         * We can take the current coin
         * only if it does not exceed
         * the remaining sum.
         */
        if(coins[index] <= sum){

            /*
             * TAKE
             *
             * Current coin contributes 1 coin.
             *
             * Remaining sum:
             * sum - coins[index]
             *
             * IMPORTANT:
             *
             * We stay at the SAME index.
             *
             * Why?
             *
             * Because Coin Change is an
             * Unbounded Knapsack problem.
             *
             * We are allowed to reuse
             * the same coin multiple times.
             */
            int take =
                    1 +
                    solve(coins,
                          sum - coins[index],
                          index);

            /*
             * SKIP
             *
             * Ignore current coin and move
             * to smaller indexed coins.
             */
            int skip =
                    solve(coins,
                          sum,
                          index - 1);

            /*
             * We need minimum coins.
             *
             * Therefore choose the better
             * (smaller) answer.
             */
            return Math.min(take, skip);
        }

        /*
         * ==========================
         * COIN TOO LARGE
         * ==========================
         *
         * Current coin cannot participate.
         *
         * Example:
         *
         * coin = 5
         * sum  = 3
         *
         * We cannot take this coin.
         *
         * Only option:
         * Skip it.
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
         * Start from the last actual index.
         *
         * Available coins:
         *
         * 1, 2, 3
         */
        int result =
                solve(coins,
                      sum,
                      n - 1);  // actual index sent

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