package Dp.Striver.DPoN2DMatrix;

public class MinimumPathSumTriangularGrid {

    public static int solve(int[][] triangle, int i, int j, int n){

        /*
            BASE CASE
            Reached last row.
            No more movement possible.
            So return current cell value.
            included current cell in path as our destination is last row cell
        */
        if(i == n - 1){

            return triangle[i][j];
        }

        /*
            MOVE DOWN
            Stay in same column.
            Current cell value
            +
            minimum path from lower cell.
        */
        int down =
                triangle[i][j]
                + solve(triangle, i + 1, j, n);

        /*
            MOVE DIAGONAL
            Move to next column.
            Current cell value
            +
            minimum path from diagonal cell.
        */
        int diagonal =
                triangle[i][j]
                + solve(triangle, i + 1, j + 1, n);

        /*
            Return minimum path sum
            among both choices.
        */
        return Math.min(down, diagonal);
    }

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

        // Total rows in triangle
        int n = triangle.length;

        /*
            Start recursion
            from top cell (0,0)

            Recursion automatically explores
            all possible paths.
        */
        int minPath =
                solve(triangle, 0, 0, n);

        /*
            Print final minimum path sum
        */
        System.out.println(minPath);
    }
}