package Practice;

public class Second {
    public static void main(String[] args) {

        int[] nums = {1,6,11,5} ;
        int n = nums.length ;

        int totalSum = 0 ;
        for(int ele : nums){
            totalSum += ele ;
        }

        boolean[][] dp = new boolean[n+1][totalSum + 1] ;

        for(int i = 0 ; i <= n ; i++){
            dp[i][0] = true ;
        }

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= totalSum ; j++) {
                if(nums[i-1] <= j){
                    dp[i][j] = dp[i-1][j - nums[i-1]] || dp[i-1][j] ;
                }
                else{
                    dp[i][j] = dp[i-1][j] ;
                }
            }
        }
        
        int minDiff = Integer.MAX_VALUE ;

        for(int s1 = 0 ; s1 <= totalSum / 2 ; s1++) {

           if(dp[n][s1]){
             int diff = totalSum - (2 * s1);
             minDiff = Math.min(minDiff , diff) ;
           } 
        }

        System.out.println(minDiff);
    }
}
