package Dp.Striver;

import java.util.Arrays;

public class DMaxSumOfNonAdjEleMemoization {

    /*
        DP ARRAY

        dp[i] means:

        Maximum sum possible
        from index 0 to i
        without taking adjacent elements.

        If dp[i] = -1
        means answer not calculated yet.
    */

    static int[] dp;

    public static int solve(int[] nums, int n) {

        /*
            BASE CASE 1

            If index becomes 0,
            only one element is available.
            we can't reach here from index 1 so we have not included 1 so 
            include this 0 as non-adj
            So maximum sum possible is nums[0].
        */
        if (n == 0) {
            return nums[0];
        }

        /*
            BASE CASE 2

            If index becomes negative,
            no element can be picked.

            Return 0.
        */
        if (n < 0) {
            return 0;
        }

        /*
            MEMOIZATION CHECK

            If answer already calculated,
            directly return stored value.

            This avoids repeated recursion.
        */
        if (dp[n] != -1) {
            return dp[n];
        }

        /*
            PICK current element

            If we pick nums[n],
            adjacent element cannot be picked.

            So move to index (n-2).
        */
        int pick =
                nums[n]
                + solve(nums, n - 2);

        /*
            NOT PICK current element

            Skip current element
            and move to previous index.
        */
        int notPick =
                solve(nums, n - 1);

        /*
            Store maximum answer in dp array

            So future recursive calls
            can reuse it directly.
        */
        dp[n] = Math.max(pick, notPick);

        /*
            Return final answer for current index
        */
        return dp[n];
    }

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
            Create DP array
        */
        dp = new int[n];

        /*
            Fill DP array with -1

            Meaning:
            answer not calculated yet
        */
        Arrays.fill(dp, -1);

        /*
            Find maximum sum of
            non-adjacent elements
        */
        int maxSum = solve(nums, n - 1);

        // Print final answer
        System.out.println(maxSum);
    }
}