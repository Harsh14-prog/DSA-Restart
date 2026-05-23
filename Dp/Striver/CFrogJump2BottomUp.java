package Dp.Striver;

public class CFrogJump2BottomUp {
    public static void main(String[] args) {

        /*
         * Height of stairs
         * 
         * Index:
         * 0 1 2 3
         * 10 20 30 10
         */
        int[] ht = { 10, 20, 30, 10 };

        // Total number of stairs
        int n = ht.length;

        // Maximum jump distance
        int k = 2;

        /*
         * DP ARRAY
         * 
         * dp[i] means:
         * minimum energy required
         * to reach stair i
         */
        int[] dp = new int[n];

        /*
         * BASE CASE
         * 
         * At stair 0,
         * no energy required.
         */
        dp[0] = 0;

        /*
         * Fill dp array from left to right
         */
        for (int i = 1; i < n; i++) {

            /*
             * Store minimum answer
             */
            int minEnergy = Integer.MAX_VALUE;

            /*
             * Try all jumps from 1 to k
             */
            for (int j = 1; j <= k; j++) {

                /*
                 * Check valid previous stair
                 */
                if (i - j >= 0) {

                    /*
                     * Energy required:
                     * 
                     * dp[i-j]
                     * +
                     * jump cost
                     */
                    int jump = dp[i - j]
                            + Math.abs(ht[i] - ht[i - j]);

                    /*
                     * Take minimum
                     */
                    minEnergy = Math.min(minEnergy, jump);
                }
            }

            /*
             * Store answer for current stair
             */
            dp[i] = minEnergy;
        }

        /*
         * Last index stores final answer
         */
        System.out.println(dp[n - 1]);
    }
}
