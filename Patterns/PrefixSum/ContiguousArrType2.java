package Patterns.PrefixSum;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArrType2 {

    public static void main(String[] args) {

        int[] nums = {23,2,4,6,7};

        int k = 6;

        // Running prefix sum
        int prefixSum = 0;

        // remainder -> first occurrence index
        //
        // Why store index?
        //
        // Because we need to check:
        // subarray length >= 2
        Map<Integer , Integer> mp = new HashMap<>();


        // IMPORTANT:
        // remainder 0 is considered seen
        // before array starts at index -1
        //
        // Why?
        //
        // Helps handle subarrays starting from index 0.
        //
        // Example:
        // If prefix sum itself becomes divisible by k,
        // then:
        //
        // subarray from 0 -> current index is valid.
        mp.put(0 , -1);


        for(int i = 0 ; i < nums.length ; i++) {

            // Build prefix sum
            prefixSum += nums[i];


            // Find remainder
            int rem = prefixSum % k;


            // IMPORTANT:
            // Java can produce negative remainder.
            //
            // Example:
            // -2 % 5 = -2
            //
            // Convert negative remainder
            // into equivalent positive remainder.
            rem = (rem + k) % k;


            // MAIN IDEA:
            //
            // If SAME remainder appeared before,
            // then:
            //
            // currentPrefix % k
            // ==
            // previousPrefix % k
            //
            // Therefore:
            //
            // (currentPrefix - previousPrefix)
            // is divisible by k
            //
            // And:
            //
            // difference of prefix sums
            // gives subarray sum.
            if(mp.containsKey(rem)) {

                // Previous occurrence index
                int prevIndex = mp.get(rem);

                // Length of subarray
                //
                // Subarray:
                // prevIndex+1 -> i
                //
                // Length:
                // i - prevIndex
                if(i - prevIndex >= 2) {

                    System.out.println(true);
                    return;
                }
            }

            // Store FIRST occurrence only
            //
            // Why?
            //
            // Earlier index gives larger subarray.
            else {
                mp.put(rem , i);
            }
        }

        System.out.println(false);
    }
}