package Patterns.PrefixSum;

public class CountSubArrWithOddCountExactlyKWithSlidingWindow {

    public static int atMost(int[] nums , int k){
       
        int l = 0 ;

        int oddCount = 0 ;
        int count = 0 ;

        for(int h = 0 ; h < nums.length ; h++){

            if(nums[h] % 2 != 0){
                oddCount++;
            }

            while(oddCount > k){

                if(nums[l] % 2 != 0){
                    oddCount--;
                }
                l++;
            }

            count += h-l+1 ;
        }

        return count ;
    }
    public static void main(String[] args) {

        int[] nums = {1,1,2,1,1};

        int k = 3 ;

        int count = atMost(nums, k) - atMost(nums, k-1) ;

        System.out.println(count);
    }
}
