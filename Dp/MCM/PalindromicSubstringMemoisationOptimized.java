package Dp.MCM;

import java.util.Arrays;

public class PalindromicSubstringMemoisationOptimized {

    // DP table
    // dp[i][j] = minimum cuts needed for substring s[i...j]
    static int[][] dp;

    /**
     * Checks whether substring s[i...j] is a palindrome.
     *
     * Example:
     * "aa" -> true
     * "aba" -> true
     * "ab" -> false
     */
    public static boolean isPalindrome(String s, int i, int j) {

        while (i < j) {

            // Characters mismatch
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    /**
     * Returns minimum cuts required to partition
     * substring s[i...j] into palindromic substrings.
     *
     * MCM Pattern:
     *
     * solve(i,j)
     * =
     * min(
     * 1 + solve(i,k) + solve(k+1,j)
     * )
     *
     * where k varies from i to j-1
     */
    public static int solve(String s, int i, int j) {

        /*
         * =========================
         * BASE CASE 1
         * =========================
         *
         * Empty substring or
         * single character substring.
         *
         * No cuts required.
         */
        if (i >= j) {
            return 0;
        }

        /*
         * =========================
         * BASE CASE 2
         * =========================
         *
         * If entire substring is already
         * a palindrome then no cut is needed.
         *
         * Example:
         * "aa"
         * "aba"
         * "racecar"
         */
        if (isPalindrome(s, i, j)) {
            return 0;
        }

        /*
         * =========================
         * MEMOIZATION CHECK
         * =========================
         *
         * If answer already calculated,
         * return it directly.
         */
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        /*
         * Stores minimum answer among
         * all possible partitions.
         */
        int minCuts = Integer.MAX_VALUE;

        /*
         * Try every possible partition point.
         *
         * Example:
         *
         * aab
         *
         * k=0
         * a | ab
         *
         * k=1
         * aa | b
         */
        for (int k = i; k < j; k++) {

            int left;
            int right;

            /*
             * =========================
             * OPTIMIZATION FOR LEFT PART
             * =========================
             *
             * If left side already solved,
             * directly use stored value.
             *
             * Otherwise solve recursively
             * and store it in dp table.
             */
            if (dp[i][k] != -1) {

                left = dp[i][k];

            } else {

                left = solve(s, i, k);

                // Store result
                dp[i][k] = left;
            }

            /*
             * =========================
             * OPTIMIZATION FOR RIGHT PART
             * =========================
             *
             * If right side already solved,
             * directly use stored value.
             *
             * Otherwise solve recursively
             * and store it in dp table.
             */
            if (dp[k + 1][j] != -1) {

                right = dp[k + 1][j];

            } else {

                right = solve(s, k + 1, j);

                // Store result
                dp[k + 1][j] = right;
            }

            /*
             * +1 because we are making
             * one cut at position k.
             *
             * Example:
             *
             * aa | b
             * ^
             * one cut
             */
            int totalCuts = 1 + left + right;

            /*
             * Update minimum answer.
             */
            minCuts = Math.min(minCuts, totalCuts);
        }

        /*
         * Store final answer for
         * substring s[i...j]
         */
        return dp[i][j] = minCuts;
    }

    public static void main(String[] args) {

        String s = "aab";

        int n = s.length();

        // Create DP table
        dp = new int[n][n];

        // Fill DP with -1 (means not calculated yet)
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int minCuts = solve(s, 0, n - 1);

        System.out.println("Minimum Cuts Required : " + minCuts);
    }
}