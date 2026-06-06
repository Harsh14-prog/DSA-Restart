package Dp.Striver.DpOn_1D;

public class BFrogJumpSpaceOptimised {
    public static void main(String[] args) {

        /*
         * Height of each stair
         * 
         * Index:
         * 0 1 2 3
         * 10 20 30 10
         */
        int[] height = { 10, 20, 30, 10 };

        // Total number of stairs
        int n = height.length;

        /*
         * prev1 -> dp[i-1]
         * prev2 -> dp[i-2]
         * 
         * Initially:
         * 
         * dp[0] = 0
         * 
         * because frog is already at stair 0,
         * so no energy is needed.
         */
        int prev1 = 0;
        int prev2 = 0;

        /*
         * Start from stair 1
         * because stair 0 is already solved.
         */
        for (int i = 1; i < n; i++) {

            /*
             * LEFT JUMP
             * 
             * Frog jumps from previous stair (i-1)
             * 
             * Energy:
             * previous energy
             * +
             * jump cost
             */
            int left = prev1
                    + Math.abs(height[i] - height[i - 1]);

            /*
             * RIGHT JUMP 
             * Initially keep very large value.
             */
            int right = Integer.MAX_VALUE;

            /*
             * Right jump possible only when i > 1
             * Frog jumps from stair (i-2)
             */
            if (i > 1) {

                right = prev2
                        + Math.abs(height[i] - height[i - 2]);
            }

            /*
             * Current minimum energy
             * required to reach stair i
             */
            int curr = Math.min(left, right);

            /*
             * Shift values for next iteration
             * 
             * prev2 becomes old prev1
             * prev1 becomes current answer
             */
            prev2 = prev1;
            prev1 = curr;
        }

        /*
         * prev1 contains final answer
         */
        System.out.println(prev1);
    }

}
