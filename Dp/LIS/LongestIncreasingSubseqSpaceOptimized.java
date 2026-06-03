package Dp.LIS;

public class LongestIncreasingSubseqSpaceOptimized {

    public static void main(String[] args) {

        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        int n = nums.length;

        // Represents dp[index + 1][...]
        int[] next = new int[n + 1];

        for (int index = n - 1; index >= 0; index--) {

            // Represents dp[index][...]
            int[] curr = new int[n + 1];

            // actual prev index in nums from index-1 to -1
            // use this index as it is when accessing in nums
            // only shift to prev + 1 while storing in dp

            for (int prev = index - 1; prev >= -1; prev--) {

                // Skip current element
                int notTake = next[prev + 1];

                int take = 0;

                // Take current element if valid
                if (prev == -1 || nums[index] > nums[prev]) {

                    /*
                     * Recursive call:
                     * solve(index + 1, index)
                     *
                     * Tabulation:
                     * dp[index + 1][index + 1]
                     *
                     * Since 'next' stores row (index + 1),
                     * use next[index + 1]
                     */
                    take = 1 + next[index + 1];
                }

                curr[prev + 1] = Math.max(take, notTake);
            }

            // Move current row to next row
            next = curr;
        }

        // dp[0][-1 + 1]
        System.out.println(next[0]);
    }
}
