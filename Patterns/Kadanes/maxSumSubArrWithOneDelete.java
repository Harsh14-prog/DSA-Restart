package Patterns.Kadanes;

public class maxSumSubArrWithOneDelete {

    public static void main(String[] args) {

        int[] nums = {1,-2,0,3};

        int oneDelete = 0;
        int noDelete = nums[0];
        int ans = nums[0];

        for(int i = 1; i < nums.length; i++) {

            int prevOneDelete = oneDelete;
            int prevNoDelete = noDelete;

            oneDelete = Math.max(
                    prevOneDelete + nums[i],
                    prevNoDelete
            );

            noDelete = Math.max(
                    prevNoDelete + nums[i],
                    nums[i]
            );

            ans = Math.max(ans,
                    Math.max(noDelete, oneDelete));
        }

        System.out.println(ans);
    }
}