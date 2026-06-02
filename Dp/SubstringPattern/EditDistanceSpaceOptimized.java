package Dp.SubstringPattern;

public class EditDistanceSpaceOptimized {

    public static void main(String[] args) {

        String s1 = "horse";
        String s2 = "ros";

        int m = s1.length();
        int n = s2.length();

        /*
         * prev represents previous row.
         *
         * Row 0 means:
         *
         * Convert "" into:
         *
         * ""
         * "r"
         * "ro"
         * "ros"
         *
         * Operations needed:
         *
         * 0 1 2 3
         */
        int[] prev = new int[n + 1];

        for (int j = 0; j <= n; j++) {
            prev[j] = j;
        }

        /*
         * Process each row.
         *
         * i represents first i characters of s1.
         */
        for (int i = 1; i <= m; i++) {

            /*
             * Current row being calculated.
             */
            int[] curr = new int[n + 1];

            /*
             * First column.
             *
             * Convert:
             *
             * first i characters of s1
             * into empty string.
             *
             * Need i deletions.
             *
             * Example:
             *
             * "h"     -> "" = 1
             * "ho"    -> "" = 2
             * "hor"   -> "" = 3
             */
            curr[0] = i;

            for (int j = 1; j <= n; j++) {

                /*
                 * Characters already match.
                 *
                 * No operation required.
                 *
                 * Take diagonal value.
                 */
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    curr[j] = prev[j - 1];

                } else {

                    /*
                     * INSERT
                     *
                     * Insert current target character.
                     *
                     * Left cell.
                     */
                    int insert = 1 + curr[j - 1];

                    /*
                     * DELETE
                     *
                     * Delete current source character.
                     *
                     * Top cell.
                     */
                    int delete = 1 + prev[j];

                    /*
                     * REPLACE
                     *
                     * Replace current source character.
                     *
                     * Diagonal cell.
                     */
                    int replace = 1 + prev[j - 1];

                    /*
                     * Choose minimum operation.
                     */
                    curr[j] = Math.min(
                            insert,
                            Math.min(delete, replace)
                    );
                }
            }

            /*
             * Entire current row completed.
             *
             * Make it previous row
             * for next iteration.
             */
            prev = curr;
        }

        /*
         * Last cell of last row.
         *
         * Minimum operations required
         * to convert complete s1 into complete s2.
         */
        System.out.println("Minimum Operations = " + prev[n]);
    }
}