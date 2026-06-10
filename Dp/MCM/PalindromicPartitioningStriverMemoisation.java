package Dp.MCM;

import java.util.Arrays;

public class PalindromicPartitioningStriverMemoisation {

    // dp[i] = minimum palindrome partitions needed
    // from index i till the end of the string
    static int[] dp;

    /**
     * Checks whether a string is palindrome.
     *
     * Example:
     * "aa" -> true
     * "aba" -> true
     * "ab" -> false
     */
    public static boolean isPalindrome(String str) {

        int left = 0;
        int right = str.length() - 1;

        while (left < right) {

            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    /**
     * solve(i)
     *
     * Meaning:
     *
     * Minimum palindrome partitions needed
     * starting from index i till the end.
     *
     * Example:
     *
     * s = "aab"
     *
     * solve(0) -> answer for entire string
     * solve(1) -> answer for "ab"
     * solve(2) -> answer for "b"
     */
    public static int solve(String s, int i) {

        /*
         * =========================
         * BASE CASE
         * =========================
         *
         * If i reaches end of string,
         * no characters remain.
         *
         * Therefore 0 partitions needed.
         */
        if (i == s.length()) {
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
        if (dp[i] != -1) {
            return dp[i];
        }

        /*
         * Used to generate all substrings
         * starting from index i.
         *
         * Example:
         *
         * i = 0
         * s = "aab"
         *
         * j=0 -> "a"
         * j=1 -> "aa"
         * j=2 -> "aab"
         */
        StringBuilder sb = new StringBuilder();

        /*
         * Stores minimum partitions
         * among all possible choices.
         */
        int minPartitions = Integer.MAX_VALUE;

        /*
         * j represents ending index
         * of current partition.
         *
         * i remains fixed.
         */
        for (int j = i; j < s.length(); j++) {

            /*
             * Extend substring.
             */
            sb.append(s.charAt(j));

            /*
             * If current substring
             * is palindrome,
             * we can choose it.
             */
            if (isPalindrome(sb.toString())) {

                /*
                 * 1 -> current partition
                 *
                 * solve(j+1)
                 * -> partitions needed
                 * for remaining string
                 */
                int partitions = 1 + solve(s, j + 1);

                /*
                 * Keep minimum answer.
                 */
                minPartitions = Math.min(minPartitions, partitions);
            }
        }

        /*
         * Store answer before returning.
         */
        return dp[i] = minPartitions;
    }

    public static void main(String[] args) {

        String s = "aab";

        int n = s.length();

        // Initialize DP array
        dp = new int[n];

        // -1 means answer not computed yet
        Arrays.fill(dp, -1);

        /*
         * solve(0) returns minimum number
         * of PALINDROME PARTITIONS
         */
        int partitions = solve(s, 0);

        /*
         * We need cuts, not partitions.
         *
         * Example:
         *
         * aa | b
         *
         * Partitions = 2
         * Cuts = 1
         *
         * Formula:
         * cuts = partitions - 1
         */
        int cuts = partitions - 1;

        System.out.println("Minimum Cuts : " + cuts);
    }
}