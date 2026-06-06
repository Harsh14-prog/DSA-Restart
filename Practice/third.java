package Practice;

public class third {

    public static int solve(int[][] nums, int i, int j1, int j2, int m, int n) {

        if (j1 < 0 || j1 >= n || j2 < 0 || j2 >= n) {
            return -(int) (1e9);
        }

        if (i == m - 1) {

            if (j1 == j2) {
                return nums[i][j1];
            }
            return nums[i][j1] + nums[i][j2];
        }

        int maxi = Integer.MIN_VALUE;

        for (int dj1 = -1; dj1 <= 1; dj1++) {

            for (int dj2 = -1; dj2 <= 1; dj2++) {

                int value;

                if (j1 == j2) {
                    value = nums[i][j1];
                } else {
                    value = nums[i][j1] + nums[i][j2];
                }

                value += solve(nums, i + 1, j1 + dj1, j2 + dj2, m, n);

                maxi = Math.max(maxi, value);
            }
        }

        return maxi;
    }

    public static void main(String[] args) {

        int[][] nums = {
                { 2, 3, 1, 2 },
                { 3, 4, 2, 2 },
                { 5, 6, 3, 5 }
        };

        int m = nums.length;
        int n = nums[0].length;

        int[][][] dp = new int[m][n][n];

        for (int j1 = 0; j1 < n; j1++) {

            for (int j2 = 0; j2 < n; j2++) {

                if (j1 == j2) {
                    dp[m - 1][j1][j2] = nums[m - 1][j1];
                } else {
                    dp[m - 1][j1][j2] = nums[m - 1][j1] + nums[m - 1][j2];
                }
            }
        }

        for (int i = m - 2; i >= 0; i--) {

            for (int j1 = 0; j1 < n; j1++) {

                for (int j2 = 0; j2 < n; j2++) {

                    int maxi = Integer.MIN_VALUE;

                    for (int dj1 = -1; dj1 <= 1; dj1++) {

                        for (int dj2 = -1; dj2 <= 1; dj2++) {

                            int value;

                            if (j1 == j2) {
                                value = nums[i][j1];
                            } else {
                                value = nums[i][j1] + nums[i][j2];
                            }

                            int left = j1 + dj1;
                            int right = j2 + dj2;

                            if ((left > 0 && left < n) && (right > 0 && right < n)) {
                                value += dp[i + 1][left][right];
                            } 
                            else {
                                value += -(int) 1e9;
                            }

                            maxi = Math.max(maxi, value);
                        }
                    }

                    dp[i][j1][j2] = maxi;
                }
            }
        }

        System.out.println(dp[0][0][n - 1]);
    }
}
