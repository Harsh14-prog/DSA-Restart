package Dp.Striver.DpOnStocks;

public class Stock_1 {

    public static int solve(int[] nums, int n) {

        // Lowest buying price seen so far
        int minPrice = nums[0];

        // Maximum profit found till now
        int maxProfit = 0;

        // Start from second day
        for(int i = 1; i < n; i++) {

            /*
             * Profit if we sell on current day.
             *
             * Current Price - Best buying price seen before today
             */
            maxProfit = Math.max(maxProfit,
                                 nums[i] - minPrice);

            /*
             * Update the minimum buying price
             * for future days.
             */
            minPrice = Math.min(minPrice,
                                nums[i]);
        }

        return maxProfit;
    }

    public static void main(String[] args) {

        int[] nums = {7,1,5,3,6,4};

        int n = nums.length;

        int maxProfit = solve(nums, n);

        System.out.println(maxProfit);
    }
}