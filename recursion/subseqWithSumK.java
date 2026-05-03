package recursion;

import java.util.ArrayList;
import java.util.List;

public class subseqWithSumK {

    public static void sumOfSubseq(int[] nums, int k, int sum, int index, List<Integer> list ) {

        // Base case
        if (index == nums.length) {
            if (sum == k) {
                System.out.println(list);
            }
            return; 
        }

        // TAKE
        list.add(nums[index]);
        sum += nums[index];

        sumOfSubseq(nums, k, sum, index + 1, list);

        // BACKTRACK --> NOT TAKE
        sum -= nums[index];
        list.remove(list.size() - 1);

        sumOfSubseq(nums, k, sum, index + 1, list);
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1};
        int k = 3;

        sumOfSubseq(nums, k, 0, 0, new ArrayList<>());
    }
}