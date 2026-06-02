package Dp.SubstringPattern;

public class EditDistanceRecursive {

    public static int solve(String s1, String s2, int i, int j) {

        /*
         * Meaning of solve(i,j):
         *
         * Minimum operations required
         * to convert first i characters of s1
         * into first j characters of s2.
         */

        /*
         * Source string becomes empty.
         *
         * Example:
         *
         * "" -> "abc"
         *
         * Need 3 insertions.
         *
         * Therefore answer = j.
         */
        if (i == 0) {
            return j;
        }

        /*
         * Target string becomes empty.
         *
         * Example:
         *
         * "abc" -> ""
         *
         * Need 3 deletions.
         *
         * Therefore answer = i.
         */
        if (j == 0) {
            return i;
        }

        /*
         * Last characters of current prefixes.
         */
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

            /*
             * Characters already match.
             *
             * No operation needed.
             *
             * Simply move both pointers.
             */
            return solve(s1, s2, i - 1, j - 1);
        }

        /*
         * Characters don't match.
         *
         * We have 3 choices.
         */

        /*
         * INSERT
         *
         * Insert s2[j-1] into s1.
         *
         * Current character of target
         * gets matched.
         *
         * So:
         *
         * i remains same
         * j decreases
         */
        int insertion =
                1 + solve(s1, s2,
                          i,
                          j - 1);

        /*
         * DELETE
         *
         * Delete current character
         * from s1.
         *
         * Therefore:
         *
         * i decreases
         * j remains same
         */
        int deletion =
                1 + solve(s1, s2,
                          i - 1,
                          j);

        /*
         * REPLACE
         *
         * Replace current character
         * of s1 with current character
         * of s2.
         *
         * Both characters are handled.
         *
         * Therefore:
         *
         * i decreases
         * j decreases
         */
        int replace =
                1 + solve(s1, s2,
                          i - 1,
                          j - 1);

        /*
         * We want minimum operations.
         *
         * Choose the best operation.
         */
        return Math.min(
                insertion,
                Math.min(deletion, replace)
        );
    }

    public static void main(String[] args) {

        String s1 = "horse";
        String s2 = "ros";

        int m = s1.length();
        int n = s2.length();

        int operations =
                solve(s1, s2, m, n);

        System.out.println(
                "Minimum Operations = "
                        + operations
        );
    }
}