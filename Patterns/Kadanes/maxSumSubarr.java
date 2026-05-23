package Patterns.Kadanes;

class maxSumSubArr {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int n = nums.length ;

        int ans = nums[0];
        int maxSum = nums[0] ;

        for(int i = 1 ; i < n ; i++) {

           int v1 = maxSum + nums[i] ;
           int v2 = nums[i] ;
           
           maxSum = Math.max(v1, v2);

           ans = Math.max(maxSum , ans);
        }

        System.out.println(ans);
    }
}
