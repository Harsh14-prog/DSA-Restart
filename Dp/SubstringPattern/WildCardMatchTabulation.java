package Dp.SubstringPattern;

public class WildCardMatchTabulation {

    public static void main(String[] args) {

        String s1 = "abdefcd";
        String s2 = "ab*cd";

        int m = s1.length();
        int n = s2.length();

        /*
         * dp[i][j] means:
         *
         * Can first i characters of s1
         * match first j characters of s2 ?
         *
         * Answer stored as:
         *
         * true  -> match possible
         * false -> match not possible
         */
        boolean[][] dp = new boolean[m + 1][n + 1];

        /*
         * BASE CASE 1
         *
         * Empty string matches
         * empty pattern.
         *
         * "" vs ""
         *
         * Match is possible.
         */
        dp[0][0] = true;

        /*
         * BASE CASE 2
         *
         * Non-empty string
         * cannot match
         * empty pattern.
         *
         * Example:
         *
         * "abc" vs ""
         *
         * Impossible.
         */
        for (int i = 1; i <= m; i++) {
            dp[i][0] = false;
        }

        /*
         * BASE CASE 3
         *
         * Empty string vs Pattern
         *
         * dp[0][j]
         *
         * Example:
         *
         * "" vs "*"
         * "" vs "**"
         * "" vs "***"
         *
         * These are possible because
         * '*' can represent empty string.
         *
         * ------------------------------------------------
         *
         * Why:
         *
         * dp[0][j] = dp[0][j-1]
         *
         * when current pattern character is '*'
         *
         * ------------------------------------------------
         *
         * Suppose:
         *
         * Pattern = "***"
         *
         * j = 1
         *
         * Pattern = "*"
         *
         * '*' can become ""
         *
         * Therefore:
         *
         * dp[0][1] = dp[0][0]
         *
         * true
         *
         * ------------------------------------------------
         *
         * j = 2
         *
         * Pattern = "**"
         *
         * Current '*' can again become ""
         *
         * Therefore:
         *
         * dp[0][2] = dp[0][1]
         *
         * true
         *
         * ------------------------------------------------
         *
         * Pattern = "*a*"
         *
         * When 'a' arrives:
         *
         * dp[0][2] becomes false
         *
         * because empty string cannot
         * match character 'a'.
         *
         * Then:
         *
         * dp[0][3]
         *
         * also remains false.
         *
         * ------------------------------------------------
         *
         * Simple Memory Trick:
         *
         * Empty string can match pattern
         * only if ALL characters seen so far
         * are '*'.
         */
        for (int j = 1; j <= n; j++) {

            if (s2.charAt(j - 1) == '*') {

                dp[0][j] = dp[0][j - 1];
            }
        }

        /*
         * Fill remaining table.
         */
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                /*
                 * Case 1:
                 *
                 * Characters are same.
                 *
                 * Example:
                 *
                 * a == a
                 *
                 * Move diagonally.
                 */
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1];
                }

                /*
                 * Case 2:
                 *
                 * Pattern contains '?'
                 *
                 * '?' matches exactly one character.
                 *
                 * Example:
                 *
                 * ? = a
                 *
                 * Move diagonally.
                 */
                else if (s2.charAt(j - 1) == '?') {

                    dp[i][j] = dp[i - 1][j - 1];
                }

                /*
                 * Case 3:
                 *
                 * Pattern contains '*'
                 */
                else if (s2.charAt(j - 1) == '*') {

                    /*
                     * Option 1:
                     *
                     * '*' matches empty string.
                     *
                     * Example:
                     *
                     * abc
                     *   *
                     *
                     * Ignore '*'
                     *
                     * dp[i][j-1]
                     */
                    boolean emptyMatch =
                            dp[i][j - 1];

                    /*
                     * Option 2:
                     *
                     * '*' matches current character.
                     *
                     * Example:
                     *
                     * abc
                     *   *
                     *
                     * '*' consumes c.
                     *
                     * dp[i-1][j]
                     */
                    boolean takeCharacter =
                            dp[i - 1][j];

                    /*
                     * If any option works,
                     * answer is true.
                     */
                    dp[i][j] =
                            emptyMatch
                                    ||
                                    takeCharacter;
                }

                /*
                 * Case 4:
                 *
                 * Characters different.
                 *
                 * Pattern is neither
                 * '?' nor '*'.
                 *
                 * Matching impossible.
                 */
                else {

                    dp[i][j] = false;
                }
            }
        }

        /*
         * Final Answer:
         *
         * Can complete string
         * match complete pattern?
         */
        System.out.println(dp[m][n]);
    }
}