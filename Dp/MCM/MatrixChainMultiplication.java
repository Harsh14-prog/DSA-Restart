package Dp.MCM;

public class MatrixChainMultiplication {

    /*
     * solve(i, j)
     *
     * Returns the minimum number of scalar multiplications
     * required to multiply matrices from Ai to Aj.
     *
     * Example:
     * nums = {10,20,30,40,50}
     *
     * Matrices:
     * A1 = 10x20
     * A2 = 20x30
     * A3 = 30x40
     * A4 = 40x50
     *
     * solve(1,4) means:
     * Find minimum cost to multiply A1,A2,A3,A4
     */
    public static int solve(int[] nums, int i, int j) {

        /*
         * Base Case:
         *
         * If there is only one matrix left,
         * no multiplication is needed.
         *
         * Example:
         * solve(2,2) => only A2 exists
         *
         * Cost = 0
         */
        if (i == j) {
            return 0;
        }

        // Stores the minimum cost among all possible partitions.
        int mini = Integer.MAX_VALUE;

        /*
         * Try every possible partition point k.
         *
         * Example:
         * solve(1,4)
         *
         * A1 A2 A3 A4
         *
         * Possible cuts:
         *
         * k=1
         * (A1) | (A2 A3 A4)
         *
         * k=2
         * (A1 A2) | (A3 A4)
         *
         * k=3
         * (A1 A2 A3) | (A4)
         */
        for (int k = i ; k <= j - 1 ; k++) {

            /*
             * Cost of current partition:
             *
             * Left Cost
             * = solve(i, k)
             *
             * Right Cost
             * = solve(k+1, j)
             *
             * Final Multiplication Cost (resultant)
             * = nums[i-1] * nums[k] * nums[j]
             *
             * Why?
             *
             * Left subchain dimensions:
             * nums[i-1] x nums[k]
             *
             * Right subchain dimensions:
             * nums[k] x nums[j]
             *
             * Multiplying them costs:
             * rows × common_dimension × columns
             *
             * => nums[i-1] * nums[k] * nums[j]
             */
            int NoOfOperations =
                    nums[i - 1] * nums[k] * nums[j]
                    + solve(nums, i, k)
                    + solve(nums, k + 1, j);

            // Keep the minimum among all partitions.
            mini = Math.min(mini, NoOfOperations);
        }

        return mini;
    }

    public static void main(String[] args) {

        int[] nums = {10, 20, 30, 40, 50};

        /*
         * Number of matrices = nums.length - 1
         *
         * nums = {10,20,30,40,50}
         *
         * Matrices:
         * A1 = 10x20
         * A2 = 20x30
         * A3 = 30x40
         * A4 = 40x50
         *
         * We need answer for A1 to A4
         * => solve(1,4)
         */
        int n = nums.length;

        int operations = solve(nums, 1, n - 1);

        System.out.println("Minimum Operations = " + operations);
    }
}