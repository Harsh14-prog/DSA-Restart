package Dp.knapSack;

public class RodCuttingWithRecursion {

    public static int solve(int[] Length,
                            int[] price,
                            int len,
                            int n){

        /*
         * Base Case:
         * No rod left OR no cut sizes left
         */
        if(n == 0 || len == 0){
            return 0;
        }

        /*
         * If current cut length can be used
         */
        if(Length[n-1] <= len){

            /*
             * Choice 1:
             * Take current cut
             *
             * IMPORTANT:
             * Stay at same n
             * because same cut can be reused
             */
            int take = price[n-1]
                     + solve(Length,
                             price,
                             len - Length[n-1],
                             n);

            /*
             * Choice 2:
             * Skip current cut
             */
            int skip = solve(Length,
                             price,
                             len,
                             n-1);

            /*
             * Return maximum profit
             */
            return Math.max(take , skip);
        }

        /*
         * Current cut too large
         */
        else{
            return solve(Length,
                         price,
                         len,
                         n-1);
        }
    }

    public static void main(String[] args) {

        int[] Length = {1,2,3,4,5,6,7,8};
        int[] price = {1,5,8,9,10,17,17,20};

        int len = 8;
        int n = Length.length;

        int maxProfit = solve(Length , price , len , n);

        System.out.println(maxProfit);
    }
}