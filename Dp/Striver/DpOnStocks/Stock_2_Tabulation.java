package Dp.Striver.DpOnStocks;

public class Stock_2_Tabulation {

    public static int solve(int[] prices) {

        int n = prices.length;

        /*
         * dp[index][buy]
         *
         * buy = 1
         * => No stock in hand, can buy
         *
         * buy = 0
         * => Stock in hand, can sell
         */
        int[][] dp = new int[n + 1][2];

        /*
         * Base Case:
         *
         * dp[n][0] = 0
         * dp[n][1] = 0
         *
         * Already initialized by Java.
         */

        for(int index = n - 1; index >= 0; index--) {

            /*
             * buy = 1
             *
             * Can Buy or Skip
             */
            int bought =
                    -prices[index]
                    + dp[index + 1][0];

            int skipBuy =
                    dp[index + 1][1];

            dp[index][1] =
                    Math.max(bought, skipBuy);

            /*
             * buy = 0
             *
             * Can Sell or Skip
             */
            int sell =
                    prices[index]
                    + dp[index + 1][1];

            int skipSell =
                    dp[index + 1][0];

            dp[index][0] =
                    Math.max(sell, skipSell);
        }

        /*
         * Start from:
         *
         * Day 0
         * No stock in hand
         * Can buy
         */
        return dp[0][1];
    }

    public static void main(String[] args) {

        int[] prices = {7,1,5,3,6,4};

        System.out.println(solve(prices));
    }
}