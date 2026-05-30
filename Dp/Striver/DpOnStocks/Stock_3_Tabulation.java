package Dp.Striver.DpOnStocks;

public class Stock_3_Tabulation {

    public static void main(String[] args) {

        int[] nums = {3, 3, 5, 0, 0, 3, 1, 4};

        int n = nums.length;

        /*
         * dp[index][buy][cap]
         *
         * index -> Current day
         *
         * buy = 1
         * Means:
         * No stock is currently in hand.
         * We are allowed to BUY.
         *
         * buy = 0
         * Means:
         * Stock is currently in hand.
         * We are allowed to SELL.
         *
         * cap -> Number of transactions remaining.
         *
         * cap can be:
         * 0, 1, 2
         *
         * Therefore dimensions are:
         *
         * index : 0 to n
         * buy   : 0 to 1
         * cap   : 0 to 2
         */
        int[][][] dp = new int[n + 1][2][3];

        /*
         * Base Case 1:
         *
         * When index == n
         * No days are left.
         *
         * Therefore:
         *
         * dp[n][buy][cap] = 0
         *
         * Already initialized by Java.
         */

        /*
         * Base Case 2:
         *
         * When cap == 0
         * No transactions are left.
         *
         * Therefore:
         *
         * dp[index][buy][0] = 0
         *
         * Already initialized by Java.
         */

        /*
         * We move from last day to first day because:
         *
         * dp[index][...][...]
         * depends on
         * dp[index + 1][...][...]
         *
         * So future states must be computed first.
         */
        for (int index = n - 1; index >= 0; index--) {

            /*
             * cap starts from 1.
             *
             * We do not start from 0 because:
             *
             * cap = 0 is already a base case.
             */
            for (int cap = 1; cap <= 2; cap++) {

                // ==========================================
                // CASE 1 : buy = 1
                // ==========================================
                //
                // Means:
                // No stock is currently in hand.
                //
                // Choices:
                // 1. Buy
                // 2. Skip
                //
                // ==========================================

                /*
                 * Option 1 : Buy stock today
                 *
                 * Money leaves our pocket,
                 * therefore subtract stock price.
                 *
                 * After buying:
                 * Stock is now in hand.
                 *
                 * So next state becomes:
                 * buy = 0
                 *
                 * cap remains same because
                 * transaction is NOT completed yet.
                 */
                int bought =
                        -nums[index]
                        + dp[index + 1][0][cap];

                /*
                 * Option 2 : Skip today's day.
                 *
                 * State remains unchanged.
                 */
                int skipBuy =
                        dp[index + 1][1][cap];

                /*
                 * Store maximum profit.
                 */
                dp[index][1][cap] =
                        Math.max(bought, skipBuy);

                // ==========================================
                // CASE 2 : buy = 0
                // ==========================================
                //
                // Means:
                // Stock is already in hand.
                //
                // Choices:
                // 1. Sell
                // 2. Skip
                //
                // ==========================================

                /*
                 * Option 1 : Sell stock today
                 *
                 * Money enters our pocket,
                 * therefore add stock price.
                 *
                 * After selling:
                 * No stock remains in hand.
                 *
                 * So next state becomes:
                 * buy = 1
                 *
                 * Transaction gets completed here.
                 *
                 * Therefore cap decreases by 1.
                 */
                int sell =
                        nums[index]
                        + dp[index + 1][1][cap - 1];

                /*
                 * Option 2 : Skip today's day.
                 *
                 * Continue holding stock.
                 */
                int skipSell =
                        dp[index + 1][0][cap];

                /*
                 * Store maximum profit.
                 */
                dp[index][0][cap] =
                        Math.max(sell, skipSell);
            }
        }

        /*
         * Initial State:
         *
         * Day 0
         * No stock in hand
         * Allowed to buy
         * 2 transactions available
         *
         * Same as recursive call:
         *
         * solve(nums, 0, 1, 2)
         */
        System.out.println(dp[0][1][2]);
    }
}