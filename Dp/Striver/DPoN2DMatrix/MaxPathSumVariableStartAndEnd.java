package Dp.Striver.DPoN2DMatrix;

public class MaxPathSumVariableStartAndEnd {

    public static int solve(int[][] grid,
                            int i,
                            int j,
                            int m,
                            int n){

        /*
            INVALID COLUMN CASE

            Diagonal movement may go
            outside matrix boundaries.

            Example:

            j < 0
            or
            j >= n

            means current cell is invalid.

            Since this is a MAXIMUM problem,
            invalid paths should NEVER be chosen.

            Therefore we return
            very small value.

            Why?

            Suppose:

            valid path = 20
            invalid path = Integer.MIN_VALUE

            Then:

            Math.max(20, Integer.MIN_VALUE)

            will always choose valid path.

            So invalid path automatically gets ignored.
        */
        if(j < 0 || j >= n){

            return Integer.MIN_VALUE;
        }

        /*
            BASE CASE

            Reached first row.

            No more upward movement possible.

            So return current cell value.
        */
        if(i == 0){

            return grid[i][j];
        }

        /*
            MOVE UP

            Came from upper cell:
            (i-1, j)

            Current cell value
            +
            maximum path from upper cell.
        */
        int up =
                grid[i][j]
                + solve(grid, i - 1, j, m, n);

        /*
            MOVE LEFT DIAGONAL

            Came from upper-left cell:
            (i-1, j-1)

            Current cell value
            +
            maximum path from left diagonal cell.
        */
        int leftDiagonal =
                grid[i][j]
                + solve(grid, i - 1, j - 1, m, n);

        /*
            MOVE RIGHT DIAGONAL

            Came from upper-right cell:
            (i-1, j+1)

            Current cell value
            +
            maximum path from right diagonal cell.
        */
        int rightDiagonal =
                grid[i][j]
                + solve(grid, i - 1, j + 1, m, n);

        /*
            Return maximum among all paths
        */
        return Math.max(
                up,
                Math.max(leftDiagonal, rightDiagonal)
        );
    }

    public static void main(String[] args) {

        /*
            Matrix

            2  1  3
            6  5  4
            7  8  9

            Goal:

            Find maximum falling path sum.

            Allowed moves:

            UP
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
            Store final maximum answer
        */
        int maxPathSum = Integer.MIN_VALUE;

        /*
            IMPORTANT:

            Any last-row cell
            can be ending point.

            So try all last-row columns.
        */
        for(int j = 0; j < n; j++){

            /*
                Find maximum path sum
                ending at current column
            */
            int maxPath =
                    solve(grid, m - 1, j, m, n);

            /*
                Update final answer
            */
            maxPathSum =
                    Math.max(maxPathSum, maxPath);
        }

        /*
            Print final maximum path sum
        */
        System.out.println(maxPathSum);
    }
}