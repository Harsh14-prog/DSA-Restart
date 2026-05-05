package Dp;

public class subSetSumWithBottomUpApproch {
    public static void main(String[] args) {

        int[] nums = { 2, 3, 7, 8, 10 };
        int target = 11;
        int n = nums.length;

        // dp[i][j] = can we form sum j using first i elements
        boolean[][] dp = new boolean[n + 1][target + 1];

        // Base Case:
        // Sum = 0 is always possible (choose nothing)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;   // 0th row already initialized to false by java
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {

                // If current element can be included
                if (nums[i - 1] <= j) {

                    // take OR skip
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];

                } else {
                    // cannot take → skip
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Final answer
        System.out.println(dp[n][target]);
    }

}
