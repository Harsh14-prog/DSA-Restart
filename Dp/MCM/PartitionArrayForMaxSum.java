package Dp.MCM;

public class PartitionArrayForMaxSum {

    /**
     * Returns the maximum sum obtainable starting from index i.
     *
     * Idea:
     * At index i, try every possible partition of size 1 to k.
     * For each partition:
     *
     * 1. Calculate its contribution
     *    (maximum element in partition × partition length)
     *
     * 2. Recursively solve the remaining array.
     *
     * 3. Take the maximum among all choices.
     */
    public static int solve(int[] nums, int i, int k) {

        // Base Case:
        // If we have reached beyond the last index,
        // no elements are left to partition.
        if (i == nums.length) {
            return 0;
        }

        // Stores the best answer from index i.
        int maxSum = 0;

        // Maximum element of the current partition.
        int maxi = 0;

        // Length of current partition.
        int len = 0;

        /*
         * Generate all possible partitions
         * starting from index i.
         *
         * Since partition size can be at most k,
         * j can go only till i + k - 1.
         */
        for (int j = i; j < Math.min(nums.length, i + k); j++) {

            // Increase partition length.
            len++;

            // Update maximum element inside
            // current partition [i...j].
            maxi = Math.max(maxi, nums[j]);

            /*
             * Contribution of current partition.
             *
             * Example:
             * Partition = [1,15,7]
             *
             * max element = 15
             * length      = 3
             *
             * contribution = 15 × 3 = 45
             */
            int currentPartitionValue = maxi * len;

            /*
             * Solve remaining array.
             *
             * If current partition ends at j,
             * next partition starts from j+1.
             */
            int remainingAnswer = solve(nums, j + 1, k);

            /*
             * Total answer for this choice:
             *
             * current partition contribution
             * +
             * best answer from remaining array
             */
            int total = currentPartitionValue + remainingAnswer;

            // Keep the maximum answer.
            maxSum = Math.max(maxSum, total);
        }

        return maxSum;
    }

    public static void main(String[] args) {

        int[] nums = {1, 15, 7, 9, 2, 5, 10};

        int k = 3;

        int answer = solve(nums, 0, k);

        System.out.println("Maximum Sum = " + answer);
    }
}