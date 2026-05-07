package Dp;

public class MinSubsetSumDifferenceBottomUp {
public static void main(String[] args) {

    int[] nums = {1, 6, 11, 5};
    int n = nums.length;

    // Step 1: total sum
    int totalSum = 0;
    for(int num : nums){
        totalSum += num;
    }

    // Step 2: subset sum DP
    boolean[][] dp = new boolean[n+1][totalSum+1];

    // Base case: sum = 0 is always possible
    for(int i = 0; i <= n; i++){
        dp[i][0] = true;
    }

    // Fill DP
    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= totalSum; j++){

            if(nums[i-1] <= j){
                dp[i][j] = dp[i-1][j] || dp[i-1][j - nums[i-1]];
            } else {
                dp[i][j] = dp[i-1][j];
            }
        }
    }

    // Step 3: find minimum difference
    int minDiff = Integer.MAX_VALUE;

    for(int s1 = 0; s1 <= totalSum/2; s1++){

        if(dp[n][s1]){
            int diff = totalSum - 2 * s1;
            minDiff = Math.min(minDiff, diff);
        }
    }

    System.out.println(minDiff);
}

}

