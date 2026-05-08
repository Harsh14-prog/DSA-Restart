package Dp;

public class TargetSumRecursion {

    public static int solve(int[] nums, int index, int currentSum, int target){

        /*
         * Base Case:
         * All elements processed
         */
        if(index == nums.length){

            /*
             * If currentSum becomes target
             * → found one valid way
             */
            if(currentSum == target){
                return 1;
            }

            /*
             * Otherwise invalid way
             */
            return 0;
        }

        /*
         * Choice 1:
         * Put '+' before current element
         */
        int add = solve(nums,
                        index + 1,
                        currentSum + nums[index],
                        target);

        /*
         * Choice 2:
         * Put '-' before current element
         */
        int subtract = solve(nums,
                             index + 1,
                             currentSum - nums[index],
                             target);

        /*
         * Total ways = add + subtract
         */
        return add + subtract;
    }

    public static void main(String[] args) {

        int[] nums = {1,1,1,1,1};
        int target = 3;

        int result = solve(nums, 0, 0, target);

        System.out.println(result);
    }
}