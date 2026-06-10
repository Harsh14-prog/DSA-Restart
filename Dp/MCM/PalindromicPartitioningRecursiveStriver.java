package Dp.MCM;

public class PalindromicPartitioningRecursiveStriver {

    public static void main(String[] args) {

        String s = "aab";

        /*
         * solve(0) returns the minimum number of PALINDROME PARTITIONS
         *
         * Example:
         * aa | b
         *
         * Partitions = 2
         * Cuts = 1
         *
         * Therefore final answer = partitions - 1
         */
        int partitions = solve(s, 0);

        int cuts = partitions - 1;

        System.out.println("Minimum Cuts : " + cuts);
    }

    /**
     * Checks whether a string is palindrome or not.
     *
     * Example:
     * "aa"  -> true
     * "aba" -> true
     * "ab"  -> false
     */
    public static boolean isPalindrome(String s) {

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {

            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    /**
     * solve(i)
     *
     * Meaning:
     *
     * Return minimum palindrome partitions needed
     * starting from index i till the end.
     *
     * Example:
     *
     * s = "aab"
     *
     * solve(0) -> answer for entire string
     * solve(1) -> answer for "ab"
     * solve(2) -> answer for "b"
     */
    public static int solve(String s, int i) {

        /*
         * BASE CASE
         *
         * If i reaches the length of the string,
         * it means we have successfully partitioned
         * the entire string.
         *
         * Nothing is left to process.
         *
         * Return 0 partitions.
         */
        if (i == s.length()) {
            return 0;
        }

        /*
         * This StringBuilder is used to generate
         * all substrings starting from index i.
         *
         * Example:
         *
         * i = 0
         * s = "aab"
         *
         * j=0 -> "a"
         * j=1 -> "aa"
         * j=2 -> "aab"
         */
        StringBuilder sb = new StringBuilder();

        /*
         * Stores minimum partitions among all choices.
         */
        int minPartitions = Integer.MAX_VALUE;

        /*
         * j represents the ENDING index
         * of the current partition.
         *
         * i remains fixed because
         * we are exploring all substrings
         * that START from i.
         */
        for (int j = i; j < s.length(); j++) {

            /*
             * Extend current substring.
             *
             * Example:
             *
             * j=0 -> "a"
             * j=1 -> "aa"
             * j=2 -> "aab"
             */
            sb.append(s.charAt(j));

            /*
             * If current substring is palindrome,
             * we can choose it as one partition.
             */
            if (isPalindrome(sb.toString())) {

                /*
                 * 1 represents the current partition.
                 *
                 * solve(j+1) calculates partitions
                 * required for the remaining string.
                 *
                 * Example:
                 *
                 * "aa" | "b"
                 *
                 * Current partition = "aa"
                 * Remaining work = solve(2)
                 */
                int partitions =
                        1 + solve(s, j + 1);

                /*
                 * Keep the minimum answer
                 * among all valid palindrome choices.
                 */
                minPartitions =
                        Math.min(minPartitions, partitions);
            }
        }

        return minPartitions;
    }
}