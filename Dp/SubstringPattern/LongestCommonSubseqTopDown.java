package Dp.SubstringPattern;

public class LongestCommonSubseqTopDown {

    // dp[n][m] stores LCS length
    // for first n chars of s1
    // and first m chars of s2
    static int[][] dp;

    public static int LCS(String s1,
                          String s2,
                          int n,
                          int m){

        /*
         * Base Case:
         * If any string becomes empty
         */
        if(n == 0 || m == 0){
            return 0;
        }

        /*
         * Memoization check
         */
        if(dp[n][m] != -1){
            return dp[n][m];
        }

        /*
         * If characters match
         */
        if(s1.charAt(n-1) == s2.charAt(m-1)){

            /*
             * Include character
             * and move diagonally
             */
            return dp[n][m]
                    = 1 + LCS(s1,
                              s2,
                              n-1,
                              m-1);
        }

        /*
         * Characters do not match
         */
        else{

            /*
             * Try both possibilities
             * and take maximum
             */
            return dp[n][m]
                    = Math.max(
                        LCS(s1, s2, n, m-1),
                        LCS(s1, s2, n-1, m)
                      );
        }
    }

    public static void main(String[] args) {

        String s1 = "abcedf";
        String s2 = "abcdh";

        int n = s1.length();
        int m = s2.length();

        /*
         * DP table creation
         */
        dp = new int[n+1][m+1];

        /*
         * Initialize with -1
         * meaning: not computed yet
         */
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= m; j++){
                dp[i][j] = -1;
            }
        }

        int result = LCS(s1, s2, n, m);

        System.out.println(result);
    }
}