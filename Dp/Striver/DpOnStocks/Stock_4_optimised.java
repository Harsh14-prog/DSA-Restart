package Dp.Striver.DpOnStocks;

public class Stock_4_optimised {
    public static void main(String[] args) {

        int[] nums = { 3, 3, 5, 0, 0, 3, 1, 4 };

        int n = nums.length;

        int k = 3;

        int[][] ahead = new int[2][k + 1];

        for (int index = n - 1; index >= 0; index--) {

            int[][] curr = new int[2][k + 1];

            for (int cap = 1; cap <= k; cap++) {

                int bought = -nums[index]
                        + ahead[0][cap];

                int skipBought = ahead[1][cap];

                curr[1][cap] = Math.max(bought, skipBought);

                int sell = nums[index]
                        + ahead[1][cap - 1];

                int skipSell = ahead[0][cap];

                curr[0][cap] = Math.max(sell, skipSell);
            }

            ahead = curr;
        }
        
        System.out.println(ahead[1][k]);
    }
}
