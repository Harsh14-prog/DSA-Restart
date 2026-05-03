package recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public static void sum(int[] nums, int target, int index, List<Integer> list) {

        // Base case
        if (index == nums.length) {
            if (target == 0) {
                System.out.println(list);
            }
            return;
        }

        // TAKE (only if possible)
        if (nums[index] <= target) {

            list.add(nums[index]);
            sum(nums, target - nums[index], index, list); // reuse

            // BACKTRACK
            list.remove(list.size() - 1);
        }

        // NOT TAKE
        sum(nums, target, index + 1, list);
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 6, 7};
        int target = 7;

        sum(nums, target, 0, new ArrayList<>());
    }
}