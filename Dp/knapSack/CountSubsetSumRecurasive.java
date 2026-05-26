package Dp.knapSack;

public class CountSubsetSumRecurasive {

    /*
     * Function:
     * Counts number of subsets whose sum is equal to target
     *
     * Parameters:
     * nums   -> input array
     * target -> required sum
     * n      -> number of elements available
     *
     * Meaning of solve(nums, target, n):
     *
     * "Using first n elements,
     *  how many subsets can form target sum?"
     */
    public static int solve(int[] nums, int target, int n) {

        /*
         * BASE CASE
         *
         * When no elements are left
         */
        if (n == 0) {

            /*
             * WHY ARE BOTH BASE CASES MERGED?
             *
             * We DO NOT write:
             *
             * if(target == 0) return 1;
             *
             * separately because of ZEROES.
             *
             * Example:
             * nums = [0,0,1]
             * target = 1
             *
             * Correct answer = 4
             *
             * Subsets:
             * [1]
             * [0,1]
             * [0,1]
             * [0,0,1]
             *
             * If we immediately return 1 when
             * target becomes 0,
             * recursion stops early
             * and remaining zeroes are not explored.
             *
             * Since zero can be:
             * TAKE or SKIP
             * without changing target,
             * they create extra subsets.
             *
             * Therefore:
             * we continue recursion until
             * all elements are processed.
             */

            /*
             * If target becomes 0 after processing
             * all elements,
             * we found one valid subset
             */
            if (target == 0) {
                return 1;
            }

            /*
             * No elements left
             * but target not formed
             */
            return 0;
        }

        /*
         * Current element = nums[n-1]
         *
         * We can only TAKE it
         * if it is <= target
         */
        if (nums[n - 1] <= target) {

            /*
             * TAKE current element
             *
             * Include nums[n-1]
             * Reduce target
             * Move to remaining elements
             */
            int take =
                    solve(nums,
                          target - nums[n - 1],
                          n - 1);

            /*
             * SKIP current element
             *
             * Do not include nums[n-1]
             * Target remains same
             */
            int skip =
                    solve(nums,
                          target,
                          n - 1);

            /*
             * Total ways =
             * ways from take
             * +
             * ways from skip
             */
            return take + skip;
        }

        /*
         * If current element is bigger than target,
         * we cannot take it
         *
         * Only option:
         * SKIP current element
         */
        else {

            return solve(nums,
                         target,
                         n - 1);
        }
    }

    public static void main(String[] args) {

        int[] nums = {2, 3, 5, 6, 8, 10};

        int target = 10;

        int n = nums.length;

        /*
         * Function call:
         *
         * Count subsets using all n elements
         * whose sum = target
         */
        int count = solve(nums, target, n);

        System.out.println("Total subsets = " + count);
    }
}