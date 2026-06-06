package Dp.Striver.DpOn_1D;

public class DMaxSumOfNonAdjEleSpaceOptimization {

    public static void main(String[] args) {

        /*
            Array elements

            Index:
            0  1  2  3
            2  1  4  9
        */
        int[] nums = {2, 1, 4, 9};

        // Total elements
        int n = nums.length;

        /*
            prev1 = dp[i-1]
            prev2 = dp[i-2]

            Initially:

            dp[0] = nums[0]
        */
        int prev1 = nums[0];

        /*
            No element before index 0,
            so initialize with 0.
        */
        int prev2 = 0;

        /*
            Start from index 1
        */
        for(int i = 1; i < n; i++){

            /*
                PICK current element

                If picked,
                adjacent element cannot be picked.
            */
            int pick = nums[i];

            /*
                Add dp[i-2]
                only when valid.
            */
            if(i > 1){
                pick += prev2;
            }

            /*
                NOT PICK current element

                Take previous answer.
            */
            int notPick = prev1;

            /*
                Current maximum answer
            */
            int curr = Math.max(pick, notPick);

            /*
                SHIFT VARIABLES

                prev2 becomes old prev1
                prev1 becomes current answer
            */
            prev2 = prev1;
            prev1 = curr;
        }

        /*
            prev1 stores final answer
        */
        System.out.println(prev1);
    }
}