package Dp.SubstringPattern;

public class EditDistanceTabulation {

    public static void main(String[] args) {

        String s1 = "horse";
        String s2 = "ros";

        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        /*
         * First row
         *
         * "" -> target
         *
         * Need j insertions.
         */
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        /*
         * First column
         *
         * source -> ""
         *
         * Need i deletions.
         */
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        /*
         * Start from 1 because
         * row 0 and column 0
         * are already filled.
         */
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1];

                } 
                else {

                    int insert =
                            1 + dp[i][j - 1];

                    int delete =
                            1 + dp[i - 1][j];

                    int replace =
                            1 + dp[i - 1][j - 1];

                    dp[i][j] = Math.min(insert,Math.min(delete , replace));
                }
            }
        }

        System.out.println(dp[m][n]);
    }
}