package recursion;

import java.util.ArrayList;
import java.util.List;

import java.util.*;

public class combinationSum2 {

    public static void sum(int[] nums, int target, int index,
        List<Integer> list, List<List<Integer>> ansList) {

        if (target == 0) {
            ansList.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < nums.length; i++) {

            // Skip duplicates
            if (i > index && nums[i] == nums[i - 1]) continue;

            // Pruning
            if (nums[i] > target) break;

            // TAKE
            list.add(nums[i]);

            sum(nums, target - nums[i], i + 1, list, ansList);

            // BACKTRACK
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2};
        int target = 4;

        Arrays.sort(nums); // VERY IMPORTANT

        List<List<Integer>> ansList = new ArrayList<>();

        sum(nums, target, 0, new ArrayList<>(), ansList);

        System.out.println(ansList);
    }
}
