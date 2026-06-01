package Dp.SubstringPattern;

public class distinctSubSequenceOptimizedUsing_1_Array {

    public static void main(String[] args) {

        String s1 = "bagbag";
        String s2 = "bag";

        int m = s1.length();
        int n = s2.length();

        /*
         * dp[j] represents:
         *
         * Number of ways to form
         * first j characters of s2
         * using processed characters of s1.
         *
         * Initially we are at Row 0:
         *
         *        ""   b   a   g
         *     ------------------
         * "" |  1   0   0   0
         *
         * Empty target can always be formed
         * in exactly one way.
         */
        int[] dp = new int[n + 1];

        dp[0] = 1;

        /*
         * Process each character of source string.
         */
        for (int i = 1; i <= m; i++) {

            /*
             * IMPORTANT:
             *
             * Traverse from RIGHT to LEFT.
             *
             * Why?
             *
             * Formula:
             *
             * dp[i][j]
             * =
             * dp[i-1][j]
             * +
             * dp[i-1][j-1]
             *
             * Both values come from PREVIOUS ROW.
             *
             * If we move left -> right,
             * dp[j-1] would already be updated
             * and previous-row information
             * would be lost.
             *
             * Moving right -> left preserves
             * previous-row values.
             */
            for (int j = n; j >= 1; j--) {

                /*
                 * Characters match.
                 */
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    /*
                     * Two choices:
                     *
                     * 1. Take current character
                     *    -> dp[j-1]
                     *
                     * 2. Don't take current character
                     *    -> dp[j]
                     *
                     * Since we need total ways:
                     *
                     * Take + Not Take
                     */
                    dp[j] = dp[j - 1] + dp[j];
                }

                /*
                 * Characters don't match.
                 */
                else {

                    /*
                     * Nothing to do.
                     *
                     * dp[j] already contains:
                     *
                     * dp[i-1][j]
                     *
                     * which means:
                     *
                     * Skip current source character.
                     */
                    dp[j] = dp[j];
                }
            }
        }

        /*
         * dp[n] contains:
         *
         * Number of ways to form
         * complete s2 using complete s1.
         */
        System.out.println("Answer = " + dp[n]);
    }
}