package Patterns.PrefixSum;

import java.util.HashMap;
import java.util.Map;

public class SubarrSumDivByK {

    public static void main(String[] args) {

        int[] nums = {4,5,0,-2,-3,1};

        int k = 5;

        // Stores count of valid subarrays
        int count = 0;

        // Running prefix sum
        int prefixSum = 0;

        // remainder -> frequency
        //
        // Why frequency?
        // Because if same remainder appeared multiple times,
        // current remainder can form subarray with ALL of them.
        Map<Integer, Integer> map = new HashMap<>();


        // IMPORTANT:
        // remainder 0 already seen once before array starts
        //
        // Helps handle subarrays starting from index 0.
        //
        // Example:
        // if prefixSum itself becomes divisible by k,
        // then subarray from 0 -> current index is valid.
        map.put(0, 1);


        for(int ele : nums) {

            // Build prefix sum
            prefixSum += ele;


            // Find remainder
            int rem = prefixSum % k;


            // Java can produce negative remainder
            //
            // Example:
            // -2 % 5 = -2
            //
            // Convert it into positive remainder.
            rem = (rem + k) % k;


            // MAIN LOGIC:
            //
            // If same remainder appeared before,
            // then difference of prefix sums is divisible by k.
            //
            // Because:
            // if prefix[i] % k == prefix[j] % k
            // then prefix[j] - prefix[i] subarr sum is div by k
            // that subarr will be from i+1 to j
            //
            // So every previous same remainder forms
            // one valid subarray.
            if(map.containsKey(rem)) {

                // Add all previous occurrences
                count += map.get(rem);
            }

            // Store/update frequency of current remainder
            map.put(rem, map.getOrDefault(rem, 0) + 1);
        }

        System.out.println(count);
    }
}