package Dp.LIS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LargestDivisibleSubset {

    public static void main(String[] args) {

        int[] nums = {1, 16, 7, 8, 4};

        int n = nums.length;

        /*
         * Sort the array first.
         *
         * Why?
         * ----
         * Divisibility works nicely in sorted order.
         *
         * Example:
         * [1, 4, 8, 16]
         *
         * If 8 is divisible by 4
         * and 4 is divisible by 1,
         * then we can build a chain.
         */
        Arrays.sort(nums);

        /*
         * dp[i] =
         * Length of Largest Divisible Subset
         * ending at index i.
         */
        int[] dp = new int[n];

        /*
         * Initially every element itself forms
         * a subset of length 1.
         */
        Arrays.fill(dp, 1);

        /*
         * parent[i] stores the previous index
         * used to build the best subset ending at i.
         *
         * This is required to reconstruct
         * the actual subset later.
         */
        int[] parent = new int[n];

        /*
         * Initially every element points to itself.
         *
         * Meaning:
         * "I am currently the start of my own subset."
         */
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        /*
         * Track overall answer.
         */
        int maxLength = 1;
        int lastIndex = 0;

        /*
         * LIS Pattern
         *
         * Make every element the ending element
         * of a divisible subset.
         */
        for (int i = 1; i < n; i++) {

            /*
             * Check all previous elements.
             */
            for (int j = 0; j < i; j++) {

                /*
                 * Can current number extend
                 * subset ending at j?
                 *
                 * If yes:
                 * nums[i] must be divisible by nums[j]
                 */
                if (nums[i] % nums[j] == 0
                        && dp[j] + 1 > dp[i]) {

                    /*
                     * Extend the subset.
                     */
                    dp[i] = dp[j] + 1;

                    /*
                     * Remember who contributed
                     * to this best answer.
                     */
                    parent[i] = j;
                }
            }

            /*
             * Update global maximum.
             */
            if (dp[i] > maxLength) {

                maxLength = dp[i];
                lastIndex = i;
            }
        }

        /*
         * Reconstruct answer using parent array.
         */
        List<Integer> ans = new ArrayList<>();

        /*
         * Follow parent chain backwards.
         *
         * Example:
         *
         * 16 -> 8 -> 4 -> 1
         */
        while (lastIndex != parent[lastIndex]) {

            ans.add(nums[lastIndex]);

            lastIndex = parent[lastIndex];
        }

        /*
         * Add starting element.
         */
        ans.add(nums[lastIndex]);

        /*
         * Currently answer is reversed.
         *
         * Example:
         * [16, 8, 4, 1]
         *
         * Reverse it.
         */
        Collections.reverse(ans);

        System.out.println("Length = " + maxLength);
        System.out.println("Largest Divisible Subset = " + ans);
    }
}