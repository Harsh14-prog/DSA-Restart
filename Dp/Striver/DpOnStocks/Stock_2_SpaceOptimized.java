package Dp.Striver.DpOnStocks;

public class Stock_2_SpaceOptimized {

    public static void main(String[] args) {

        int[] prices = {7, 1, 5, 3, 6, 4};

        int n = prices.length;

        /*
         * ahead represents dp[index + 1][buy]
         *
         * ahead[1] -> Profit from next day when we are allowed to buy
         *             (No stock currently in hand)
         *
         * ahead[0] -> Profit from next day when we are allowed to sell
         *             (Stock currently in hand)
         *
         * Initially, ahead represents the base case:
         *
         * dp[n][0] = 0
         * dp[n][1] = 0
         *
         * Because after the last day no profit can be earned.
         */
        int[] ahead = new int[2];

        /*
         * Fill DP from last day to first day because:
         *
         * dp[index][...]
         * depends on
         * dp[index + 1][...]
         */
        for (int index = n - 1; index >= 0; index--) {

            /*
             * curr represents dp[index][buy]
             *
             * Current row being calculated.
             */
            int[] curr = new int[2];

            // ==================================================
            // CASE 1 : buy = 1
            // Meaning:
            // No stock is currently in hand.
            // We are allowed to BUY.
            // ==================================================

            /*
             * Option 1 : Buy the stock today.
             *
             * Money leaves our pocket,
             * therefore subtract current price.
             *
             * After buying:
             * stock is now in hand,
             * so next state becomes buy = 0.
             */
            int buy = - prices[index] + ahead[0];

            /*
             * Option 2 : Skip today's day.
             *
             * Still no stock in hand,
             * so state remains buy = 1.
             */
            int skip = ahead[1];

            /*
             * Store maximum profit among:
             * Buy
             * Skip
             */
            curr[1] = Math.max(buy, skip);

            // ==================================================
            // CASE 2 : buy = 0
            // Meaning:
            // Stock is already in hand.
            // We are allowed to SELL.
            // ==================================================

            /*
             * Option 1 : Sell the stock today.
             *
             * Money enters our pocket,
             * therefore add current price.
             *
             * After selling:
             * no stock remains in hand,
             * so next state becomes buy = 1.
             */
            int sell = prices[index] + ahead[1];

            /*
             * Option 2 : Do not sell today.
             *
             * Keep holding the stock.
             *
             * State remains buy = 0.
             */
            int skips = ahead[0];

            /*
             * Store maximum profit among:
             * Sell
             * Skip
             */
            curr[0] = Math.max(sell, skips);

            /*
             * Move current row to ahead row.
             *
             * Because in the next iteration:
             *
             * current dp[index]
             * becomes
             * future dp[index + 1]
             */
            ahead = curr;
        }

        /*
         * Initial state:
         *
         * Day = 0
         * No stock in hand
         * Allowed to buy
         *
         * Therefore answer is stored in:
         */
        System.out.println(ahead[1]);
    }
}