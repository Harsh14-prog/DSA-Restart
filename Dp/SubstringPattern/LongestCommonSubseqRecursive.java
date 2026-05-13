package Dp.SubstringPattern;

public class LongestCommonSubseqRecursive {

    public static int LCS(String s1,
                          String s2,
                          int n,
                          int m){

        /*
         * Base Case:
         * If any 1 string becomes empty
         * → no common subsequence possible
         */
        if(n == 0 || m == 0){
            return 0;
        }

        /*
         * If last characters match
         */
        if(s1.charAt(n-1) == s2.charAt(m-1)){

            /*
             * Include this character in LCS
             * and move diagonally (backward)
             */
            return 1 + LCS(s1,
                           s2,
                           n-1,
                           m-1);
        }

        /*
         * If characters do not match
         */
        else{

            /*
             * Two choices:
             *
             * 1. Ignore character from s1
             * 2. Ignore character from s2
             *
             * Take maximum answer -- bex longest subseq
             */
            return Math.max(
                    LCS(s1, s2, n, m-1),
                    LCS(s1, s2, n-1, m)
            );
        }
    }

    public static void main(String[] args) {

        String s1 = "abcedf";
        String s2 = "abcdh";

        int n = s1.length();
        int m = s2.length();

        System.out.println(LCS(s1, s2, n, m));
    }
}