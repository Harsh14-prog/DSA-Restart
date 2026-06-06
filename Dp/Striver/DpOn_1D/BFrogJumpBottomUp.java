package Dp.Striver.DpOn_1D;

public class BFrogJumpBottomUp {
        public static void main(String[] args) {

        /*
            Height of each stair

            Index:
            0   1   2   3
            10  20  30  10
        */
        int[] height = {10, 20, 30, 10};

        // Total stairs
        int n = height.length;

        /*
            DP ARRAY

            dp[i] means:
            Minimum energy required
            to reach stair i
        */
        int[] dp = new int[n];

        /*
            BASE CASE

            At stair 0,
            no energy required.
        */
        dp[0] = 0;

        /*
            Fill DP array from left to right
        */
        for(int i = 1; i < n; i++){

            /*
                LEFT JUMP

                Jump from previous stair
            */
            int left =
                    dp[i - 1]
                    + Math.abs(height[i] - height[i - 1]);

            /*
                RIGHT JUMP

                Initially very large value
            */
            int right = Integer.MAX_VALUE;

            /*
                Right jump possible only if i > 1
            */
            if(i > 1){

                right =
                        dp[i - 2]
                        + Math.abs(height[i] - height[i - 2]);
            }

            /*
                Store minimum energy
            */
            dp[i] = Math.min(left, right);
        }

        /*
            Last index contains answer
        */
        System.out.println(dp[n - 1]);
    }
}
