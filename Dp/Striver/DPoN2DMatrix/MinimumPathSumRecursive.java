package Dp.Striver.DPoN2DMatrix;

public class MinimumPathSumRecursive {

    public static int solve(int[][] grid , int i , int j) {

        if(i == 0 && j == 0){
            return grid[i][j] ;
        }

        if(i < 0 || j < 0){
            return (int)1e9 ;
        }
       
        int up = grid[i][j] + solve(grid , i-1 , j);

        int left = grid[i][j] + solve(grid , i , j-1) ;

        return Math.min(up , left) ;
    }
    public static void main(String[] args) {

        int[][] grid = {

                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        int m = grid.length ;
        int n = grid[0].length ;

        int minCost = solve(grid , m-1 , n-1) ;
        System.out.println(minCost);
    }
}
