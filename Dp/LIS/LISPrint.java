package Dp.LIS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LISPrint {

    public static void main(String[] args) {

        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        int n = nums.length;

        /*
         * dp[i] =
         * Length of Longest Increasing Subsequence
         * ending at index i
         */
        int[] dp = new int[n];

        /*
         * parent[i] =
         * Previous index used to build the LIS ending at i
         *
         * This array helps us reconstruct the actual LIS.
         */
        int[] parent = new int[n];

        /*
         * Every element alone forms an LIS of length 1.
         */
        Arrays.fill(dp, 1);

        /*
         * Initially every element points to itself.
         *
         * Meaning:
         * "I am currently the start of my own subsequence."
         *
         * This also acts as the stopping condition while
         * reconstructing the LIS later.
         */
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int maxLength = 1;
        int lastIndex = 0;

        /*
         * Try making every element the ending element
         * of an increasing subsequence.
         */
        for (int i = 1; i < n; i++) {

            /*
             * Check all previous elements.
             */
            for (int j = 0; j < i; j++) {

                /*
                 * Can we append nums[i]
                 * after nums[j] ?
                 */
                if (nums[j] < nums[i]
                        && dp[j] + 1 > dp[i]) {

                    /*
                     * Better LIS found.
                     *
                     * Extend the LIS ending at j
                     * by including nums[i].
                     */
                    dp[i] = dp[j] + 1;

                    /*
                     * Remember who contributed
                     * to this best answer.
                     *
                     * Meaning:
                     * LIS ending at i came from j.
                     */
                    parent[i] = j;
                }
            }

            /*
             * Track overall maximum LIS length
             * and where it ends.
             */
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                lastIndex = i;
            }
        }

        /*
         * Reconstruct LIS using parent array.
         */
        List<Integer> lis = new ArrayList<>();

        /*
         * Example:
         *
         * parent:
         * 6 -> 5 -> 3 -> 2
         *
         * We move backwards through the chain.
         */
        while (lastIndex != parent[lastIndex]) {

            lis.add(nums[lastIndex]);

            lastIndex = parent[lastIndex];
        }

        /*
         * Add the first element of the LIS.
         *
         * Loop stops when an element points
         * to itself.
         */
        lis.add(nums[lastIndex]);

        /*
         * We collected sequence backwards.
         *
         * Example:
         * 101 -> 7 -> 5 -> 2
         *
         * Reverse it.
         */
        Collections.reverse(lis);

        System.out.println("Length = " + maxLength);
        System.out.println("LIS = " + lis);
    }
}