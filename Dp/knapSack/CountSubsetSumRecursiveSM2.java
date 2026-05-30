package Dp.knapSack;

public class CountSubsetSumRecursiveSM2 {

    /*
     * solve(nums, n, target)
     *
     * Meaning:
     * Count the number of subsets from index 0 to n
     * whose sum is equal to target.
     */
    public static int solve(int[] nums, int n, int target) {

        /*
         * BASE CASE
         *
         * When n == 0, only the first element (nums[0])
         * is available for making the target.
         */
        if (n == 0) { // means we are at first ele , in m1 n = 0 means arr finished on -1

            /*
             * Special case:
             *
             * nums[0] = 0
             * target = 0
             *
             * We can form target in TWO ways:
             *
             * 1. Take nothing -> {}
             * 2. Take 0      -> {0}
             *
             * Therefore return 2.
             */
            if (target == 0 && nums[0] == 0) {
                return 2;
            }

            /*
             * If target is 0,
             * empty subset {} forms the target.
             *
             * Example:
             * nums = {5}
             * target = 0
             *
             * Valid subset = {}
             */
            if (target == 0) {
                return 1;
            }

            /*
             * If first element itself equals target,
             * then one valid subset exists.
             *
             * Example:
             * nums = {5}
             * target = 5
             *
             * Valid subset = {5}
             */
            if (nums[0] == target) {
                return 1;
            }

            /*
             * No valid subset can be formed.
             */
            return 0;
        }

        /*
         * If current element can participate in the subset,
         * we have two choices:
         *
         * 1. Take it
         * 2. Skip it
         */
        if (nums[n] <= target) {

            /*
             * TAKE
             *
             * Include current element.
             *
             * Since it is included,
             * reduce target by nums[n].
             */
            int take = solve(nums, n - 1, target - nums[n]);

            /*
             * SKIP
             *
             * Exclude current element.
             *
             * Target remains unchanged.
             */
            int skip = solve(nums, n - 1, target);

            /*
             * WHY ADD?
             *
             * take = number of valid subsets that include nums[n]
             *
             * skip = number of valid subsets that exclude nums[n]
             *
             * Total valid subsets =
             * subsets from take branch +
             * subsets from skip branch
             */
            return take + skip;
        }

        /*
         * Current element is greater than target.
         *
         * It cannot be included because subset sum
         * would exceed the required target.
         *
         * Therefore only skip option remains.
         */
        return solve(nums, n - 1, target);
    }

    public static void main(String[] args) {

        int[] nums = {2, 3, 5, 6, 8, 10};
        int target = 10;

        int n = nums.length ;

        /*
         * Start recursion from the last index.
           actual index sent , not sent "n" like method-1
         */
        int count = solve(nums, n - 1, target);

        System.out.println("Total subsets = " + count);
    }
}