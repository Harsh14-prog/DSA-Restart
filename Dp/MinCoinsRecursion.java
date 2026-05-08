package Dp;

public class MinCoinsRecursion {

    static final int INF = Integer.MAX_VALUE - 1;

    public static int solve(int[] coins,
                            int sum,
                            int n){

        /*
         * Base Case:
         * Sum formed
         */
        if(sum == 0){
            return 0;
        }

        /*
         * No coins left
         */
        if(n == 0){
            return INF;
        }

        /*
         * If current coin can be used
         */
        if(coins[n-1] <= sum){

            /*
             * Take coin
             *
             * IMPORTANT:
             * Stay at same n
             * because coin can be reused
             */
            int take = 1 + solve(coins,
                                 sum - coins[n-1],
                                 n);

            /*
             * Skip coin
             */
            int skip = solve(coins,
                             sum,
                             n-1);

            /*
             * Take minimum
             */
            return Math.min(take, skip);
        }

        /*
         * Coin too large
         */
        else{
            return solve(coins,
                         sum,
                         n-1);
        }
    }

    public static void main(String[] args) {

        int[] coins = {1,2,3};

        int sum = 5;

        int result = solve(coins,
                           sum,
                           coins.length);

        /*
         * If impossible
         */
        if(result == INF){
            System.out.println(-1);
        }
        else{
            System.out.println(result);
        }
    }
}