package Patterns.Kadanes;

public class maxProdSubArr {

    public static void main(String[] args) {

        int[] nums = {-2, 3, -4};

        int minProd = nums[0];
        int maxProd = nums[0];
        int ans = nums[0];

        for(int i = 1; i < nums.length; i++) {

            int prevMaxProd = maxProd;
            int prevMinProd = minProd;

            maxProd = Math.max(nums[i],
                    Math.max(nums[i] * prevMaxProd,
                             nums[i] * prevMinProd));

            minProd = Math.min(nums[i],
                    Math.min(nums[i] * prevMaxProd,
                             nums[i] * prevMinProd));

            ans = Math.max(ans, maxProd);
        }

        System.out.println(ans);
    }
}