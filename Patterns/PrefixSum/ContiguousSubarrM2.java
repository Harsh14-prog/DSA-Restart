package Patterns.PrefixSum;

import java.util.HashMap;
import java.util.Map;

public class ContiguousSubarrM2 {

    public static void main(String[] args) {

        int[] nums = {0,1,0,0,1,1,1,0};

        // Stores maximum length of valid subarray
        int maxLength = 0;

        // Running prefix sum
        int sum = 0;

        // prefixSum -> first occurrence index
        //
        // Why first occurrence?
        // Because earlier index gives larger subarray length.
        Map<Integer , Integer> mp = new HashMap<>();


        // IMPORTANT:
        // Prefix sum 0 is considered seen before array starts
        // at index -1.
        //
        // This helps handle subarrays starting from index 0.
        //
        // Example:
        // If sum becomes 0 at index 3,
        // then length = 3 - (-1) = 4
        mp.put(0 , -1);


        for(int i = 0 ; i < nums.length ; i++) {

            // Convert:
            //
            // 0 -> -1
            // 1 -> +1
            //
            // Why?
            //
            // Equal number of 0s and 1s
            // means equal number of -1 and +1
            //
            // Their total sum becomes 0.
            if(nums[i] == 0) {
                sum += -1;
            }
            else {
                sum += 1;
            }


            // MAIN IDEA:
            //
            // If same prefix sum appears again,
            // then subarray between them has sum 0.
            //
            // Because:
            //
            // prefix[j] - prefix[i] = 0
            //
            // Meaning:
            //
            // prefix[j] == prefix[i]
            //
            // And sum 0 means:
            // equal number of 0s and 1s
            if(mp.containsKey(sum)) {

                // Previous occurrence index
                int prevIndex = mp.get(sum);

                // Length of subarray
                //
                // Subarray is:
                // prevIndex + 1  -> i
                int currentLength = i - prevIndex;

                // Update maximum length
                maxLength = Math.max(maxLength,
                                     currentLength);
            }

            // Store first occurrence only
            //
            // Why first occurrence?
            //
            // Earlier index gives bigger length.
            else {
                mp.put(sum , i);
            }
        }

        System.out.println(maxLength);
    }
}