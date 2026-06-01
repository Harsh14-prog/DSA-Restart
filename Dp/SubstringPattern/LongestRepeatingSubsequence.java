package Dp.SubstringPattern;

public class LongestRepeatingSubsequence {

    public static void main(String[] args) {

        /*
         * Given String
         */
        String s = "aabebcdd";

        int n = s.length();

        /*
         * Longest Repeating Subsequence (LRS)
         * -----------------------------------
         * LRS is solved using the LCS concept.
         *
         * Idea:
         * Compare the string with itself.
         *
         *          s
         *          |
         *          v
         * "aabebcdd"
         *
         * "aabebcdd"
         *          ^
         *          |
         *          s
         *
         * But there is one extra condition:
         *
         * i != j
         *
         * because we cannot match a character
         * with itself.
         *
         * Otherwise every character would match
         * itself and answer would always become n.
         */

        /*
         * dp[i][j] =
         * Length of Longest Repeating Subsequence
         * using first i characters of string
         * and first j characters of same string.
         *
         * Exactly same meaning as LCS.
         */
        int[][] dp = new int[n + 1][n + 1];

        /*
         * Row 0 and Column 0 represent
         * empty string.
         *
         * Java automatically initializes them
         * with 0.
         */
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= n; j++) {

                /*
                 * Characters are same
                 * AND indices are different.
                 */
                if (s.charAt(i - 1) == s.charAt(j - 1)
                        && i != j) {

                    /*
                     * Why i != j ?
                     * -------------
                     *
                     * Suppose:
                     *
                     * s = "abc"
                     *
                     * If we allow:
                     *
                     * i == j
                     *
                     * then:
                     *
                     * a matches a
                     * b matches b
                     * c matches c
                     *
                     * Answer becomes 3.
                     *
                     * But this is wrong because
                     * we are matching each character
                     * with itself, not finding a
                     * repeating subsequence.
                     *
                     * Therefore:
                     *
                     * Same character is allowed
                     * but it must come from a
                     * different position.
                     *
                     * Example:
                     *
                     * s = "aab"
                     *
                     * Index:
                     * 1 2 3
                     * a a b
                     *
                     * First 'a' can match second 'a'
                     * because:
                     *
                     * character same ✔
                     * index different ✔
                     */

                    /*
                     * Include this character
                     * and move diagonally.
                     */
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }

                else {

                    /*
                     * Either:
                     *
                     * 1. Characters are different
                     * OR
                     * 2. Same index encountered
                     *
                     * Then we cannot include
                     * current pair.
                     *
                     * Same logic as LCS:
                     *
                     * Ignore one character from
                     * either side and take maximum.
                     */
                    dp[i][j] = Math.max(
                            dp[i - 1][j], // move up
                            dp[i][j - 1]  // move left
                    );
                }
            }
        }

        /*
         * Final answer stored in
         * bottom-right corner.
         */
        System.out.println("Length of Longest Repeating Subsequence = "
                + dp[n][n]);
    }
}