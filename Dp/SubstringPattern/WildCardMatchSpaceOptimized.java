package Dp.SubstringPattern;

public class WildCardMatchSpaceOptimized {

    public static void main(String[] args) {

        String s1 = "abdefcd";
        String s2 = "ab*cd";

        int m = s1.length();
        int n = s2.length();

        /*
         * prev represents previous row.
         */
        boolean[] prev = new boolean[n + 1];

        /*
         * dp[0][0]
         *
         * Empty string matches
         * empty pattern.
         */
        prev[0] = true;

        /*
         * First row:
         *
         * Empty string vs pattern.
         */
        for(int j = 1; j <= n; j++) {

            if(s2.charAt(j - 1) == '*') {

                prev[j] = prev[j - 1];
            }
        }

        /*
         * Process remaining rows.
         */
        for(int i = 1; i <= m; i++) {

            boolean[] curr = new boolean[n + 1];

            /*
             * dp[i][0]
             *
             * Non-empty string
             * cannot match
             * empty pattern.
             */
            curr[0] = false;

            for(int j = 1; j <= n; j++) {

                /*
                 * Characters match
                 * OR pattern contains '?'.
                 */
                if(s1.charAt(i - 1) == s2.charAt(j - 1)
                        || s2.charAt(j - 1) == '?') {

                    curr[j] = prev[j - 1];
                }

                /*
                 * Pattern contains '*'.
                 */
                else if(s2.charAt(j - 1) == '*') {

                    /*
                     * '*' matches empty string
                     * OR
                     * '*' consumes current character.
                     */
                    curr[j] =
                            curr[j - 1]
                                    ||
                            prev[j];
                }

                /*
                 * No match.
                 */
                else {

                    curr[j] = false;
                }
            }

            /*
             * Current row becomes
             * previous row.
             */
            prev = curr;
        }

        System.out.println(prev[n]);
    }
}