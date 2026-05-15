package Dp.SubstringPattern;

public class ShortestCommonSuperSequence {

    public static void main(String[] args) {

        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

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
         * Build LCS table
         */
        for(int i = 1; i <= n; i++){

            for(int j = 1; j <= m; j++){

                /*
                 * If characters match
                 */
                if(s1.charAt(i-1) == s2.charAt(j-1)){

                    /*
                     * Include character
                     */
                    dp[i][j] = 1 + dp[i-1][j-1];
                }

                /*
                 * Characters don't match
                 */
                else{

                    /*
                     * Take maximum from:
                     * left or top
                     */
                    dp[i][j] = Math.max(
                                        dp[i][j-1],
                                        dp[i-1][j]
                                   );
                }
            }
        }

        /*
         * Length of LCS
         */
        int lcs = dp[n][m];

        /*
         * Formula:
         *
         * SCS Length =
         * n + m - LCS length
         */
        int shortestSuperSeq = n + m - lcs;

        System.out.println(shortestSuperSeq);
    }
}