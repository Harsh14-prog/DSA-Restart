package Dp.Striver.DpOnStocks;

public class Stock_2_WithRecursion {

    public static int solve(int[] prices, int index, int buy) {

        /*
         * Base Case:
         *
         * If we have processed all days,
         * no further profit can be made.
         */
        if (index == prices.length) {
            return 0;
        }

        /*
         * buy == 1
         *
         * Means:
         * We currently DO NOT own any stock.
         *
         * Therefore we have two choices:
         *
         * 1. Buy the stock today
         * 2. Skip today's day
         */
        if (buy == 1) {

            /*
             * Choice 1 : Buy
             *
             * Money leaves our pocket,
             * therefore profit decreases.
             *
             * After buying:
             * We now own a stock,
             * so next state becomes buy = 0.
             */
            int bought =
                    -prices[index]
                    + solve(prices, index + 1, 0);

            /*
             * Choice 2 : Skip
             *
             * Do nothing today.
             *
             * Still no stock in hand,
             * therefore buy remains 1.
             */
            int skip =
                    solve(prices, index + 1, 1);

            /*
             * Return the better choice.
             */
            return Math.max(bought, skip);
        }

        /*
         * buy == 0
         *
         * Means:
         * We already own a stock.
         *
         * Therefore we have two choices:
         *
         * 1. Sell the stock
         * 2. Keep holding it
         */
        else {

            /*
             * Choice 1 : Sell
             *
             * Money enters our pocket,
             * therefore profit increases.
             *
             * After selling:
             * We no longer own a stock,
             * therefore next state becomes buy = 1.
             */
            int sell =
                    prices[index]
                    + solve(prices, index + 1, 1);

            /*
             * Choice 2 : Skip
             *
             * Keep holding the stock.
             *
             * State remains buy = 0.
             */
            int skip =
                    solve(prices, index + 1, 0);

            /*
             * Return the better choice.
             */
            return Math.max(sell, skip);
        }
    }

    public static void main(String[] args) {

        int[] prices = {7, 1, 5, 3, 6, 4};

        /*
         * Start from day 0.
         */
        int index = 0;

        /*
         * Initially:
         * No stock is owned.
         *
         * Therefore we are allowed to buy.
         */
        int buy = 1;

        int maxProfit = solve(prices, index, buy);

        System.out.println(maxProfit);
    }
}