package Dp.Striver.DpOn_1D;

public class BFrogJump {

    public static int solve(int[] height, int n){

        /*
            BASE CASE

            If frog is at stair 0,
            no energy is needed.
        */
        if(n == 0) {
            return 0;
        }

        /*
            LEFT JUMP

            Frog comes from previous stair (n-1)

            Energy required:
            energy to reach (n-1)
            +
            jump cost
        */
        int left =
                solve(height, n - 1)
                + Math.abs(height[n] - height[n - 1]);

        /*
            Initially keep right jump very large.
            (acts like infinity)
        */
        int right = Integer.MAX_VALUE;

        /*
            RIGHT JUMP

            Possible only if n > 1

            Frog comes from stair (n-2)
        */
        if(n > 1){

            right =
                    solve(height, n - 2)
                    + Math.abs(height[n] - height[n - 2]);
        }

        /*
            Return minimum energy path
        */
        return Math.min(left, right);
    }

    public static void main(String[] args) {

        int[] height = {10, 20, 30, 10};

        int n = height.length;

        /*
            solve(n-1)
            means:

            minimum energy needed
            to reach last stair
        */
        int minEnergy = solve(height, n - 1);

        System.out.println(minEnergy);
    }
}