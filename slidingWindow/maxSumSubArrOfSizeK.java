package slidingWindow;

public class maxSumSubArrOfSizeK {
    public static void main(String[] args) {
        int[] nums = {2,1,5,1,3,2};
        int n = nums.length ;
        int k = 3 ;
        int sum = 0 ;
        int maxSum = 0 ;

        int l = 0 , h = 0 ;

        while (h < n){

          sum += nums[h];

          if(h-l+1 < k){
             h++;
          }
          else if(h-l+1 == k){
            maxSum = Math.max(maxSum , sum);
            sum -= nums[l];
            l++;
            h++;
          }
        }

        System.out.println(maxSum);
    }
}
