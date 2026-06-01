package Dp.SubstringPattern;

public class LongestCommonSubseqIndexBased {
    public static void main(String[] args) {

        String s1 = "abcedf";
        String s2 = "abcdh";

        int n = s1.length();
        int m = s2.length();

        /*
         * Why dp[n+1][m+1] ?
         * --------------------
         * Extra Row  (row 0)    -> Represents EMPTY string for s1
         * Extra Column (col 0)  -> Represents EMPTY string for s2
         *
         * Example:
         *
         *        ""  a  b  c  d  h
         *     ----------------------
         * "" | 0  0  0  0  0  0
         * a  |
         * b  |
         * c  |
         * e  |
         * d  |
         * f  |
         *
         * dp[0][j] = LCS("", first j chars of s2) = 0
         * dp[i][0] = LCS(first i chars of s1, "") = 0
         *
         * Since Java initializes int arrays with 0,
         * these base cases are automatically handled.
         *
         * The extra row and column also prevent
         * negative indexing.
         *
         * Without shifting:
         *
         * dp[0][0] = 1 + dp[-1][-1]   // Invalid
         *
         * With shifting:
         *
         * dp[1][1] = 1 + dp[0][0]     // Valid
         */
        int[][] dp = new int[n + 1][m + 1];

        /*
         * Meaning of dp[i][j]:
         *
         * LCS length between:
         * First i characters of s1
         * First j characters of s2
         *
         * Example:
         *
         * dp[3][4]
         * = LCS("abc", "abcd")
         */
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                /*
                 * Since dp indices are shifted by 1,
                 * actual character index becomes:
                 *
                 * s1.charAt(i - 1)
                 * s2.charAt(j - 1)
                 */
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    /*
                     * Characters match
                     *
                     * Include this character in LCS
                     * and move diagonally.
                     *
                     * Example:
                     * a == a
                     *
                     * dp[i][j] =
                     * 1 + dp[i-1][j-1]
                     */
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                } else {

                    /*
                     * Characters do not match
                     *
                     * Two choices:
                     *
                     * 1. Ignore current character of s1
                     *    -> dp[i-1][j]
                     *
                     * 2. Ignore current character of s2
                     *    -> dp[i][j-1]
                     *
                     * Take maximum of both choices.
                     */
                    dp[i][j] = Math.max(
                            dp[i][j - 1],
                            dp[i - 1][j]
                    );
                }
            }
        }

        /*
         * dp[n][m] contains answer for:
         *
         * LCS(full s1, full s2)
         */
        System.out.println(dp[n][m]);
    }
}