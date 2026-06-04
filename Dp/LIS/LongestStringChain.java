package Dp.LIS;

import java.util.Arrays;

public class LongestStringChain {

    /*
     * checkPossible(longer, shorter)
     *
     * Purpose:
     * --------
     * Checks whether 'shorter' can become 'longer'
     * by inserting exactly one character.
     *
     * Example:
     * --------
     * shorter = "ba"
     * longer  = "bda"
     *
     * Insert 'd' into "ba"
     * -> "bda"
     *
     * Return true.
     *
     * This function acts as the comparison condition
     * of LIS.
     */
    public static boolean checkPossible(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();

        /*
         * For a valid predecessor,
         * longer string must have exactly
         * one extra character.
         */
        if (m - n != 1)
            return false;

        int i = 0; // Pointer for longer string
        int j = 0; // Pointer for shorter string

        /*
         * Two Pointer Technique
         *
         * Goal:
         * Match all characters of shorter string
         * inside longer string.
         */
        while (i < m && j < n) {

            /*
             * Characters match.
             *
             * Move both pointers.
             */
            if (s1.charAt(i) == s2.charAt(j)) {

                i++;
                j++;
            }
            else {

                /*
                 * Mismatch found.
                 *
                 * Since longer string contains
                 * one extra character,
                 * skip that character.
                 *
                 * Move only i.
                 */
                i++;
            }
        }

        /*
         * If all characters of shorter string
         * were successfully matched,
         * then s2 is a valid predecessor of s1.
         */
        return (i == m && j == n);
    }

    public static void main(String[] args) {

        String[] words = {"a", "b", "ba", "bca", "bda", "bdca"};

        int n = words.length;

        /*
         * Sort words by length.
         *
         * Why?
         * ----
         * A predecessor must always be shorter.
         *
         * After sorting:
         *
         * a
         * b
         * ba
         * bca
         * bda
         * bdca
         *
         * This allows us to apply LIS pattern.
         */
        Arrays.sort(words,
                (a, b) -> a.length() - b.length());

        /*
         * dp[i]
         *
         * Length of longest string chain
         * ending at word i.
         *
         * Every word itself forms a chain
         * of length 1.
         */
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        /*
         * Stores final answer.
         */
        int maxLen = 1;

        /*
         * LIS Pattern
         *
         * Treat each word as the ending word
         * of a possible chain.
         */
        for (int i = 0; i < n; i++) {

            String currentWord = words[i];

            /*
             * Check all previous words.
             */
            for (int j = 0; j < i; j++) {

                String previousWord = words[j];

                /*
                 * Can previousWord become
                 * currentWord by adding
                 * exactly one character?
                 */
                if (checkPossible(currentWord, previousWord)) {

                    /*
                     * LIS Transition
                     *
                     * If chain ending at j
                     * can be extended using i,
                     * update dp[i].
                     *
                     * Formula:
                     *
                     * dp[i] = max(dp[i],
                     *             dp[j] + 1)
                     */
                    if (dp[j] + 1 > dp[i]) {

                        dp[i] = dp[j] + 1;
                    }
                }
            }

            /*
             * Track maximum chain length found.
             */
            maxLen = Math.max(maxLen, dp[i]);
        }

        System.out.println("Longest String Chain Length = " + maxLen);
    }
}