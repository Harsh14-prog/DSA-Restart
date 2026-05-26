package Dp.Striver.DPoN2DMatrix;

public class UniquePathsTabulation {
    public static void main(String[] args) {

        /*
            Grid size

            3 rows
            3 columns
        */
        int m = 3;
        int n = 3;

        /*
            DP TABLE

            dp[i][j] means:

            Total unique paths
            to reach cell (i,j)
        */
        int[][] dp = new int[m][n];

        /*
            Traverse entire grid
        */
        for(int i = 0; i < m; i++){

            for(int j = 0; j < n; j++){

                /*
                    BASE CASE

                    Starting cell (0,0)

                    There is exactly one way
                    to stand at starting position.
                */
                if(i == 0 && j == 0){

                    dp[i][j] = 1;
                }

                else{

                    /*
                        Store paths coming
                        from upper cell
                    */
                    int up = 0;

                    /*
                        Store paths coming
                        from left cell
                    */
                    int left = 0;

                    /*
                        UP MOVE

                        If upper cell exists,
                        take its paths.
                    */
                    if(i > 0){

                        up = dp[i - 1][j];
                    }

                    /*
                        LEFT MOVE

                        If left cell exists,
                        take its paths.
                    */
                    if(j > 0){

                        left = dp[i][j - 1];
                    }

                    /*
                        Total paths to current cell

                        =
                        paths from top
                        +
                        paths from left
                    */
                    dp[i][j] = up + left;
                }
            }
        }

        /*
            Bottom-right cell
            contains final answer.
        */
        System.out.println(dp[m - 1][n - 1]);
    }
}
