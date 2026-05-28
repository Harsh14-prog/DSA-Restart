package Dp.Striver.DPoN2DMatrix;

public class MinimumPathSumTriangularGridTabulation {

    public static void main(String[] args) {

        /*
            Triangle structure

                    2
                  3   4
                6   5   7
              4   1   8   3

            Goal:

            Find minimum path sum
            from top to bottom.

            Allowed moves:

            DOWN
            DIAGONAL DOWN RIGHT
        */
        int[][] triangle = {

                {2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}
        };

        // Total rows
        int n = triangle.length;

        /*
            DP TABLE

            dp[i][j] means:

            Minimum path sum
            starting from cell (i,j)
            till bottom.
        */
        int[][] dp = new int[n][n];

        /*
            BASE CASE

            Copy last row of triangle.

            Because from last row,
            no further movement possible.
        */
        for(int j = 0; j < n; j++){

            dp[n - 1][j] =
                    triangle[n - 1][j];
        }

        /*
            Start filling from
            second last row upward.
        */
        for(int i = n - 2; i >= 0; i--){

            /*
                Valid columns for row i are:
                0 to i
            */
            for(int j = i; j >= 0; j--){

                /*
                    MOVE DOWN

                    Stay in same column.
                */
                int down =
                        triangle[i][j]
                        + dp[i + 1][j];

                /*
                    MOVE DIAGONAL

                    Move to next column.
                */
                int diagonal =
                        triangle[i][j]
                        + dp[i + 1][j + 1];

                /*
                    Store minimum path sum
                */
                dp[i][j] =
                        Math.min(down, diagonal);
            }
        }

        /*
            Top cell contains final answer
        */
        System.out.println(dp[0][0]);
    }
}