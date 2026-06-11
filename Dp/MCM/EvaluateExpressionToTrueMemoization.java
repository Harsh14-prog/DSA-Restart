package Dp.MCM;

import java.util.Arrays;

public class EvaluateExpressionToTrueMemoization {

    // dp[i][j][0] -> no of ways to substr(expression)[i...j] becomes false
    // dp[i][j][1] -> True ways
    static int[][][] dp;

    static int solve(String s, int i, int j, boolean isTrue) {

        // Invalid range
        if (i > j)
            return 0;

        int flag = isTrue ? 1 : 0;

        // Already solved
        if (dp[i][j][flag] != -1)
            return dp[i][j][flag];

        // Single character
        if (i == j) {

            if (isTrue)
                return dp[i][j][flag] =
                        (s.charAt(i) == 'T' ? 1 : 0);

            else
                return dp[i][j][flag] =
                        (s.charAt(i) == 'F' ? 1 : 0);
        }

        int ans = 0;

        // Partition at every operator
        for (int k = i + 1; k <= j - 1; k += 2) {

            int LT = solve(s, i, k - 1, true);
            int LF = solve(s, i, k - 1, false);

            int RT = solve(s, k + 1, j, true);
            int RF = solve(s, k + 1, j, false);

            char op = s.charAt(k);

            if (op == '&') {

                if (isTrue)
                    ans += LT * RT;
                else
                    ans += LT * RF
                         + LF * RT
                         + LF * RF;
            }

            else if (op == '|') {

                if (isTrue)
                    ans += LT * RT
                         + LT * RF
                         + LF * RT;
                else
                    ans += LF * RF;
            }

            else if (op == '^') {

                if (isTrue)
                    ans += LT * RF
                         + LF * RT;
                else
                    ans += LT * RT
                         + LF * RF;
            }
        }

        // Store answer before returning
        return dp[i][j][flag] = ans;
    }

    public static void main(String[] args) {

        String s = "T|F&T";

        int n = s.length();

        dp = new int[n][n][2];

        // Initialize DP with -1
        for (int[][] row : dp) {
            for (int[] col : row) {
                Arrays.fill(col, -1);
            }
        }

        System.out.println(
                solve(s, 0, n - 1, true)
        );
    }
}