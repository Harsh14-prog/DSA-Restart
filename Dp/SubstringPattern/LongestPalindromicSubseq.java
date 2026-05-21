package Dp.SubstringPattern;

public class LongestPalindromicSubseq {

    public static void main(String[] args) {

        /*
         * Original string
         */
        String s1 = "agbcba";

        int n = s1.length();

        /*
         * Step 1:
         * Reverse the string
         *
         * Longest Palindromic Subsequence (LPS)
         * problem can be converted into:
         *
         * LCS(originalString, reversedString)
         */
        StringBuilder sb = new StringBuilder();

        for(int i = n-1; i >= 0; i--){
            sb.append(s1.charAt(i));
        }

        String s2 = sb.toString();

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
         * First row and first column are 0
         * because empty string has no common subsequence
         *
         * Java initializes int arrays with 0 by default
         */

        /*
         * Step 2:
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
                     * and move diagonally
                     */
                    dp[i][j] = 1 + dp[i-1][j-1];
                }

                /*
                 * Characters do not match
                 */
                else{

                    /*
                     * Try:
                     * 1. Ignore char from s1
                     * 2. Ignore char from s2
                     *
                     * Take maximum
                     */
                    dp[i][j] = Math.max(
                                        dp[i][j-1],
                                        dp[i-1][j]
                                   );
                }
            }
        }

        /*
         * Final Answer:
         * Length of Longest Palindromic Subsequence
         */
        System.out.println(dp[n][m]);
    }
} 