package Patterns.Kadanes;

public class MaxAbsSumSubArr {

    public static void main(String[] args) {

        int[] nums = {1, -3, 2, 3, -4};

        int maxSum = nums[0];
        int minSum = nums[0];

        int ans = Math.abs(nums[0]);

        for(int i = 1; i < nums.length; i++) {

            maxSum = Math.max(nums[i],
                              nums[i] + maxSum);

            minSum = Math.min(nums[i],
                              nums[i] + minSum);

            ans = Math.max(ans,
                    Math.max(Math.abs(maxSum),
                             Math.abs(minSum)));
        }

        System.out.println(ans);
    }
}