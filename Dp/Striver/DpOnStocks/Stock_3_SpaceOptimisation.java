package Dp.Striver.DpOnStocks;

public class Stock_3_SpaceOptimisation {

    public static void main(String[] args) {

        int[] nums = {3, 3, 5, 0, 0, 3, 1, 4};

        int n = nums.length;

        /*
         * ahead represents dp[index + 1]
         *
         * ahead[buy][cap]
         *
         * buy = 1 -> Can Buy
         * buy = 0 -> Can Sell
         *
         * cap = transactions remaining
         *
         * Initially ahead represents:
         *
         * dp[n][buy][cap]
         *
         * which is the base case.
         *
         * Since after the last day no profit can be earned,
         * all values remain 0.
         */
        int[][] ahead = new int[2][3];

        /*
         * Traverse from last day to first day.
         *
         * Reason:
         *
         * dp[index][...][...]
         * depends on
         * dp[index + 1][...][...]
         *
         * Therefore future states must already be calculated.
         */
        for (int index = n - 1; index >= 0; index--) {

            /*
             * curr represents dp[index]
             *
             * Current layer being calculated.
             */
            int[][] curr = new int[2][3];

            /*
             * cap starts from 1 because:
             *
             * cap = 0 is already a base case.
             *
             * dp[index][buy][0] = 0
             */
            for (int cap = 1; cap <= 2; cap++) {

                // =====================================
                // STATE : buy = 1
                // =====================================
                //
                // No stock is currently in hand.
                //
                // Choices:
                // 1. Buy
                // 2. Skip
                //
                // =====================================

                /*
                 * Option 1 : Buy today
                 *
                 * Money leaves our pocket.
                 *
                 * Therefore:
                 *
                 * -nums[index]
                 *
                 * After buying:
                 *
                 * Stock is now in hand.
                 *
                 * Next state:
                 *
                 * buy = 0
                 *
                 * cap remains same because
                 * transaction is not completed yet.
                 */
                int bought =
                        -nums[index]
                        + ahead[0][cap];

                /*
                 * Option 2 : Skip today
                 *
                 * State remains:
                 *
                 * buy = 1
                 * cap unchanged
                 */
                int skipBuy =
                        ahead[1][cap];

                /*
                 * Store maximum profit.
                 */
                curr[1][cap] =
                        Math.max(bought, skipBuy);

                // =====================================
                // STATE : buy = 0
                // =====================================
                //
                // Stock is already in hand.
                //
                // Choices:
                // 1. Sell
                // 2. Skip
                //
                // =====================================

                /*
                 * Option 1 : Sell today
                 *
                 * Money enters our pocket.
                 *
                 * Therefore:
                 *
                 * +nums[index]
                 *
                 * After selling:
                 *
                 * No stock remains in hand.
                 *
                 * Next state:
                 *
                 * buy = 1
                 *
                 * One complete transaction
                 * is finished here.
                 *
                 * Therefore:
                 *
                 * cap decreases by 1
                 */
                int sell =
                        nums[index]
                        + ahead[1][cap - 1];

                /*
                 * Option 2 : Skip today
                 *
                 * Keep holding the stock.
                 *
                 * State remains:
                 *
                 * buy = 0
                 * cap unchanged
                 */
                int skipSell =
                        ahead[0][cap];

                /*
                 * Store maximum profit.
                 */
                curr[0][cap] =
                        Math.max(sell, skipSell);
            }

            /*
             * Current layer becomes
             * future layer for next iteration.
             *
             * dp[index]
             * becomes
             * dp[index + 1]
             */
            ahead = curr;
        }

        /*
         * Initial State:
         *
         * Day = 0
         * No stock in hand
         * Can Buy
         * 2 transactions available
         *
         * Same as recursive call:
         *
         * solve(nums, 0, 1, 2)
         */
        System.out.println(ahead[1][2]);
    }
}