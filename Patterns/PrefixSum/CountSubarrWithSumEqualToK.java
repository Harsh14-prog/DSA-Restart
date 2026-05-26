package Patterns.PrefixSum;

import java.util.HashMap;
import java.util.Map;

public class CountSubarrWithSumEqualToK {
    public static void main(String[] args) {

        int[] nums = {3,4,7,2,-3,1,4,2};
        int n = nums.length ;

        int k = 7 ;
        int sum = 0 ;
        int count = 0 ;

        Map <Integer , Integer> mp = new HashMap<>() ;

        for(int i = 0 ; i < n ; i++) {
            
           sum += nums[i] ;

           if(sum == k) {
              count++ ;
           }

           if(mp.containsKey(sum - k)) {
              count += mp.get(sum-k);
           }

           mp.put(sum , mp.getOrDefault(sum, 0) + 1) ;
        }

        System.out.println(count);
    }
}
