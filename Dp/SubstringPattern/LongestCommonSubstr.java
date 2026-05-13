package Dp.SubstringPattern;

public class LongestCommonSubstr {
    public static void main(String[] args) {

        String s1 = "abcdgh";
        String s2 = "abedfhr";

        int n = s1.length();
        int m = s2.length();

        /*
         * dp[i][j] =
         * length of longest common substring
         * ending at:
         *
         * s1[i-1]
         * s2[j-1]
         */
        int[][] dp = new int[n + 1][m + 1];

        /*
         * Stores maximum substring length found
         */
        int maxLen = 0;

        /*
         * Fill DP table
         */
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {

                /*
                 * If characters match
                 */
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    /*
                     * Continue substring diagonally
                     */
                    dp[i][j] = 1 + dp[i - 1][j - 1];

                    /*
                     * Update maximum length
                     */
                    maxLen = Math.max(maxLen, dp[i][j]);
                }

                /*
                 * Characters do not match
                 */
                else {

                    /*
                     * Substring continuity breaks , length will become 0
                       start counting from start for new substring
                     */
                    dp[i][j] = 0;
                }
            }
        }

        /*
         * Final Answer
         */
        System.out.println(maxLen);
    }
}
