package Dp.SubstringPattern;

public class PrintShortestCommonSupersequence {

    public static void main(String[] args) {

        /*
         * Input strings
         */
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
         * Step 1:
         * Build normal LCS table
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
                     * Take maximum from:
                     * top or left
                     */
                    dp[i][j] = Math.max(
                                        dp[i-1][j],
                                        dp[i][j-1]
                                   );
                }
            }
        }

        /*
         * Step 2:
         * Backtrack to build
         * Shortest Common Supersequence
         */
        int i = n;
        int j = m;

        StringBuilder sb = new StringBuilder();

        while(i > 0 && j > 0){

            /*
             * If characters match
             */
            if(s1.charAt(i-1) == s2.charAt(j-1)){

                /*
                 * Add character only once
                 * because it is common in both
                 */
                sb.append(s1.charAt(i-1));

                /*
                 * Move diagonally
                 */
                i--;
                j--;
            }

            /*
             * Characters do not match
             */
            else{

                /*
                 * Move toward larger (max) LCS value
                 */
                if(dp[i-1][j] > dp[i][j-1]){

                    /*
                     * Add character from s1
                     */
                    sb.append(s1.charAt(i-1));

                    /*
                     * Move upward
                     */
                    i--;
                }

                else{

                    /*
                     * Add character from s2
                     */
                    sb.append(s2.charAt(j-1));

                    /*
                     * Move left
                     */
                    j--;
                }
            }
        }

        /*
         * If characters remain in s1
         */
        while(i > 0){

            sb.append(s1.charAt(i-1));
            i--;
        }

        /*
         * If characters remain in s2
         */
        while(j > 0){

            sb.append(s2.charAt(j-1));
            j--;
        }

        /*
         * We built answer backwards
         * so reverse it
         */
        String ans = sb.reverse().toString();

        /*
         * Final Shortest Common Supersequence
         */
        System.out.println(ans);
    }
}