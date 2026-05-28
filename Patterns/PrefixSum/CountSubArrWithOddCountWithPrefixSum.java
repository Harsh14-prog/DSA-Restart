package Patterns.PrefixSum;

import java.util.HashMap;
import java.util.Map;

public class CountSubArrWithOddCountWithPrefixSum {

    public static void main(String[] args) {
        
        int[] nums = {1,1,2,1,1};

        int k = 3 ;

        // Stores final answer
        int count = 0 ;

        // Running prefix sum
        //
        // Here:
        // sum represents total odd count
        // till current index
        int sum = 0 ;

        // prefixSum -> frequency
        //
        // Why frequency?
        //
        // Because same prefix sum
        // can occur multiple times.
        //
        // Every occurrence can form
        // one valid subarray.
        Map <Integer , Integer> mp = new HashMap<>();

        for(int ele : nums){

            // Convert:
            //
            // odd  -> 1
            // even -> 0
            //
            // Why?
            //
            // We only care about
            // count of odd numbers.
            //
            // So prefix sum directly
            // represents odd count.
            if(ele % 2 != 0){
               sum += 1 ;
            }
            else{

                // Even contributes 0
                // to odd count
                sum += 0 ;
            }

            // sum is representing odd count
            // till current index i

            // If current sum itself becomes k,
            // then subarray from 0 -> current index
            // is valid.
            if(sum == k){
                count++ ;
            }

            // MAIN IDEA:
            //
            // We want:
            //
            // currentPrefix - previousPrefix = k
            //
            // Therefore:
            //
            // previousPrefix = currentPrefix - k
            //
            // If (sum-k) existed before,
            // then subarray with exactly k odds exists.
            if(mp.containsKey(sum-k)){

                // Add frequency because
                // every previous occurrence
                // forms one valid subarray
                count += mp.get(sum-k) ;
            }

            // Store/update frequency
            // of current prefix sum
            mp.put(sum ,
                   mp.getOrDefault(sum , 0) + 1) ;
        }

        System.out.println(count);
    }
}