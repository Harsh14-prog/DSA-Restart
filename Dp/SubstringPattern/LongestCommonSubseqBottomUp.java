package Dp.SubstringPattern;

public class LongestCommonSubseqBottomUp {

    public static void main(String[] args) {

        String s1 = "abcedf";
        String s2 = "abcdh";

        int n = s1.length();
        int m = s2.length();

        /*
         * dp[i][j] =
         * length of LCS between:
         *
         * first i chars of s1
         * first j chars of s2
         */
        int[][] dp = new int[n+1][m+1];

        /*
         * Base Case:
         *
         * If any string becomes empty
         * → LCS length = 0
         *
         * Java initializes arrays with 0 by default
         * so 1st row and 1st column handled auto for initialization to 0
         */

        /*
         * Fill DP table
         */
        for(int i = 1; i <= n; i++){

            for(int j = 1; j <= m; j++){

                /*
                 * If characters match
                 */
                if(s1.charAt(i-1) == s2.charAt(j-1)){

                    /*
                     * Include character in LCS
                     * and move diagonally
                     */
                    dp[i][j] = 1 + dp[i-1][j-1];
                }

                /*
                 * Characters do not match
                 */
                else{

                    /*
                     * Two choices:
                     *
                     * 1. Ignore char from s1
                     * 2. Ignore char from s2
                     *
                     * Take maximum
                     */
                    dp[i][j] = Math.max(
                                    dp[i-1][j],
                                    dp[i][j-1]
                               );
                }
            }
        }

        /*
         * Final Answer:
         * LCS length of full strings
         */
        System.out.println(dp[n][m]);
    }
}