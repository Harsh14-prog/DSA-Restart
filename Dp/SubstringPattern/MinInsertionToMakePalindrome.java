package Dp.SubstringPattern;

public class MinInsertionToMakePalindrome {

    public static void main(String[] args) {

        /*
         * Original string
         */
        String s1 = "agbcba";

        int n = s1.length();

        /*
         * Step 1:
         * Reverse the string
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
         * Step 2:
         * Build LCS table
         *
         * LCS(original, reverse)
         * = Longest Palindromic Subsequence
         */
        for(int i = 1; i <= n; i++){

            for(int j = 1; j <= n; j++){

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
         * Length of Longest Palindromic Subsequence
         */
        int lps = dp[n][n];

        /*
         * Minimum insertions needed
         */
        int minInsertion = n - lps;

        /*
         * Final Answer
         */
        System.out.println(minInsertion);
    }
}