package Dp.Striver.DPoN2DMatrix;

public class MinimumPathSumTabulation {

    public static void main(String[] args) {

        /*
            Grid values

            1  3  1
            1  5  1
            4  2  1

            Goal:

            Find minimum path sum
            from top-left to bottom-right.

            Allowed moves:
            RIGHT
            DOWN
        */
        int[][] grid = {

                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        // Total rows
        int m = grid.length;

        // Total columns
        int n = grid[0].length;

        /*
            DP TABLE

            dp[i][j] means:

            Minimum path sum
            to reach cell (i,j)
        */
        int[][] dp = new int[m][n];

        /*
            Traverse entire grid

            We solve smaller cells first,
            then build bigger answers.
        */
        for(int i = 0; i < m; i++){

            for(int j = 0; j < n; j++){

                /*
                    BASE CASE

                    Starting cell (0,0)

                    Minimum cost to reach starting cell
                    is its own value because
                    journey starts here.
                */
                if(i == 0 && j == 0){

                    dp[i][j] = grid[i][j];
                }

                else{

                    /*
                        Store path cost
                        coming from upper cell
                    */
                    int up = Integer.MAX_VALUE;

                    /*
                        Store path cost
                        coming from left cell
                    */
                    int left = Integer.MAX_VALUE;

                    /*
                        WHY Integer.MAX_VALUE ?

                        We are solving a MINIMUM problem.

                        Invalid paths should NEVER be chosen.

                        So initially we keep very large value
                        (acts like infinity).

                        Example:

                        Math.min(validPath, Integer.MAX_VALUE)

                        will always choose valid path.
                    */

                    /*
                        UP MOVE

                        If upper cell exists,
                        then current path cost becomes:

                        current cell value
                        +
                        minimum cost to reach upper cell
                    */
                    if(i > 0) {

                        up =
                                grid[i][j]
                                + dp[i - 1][j];
                    }

                    /*
                        LEFT MOVE

                        If left cell exists,
                        then current path cost becomes:

                        current cell value
                        +
                        minimum cost to reach left cell
                    */
                    if(j > 0){

                        left =
                                grid[i][j]
                                + dp[i][j - 1];
                    }

                    /*
                        Choose minimum path cost
                        among UP and LEFT paths
                    */
                    dp[i][j] = Math.min(up, left);
                }
            }
        }

        /*
            Bottom-right cell contains
            final minimum path sum
        */
        System.out.println(dp[m - 1][n - 1]);
    }
}