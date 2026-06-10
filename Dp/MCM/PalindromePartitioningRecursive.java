package Dp.MCM;

public class PalindromePartitioningRecursive {


    /**
     * Checks whether the substring s[i...j] is a palindrome.
     *
     * Example:
     * s = "racecar"
     * isPalindrome(0,6) -> true
     */
    public static boolean isPalindrome(String s, int i, int j) {

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
     * Returns the minimum number of cuts required to partition
     * the substring s[i...j] into palindrome substrings.
     *
     * This follows the Matrix Chain Multiplication (MCM) pattern:
     *
     * 1. Try every possible partition point k.
     * 2. Recursively solve left and right parts.
     * 3. Add 1 for the current cut.
     * 4. Return the minimum among all possible cuts.
     */
    public static int solve(String s, int i, int j) {

        /*
         * BASE CASE 1:
         *
         * If the substring contains
         * 0 or 1 character,
         * no cut is required.
         */
        if (i >= j) {
            return 0;
        }

        /*
         * BASE CASE 2:
         *
         * If the entire substring is already a palindrome,
         * no partition is needed.
         *
         * Example:
         * "aa"
         * "aba"
         * "racecar"
         */
        if (isPalindrome(s, i, j)) {
            return 0;
        }

        // Stores the minimum cuts among all partitions
        int minCuts = Integer.MAX_VALUE;

        /*
         * Try every possible partition point.
         *
         * Example:
         * s = "aab"
         *
         * k = 0
         * a | ab
         *
         * k = 1
         * aa | b
         */
        for (int k = i; k < j; k++) {

            /*
             * Solve left part
             */
            int left = solve(s, i, k);

            /*
             * Solve right part
             */
            int right = solve(s, k + 1, j);

            /*
             * +1 because we are making one cut at position k
             */
            int totalCuts = 1 + left + right;

            /*
             * Update minimum answer
             */
            minCuts = Math.min(minCuts, totalCuts);
        }

        return minCuts;
    }

        public static void main(String[] args) {

        String s = "aab";

        int minCuts = solve(s, 0, s.length() - 1);

        System.out.println("Minimum Cuts Required : " + minCuts);
    }
}