package Dp.Striver.DPoN2DMatrix;

public class MazeObstaclesRecursion {

    public static int solve(int i, int j, int[][] maze){

        /*
            OUT OF BOUND CASE

            If row or column becomes negative,
            cell is invalid.

            So no path exists.
        */
        if(i < 0 || j < 0){
            return 0;
        }

        /*
            OBSTACLE CASE

            If current cell contains obstacle,
            ninja cannot stand here.

            Therefore no path exists.
        */
        if(maze[i][j] == -1){
            return 0;
        }

        /*
            BASE CASE

            Reached starting cell.

            One valid path found.
        */
        if(i == 0 && j == 0){
            return 1;
        }

        /*
            MOVE UP

            Meaning:
            current cell was reached
            from upper cell.
        */
        int up =
                solve(i - 1, j, maze);

        /*
            MOVE LEFT

            Meaning:
            current cell was reached
            from left cell.
        */
        int left =
                solve(i, j - 1, maze);

        /*
            Total paths to current cell

            =
            paths from top
            +
            paths from left
        */
        return up + left;
    }

    public static void main(String[] args) {

        /*
            Maze Grid

            0  -> free cell
            -1 -> obstacle
        */
        int[][] maze = {

                {0, 0, 0},
                {0, -1, 0},
                {0, 0, 0}
        };

        // Total rows
        int m = maze.length;

        // Total columns
        int n = maze[0].length;

        /*
            Start recursion
            from destination cell
        */
        int totalPaths =
                solve(m - 1, n - 1, maze);

        /*
            Print final answer
        */
        System.out.println(totalPaths);
    }
}
