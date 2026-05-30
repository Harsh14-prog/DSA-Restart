package Dp.Striver.DpOnStocks;

public class StockWithTransactionFee {

    public static int solve(
            int[] prices,
            int index,
            int buy,
            int fee) {

        /*
         * Base Case:
         *
         * If all days have been processed,
         * no further profit can be earned.
         */
        if (index == prices.length)
            return 0;

        /*
         * buy = 1
         *
         * Meaning:
         * No stock is currently in hand.
         *
         * We are allowed to:
         * 1. Buy
         * 2. Skip
         */
        if (buy == 1) {

            /*
             * Option 1 : Buy stock today
             *
             * Money leaves our pocket,
             * therefore subtract stock price.
             *
             * After buying:
             * Stock is now in hand.
             *
             * Next state:
             * buy = 0
             */
            int bought =
                    -prices[index]
                    + solve(prices, index + 1, 0, fee);

            /*
             * Option 2 : Skip today's day
             *
             * No stock is still in hand.
             *
             * Next state:
             * buy = 1
             */
            int skip =
                    solve(prices, index + 1, 1, fee);

            /*
             * Choose the better option.
             */
            return Math.max(bought, skip);
        }

        /*
         * buy = 0
         *
         * Meaning:
         * Stock is already in hand.
         *
         * We are allowed to:
         * 1. Sell
         * 2. Skip
         */
        else {

            /*
             * Option 1 : Sell stock today
             *
             * Money enters our pocket,
             * therefore add stock price.
             *
             * Transaction fee is charged
             * when the stock is sold.
             *
             * Therefore:
             *
             * +price - fee
             *
             * After selling:
             * No stock remains in hand.
             *
             * Next state:
             * buy = 1
             */
            int sell =
                    prices[index]
                    - fee
                    + solve(prices, index + 1, 1, fee);

            /*
             * Option 2 : Skip today's day
             *
             * Continue holding the stock.
             *
             * Next state:
             * buy = 0
             */
            int skip =
                    solve(prices, index + 1, 0, fee);

            /*
             * Choose the better option.
             */
            return Math.max(sell, skip);
        }
    }

    public static void main(String[] args) {

        int[] prices = {1, 3, 2, 8, 4, 9};

        /*
         * Transaction fee charged
         * every time we sell.
         */
        int fee = 2;

        /*
         * Initial State:
         *
         * Day 0
         * No stock in hand
         * Allowed to buy
         */
        int maxProfit =
                solve(prices, 0, 1, fee);

        System.out.println(maxProfit);
    }
}