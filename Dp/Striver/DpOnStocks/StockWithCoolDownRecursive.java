package Dp.Striver.DpOnStocks;

public class StockWithCoolDownRecursive {

    public static int solve(
            int[] prices,
            int index,
            int buy) {

        /*
         * Base Case:
         *
         * If index goes beyond the last day,
         * no more profit can be earned.
         *
         * We use:
         *
         * index >= prices.length
         *
         * instead of:
         *
         * index == prices.length
         *
         * because after selling we move to:
         *
         * index + 2
         *
         * due to cooldown.
         */
        if (index >= prices.length)
            return 0;

        /*
         * buy = 1
         *
         * Meaning:
         *
         * No stock is currently in hand.
         *
         * We are allowed to:
         *
         * 1. Buy
         * 2. Skip
         */
        if (buy == 1) {

            /*
             * Option 1 : Buy stock today
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
                    + solve(prices, index + 1, 0);

            /*
             * Option 2 : Skip today's day
             *
             * Still no stock in hand.
             *
             * Next state:
             *
             * buy = 1
             */
            int skip =
                    solve(prices, index + 1, 1);

            /*
             * Return maximum profit.
             */
            return Math.max(bought, skip);
        }

        /*
         * buy = 0
         *
         * Meaning:
         *
         * Stock is already in hand.
         *
         * We are allowed to:
         *
         * 1. Sell
         * 2. Skip
         */
        else {

            /*
             * Option 1 : Sell stock today
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
             * one cooldown day is mandatory.
             *
             * So:
             *
             * Today     -> Sell
             * Tomorrow  -> Cooldown
             * at index + 1 can't buy like prev part
             * We can only buy again from:
             *
             * index + 2
             *
             * Therefore:
             *
             * solve(index + 2, 1)
             */
            int sell =
                    prices[index]
                    + solve(prices, index + 2, 1);

            /*
             * Option 2 : Skip today's day
             *
             * Continue holding stock.
             *
             * Next state:
             *
             * buy = 0
             */
            int skip =
                    solve(prices, index + 1, 0);

            /*
             * Return maximum profit.
             */
            return Math.max(sell, skip);
        }
    }

    public static void main(String[] args) {

        int[] prices = {4, 9, 0, 4, 10};

        /*
         * Initial State:
         *
         * Day 0
         * No stock in hand
         * Allowed to buy
         */
        int maxProfit =
                solve(prices, 0, 1);

        System.out.println(maxProfit);
    }
}