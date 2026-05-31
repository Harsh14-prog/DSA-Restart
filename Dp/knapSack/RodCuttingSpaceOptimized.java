package Dp.knapSack;

public class RodCuttingSpaceOptimized {

    public static void main(String[] args) {

        /*
         * price[i]
         *
         * Profit obtained by selling
         * a rod piece of length (i + 1).
         */
        int[] price = {2, 5, 7, 8, 10};

        /*
         * Available rod piece lengths.
         */
        int[] length = {1, 2, 3, 4, 5};

        int n = length.length;

        /*
         * Total rod length available.
         */
        int rodLength = 4;

        /*
         * dp[len]
         *
         * Maximum profit obtainable
         * for rod length = len.
         */
        int[] dp = new int[rodLength + 1];

        /*
         * ==========================
         * BASE CASE
         * ==========================
         *
         * Only length 1 piece is available.
         *
         * Since length[0] = 1,
         * we can always fill the rod.
         *
         * Example:
         *
         * rod length = 4
         *
         * profit =
         * 4 * price[0]
         */
        for(int len = 0; len <= rodLength; len++){

            dp[len] = len * price[0];
        }

        /*
         * Process remaining rod pieces.
         */
        for(int index = 1; index < n; index++){

            /*
             * LEFT -> RIGHT
             *
             * Rod Cutting is an
             * Unbounded Knapsack problem.
             *
             * Current piece can be reused.
             *
             * Therefore we WANT
             * current updates to be visible.
             */
            for(int len = 1; len <= rodLength; len++){

                /*
                 * Skip current piece.
                 *
                 * Existing answer remains.
                 */
                int skip = dp[len];

                /*
                 * Assume impossible initially.
                 */
                int take = Integer.MIN_VALUE;

                /*
                 * Take current piece
                 * if it fits.
                 */
                if(length[index] <= len){

                    /*
                     * Why dp and not curr/prev?
                     *
                     * Rod Cutting is
                     * Unbounded Knapsack.
                     *
                     * After taking current piece,
                     * we are allowed to take
                     * the same piece again.
                     *
                     * Therefore:
                     *
                     * take =
                     * price[index]
                     * + dp[len - length[index]]
                     *
                     * We intentionally use
                     * the already updated value.
                     */
                    take =
                            price[index]
                            + dp[len - length[index]];
                }

                /*
                 * Store maximum profit.
                 */
                dp[len] = Math.max(skip, take);
            }
        }

        /*
         * Maximum profit for full rod.
         */
        System.out.println(dp[rodLength]);
    }
}