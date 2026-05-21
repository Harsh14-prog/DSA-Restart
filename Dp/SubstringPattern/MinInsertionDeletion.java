package Dp.SubstringPattern;

public class MinInsertionDeletion {

    public static void main(String[] args) {

        String s1 = "heap";
        String s2 = "pea";

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

                    dp[i][j] = 1 + dp[i-1][j-1];
                }

                /*
                 * Characters don't match
                 */
                else{

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
         * Characters not in LCS
         * must be deleted from s1
         */
        int deletion = n - lcs;

        /*
         * Characters missing in s1
         * must be inserted
         */
        int insertion = m - lcs;

        System.out.println("Deletion: " + deletion);
        System.out.println("Insertion: " + insertion);
    }
}