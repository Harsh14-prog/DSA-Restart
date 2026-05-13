package Dp.knapSack;

// ------------ Equal Partition → Subset Sum (target = totalSum / 2) ---------------

public class EqualPartitionSumBottomUp {
public static void main(String[] args) {

    // Input array
    int[] nums = {1, 5, 11, 5};
    int n = nums.length;

    /*
     * Step 1: Calculate total sum of all elements
     */
    int totalSum = 0;
    for(int num : nums){
        totalSum += num;
    }

    /*
     * Step 2: If total sum is odd → cannot divide into two equal subsets
     */
    if(totalSum % 2 != 0){
        System.out.println(false);
        return;
    }

    /*
     * Step 3: Target sum becomes half of total sum
     * We just need to check if a subset with this sum exists
     */
    int target = totalSum / 2;

    /*
     * Step 4: Create DP table
     * dp[i][j] = can we form sum j using first i elements?
     */
    boolean[][] dp = new boolean[n+1][target+1];

    /*
     * Step 5: Base Case Initialization
     *
     * - Sum = 0 is always possible (by choosing no elements)
     * - So, dp[i][0] = true for all i
     */
    for(int i = 0; i <= n; i++){
        dp[i][0] = true;
    }

    /*
     * Note:
     * dp[0][j] = false (for j > 0) automatically,
     * because Java initializes boolean arrays with false.
     */

    /*
     * Step 6: Fill the DP table
     */
    for(int i = 1; i <= n; i++){              // i → number of items considered
        for(int j = 1; j <= target; j++){     // j → current target sum

            /*
             * If current element can be included
             */
            if(nums[i-1] <= j){

                /*
                 * Two choices:
                 * 1. Take the element → reduce target
                 * 2. Skip the element → keep target same
                 *
                 * If any one is true → answer is true
                 */
                dp[i][j] = dp[i-1][j - nums[i-1]] || dp[i-1][j];

            } else {
                /*
                 * If element is too large → cannot take it
                 * Only option is to skip it
                 */
                dp[i][j] = dp[i-1][j];
            }
        }
    }

    /*
     * Step 7: Final Answer
     * Can we form 'target' using all elements?
     */
    System.out.println(dp[n][target]);
}


}
