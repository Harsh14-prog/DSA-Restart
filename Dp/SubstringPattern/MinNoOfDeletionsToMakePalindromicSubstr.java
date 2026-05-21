package Dp.SubstringPattern;

public class MinNoOfDeletionsToMakePalindromicSubstr {

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
         * can be found using:
         *
         * LCS(originalString, reversedString)
         */
        StringBuilder sb = new StringBuilder();

        for(int i = n-1; i >= 0; i--){
            sb.append(s1.charAt(i));
        }

        /*
         * Reversed string
         */
        String s2 = sb.toString();

        /*
         * dp[i][j] =
         * length of LCS between:
         *
         * first i chars of s1
         * first j chars of s2
         */
        int[][] dp = new int[n+1][n+1];

        /*
         * Base Case:
         *
         * First row and first column remain 0
         * because:
         *
         * Empty string has no common subsequence
         */

        /*
         * Step 2:
         * Build LCS table
         */
        for(int i = 1; i <= n; i++){

            for(int j = 1; j <= n; j++){

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
         * Length of Longest Palindromic Subsequence
         */
        int lps = dp[n][n];

        /*
         * Minimum deletions needed:
         *
         * String Length - LPS Length
         */
        int minDeletion = n - lps;

        /*
         * Final Answer
         */
        System.out.println(minDeletion);
    }
}