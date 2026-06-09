package Dp.Square;

public class CountSquareSubMatricesWithAll_1s {

    public static int countSquares(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        /*
         * dp[i][j]
         *
         * Meaning:
         * Largest square size ending at cell (i,j).
         *
         * Example:
         * dp[i][j] = 3
         *
         * Means:
         * A 3x3 square ends at (i,j).
         *
         * It also implies:
         * - one 1x1 square
         * - one 2x2 square
         * - one 3x3 square
         *
         * Therefore this cell contributes 3 squares.
         */
        int[][] dp = new int[rows][cols];

        int totalSquares = 0;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                /*
                 * First row or first column.
                 *
                 * Why?
                 *
                 * No top, left or diagonal cells exist.
                 *
                 * So only a 1x1 square can be formed. copy as it is from matrix
                 * always
                 */
                if (i == 0 || j == 0) {

                    dp[i][j] = matrix[i][j];
                }

                /*
                 * Current cell contains 1.
                 *
                 * We try to extend a square from:
                 * Top
                 * Left
                 * Diagonal
                 */
                else if (matrix[i][j] == 1) {

                    int top = dp[i - 1][j];

                    int left = dp[i][j - 1];

                    int diagonal = dp[i - 1][j - 1];

                    /*
                     * Why minimum?
                     *
                     * Suppose:
                     *
                     * top      = 3
                     * left     = 3
                     * diagonal = 1
                     *
                     * Then a square larger than 2x2
                     * cannot exist because diagonal
                     * is the limiting factor.
                     *
                     * A square can only be as large as
                     * its weakest neighbour.
                     */
                    dp[i][j] =
                            1 +
                            Math.min(top,
                                    Math.min(left, diagonal));
                }

                /*
                 * Current cell contains 0.
                 *
                 * No square can "end" here.
                 */
                else {

                    dp[i][j] = 0;
                }

                /*
                 * Every dp value contributes directly
                 * to the answer.
                 *
                 * Example:
                 *
                 * dp[i][j] = 2
                 *
                 * Means:
                 * one 1x1 square
                 * one 2x2 square
                 *
                 * Total contribution = 2
                 */
                totalSquares += dp[i][j];
            }
        }

        return totalSquares;
    }

    public static void main(String[] args) {

        int[][] matrix = {
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        };

        int answer = countSquares(matrix);

        System.out.println("Total Square Submatrices = " + answer);
    }
}