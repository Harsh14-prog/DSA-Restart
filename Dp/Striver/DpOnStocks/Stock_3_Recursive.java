package Dp.Striver.DpOnStocks;

public class Stock_3_Recursive {

    public static int solve(int[] nums, int index, int buy, int cap) {

        /*
         * Base Case 1:
         * No days left.
         */
        if(index == nums.length){
            return 0;
        }

        /*
         * Base Case 2:
         * No transactions remaining.
         */
        if(cap == 0){
            return 0;
        }

        /*
         * buy = 1
         *
         * No stock currently in hand.
         * We can either buy or skip.
         */
        if(buy == 1){

            int bought =
                    -nums[index]
                    + solve(nums, index + 1, 0, cap);

            int skip =
                    solve(nums, index + 1, 1, cap);

            return Math.max(bought, skip);
        }

        /*
         * buy = 0
         *
         * Stock already in hand.
         * We can either sell or skip.
         */
        else{

            /*
             * Selling completes one transaction.
             * Therefore cap decreases by 1.
             */
            int sell =
                    nums[index]
                    + solve(nums, index + 1, 1, cap - 1);

            int skip =
                    solve(nums, index + 1, 0, cap);

            return Math.max(sell, skip);
        }
    }

    public static void main(String[] args) {

        int[] nums = {3,3,5,0,0,3,1,4};

        System.out.println(
                solve(nums, 0, 1, 2)
        );
    }
}