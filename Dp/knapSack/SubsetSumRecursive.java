package Dp.knapSack;

public class SubsetSumRecursive {

    public static boolean solve(int[] nums, int target, int n) {

        // If target becomes 0,
        // subset is found
        if (target == 0) {
            return true;
        }

        // No elements left
        if (n == 0) {
            return false;
        }

        // In the "count subsets" variation,
        // we usually combine both base cases like:
        //
        // if(n == 0){
        // return (target == 0) ? 1 : 0;
        // }
        //
        // because even if target becomes 0,
        // we do NOT stop recursion immediately.
        //
        // Why?
        // Because we want to count ALL possible subsets
        // that can form the target sum.
        //
        // So recursion continues exploring other combinations.
        //
        // --------------------------------------------------
        //
        // But in this Boolean Subset Sum problem,
        // our goal is only to check:
        //
        // "Does at least one valid subset exist?"
        //
        // We are NOT interested in counting all subsets.
        //
        // Therefore, as soon as target becomes 0,
        // we can immediately return true.
        //
        // This acts like an early stopping condition
        // and avoids unnecessary recursive calls,
        // improving efficiency.

        // Choice: include or exclude
        if (nums[n - 1] <= target) {

            // Include current element
            boolean left = solve(nums,
                    target - nums[n - 1],
                    n - 1);

            // Exclude current element
            boolean right = solve(nums,
                    target,
                    n - 1);

            return left || right;
        }

        // Cannot include current element
        else {
            return solve(nums, target, n - 1);
        }
    }

    public static void main(String[] args) {

        int[] nums = { 2, 3, 7, 8, 10 };
        int n = nums.length;

        int target = 11;

        boolean ans = solve(nums, target, n);

        System.out.println(ans);
    }
}