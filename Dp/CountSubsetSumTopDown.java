package Dp;

public class CountSubsetSumTopDown {

    static int[][] dp;

    public static int solve(int[] nums, int target, int n) {

        // Base case (IMPORTANT)
        if(n == 0){
            if(target == 0) return 1;
            else return 0;
        }

        // Memoization check
        if(dp[n][target] != -1){
            return dp[n][target];
        }

        // If current element can be taken
        if(nums[n-1] <= target){

            int take = solve(nums, target - nums[n-1], n-1);
            int skip = solve(nums, target, n-1);

            return dp[n][target] = take + skip;
        }
        else{
            return dp[n][target] = solve(nums, target, n-1);
        }
    }

    public static void main(String[] args) {

        int[] nums = {2, 3, 5, 6, 8, 10};
        int target = 10;
        int n = nums.length;

        dp = new int[n+1][target+1];

        // Initialize dp with -1
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= target; j++){
                dp[i][j] = -1;
            }
        }

        int result = solve(nums, target, n);
        System.out.println(result);
    }
}