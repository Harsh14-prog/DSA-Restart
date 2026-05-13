package Dp.knapSack;

public class EqualSumPartitionTopDown {

    // dp[n][target] will store:
    // 1 → true (subset possible)
    // 0 → false (subset not possible)
    // -1 → not computed yet
    static int[][] dp;

    public static boolean solve(int[] nums, int target, int n) {

        /*
         * Base Case 1:
         * If target becomes 0 → we found a valid subset
         */
        if (target == 0) {
            return true;
        }

        /*
         * Base Case 2:
         * If no elements left and target is still not 0 → not possible
         */
        if (n == 0) {
            return false;
        }

        /*
         * Memoization check:
         * If already computed, return stored result (will avoid rec fun call again)
         */
        if (dp[n][target] != -1) {
            return dp[n][target] == 1;
        }

        /*
         * If current element can be included
         */
        if (nums[n - 1] <= target) {

            // Choice 1: Take the current element
            boolean take = solve(nums, target - nums[n - 1], n - 1);

            // Choice 2: Skip the current element
            boolean skip = solve(nums, target, n - 1);

            /*
             * Store result:
             * If either choice works → true
             */
            boolean result = take || skip;
            dp[n][target] = result ? 1 : 0;

            return result;
        } else {
            /*
             * Current element is too large → cannot take it
             * Only option: skip it
             */
            boolean result = solve(nums, target, n - 1);

            dp[n][target] = result ? 1 : 0;
            return result;
        }
    }

    public static void main(String[] args) {

        int[] nums = { 1, 5, 11, 5 };
        int n = nums.length;

        int totalSum = 0;

        /*
         * Step 1: Calculate total sum of array
         */
        for (int num : nums) {
            totalSum += num;
        }

        /*
         * Step 2: If total sum is odd → cannot divide into equal subsets
         */
        if (totalSum % 2 != 0) {
            System.out.println(false);
            return;
        }

        /*
         * Step 3: Target becomes half of total sum
         */
        int target = totalSum / 2;

        /*
         * Step 4: Initialize DP table
         */
        dp = new int[n + 1][target + 1];

        // Fill with -1 (not computed)
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = -1;
            }
        }

        /*
         * Step 5: Solve using recursion + memoization
         */
        boolean result = solve(nums, target, n);

        /*
         * Final result
         */
        System.out.println(result);
    }

}
