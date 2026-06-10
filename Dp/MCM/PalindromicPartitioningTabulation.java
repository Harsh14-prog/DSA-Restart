package Dp.MCM;

public class PalindromicPartitioningTabulation {

    /**
     * Checks whether substring s[i...j]
     * is a palindrome or not.
     *
     * Example:
     *
     * "aa"  -> true
     * "aba" -> true
     * "ab"  -> false
     */
    public static boolean isPalindrome(String s, int i, int j) {

        while (i < j) {

            // Mismatch found
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {

        String s = "bababcbadcede";

        int n = s.length();

        /*
         * dp[i] =
         * Minimum palindrome partitions needed
         * from index i till the end.
         *
         * Example:
         *
         * String = "aab"
         *
         * dp[0] -> answer for "aab"
         * dp[1] -> answer for "ab"
         * dp[2] -> answer for "b"
         * dp[3] -> answer for ""
         */
        int[] dp = new int[n + 1];

        /*
         * Base Case
         *
         * dp[n] represents:
         *
         * Empty string
         *
         * No partitions needed.
         */
        dp[n] = 0;

        /*
         * Fill DP from right to left.
         *
         * Why?
         *
         * Because:
         *
         * dp[i] depends on dp[j+1]
         *
         * and j+1 is always on the right side.
         *
         * Therefore those answers must already
         * be computed before calculating dp[i].
         */
        for (int i = n - 1; i >= 0; i--) {

            /*
             * Stores minimum partitions
             * among all possible choices.
             */
            int minPartitions = Integer.MAX_VALUE;

            /*
             * Try every possible ending index
             * for current partition.
             *
             * Example:
             *
             * s = "aab"
             *
             * i = 0
             *
             * j = 0 -> "a"
             * j = 1 -> "aa"
             * j = 2 -> "aab"
             */
            for (int j = i; j < n; j++) {

                /*
                 * If current substring
                 * s[i...j] is palindrome,
                 * then we can choose it.
                 */
                if (isPalindrome(s, i, j)) {

                    /*
                     * 1 -> Current palindrome partition
                     *
                     * dp[j+1]
                     * -> Minimum partitions needed
                     *    for the remaining string.
                     *
                     * Example:
                     *
                     * aa | b
                     *
                     * Current partition = "aa"
                     * Remaining work = dp[2]
                     */
                    int partitions = 1 + dp[j + 1];

                    /*
                     * Keep minimum answer.
                     */
                    minPartitions =
                            Math.min(minPartitions,
                                    partitions);
                }
            }

            /*
             * Store final answer for index i.
             */
            dp[i] = minPartitions;
        }

        /*
         * dp[0] contains minimum number
         * of PALINDROME PARTITIONS.
         */
        int partitions = dp[0];

        /*
         * We need cuts, not partitions.
         *
         * Example:
         *
         * aa | b
         *
         * Partitions = 2
         * Cuts = 1
         *
         * Formula:
         *
         * Cuts = Partitions - 1
         */
        int cuts = partitions - 1;

        System.out.println("Minimum Cuts : " + cuts);
    }
}