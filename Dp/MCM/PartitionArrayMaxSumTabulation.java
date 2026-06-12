package Dp.MCM;

public class PartitionArrayMaxSumTabulation {

    public static void main(String[] args) {

        int[] nums = {1, 15, 7, 9, 2, 5, 10};
        int k = 3;

        int n = nums.length;

        /*
         * dp[i] = Maximum sum obtainable
         * starting from index i.
         *
         * Example:
         *
         * dp[0] -> answer for entire array
         * dp[1] -> answer starting from index 1
         * dp[2] -> answer starting from index 2
         * ...
         *
         * dp[n] represents:
         * "No elements left to process"
         * Therefore dp[n] = 0
         */
        int[] dp = new int[n + 1];

        // Base Case
        dp[n] = 0;

        /*
         * We fill from right to left.
         *
         * Why?
         *
         * Because:
         *
         * dp[i] depends on dp[j+1]
         *
         * which always lies on the right side.
         *
         * Therefore those states must already be computed.
         */
        for (int i = n - 1; i >= 0; i--) {

            /*
             * These variables help us build
             * partitions starting from index i.
             */
            int len = 0;      // current partition length
            int maxi = 0;     // maximum element in current partition
            int maxSum = 0;   // best answer from index i

            /*
             * Generate all possible partitions
             * starting from index i.
             *
             * Partition size can be at most k.
             */
            for (int j = i ; j < Math.min(n, i + k); j++) {

                // Increase current partition length.
                len++;

                // Update maximum element in partition [i...j].
                maxi = Math.max(maxi, nums[j]);

                /*
                 * Contribution of current partition.
                 *
                 * Example:
                 *
                 * Partition = [1,15,7]
                 * max element = 15
                 * length = 3
                 *
                 * Contribution = 15 × 3 = 45
                 */
                int currentPartitionValue = len * maxi;

                /*
                 * Remaining answer.
                 *
                 * If current partition ends at j,
                 * next partition starts from j+1.
                 *
                 * dp[j+1] already contains the best
                 * answer for the remaining suffix.
                 */
                int remainingAnswer = dp[j + 1];

                /*
                 * Total answer for choosing
                 * partition [i...j].
                 */
                int sum = currentPartitionValue + remainingAnswer;

                // Keep the best among all partition choices.
                maxSum = Math.max(maxSum, sum);
            }

            /*
             * Store the best answer obtainable
             * starting from index i.
             */
            dp[i] = maxSum;
        }

        /*
         * dp[0] contains answer for the
         * entire array.
         */
        System.out.println("Maximum Sum = " + dp[0]);
    }
}