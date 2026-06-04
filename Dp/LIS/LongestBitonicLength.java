package Dp.LIS;

import java.util.Arrays;

public class LongestBitonicLength {

    public static void main(String[] args) {

        int[] nums = {1, 11, 2, 10, 4, 5, 2, 1};

        int n = nums.length;

        /*
         * LIS[i]
         *
         * Length of Longest Increasing Subsequence
         * ending at index i.
         *
         * Example:
         *
         * nums = [1,11,2,10]
         *
         * LIS =
         * [1,2,2,3]
         *
         * Meaning:
         *
         * LIS ending at 1  = 1
         * LIS ending at 11 = 2
         * LIS ending at 2  = 2
         * LIS ending at 10 = 3
         */
        int[] LIS = new int[n];

        /*
         * LDS[i]
         *
         * Length of Longest Decreasing Subsequence
         * starting from index i.
         *
         * Example:
         *
         * nums = [10,5,2,1]
         *
         * LDS =
         * [4,3,2,1]
         */
        int[] LDS = new int[n];

        /*
         * Every element itself forms:
         *
         * 1 Increasing Subsequence
         * 1 Decreasing Subsequence
         *
         * Therefore initialize with 1.
         */
        Arrays.fill(LIS, 1);
        Arrays.fill(LDS, 1);

        // --------------------------------------------------
        // STEP 1 : Calculate LIS from Left to Right
        // --------------------------------------------------

        /*
         * Standard LIS DP
         *
         * For every element,
         * check all previous elements.
         */
        for (int i = 1; i < n; i++) {

            for (int j = 0; j < i; j++) {

                /*
                 * Can nums[i] extend
                 * increasing subsequence ending at j ?
                 */
                if (nums[j] < nums[i]
                        && LIS[j] + 1 > LIS[i]) {

                    /*
                     * Extend the LIS.
                     */
                    LIS[i] = LIS[j] + 1;
                }
            }
        }

        // --------------------------------------------------
        // STEP 2 : Calculate LDS from Right to Left
        // --------------------------------------------------

        /*
         * Similar to LIS.
         *
         * Difference:
         *
         * We move from right to left because
         * we want decreasing subsequence
         * starting at index i.
         */
        for (int i = n - 2; i >= 0; i--) {

            /*
             * Check all elements on right side.
             */
            for (int j = n - 1; j > i; j--) {

                /*
                 * Can nums[i] be followed by nums[j]
                 * in a decreasing sequence?
                 *
                 * Example:
                 *
                 * 10 -> 5
                 * 10 -> 2
                 * 10 -> 1
                 */
                if (nums[j] < nums[i]
                        && LDS[j] + 1 > LDS[i]) {

                    /*
                     * Extend decreasing subsequence.
                     */
                    LDS[i] = LDS[j] + 1;
                }
            }
        }

        // --------------------------------------------------
        // STEP 3 : Treat Every Index as Peak
        // --------------------------------------------------

        int maxLen = 1;

        /*
         * Bitonic Sequence:
         *
         * Increasing Part
         * +
         * Decreasing Part
         *
         * Assume nums[i] is peak.
         */
        for (int i = 0; i < n; i++) {

            /*
             * Why -1 ?
             *
             * Peak element gets counted twice:
             *
             * Once in LIS
             * Once in LDS
             *
             * Therefore subtract 1.
             */
            maxLen = Math.max(
                    maxLen,
                    LIS[i] + LDS[i] - 1
            );
        }

        System.out.println(maxLen);
    }
}