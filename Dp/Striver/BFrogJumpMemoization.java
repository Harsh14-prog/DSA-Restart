package Dp.Striver;

public class BFrogJumpMemoization {

    /*
     * DP ARRAY
     * dp[i] means:
     * Minimum energy required
     * to reach stair i
     * If dp[i] = -1
     * means answer not calculated yet.
     */
    static int[] dp;

    public static int solve(int[] height, int n) {

        /*
         * BASE CASE
         * If frog is standing on stair 0,
         * no energy is required because
         * frog is already there.
         */
        if (n == 0) {
            return 0;
        }

        /*
         * MEMOIZATION CHECK
         * If answer already calculated,
         * directly return stored answer.
         * This avoids repeated recursion.
         */
        if (dp[n] != -1) {
            return dp[n];
        }

        /*
         * LEFT JUMP
         * Frog jumps from previous stair (n-1)
         * Total energy:
         * energy required to reach (n-1)
         * +
         * jump cost from (n-1) to n
         */
        int left = solve(height, n - 1)
                + Math.abs(height[n] - height[n - 1]);

        /*
         * RIGHT JUMP
         * Initially assign very large value.
         * This acts like infinity.
         */
        int right = Integer.MAX_VALUE;

        /*
         * Right jump is possible only when n > 1
         * Frog jumps from stair (n-2)
         */
        if (n > 1) {

            /*
             * Total energy:
             * energy required to reach (n-2)
             * +
             * jump cost from (n-2) to n
             */
            right = solve(height, n - 2)
                    + Math.abs(height[n] - height[n - 2]);
        }

        /*
         * Store minimum energy in dp array
         * So next time if same state appears,
         * recursion will not calculate again.
         */
        dp[n] = Math.min(left, right);

        /*
         * Return minimum energy required
         * to reach stair n
         */
        return dp[n];
    }

    public static void main(String[] args) {

        /*
         * Height of each stair
         * Index:
         * 0 1 2 3
         * 10 20 30 10
         */
        int[] height = { 10, 20, 30, 10 };

        // Total number of stairs
        int n = height.length;

        /*
         * Create DP array
         * Size n+1 for safe indexing
         * each cell stores min energy req to reach that indexed stair
         */
        dp = new int[n + 1];

        /*
         * Initialize all values with -1
         * Meaning:
         * answer not calculated yet
         */
        for (int i = 0; i <= n; i++) {
            dp[i] = -1;
        }

        /*
         * solve(n-1)
         * means:
         * Minimum energy required
         * to reach last stair
         */
        int minEnergy = solve(height, n - 1);

        System.out.println(minEnergy);
    }
}