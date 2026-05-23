package Dp.MCM;

public class MatrixChainMultiplication {

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
             * Current multiplication cost -- RESULTANT MULTIPLIED MATRIX
             */
            int cost =
                    arr[i-1] * arr[k] * arr[j];

            /*
             * Total cost
             */
            int tempAns =
                    left + right + cost;

            /*
             * Update minimum
             */
            min = Math.min(min, tempAns);
        }

        return min;
    }

    public static void main(String[] args) {

        int[] arr = {10,20,30,40};

        int n = arr.length;

        System.out.println(
                solve(arr,1,n-1)
        );
    }
}