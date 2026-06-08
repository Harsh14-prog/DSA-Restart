package Dp.MCM;

public class MatrixChainMultiplicationTabulation {

    public static void main(String[] args) {

        /*
         * nums represents matrix dimensions.
         *
         * A1 = 10 x 20
         * A2 = 20 x 30
         * A3 = 30 x 40
         * A4 = 40 x 50
         */
        int[] nums = {10, 20, 30, 40, 50};

        int n = nums.length;

        /*
         * dp[i][j]
         *
         * Meaning:
         * Minimum number of multiplications required
         * to multiply matrices from Ai to Aj.
         *
         * Example:
         * dp[1][3] = minimum cost to multiply
         * A1 A2 A3
         */
        int[][] dp = new int[n][n];

        /*
         * Base Case:
         *
         * A single matrix does not need multiplication.
         *
         * Example:
         * dp[2][2] => only A2 exists
         * cost = 0
         */
        for (int i = 1; i < n; i++) {
            dp[i][i] = 0;
        }

        /*
         * We fill the table from bottom to top.
         *
         * Why?
         *
         * dp[i][j] depends on dp[k+1][j]
         *
         * Example:
         * dp[1][4] needs dp[2][4], dp[3][4]
         *
         * These states are in lower rows,
         * so lower rows must be computed first.
         */
        for (int i = n - 1; i >= 1; i--) {

            /*
             * Move left to right in the current row.
             *
             * Why?
             *
             * dp[i][j] depends on dp[i][k]
             *
             * Example:
             * dp[1][4] needs dp[1][2], dp[1][3]
             *
             * These values are on the left side
             * of the same row, so they must
             * already be computed.
             */
            for (int j = i + 1; j < n; j++) {

                // Store minimum cost among all partitions.
                int mini = Integer.MAX_VALUE;

                /*
                 * Try every possible partition.
                 *
                 * Example:
                 *
                 * A1 A2 A3 A4
                 *
                 * k = 1
                 * (A1) | (A2 A3 A4)
                 *
                 * k = 2
                 * (A1 A2) | (A3 A4)
                 *
                 * k = 3
                 * (A1 A2 A3) | (A4)
                 */
                for (int k = i; k < j; k++) {

                    /*
                     * Cost =
                     *
                     * Left Part Cost
                     * + Right Part Cost
                     * + Cost of multiplying
                     *   resulting matrices
                     *
                     * Formula:
                     *
                     * nums[i-1] * nums[k] * nums[j]
                     *
                     * Because:
                     *
                     * Left Result Matrix:
                     * nums[i-1] x nums[k]
                     *
                     * Right Result Matrix:
                     * nums[k] x nums[j]
                     *
                     * Multiplication cost:
                     *
                     * rows * common_dimension * columns
                     */
                    int cost =
                            nums[i - 1] * nums[k] * nums[j]
                            + dp[i][k]
                            + dp[k + 1][j];

                    // Keep minimum among all partitions.
                    mini = Math.min(mini, cost);
                }

                /*
                 * Best partition cost for
                 * matrices Ai to Aj.
                 */
                dp[i][j] = mini;
            }
        }

        /*
         * Final answer:
         *
         * Minimum cost to multiply
         * all matrices A1 to A(n-1)
         */
        System.out.println(dp[1][n - 1]);
    }
}