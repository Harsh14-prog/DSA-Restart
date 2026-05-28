package Dp.Striver.DPoN2DMatrix;

public class MaxPathSumVariableStartAndEndTabulation {

    public static void main(String[] args) {

        /*
            Matrix

            2  1  3
            6  5  4
            7  8  9

            Goal:

            Find maximum falling path sum.

            Allowed moves:

            DOWN
            LEFT DIAGONAL
            RIGHT DIAGONAL
        */
        int[][] grid = {

                {2, 1, 3},
                {6, 5, 4},
                {7, 8, 9}
        };

        // Total rows
        int m = grid.length;

        // Total columns
        int n = grid[0].length;

        /*
            DP TABLE

            dp[i][j] means:

            Maximum path sum
            to reach cell (i,j)
            starting from first row.
        */
        int[][] dp = new int[m][n];

        /*
            BASE CASE

            Path can start from ANY cell
            in first row.

            Therefore first row itself
            becomes base row.

            Example:

            To reach cell (0,2),
            maximum path sum is simply:

            grid[0][2]

            because journey starts there.
        */
        for(int j = 0; j < n; j++){

            dp[0][j] = grid[0][j];
        }

        /*
            Fill remaining rows
        */
        for(int i = 1; i < m; i++){

            for(int j = 0; j < n; j++){

                /*
                    MOVE UP

                    Came from upper cell:
                    (i-1, j)

                    Current cell value
                    +
                    maximum path till upper cell.
                */
                int up =
                        grid[i][j]
                        + dp[i - 1][j];

                /*
                    MOVE LEFT DIAGONAL

                    Came from:
                    (i-1, j-1)

                    Initially keep very small value.

                    Why?

                    Because this is MAXIMUM problem.

                    Invalid paths should never
                    be chosen by Math.max().
                */
                int leftDiagonal = Integer.MIN_VALUE;

                /*
                    Left diagonal exists only if
                    j > 0
                */
                if(j > 0){

                    leftDiagonal =
                            grid[i][j]
                            + dp[i - 1][j - 1];
                }

                /*
                    MOVE RIGHT DIAGONAL

                    Came from:
                    (i-1, j+1)
                */
                int rightDiagonal = Integer.MIN_VALUE;

                /*
                    Right diagonal exists only if
                    j < n-1
                */
                if(j < n - 1){

                    rightDiagonal =
                            grid[i][j]
                            + dp[i - 1][j + 1];
                }

                /*
                    Store maximum among:

                    UP
                    LEFT DIAGONAL
                    RIGHT DIAGONAL
                */
                dp[i][j] = Math.max(
                        up,
                        Math.max(leftDiagonal, rightDiagonal)
                );
            }
        }

        /*
            IMPORTANT UNDERSTANDING

            Why do we again take maximum
            after nested loops?

            Because:

            Problem says path can END
            at ANY cell in last row.

            Final answer is NOT fixed at:
            dp[m-1][n-1]

            Instead:

            Any last-row cell may contain
            maximum path answer.
            bez we are taking all possible end points from last row
            so each end point will give max path sum at that end cell
            we have to take max among them

            Example:

            Last row DP values may become:

            15  17  16

            Then final answer is:

            max(15,17,16)
            = 17
        */
        int maxi = Integer.MIN_VALUE;

        /*
            Traverse last row
            to find final maximum answer.
        */
        for(int j = 0; j < n; j++){

            maxi = Math.max(maxi, dp[m - 1][j]);
        }

        /*
            Print final maximum path sum
        */
        System.out.println(maxi);
    }
}