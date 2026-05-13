package Dp.knapSack;

public class subSetSumTopDownApproch {

    static int[][] dp;

    public static boolean solve(int[] nums, int target, int n) {

        // Base cases
        if (target == 0) {
            return true;
        }

        if (n == 0) {
            return false;
        }

        // Memoization check
        if (dp[n][target] != -1) {
            return dp[n][target] == 1;
        }

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
            boolean result = solve(nums, target, n - 1);
            dp[n][target] = result ? 1 : 0;
            return result;
        }
    }

    public static void main(String[] args) {

        int[] nums = { 2, 3, 7, 8, 10 };
        int target = 11;
        int n = nums.length;

        dp = new int[n + 1][target + 1];

        // Initialize dp with -1
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(solve(nums, target, n));
    }
}