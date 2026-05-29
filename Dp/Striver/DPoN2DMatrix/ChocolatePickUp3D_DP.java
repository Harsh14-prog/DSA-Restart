package Dp.Striver.DPoN2DMatrix;

public class ChocolatePickUp3D_DP {

    /*
     * solve(i, j1, j2)
     *
     * i  -> current row
     * j1 -> Robot 1's column position
     * j2 -> Robot 2's column position
     *
     * Returns:
     * Maximum chocolates that can be collected
     * from row i till the last row.
     */
    public static int solve(int[][] grid, int i, int j1, int j2, int n, int m) {

        /*
         * If any robot goes outside the grid,
         * this path becomes invalid.
         *
         * We return a very small value so that
         * this path is never selected while taking maximum.
         *
         * Do NOT use Integer.MIN_VALUE because later
         * we add chocolates to this value and that can
         * cause integer overflow.
         */
        if (j1 < 0 || j1 >= m || j2 < 0 || j2 >= m) {
            return -(int) 1e9;
        }

        /*
         * Base Case:
         * Both robots have reached the last row.
         *
         * No further movement is possible.
         */
        if (i == n - 1) {

            /*
             * If both robots are standing on the same cell,
             * count that chocolate only once.
             */
            if (j1 == j2) {
                return grid[i][j1];
            }

            /*
             * Otherwise both robots collect chocolates
             * from their respective cells.
             */
            return grid[i][j1] + grid[i][j2];
        }

        /*
         * Stores the best answer among all possible
         * combinations of moves.
         */
        int maxi = Integer.MIN_VALUE;

        /*
         * Robot 1 has 3 possible moves:
         * -1 -> down-left
         *  0 -> down
         * +1 -> down-right
         */
        for (int dj1 = -1; dj1 <= 1; dj1++) {

            /*
             * Robot 2 also has 3 possible moves:
             * -1 -> down-left
             *  0 -> down
             * +1 -> down-right
             */
            for (int dj2 = -1; dj2 <= 1; dj2++) {

                int chocolates;

                /*
                 * If both robots are on the same cell,
                 * collect chocolate only once.
                 */
                if (j1 == j2) {
                    // solve fun does movement of both alice and bob at same time
                    chocolates =
                            grid[i][j1]
                            + solve(grid,    
                                    i + 1,
                                    j1 + dj1,
                                    j2 + dj2,
                                    n,
                                    m);
                }

                /*
                 * If robots are on different cells,
                 * both chocolates can be collected.
                 */
                else {

                    chocolates =
                            grid[i][j1]
                            + grid[i][j2]
                            + solve(grid,
                                    i + 1,
                                    j1 + dj1,
                                    j2 + dj2,
                                    n,
                                    m);
                }

                /*
                 * Keep track of the maximum chocolates
                 * obtainable from all 9 move combinations.
                 */
                maxi = Math.max(maxi, chocolates);
            }
        }

        return maxi;
    }

    public static void main(String[] args) {

        int[][] grid = {
                {2, 3, 1, 2},
                {3, 4, 2, 2},
                {5, 6, 3, 5}
        };

        int n = grid.length;
        int m = grid[0].length;

        /*
         * Robot 1 starts from top-left corner.
         */
        int robot1StartCol = 0;

        /*
         * Robot 2 starts from top-right corner.
         */
        int robot2StartCol = m - 1;

        int maxChocolate =
                solve(grid,
                        0,
                        robot1StartCol,
                        robot2StartCol,
                        n,
                        m);

        System.out.println("Maximum Chocolates = " + maxChocolate);
    }
}