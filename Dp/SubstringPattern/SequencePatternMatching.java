package Dp.SubstringPattern;

public class SequencePatternMatching {

    public static void main(String[] args) {

        /*
         * Pattern string
         */
        String s1 = "AXY";

        /*
         * Main string
         */
        String s2 = "ADXCPY";

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
                 * Characters do not match
                 */
                else{

                    /*
                     * Take maximum from:
                     * left or top
                     */
                    dp[i][j] = Math.max(
                                        dp[i-1][j],
                                        dp[i][j-1]
                                   );
                }
            }
        }

        /*
         * Length of LCS
         */
        int lcs = dp[n][m];

        /*
         * If entire s1 matched
         * then s1 is subsequence of s2
         */
        if(lcs == n){

            System.out.println(true);
        }
        else{

            System.out.println(false);
        }
    }
}