package Dp.MCM;

public class MatrixChainMultiplicationMemoization {

    /*
     * DP table
     *
     * dp[i][j] stores:
     * minimum multiplication cost
     * from matrix i to j
     */
    static int[][] dp;

    public static int solve(int[] arr,
                            int i,
                            int j){

        /*
         * Base Case:
         *
         * Single matrix
         * needs no multiplication
         */
        if(i >= j){
            return 0;
        }

        /*
         * If already computed
         * return stored answer
         */
        if(dp[i][j] != -1){
            return dp[i][j];
        }

        /*
         * Store minimum answer
         */
        int min = Integer.MAX_VALUE;

        /*
         * Try every partition
         */
        for(int k = i; k < j; k++){

            /*
             * Left partition cost
             */
            int left = solve(arr, i, k);

            /*
             * Right partition cost
             */
            int right = solve(arr, k+1, j);

            /*
             * Current multiplication cost
             *
             * Cost to multiply:
             *
             * Left matrix:
             * arr[i-1] × arr[k]
             *
             * Right matrix:
             * arr[k] × arr[j]
             */
            int currentCost =
                    arr[i-1] * arr[k] * arr[j];

            /*
             * Total cost
             */
            int totalCost =
                    left + right + currentCost;

            /*
             * Update minimum answer
             */
            min = Math.min(min, totalCost);
        }

        /*
         * Store answer in DP table
         */
        return dp[i][j] = min;
    }

    public static void main(String[] args) {

        /*
         * Matrix dimensions
         */
        int[] arr = {10,20,30,40};

        int n = arr.length;

        /*
         * DP table initialization
         */
        dp = new int[n+1][n+1];

        /*
         * Fill DP with -1
         */
        for(int i = 0; i <= n; i++){

            for(int j = 0; j <= n; j++){

                dp[i][j] = -1;
            }
        }

        /*
         * solve(1,n-1)
         *
         * because:
         * matrices start from index 1
         */
        int ans = solve(arr,1,n-1);

        System.out.println(ans);
    }
}