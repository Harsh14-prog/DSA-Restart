package slidingWindow;

public class maxSumSubArrOfSizeK {
  public static void main(String[] args) {

    int[] nums = { 2, 1, 5, 1, 3, 2 };

    int k = 3;

    int l = 0;

    // Current window sum
    int sum = 0;

    // Stores answer
    int maxSum = 0;

    for (int h = 0; h < nums.length; h++) {

      // Include current element
      sum += nums[h];

      // Window size reached k
      if (h - l + 1 == k) {

        // Update answer
        maxSum = Math.max(maxSum, sum);

        // Slide window
        //
        // Remove lmost element
        sum -= nums[l];

        // Move l pointer
        l++;
      }
    }

    System.out.println(maxSum);

  }
}
