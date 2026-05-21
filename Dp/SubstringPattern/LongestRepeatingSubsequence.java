package Dp.SubstringPattern;

public class LongestRepeatingSubsequence {

    public static void main(String[] args) {

        /*
         * Input string
         */
        String s = "aabebcdd";

        int n = s.length();

        /*
         * dp[i][j] =
         * length of Longest Repeating Subsequence
         *
         * Similar to LCS
         */
        int[][] dp = new int[n+1][n+1];

        /*
         * Build DP table
         */
        for(int i = 1; i <= n; i++){

            for(int j = 1; j <= n; j++){

                /*
                 * Characters match
                 * AND indices must be different
                 */
                if(s.charAt(i-1) == s.charAt(j-1)
                        && i != j){

                    /*
                     * Include character
                     */
                    dp[i][j] = 1 + dp[i-1][j-1];
                }

                /*
                 * Characters don't match
                 * OR same index
                 */
                else{

                    /*
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
         * Final Answer
         */
        System.out.println(dp[n][n]);
    }
}