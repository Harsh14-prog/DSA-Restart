package Dp.LIS;

public class LongestIncreasingSubseqOptimizedUsing_1D_Arr {

    public static void main(String[] args) {

        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        int n = nums.length;

        // dp[i] = Length of Longest Increasing Subsequence
        // ending at index i
        int[] dp = new int[n];

        // Stores the answer (maximum LIS found so far)
        int overallMax = 0;

        // Process every element as the ending element
        // of a possible increasing subsequence
        for (int i = 0; i < n; i++) {

            // Every element itself forms an LIS of length 1
            dp[i] = 1;

            // Check all previous elements
            for (int j = 0; j < i; j++) {

                // We can extend the subsequence ending at j
                // only if current element is greater
                if (nums[j] < nums[i]) {

                    /*
                     * If we append nums[i] to the LIS ending at j,
                     * the new length becomes:
                     *
                     * dp[j] + 1
                     *
                     * We take the maximum among all valid previous
                     * elements because we want the longest subsequence.
                     */
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            // Update global answer
            overallMax = Math.max(overallMax, dp[i]);
        }

        System.out.println("Length of LIS = " + overallMax);
    }
}