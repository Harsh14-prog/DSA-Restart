package Dp.knapSack;

public class MinSubsetSumDifferenceBottomUp {

    public static void main(String[] args) {

        /*
         * Input array
         */
        int[] nums = {1, 6, 11, 5};

        int n = nums.length;

        /*
         * STEP 1:
         * Find total sum of all elements
         */
        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        /*
         * dp[i][j]
         *
         * Meaning:
         *
         * Using first i elements,
         * can we form subset sum = j ?
         */
        boolean[][] dp =
                new boolean[n + 1][totalSum + 1];

        /*
         * BASE CASE
         *
         * Sum 0 is always possible
         * using empty subset
         *
         * Therefore:
         * dp[i][0] = true
         */
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        /*
         * Fill DP table
         */
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= totalSum; j++) {

                /*
                 * Current element
                 */
                int current = nums[i - 1];

                /*
                 * If current element can be included
                 */
                if (current <= j) {

                    /*
                     * TAKE:
                     * Include current element
                     *
                     * Remaining sum:
                     * j - current
                     */
                    boolean take =
                            dp[i - 1][j - current];

                    /*
                     * SKIP:
                     * Do not include current element
                     */
                    boolean skip =
                            dp[i - 1][j];

                    /*
                     * Sum j is possible if:
                     * take OR skip is true
                     */
                    dp[i][j] = take || skip;
                }

                /*
                 * Current element bigger than target sum
                 *
                 * Cannot include it
                 */
                else {

                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        /*
         * STEP 3:
         * Find minimum subset sum difference
         */
        int minDiff = Integer.MAX_VALUE;

        /*
         * We only check till totalSum/2
         *
         * Because after half,
         * differences start repeating
         *
         * Example:
         * s1 = 5 , s2 = 10
         * diff = 5
         *
         * same as:
         * s1 = 10 , s2 = 5
         * diff = 5
         */
        for (int s1 = 0;
             s1 <= totalSum / 2;
             s1++) {

            /*
             * Check only those subset sums
             * which are possible
             *
             * We use dp[n][s1]
             * because:
             *
             * dp[n][s1] means:
             * Using ALL elements,
             * can we form subset sum s1 ?
             */
            if (dp[n][s1]) {

                /*
                 * Remaining subset sum
                 */
                // int s2 = totalSum - s1;

                /*
                 * Difference between subsets
                 *
                 * diff = |s1 - s2|
                 *
                 * Since:
                 * s2 = totalSum - s1
                 *
                 * diff becomes:
                 *
                 * totalSum - 2*s1
                 */
                int diff =
                        totalSum - (2 * s1);

                /*
                 * Update minimum difference
                 */
                minDiff =
                        Math.min(minDiff, diff);
            }
        }

        /*
         * Final answer
         */
        System.out.println(minDiff);
    }
}