package recursion;

import java.util.ArrayList;
import java.util.List;

public class PermutationAll {

    public static void recurPermutation(int[] nums, List<Integer> list, boolean[] freq, List<List<Integer>> ansList) {

        if (list.size() == nums.length) {
            ansList.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (!freq[i]) {

                freq[i] = true;
                list.add(nums[i]);

                recurPermutation(nums, list, freq, ansList);
                freq[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {

        int[] nums = { 1, 2, 3 };
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> ansList = new ArrayList<>();
        boolean[] freq = new boolean[nums.length];

        recurPermutation(nums, list, freq, ansList);

        System.out.println(ansList);
    }
}
