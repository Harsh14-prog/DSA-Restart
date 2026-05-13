package Dp.knapSack;

public class MinSubsetSumDiffTopDown {

// dp[n][sum1] = minimum difference using first n elements with sum1
static int[][] dp;

public static int solve(int[] nums , int n , int sum1 , int totalSum){

    /*
     * Base Case:
     * When all elements are processed
     */
    if(n == 0){
        int sum2 = totalSum - sum1;
        return Math.abs(sum1 - sum2);
    }

    /*
     * Memoization check
     */
    if(dp[n][sum1] != -1){
        return dp[n][sum1];
    }

    /*
     * Choice 1: Include current element in subset1
     */
    int take = solve(nums , n-1 , sum1 + nums[n-1] , totalSum);

    /*
     * Choice 2: Exclude from subset1 (goes to subset2)
     */
    int skip = solve(nums , n-1 , sum1 , totalSum);

    /*
     * Store minimum difference
     */
    return dp[n][sum1] = Math.min(take , skip);
}

public static void main(String[] args) {

    int[] nums = {1,6,11,5};
    int n = nums.length;

    int totalSum = 0;

    // Calculate total sum
    for(int num : nums){
        totalSum += num;
    }

    /*
     * dp size must cover all possible sum1 values
     */
    dp = new int[n+1][totalSum + 1];

    // Initialize with -1 (means not computed)
    for(int i = 0 ; i <= n ; i++){
        for(int j = 0 ; j <= totalSum ; j++){
            dp[i][j] = -1;
        }
    }
    
    int result = solve(nums , n , 0 , totalSum);

    System.out.println(result);
}
}


