package Dp.knapSack;

public class MinCoinSpaceOptimised {

    static final int INF = Integer.MAX_VALUE - 1;

    public static void main(String[] args) {

        int[] coins = {1, 2, 3};

        int sum = 5;

        int n = coins.length;

        /*
         * prev[amt]
         *
         * Represents:
         *
         * Minimum coins required
         * to form amount = amt
         * using coins processed so far.
         */
        int[] prev = new int[sum + 1];

        /*
         * ==========================
         * BASE CASE
         * ==========================
         *
         * First row of DP table.
         *
         * We are allowed to use
         * only coins[0].
         *
         * If amount is divisible by
         * coins[0], we can form it.
         *
         * Example:
         *
         * coin = 2
         *
         * amt = 6
         *
         * 6/2 = 3 coins
         *
         * Otherwise it is impossible.
         */
        for (int amt = 0; amt <= sum; amt++) {

            if (amt % coins[0] == 0) {
                prev[amt] = amt / coins[0];
            } else {
                prev[amt] = INF;
            }
        }

        /*
         * Process remaining coins.
         */
        for (int index = 1; index < n; index++) {

            /*
             * Current row.
             */
            int[] curr = new int[sum + 1];

            /*
             * Amount 0 always requires
             * 0 coins.
             */
            curr[0] = 0;

            for (int amt = 1; amt <= sum; amt++) {

                /*
                 * ==========================
                 * SKIP CURRENT COIN
                 * ==========================
                 *
                 * Move to previous row.
                 *
                 * Meaning:
                 * Do not use current coin.
                 */
                int skip = prev[amt];

                /*
                 * Assume impossible initially.
                 */
                int take = INF;

                /*
                 * ==========================
                 * TAKE CURRENT COIN
                 * ==========================
                 *
                 * Coin Change is an
                 * Unbounded Knapsack problem.
                 *
                 * Therefore after taking
                 * current coin, we stay
                 * in the same row.
                 *
                 * Hence:
                 *
                 * curr[amt - coins[index]]
                 *
                 * and NOT prev[...]
                 */
                if (coins[index] <= amt) {

                    take =
                            1 +
                            curr[amt - coins[index]];
                }

                /*
                 * We need minimum coins.
                 */
                curr[amt] = Math.min(take, skip);
            }

            /*
             * Current row becomes
             * previous row for next iteration.
             */
            prev = curr;
        }

        int answer = prev[sum];

        if (answer >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}