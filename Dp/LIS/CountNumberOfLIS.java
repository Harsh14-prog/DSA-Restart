package Dp.LIS;

import java.util.Arrays;

public class CountNumberOfLIS {

    public static void main(String[] args) {

        int[] nums = {1,5,4,3,2,6,7,10,8,9,15};

        int n = nums.length;

        /*
         * dp[i]
         *
         * Length of LIS ending at i
         */
        int[] dp = new int[n];

        /*
         * count[i]
         *
         * Number of LIS ending at i
         */
        int[] count = new int[n];

        /*
         * Every element itself forms:
         *
         * Length = 1
         * Count  = 1
         */
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);

        int maxLen = 1;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[j] < nums[i]) {

                    /*
                     * Better LIS found.
                     */
                    if (dp[j] + 1 > dp[i]) {

                        dp[i] = dp[j] + 1;

                        /*
                         * Inherit count from j.
                         */
                        count[i] = count[j];
                    }

                    /*
                     * Another way to achieve
                     * same LIS length.
                     */
                    else if (dp[j] + 1 == dp[i]) {

                        count[i] += count[j];
                    }
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        /*
         * Sum counts of all indices
         * having maximum LIS length.
         */
        int answer = 0;

        for (int i = 0; i < n; i++) {

            if (dp[i] == maxLen) {

                answer += count[i];
            }
        }

        System.out.println(answer);
    }
}