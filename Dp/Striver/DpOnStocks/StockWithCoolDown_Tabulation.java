package Dp.Striver.DpOnStocks;

public class StockWithCoolDown_Tabulation {

    public static void main(String[] args) {

        int[] prices = {4, 9, 0, 4, 10};

        int n = prices.length;

        /*
         * dp[index][buy]
         *
         * buy = 1
         * Means:
         * No stock in hand.
         * We are allowed to BUY.
         *
         * buy = 0
         * Means:
         * Stock is already in hand.
         * We are allowed to SELL.
         *
         * We need n + 2 rows because:
         *
         * Sell transition uses:
         *
         * dp[index + 2][1]
         *
         * due to cooldown.
         */
        int[][] dp = new int[n + 2][2];

        /*
         * Base Cases:
         *
         * dp[n][0] = 0
         * dp[n][1] = 0
         *
         * dp[n+1][0] = 0
         * dp[n+1][1] = 0
         *
         * Already initialized by Java.
         */

        /*
         * Fill DP table from bottom to top.
         *
         * Reason:
         *
         * dp[index][...]
         * depends on:
         *
         * dp[index + 1][...]
         * dp[index + 2][...]
         *
         * Therefore future states must
         * already be computed.
         */
        for (int index = n - 1; index >= 0; index--) {

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
             */
            int bought =
                    -prices[index]
                    + dp[index + 1][0];

            /*
             * Option 2 : Skip today
             *
             * Stay in buy state.
             */
            int skipBuy =
                    dp[index + 1][1];

            /*
             * Store maximum profit.
             */
            dp[index][1] =
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
             * Therefore we cannot buy on:
             *
             * index + 1
             *
             * We can only buy again from:
             *
             * index + 2
             */
            int sell =
                    prices[index]
                    + dp[index + 2][1];

            /*
             * Option 2 : Skip today
             *
             * Continue holding stock.
             */
            int skipSell =
                    dp[index + 1][0];

            /*
             * Store maximum profit.
             */
            dp[index][0] =
                    Math.max(sell, skipSell);
        }

        /*
         * Initial State:
         *
         * Day 0
         * No stock in hand
         * Allowed to buy
         *
         * Same as:
         *
         * solve(prices, 0, 1)
         */
        System.out.println(dp[0][1]);
    }
}