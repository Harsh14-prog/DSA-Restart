package Dp.Striver.DPoN2DMatrix;

public class UniquePaths {

    public static int solve(int i, int j){

        /*
            BASE CASE

            Reached starting cell.
        */
        if(i == 0 && j == 0){
            return 1;
        }

        /*
            OUT OF BOUND

            Invalid cell.
        */
        if(i < 0 || j < 0){
            return 0;
        }

        /*
            Move UP

            Meaning:
            came from top cell.
        */
        int up = solve(i - 1, j);

        /*
            Move LEFT

            Meaning:
            came from left cell.
        */
        int left = solve(i, j - 1);

        /*
            Total paths
        */
        return up + left;
    }

    public static void main(String[] args) {

        int m = 3;
        int n = 3;

        /*
            Start from destination cell
        */
        System.out.println(solve(m - 1, n - 1));
    }
}