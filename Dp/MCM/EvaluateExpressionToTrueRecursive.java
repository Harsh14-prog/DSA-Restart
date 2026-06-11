package Dp.MCM;

public class EvaluateExpressionToTrueRecursive {

    /**
     * solve(i, j, isTrue)
     *
     * Returns the number of ways the expression from index i to j
     * can be parenthesized so that it evaluates to:
     *
     * isTrue = true  -> True
     * isTrue = false -> False
     */
    static int solve(String s, int i, int j, boolean isTrue) {

        // Invalid expression range
        // Example: solve(5,4)
        if (i > j)
            return 0;

        /*
         * Base Case:
         *
         * Only one symbol remains.
         *
         * Example:
         * T
         * or
         * F
         *
         * If we are asking for TRUE:
         * return 1 only if symbol is T
         *
         * If we are asking for FALSE:
         * return 1 only if symbol is F
         */
        if (i == j) {

            if (isTrue)
                return s.charAt(i) == 'T' ? 1 : 0;
            else
                return s.charAt(i) == 'F' ? 1 : 0;
        }

        // Stores total number of valid ways
        int ans = 0;

        /*
         * Try every operator as a partition point.
         *
         * Expression format:
         *
         * T | F & T ^ F
         * 0 1 2 3 4 5 6
         *
         * Operands are at even indices.
         * Operators are at odd indices.
         *
         * Therefore k moves by 2.
         */
        for (int k = i + 1; k <= j - 1; k += 2) {

            /*
             * Divide expression into:
             *
             * Left  = i ... k-1
             * Right = k+1 ... j
             *
             * Example:
             *
             * T | F&T
             *   ^
             *   k
             *
             * Left  = T
             * Right = F&T
             */

            // Number of ways left expression becomes TRUE
            int LT = solve(s, i, k - 1, true);

            // Number of ways left expression becomes FALSE
            int LF = solve(s, i, k - 1, false);

            // Number of ways right expression becomes TRUE
            int RT = solve(s, k + 1, j, true);

            // Number of ways right expression becomes FALSE
            int RF = solve(s, k + 1, j, false);

            // Current operator
            char op = s.charAt(k);

            /*
             * Operator = AND (&)
             *
             * Truth Table:
             *
             * T & T = T
             * T & F = F
             * F & T = F
             * F & F = F
             */
            if (op == '&') {

                // Need overall TRUE
                if (isTrue) {

                    /*
                     * Only one possibility:
                     *
                     * T & T = T
                     */
                    ans += LT * RT;
                }

                // Need overall FALSE
                else {

                    /*
                     * Possible combinations:
                     *
                     * T & F
                     * F & T
                     * F & F
                     */
                    ans += LT * RF
                         + LF * RT
                         + LF * RF;
                }
            }

            /*
             * Operator = OR (|)
             *
             * Truth Table:
             *
             * T | T = T
             * T | F = T
             * F | T = T
             * F | F = F
             */
            else if (op == '|') {

                // Need overall TRUE
                if (isTrue) {

                    /*
                     * Possible combinations:
                     *
                     * T | T
                     * T | F
                     * F | T
                     */
                    ans += LT * RT
                         + LT * RF
                         + LF * RT;
                }

                // Need overall FALSE
                else {

                    /*
                     * Only one possibility:
                     *
                     * F | F
                     */
                    ans += LF * RF;
                }
            }

            /*
             * Operator = XOR (^)
             *
             * Truth Table:
             *
             * T ^ T = F
             * T ^ F = T
             * F ^ T = T
             * F ^ F = F
             */
            else if (op == '^') {

                // Need overall TRUE
                if (isTrue) {

                    /*
                     * Possible combinations:
                     *
                     * T ^ F
                     * F ^ T
                     */
                    ans += LT * RF
                         + LF * RT;
                }

                // Need overall FALSE
                else {

                    /*
                     * Possible combinations:
                     *
                     * T ^ T
                     * F ^ F
                     */
                    ans += LT * RT
                         + LF * RF;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        // Example expression
        String s = "T|F&T";

        int n = s.length();

        // We want number of ways expression becomes TRUE
        boolean isTrue = true;

        int count = solve(s, 0, n - 1, isTrue);

        System.out.println("Number of ways = " + count);
    }
}