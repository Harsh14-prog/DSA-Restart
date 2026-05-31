package Dp.knapSack;

public class knapSackWithSpaceOptimised {

    public static void main(String[] args) {

        // Weight of each item
        int[] wt = {1, 2, 4, 5};

        // Value of each item
        int[] val = {5, 4, 8, 6};

        // Maximum capacity of knapsack
        int W = 5;

        // Number of items
        int n = wt.length;

        /*
         dp[cap]

         Meaning:
         Maximum value we can obtain
         for a knapsack capacity = cap.

         Since we are using 1D DP,
         this array will keep getting updated
         for each item.
        */
        int[] dp = new int[W + 1];

        /*
         ==========================
         BASE CASE
         ==========================

         Consider only the first item.

         wt[0] = 1
         val[0] = 5

         If capacity >= wt[0],
         we can take the first item.

         Therefore for every capacity
         from wt[0] to W,
         answer becomes val[0].

         Capacities smaller than wt[0]
         cannot hold the item,
         so answer remains 0.

         Java automatically initializes
         array elements to 0.
        */
        for (int cap = wt[0]; cap <= W; cap++) {
            dp[cap] = val[0];
        }

        /*
         Process remaining items one by one.
        */
        for (int index = 1; index < n; index++) {

            /*
             ==========================
             WHY RIGHT -> LEFT ?
             ==========================

             Normal tabulation uses:

             dp[index][cap]

             Transition:

             dp[index][cap] =
             max(
                 dp[index-1][cap],                        // not pick
                 val[index] + dp[index-1][cap-wt[index]] // pick
             )

             Notice both values come from
             PREVIOUS ROW.

             Since we are using only one array,
             we must make sure that while computing
             current answers we do not accidentally
             use values already updated in the same row.

             Therefore we move from RIGHT -> LEFT.

             This guarantees that:

             dp[cap - wt[index]]

             still contains the previous row value.

             If we move LEFT -> RIGHT,
             current item could reuse itself
             multiple times.

             That would become
             UNBOUNDED KNAPSACK,
             not 0/1 KNAPSACK.
            */
            for (int cap = W; cap >= wt[index]; cap--) {

                /*
                 Not Pick current item.

                 dp[cap] currently stores
                 the answer from previous row.
                */
                int notPick = dp[cap];

                /*
                 Pick current item.

                 We add current item's value
                 and use the remaining capacity.

                 Since we are moving
                 RIGHT -> LEFT,

                 dp[cap - wt[index]]

                 still belongs to the previous row,
                 which is exactly what 0/1 Knapsack needs.
                */
                int pick =
                        val[index]
                        + dp[cap - wt[index]];

                /*
                 Store the best answer.
                */
                dp[cap] = Math.max(pick, notPick);
            }

            /*
             WHY LOOP TILL wt[index] ?

             Suppose:

             wt[index] = 4

             Capacities:

             0, 1, 2, 3

             cannot hold this item.

             Therefore pick operation
             is impossible.

             Those answers will remain unchanged.

             So instead of looping till 0,
             we directly stop at wt[index].

             This avoids unnecessary iterations.
            */
        }

        /*
         Final answer:

         Maximum value obtainable
         with capacity W.
        */
        System.out.println(dp[W]);
    }
}