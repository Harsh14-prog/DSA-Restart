package Dp.SubstringPattern;

public class WildCardMatchingRecursive {

    public static boolean solve(String s1, String s2, int i, int j) {

        /*
         * Meaning:
         *
         * Can first i characters of s1
         * match first j characters of s2 ?
         */

        /*
         * Both string and pattern finished.
         *
         * Example:
         *
         * "" vs ""
         *
         * Perfect match.
         */
        if (i == 0 && j == 0) {
            return true;
        }

        /*
         * Pattern finished
         * but string still remains.
         *
         * Example:
         *
         * "abc" vs ""
         *
         * Impossible.
         */
        if (j == 0 && i > 0) {
            return false;
        }

        /*
         * String finished
         * but pattern still remains.
         *
         * Example:
         *
         * "" vs "***"
         *
         * Possible only if all remaining
         * pattern characters are '*'.
         */
        if (i == 0 && j > 0) {

            for (int k = 0; k < j; k++) {

                if (s2.charAt(k) != '*') {
                    return false;
                }
            }

            return true;
        }

        /*
         * Characters match
         *
         * OR
         *
         * Pattern contains '?'
         *
         * '?' matches exactly one character.
         *
         * Move both pointers.
         */
        if (s1.charAt(i - 1) == s2.charAt(j - 1)
                || s2.charAt(j - 1) == '?') {

            return solve(s1, s2,
                    i - 1,
                    j - 1);
        }

        /*
         * Pattern contains '*'
         */
        if (s2.charAt(j - 1) == '*') {

            /*
             * Choice 1:
             *
             * '*' matches empty string.
             *
             * Ignore '*'.
             *
             * Move pattern pointer.
             */
            boolean emptyMatch =
                    solve(s1, s2,
                            i,
                            j - 1);

            /*
             * Choice 2:
             *
             * '*' matches current character.
             *
             * Consume one character from string.
             *
             * Keep '*' because it may match
             * more characters hence "j" not moved.
             */
            boolean takeCharacter =
                    solve(s1, s2,
                            i - 1,
                            j);

            /*
             * If any path succeeds,
             * answer is true.
             */
            return emptyMatch || takeCharacter;
        }

        /*
         * Characters don't match.
         *
         * Pattern is neither '?'
         * nor '*'.
         *
         * Therefore matching is impossible.
         */
        return false;
    }

    public static void main(String[] args) {

        String s1 = "abdefcd";
        String s2 = "ab*cd";

        int m = s1.length();
        int n = s2.length();

        boolean ans = solve(s1, s2, m, n);

        System.out.println(ans);
    }
}