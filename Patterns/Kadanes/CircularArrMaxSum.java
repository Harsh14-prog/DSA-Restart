package Patterns.Kadanes;

public class CircularArrMaxSum {

    public static void main(String[] args) {

        int[] nums = {5,-3,5};

        int totalSum = nums[0];

        // split ans variable into 2 minSum and maxSum
        // prev que ans madhe maxSum store karaycho , ata both havet mhanun 
        // 2 diff variable used

        int maxSum = nums[0];   // normal kadane's maxSum found in straight arr
        int minSum = nums[0];

        int maxEnding = nums[0];
        int minEnding = nums[0];

        for(int i = 1 ; i < nums.length ; i++) {

            totalSum += nums[i];

            maxEnding = Math.max(nums[i],
                                 nums[i] + maxEnding);

            minEnding = Math.min(nums[i],
                                 nums[i] + minEnding);

            maxSum = Math.max(maxSum, maxEnding);

            minSum = Math.min(minSum, minEnding);
        }

        // all negative case
        if(maxSum < 0) {
            System.out.println(maxSum);
            return;
        }

        int maxCircularSum =
                Math.max(maxSum,
                         totalSum - minSum);

        System.out.println(maxCircularSum);
    }
}