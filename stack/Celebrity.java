
public class Celebrity {
    
    // "Elimination method" ---->>>>>>
    public static int findCelebrity(int[][] M) {

        int n = M.length;

        /*
         * ------------------------------------
         * STEP 1 : Find Potential Celebrity
         * ------------------------------------
         *
         * Start with two people:
         *
         * left = 0
         * right = n - 1
         *
         * At every comparison, one person
         * gets eliminated from becoming
         * a celebrity.
         */
        int left = 0;
        int right = n - 1;

        while (left < right) {

            /*
             * If left knows right:
             *
             * left ---> right
             *
             * Then left cannot be celebrity
             * because celebrity knows nobody.
             *
             * Eliminate left.
             */
            if (M[left][right] == 1) {
                left++;
            }

            /*
             * If left does NOT know right:
             *
             * left -X-> right
             *
             * Then right cannot be celebrity
             * because everybody must know
             * the celebrity.
             *
             * Eliminate right.
             */
            else {
                right--;
            }
        }

        /*
         * After elimination process,
         * only one candidate remains.
         *
         * Candidate may or may not
         * actually be a celebrity.
         */
        int candidate = left;

        /*
         * ------------------------------------
         * STEP 2 : Verify Candidate
         * ------------------------------------
         *
         * Celebrity Conditions:
         *
         * 1. Candidate knows nobody.
         * 2. Everybody knows candidate.
         */
        for (int i = 0; i < n; i++) {

            /*
             * Skip self-check.
             */
            if (i == candidate) {
                continue;
            }

            /*
             * Condition 1:
             * Candidate should know nobody.
             */
            if (M[candidate][i] == 1) {
                return -1;
            }

            /*
             * Condition 2:
             * Everybody should know candidate.
             */
            if (M[i][candidate] == 0) {
                return -1;
            }
        }

        /*
         * Candidate satisfies both conditions.
         */
        return candidate;
    }

        public static void main(String[] args) {

        int[][] M = {
                {0, 1, 1},
                {0, 0, 0},
                {0, 1, 0}
        };

        int ans = findCelebrity(M);

        System.out.println("Celebrity Index = " + ans);
    }
}
