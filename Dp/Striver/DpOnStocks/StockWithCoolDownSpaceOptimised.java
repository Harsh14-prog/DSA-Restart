package Dp.Striver.DpOnStocks;

public class StockWithCoolDownSpaceOptimised {

    public static void main(String[] args) {

        int[] prices = {4, 9, 0, 4, 10};

        int n = prices.length;

        /*
         * ahead1 represents:
         *
         * dp[index + 1]
         *
         * ahead1[1] -> Can Buy
         * ahead1[0] -> Can Sell
         *
         * Initially represents:
         *
         * dp[n]
         *
         * which is the base case.
         */
        int[] ahead1 = new int[2];

        /*
         * ahead2 represents:
         *
         * dp[index + 2]
         *
         * Needed because cooldown
         * uses index + 2.
         *
         * Initially represents:
         *
         * dp[n + 1]
         *
         * which is also a base case.
         */
        int[] ahead2 = new int[2];

        /*
         * Traverse from last day
         * to first day.
         *
         * Reason:
         *
         * Current state depends on:
         *
         * dp[index + 1]
         * dp[index + 2]
         *
         * Therefore future states
         * must already be calculated.
         */
        for (int index = n - 1; index >= 0; index--) {

            /*
             * Current row being calculated.
             *
             * curr[1] -> Can Buy
             * curr[0] -> Can Sell
             */
            int[] curr = new int[2];

            // =====================================
            // STATE : buy = 1
            // =====================================
            //
            // No stock currently in hand.
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
             * -prices[index]
             *
             * After buying:
             *
             * Stock is now in hand.
             *
             * Next state:
             *
             * buy = 0
             *
             * Stored in:
             *
             * ahead1[0]
             */
            int bought =
                    -prices[index]
                    + ahead1[0];

            /*
             * Option 2 : Skip today
             *
             * Remain in buy state.
             *
             * Stored in:
             *
             * ahead1[1]
             */
            int skipBuy =
                    ahead1[1];

            /*
             * Store maximum profit.
             */
            curr[1] =
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
             * +prices[index]
             *
             * IMPORTANT:
             *
             * After selling,
             * next day becomes cooldown day.
             *
             * Therefore:
             *
             * index + 1 -> Cooldown
             *
             * We can buy again only from:
             *
             * index + 2
             *
             * Hence:
             *
             * ahead2[1]
             */
            int sell =
                    prices[index]
                    + ahead2[1];

            /*
             * Option 2 : Skip today
             *
             * Continue holding stock.
             *
             * Stored in:
             *
             * ahead1[0]
             */
            int skipSell =
                    ahead1[0];

            /*
             * Store maximum profit.
             */
            curr[0] =
                    Math.max(sell, skipSell);

            /*
             * Shift rows.
             *
             * Before shift:
             *
             * ahead1 = dp[index + 1]
             * ahead2 = dp[index + 2]
             *
             * curr   = dp[index]
             *
             * For next iteration we need:
             *
             * ahead1 = dp[index]
             * ahead2 = dp[index + 1]
             *
             * Therefore:
             */

            ahead2 = ahead1;
            ahead1 = curr;
        }

        /*
         * Initial State:
         *
         * Day 0
         * No stock in hand
         * Can Buy
         *
         * Same as:
         *
         * solve(prices, 0, 1)
         */
        System.out.println(ahead1[1]);
    }
}