package Dp.SubstringPattern;

public class distinctSubsequenceSpaceOptimized {

    public static void main(String[] args) {

        String s1 = "bagbag";
        String s2 = "bag";

        int m = s1.length();
        int n = s2.length();

        /*
         * dp[i][j] means:
         *
         * Number of ways to form
         * first j characters of s2
         * using first i characters of s1
         *
         * Space Optimization:
         *
         * dp[i][j] depends only on:
         *
         * dp[i-1][j-1]
         * dp[i-1][j]
         *
         * (only previous row)
         *
         * Therefore we only need:
         *
         * prev[] -> previous row
         * curr[] -> current row
         */

        int[] prev = new int[n + 1];

        /*
         * Represents Row 0
         *
         * Original DP table:
         *
         *        ""   b   a   g
         *     ------------------
         * "" |  1   0   0   0
         *
         * Why prev[0] = 1 ?
         *
         * Empty target can always be formed
         * from empty source in exactly one way:
         *
         * Take nothing.
         */
        prev[0] = 1;

        for (int i = 1; i <= m; i++) {

            /*
             * curr represents current row.
             *
             * Java initializes it as:
             *
             * [0,0,0,0...]
             */
            int[] curr = new int[n + 1];

            /*
             * First column is always 1.
             *
             * Why?
             *
             * dp[i][0] means:
             *
             * Form empty target ("")
             * using first i characters of source.
             *
             * This is always possible
             * by deleting (skipping) all characters.
             *
             * Therefore:
             *
             * dp[i][0] = 1
             *
             * So every row starts with 1.
             */
            curr[0] = 1;

            for (int j = 1; j <= n; j++) {

                /*
                 * Characters match
                 */
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    /*
                     * Two choices:
                     *
                     * 1. Take current character
                     *
                     * prev[j-1]
                     *
                     * because:
                     *
                     * both characters are used
                     *
                     * 2. Don't take current character
                     *
                     * prev[j]
                     *
                     * because:
                     *
                     * target character still remains
                     *
                     * Since we need total ways:
                     *
                     * Take + Not Take
                     */
                    curr[j] =
                            prev[j - 1]
                                    + prev[j];

                } else {

                    /*
                     * Characters don't match.
                     *
                     * Current source character
                     * cannot help.
                     *
                     * Only option:
                     *
                     * Skip source character.
                     *
                     * Therefore:
                     *
                     * dp[i][j] = dp[i-1][j]
                     */
                    curr[j] = prev[j];
                }
            }

            /*
             * Current row becomes
             * previous row for next iteration.
             */
            prev = curr;
        }

        /*
         * prev[n] contains answer.
         *
         * Number of ways to form
         * full s2 using full s1.
         */
        System.out.println("Answer = " + prev[n]);
    }
}