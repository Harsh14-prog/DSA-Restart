package Dp.SubstringPattern;

public class PrintLongCommanSubseq {

    public static void main(String[] args) {

        String s1 = "abcdgh";
        String s2 = "abedfhr";

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
        for(int i = 1 ; i <= n ; i++){

            for(int j = 1 ; j <= m ; j++){

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
         * Start backtracking
         * from bottom-right corner
         */
        int i = n;
        int j = m;

        StringBuilder sb = new StringBuilder();

        while(i > 0 && j > 0){

            /*
             * If characters match
               string indices are 0 to length-1 , but i = n and j = n
               s.charAt(i) will become s.charAt(n) which will be out of bound
             */
            if(s1.charAt(i-1) == s2.charAt(j-1)){

                /*
                 * Character is part of LCS
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
                 * Move toward larger value
                 */
                if(dp[i][j-1] > dp[i-1][j]){
                    j--;
                }
                else{
                    i--;
                }
            }
        }

        /*
         * We collected string in reverse order
         */
        String s = sb.reverse().toString();

        System.out.println(s);
    }
}