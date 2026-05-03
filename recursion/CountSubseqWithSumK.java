package recursion;


public class CountSubseqWithSumK {

    public static int sumOfSubseq(int[] nums, int k, int sum, int index) {

        // Base case
        if (index == nums.length) {
            if (sum == k) {
                return 1;
            }
            return 0;
        }

        // TAKE
        int left = sumOfSubseq(nums, k, sum + nums[index], index + 1);

        // NOT TAKE
        int right = sumOfSubseq(nums, k, sum, index + 1);

        return left + right;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1};
        int k = 3;

        int count = sumOfSubseq(nums, k, 0, 0);
        System.out.println(count);
    }
}