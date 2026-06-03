package Dp.LIS;

public class LongestIncreasingSubseq {

    public static int solve(int[] nums , int idx , int prevIdx){

        if(idx == nums.length){
            return 0 ;
        }
      
        int skip = 0 + solve(nums , idx + 1 , prevIdx) ;

        int take = Integer.MIN_VALUE ;

        if(prevIdx == -1 || nums[idx] > nums[prevIdx]){

            take = 1 + solve(nums , idx + 1 , idx) ;
        }

        return Math.max(take , skip) ;

    }
    public static void main(String[] args) {

        int[] nums = {10,9,2,5,3,7,101,18} ;

        int index = 0 ;
        int prevIdx = -1 ;

        int maxLen = solve(nums , index , prevIdx) ;

        System.out.println(maxLen);
        
    }
}
