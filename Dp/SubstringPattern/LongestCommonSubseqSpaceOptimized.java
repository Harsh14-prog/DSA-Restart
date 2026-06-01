package Dp.SubstringPattern;

public class LongestCommonSubseqSpaceOptimized {
    public static void main(String[] args) {

        String s1 = "abcedf";
        String s2 = "abcdh";

        int n = s1.length();
        int m = s2.length();

        /*
         * prev[j] represents the previous row
         *
         * Initially all values are 0 because:
         *
         * LCS("", anything) = 0
         */
        int[] prev = new int[m + 1];

        // Traverse rows
        for (int i = 1; i <= n; i++) {

            /*
             * Current row being calculated.
             *
             * curr[j] will represent:
             * LCS(first i chars of s1,
             * first j chars of s2)
             */
            int[] curr = new int[m + 1];

            // Traverse columns
            for (int j = 1; j <= m; j++) {

                /*
                 * Actual characters:
                 *
                 * i corresponds to i-1 index in string
                 * j corresponds to j-1 index in string
                 */
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    /*
                     * Characters match
                     *
                     * Include this character in LCS.
                     *
                     * Formula:
                     * dp[i][j] = 1 + dp[i-1][j-1]
                     *
                     * Mapping:
                     * dp[i-1][j-1] -> prev[j-1]
                     */
                    curr[j] = 1 + prev[j - 1];

                } else {

                    /*
                     * Characters do not match.
                     *
                     * Option 1:
                     * Ignore current character of s1
                     * -> dp[i-1][j]
                     * -> prev[j]
                     *
                     * Option 2:
                     * Ignore current character of s2
                     * -> dp[i][j-1]
                     * -> curr[j-1]
                     *
                     * Take maximum.
                     */
                    curr[j] = Math.max(
                            prev[j], // top cell
                            curr[j - 1] // left cell
                    );
                }
            }

            /*
             * Current row becomes previous row
             * for the next iteration.
             */
            prev = curr;
        }

        /*
         * prev[m] contains the answer:
         *
         * LCS(full s1, full s2)
         */
        System.out.println("LCS Length = " + prev[m]);
    }
}
