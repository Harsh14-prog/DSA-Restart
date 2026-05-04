package Dp;

public class knapSackWithTopDown {

    static int[][] dp;

    public static int maxProfit(int[] wt, int[] val, int capacity, int n) {

        if (n == 0 || capacity == 0) {
            return 0;
        }

        if (dp[n][capacity] != -1) {
            return dp[n][capacity];
        }

        if (wt[n - 1] <= capacity) {

            int take = val[n - 1] + maxProfit(wt, val, capacity - wt[n - 1], n - 1);
            int skip = maxProfit(wt, val, capacity, n - 1);

            dp[n][capacity] = Math.max(take, skip);
            return dp[n][capacity];
        } else {
            return dp[n][capacity] = maxProfit(wt, val, capacity, n - 1);
        }
    }

    public static void main(String[] args) {

        int[] wt = { 2, 3, 4 };
        int[] val = { 4, 5, 6 };
        int capacity = 7;
        int n = wt.length;

        dp = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                dp[i][j] = -1;
            }
        }

        int result = maxProfit(wt, val, capacity, n);

        System.out.println(result);
    }
}
