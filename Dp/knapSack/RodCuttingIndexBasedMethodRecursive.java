package Dp.knapSack;

public class RodCuttingIndexBasedMethodRecursive {

    public static int solve(int[] price,
                            int[] length,
                            int rodLength,
                            int index){

        /*
         * Rod completely used.
         */
        if(rodLength == 0){
            return 0;
        }

        /*
         * Only length 1 piece remains.
         *
         * Since length[0] = 1, always
         * we can always fill the rod.
         * no need to check divisiblity like rodLength % Length[index]
         */
        if(index == 0){
            return rodLength * price[0];
        }

        // Skip current piece
        int skip =
                solve(price,
                      length,
                      rodLength,
                      index - 1);

        // Take current piece
        int take = Integer.MIN_VALUE;

        if(length[index] <= rodLength){

            take =
                    price[index]
                    +
                    solve(price,
                          length,
                          rodLength - length[index],
                          index);
        }

        return Math.max(take, skip);
    }

    public static void main(String[] args) {

        int[] price = {2,5,7,8,10};

        int[] length = {1,2,3,4,5};

        int rodLength = 4;

        System.out.println(
                solve(price,
                      length,
                      rodLength,
                      length.length - 1)
        );
    }
}