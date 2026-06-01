package Dp.SubstringPattern;

public class distinctSubsequenceRecursive {

    public static int solve(String s1, String s2, int i, int j) {

        // Empty target can always be formed
        // in exactly one way.
        if (j == 0) {
            return 1;
        }

        // Source exhausted but target remains.
        if (i == 0) {
            return 0;
        }

        // Characters match
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

            // Take current character
            // +
            // Skip current character
            return solve(s1, s2, i - 1, j - 1)
                    + solve(s1, s2, i - 1, j);
        }

        // Characters don't match
        // Must skip current source character
        return solve(s1, s2, i - 1, j);
    }

    public static void main(String[] args) {

        String s1 = "bagbag";
        String s2 = "bag";

        int m = s1.length();
        int n = s2.length();

        int count = solve(s1, s2, m, n);

        System.out.println(count);
    }
}
