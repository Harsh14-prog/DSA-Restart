package Dp.Striver.DPoN2DMatrix;

public class ChocolatePickUp3D_Tabulation {

    public static void main(String[] args) {

        int[][] grid = {
                {2, 3, 1, 2},
                {3, 4, 2, 2},
                {5, 6, 3, 5}
        };

        int n = grid.length;
        int m = grid[0].length;

        /*
         * dp[i][j1][j2]
         *
         * Maximum chocolates collectable
         * from row i till last row
         *
         * Robot1 at column j1
         * Robot2 at column j2
         */
        int[][][] dp = new int[n][m][m];

        /*
         * Base Case
         *
         * Fill the last row.
         */
        for (int j1 = 0; j1 < m; j1++) {

            for (int j2 = 0; j2 < m; j2++) {

                /*
                 * Both robots on same cell.
                 */
                if (j1 == j2) {
                    dp[n - 1][j1][j2] =
                            grid[n - 1][j1];
                }

                /*
                 * Robots on different cells.
                 */
                else {
                    dp[n - 1][j1][j2] =
                            grid[n - 1][j1]
                                    + grid[n - 1][j2];
                }
            }
        }

        /*
         * Start from second last row
         * and move upwards.
         */
        for (int i = n - 2; i >= 0; i--) {

            for (int j1 = 0; j1 < m; j1++) {

                for (int j2 = 0; j2 < m; j2++) {

                    int maxi = Integer.MIN_VALUE;

                    /*
                     * Explore all 9 combinations.
                     */
                    for (int dj1 = -1; dj1 <= 1; dj1++) {

                        for (int dj2 = -1; dj2 <= 1; dj2++) {

                            int chocolates;

                            /*
                             * Current row chocolates.
                             */
                            if (j1 == j2) {
                                chocolates = grid[i][j1];
                            } else {
                                chocolates =
                                        grid[i][j1]
                                                + grid[i][j2];
                            }

                            int nextJ1 = j1 + dj1;
                            int nextJ2 = j2 + dj2;
                            // we have already done these movement
                            // downward we are checking either is this movement
                            // valid or not

                            /*
                             * Valid next position.
                             */
                            if (nextJ1 >= 0 && nextJ1 < m
                                    &&
                                    nextJ2 >= 0 && nextJ2 < m) {

                                chocolates +=
                                        dp[i + 1][nextJ1][nextJ2];
                            }

                            /*
                             * Invalid move.
                             */
                            else {
                                chocolates += -(int) 1e9;
                            }

                            maxi = Math.max(maxi, chocolates);
                        }
                    }

                    dp[i][j1][j2] = maxi;
                }
            }
        }

        /*
         * Initial positions:
         *
         * Robot1 -> (0,0)
         * Robot2 -> (0,m-1)
         */
        System.out.println(
                "Maximum Chocolates = "
                        + dp[0][0][m - 1]
        );
    }
}