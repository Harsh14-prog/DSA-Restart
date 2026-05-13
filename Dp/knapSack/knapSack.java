package Dp.knapSack;

// index based solution -->> “Start from beginning → move forward” --------------->>>>>
//------------------------------------------------------------------------

// public class knapSack {

//     public static int maxProfit(int[] wt, int[] val, int capacity, int index) {

//         // Base case
//         if (index == wt.length || capacity == 0) {
//             return 0;
//         }

//         // If item can be taken
//         if (wt[index] <= capacity) {

//             int take = val[index] + maxProfit(wt, val, capacity - wt[index], index + 1);

//             int skip = maxProfit(wt, val, capacity, index + 1);

//             return Math.max(take, skip);
//         }

//         // If item cannot be taken --“I cannot take this item” So I only explore the skip option
//         else {
//             return maxProfit(wt, val, capacity, index + 1);
//         }
//     }

//     public static void main(String[] args) {

//         int[] wt = {2, 3, 4};
//         int[] val = {4, 5, 6};
//         int capacity = 7;

//         int result = maxProfit(wt, val, capacity, 0);

//         System.out.println(result);
//     }
// }
//------------------------------------------------------------------------------

// n approch -->> “Start from full array i.e n → reduce size”
// moving from right to left unlike upper approch (left to right)
// in recursion initially we take full array then try to reduce i/p arr at each step
// -----------------------------------------------------------------------------

public class knapSack {

    public static int maxProfit(int[] wt, int[] val, int capacity, int n) {

        // Base case
        if (n == 0 || capacity == 0) {
            return 0;  // maxprofit 0
        }

        // If item can be taken
        if (wt[n-1] <= capacity) {

            int take = val[n-1] + maxProfit(wt, val, capacity - wt[n-1], n-1);

            int skip = maxProfit(wt, val, capacity, n-1);

            return Math.max(take, skip);
        }

        // If item cannot be taken --“I cannot take this item” So I only explore the skip option
        else {
            return maxProfit(wt, val, capacity, n-1);
        }
    }

    public static void main(String[] args) {

        int[] wt = {2, 3, 4};
        int[] val = {4, 5, 6};
        int capacity = 7;
        int n = wt.length ;

        int result = maxProfit(wt, val, capacity, n);

        System.out.println(result);
    }
}