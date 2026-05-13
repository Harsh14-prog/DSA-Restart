package Dp.knapSack;

public class CountSubsetWithGivenDifference {
public static void main(String[] args) {

    // Input array
    int[] nums = {1, 1, 2, 3};
    int diff = 1;
    int n = nums.length;

    /*
     * Step 1: Calculate total sum of array
     */
    int totalSum = 0;
    for(int num : nums){
        totalSum += num;
    }

    /*
     * Step 2: Validate the problem
     *
     * We use the formula:
     *   s1 = (totalSum + diff) / 2
     *
     * If (totalSum + diff) is odd → not possible
     */
    if((totalSum + diff) % 2 != 0){
        System.out.println(0);
        return;
    }

    /*
     * Step 3: Convert problem to subset sum
     */
    int target = (totalSum + diff) / 2;

    /*
     * Step 4: Create DP table
     *
     * dp[i][j] = number of ways to form sum j
     *            using first i elements
     */
    int[][] dp = new int[n+1][target+1];

    /*
     * Step 5: Base Case
     *
     * There is exactly 1 way to form sum = 0:
     * → by taking no elements (empty subset)
     */
    for(int i = 0; i <= n; i++){
        dp[i][0] = 1;
    }

    /*
     * Step 6: Fill the DP table
     */
    for(int i = 1; i <= n; i++){              // i → elements considered
        for(int j = 0; j <= target; j++){     // j → current sum

            /*
             * Case 1: If current element can be taken
             */
            if(nums[i-1] <= j){

                /*
                 * Two choices:
                 * 1. Take current element
                 * 2. Skip current element
                 *
                 * Total ways = take + skip
                 */
                dp[i][j] = dp[i-1][j - nums[i-1]] + dp[i-1][j];

            } else {
                /*
                 * Case 2: Cannot take element
                 * Only option: skip it
                 */
                dp[i][j] = dp[i-1][j];
            }
        }
    }

    /*
     * Step 7: Final Answer
     *
     * Number of subsets with required difference
     */
    System.out.println(dp[n][target]);
}
}
