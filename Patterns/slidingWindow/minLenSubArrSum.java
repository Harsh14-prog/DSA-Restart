package Patterns.slidingWindow;

public class minLenSubArrSum {

    public static int minSubArrayLen(int target, int[] arr) {
        int n = arr.length;

        int l = 0, h = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;

        while (h < n) {

            // expand window
            sum += arr[h];

            // shrink window while condition satisfied
            while (sum >= target) {
                minLen = Math.min(minLen, h - l + 1);
                sum -= arr[l];
                l++;
            }

            h++;
        }

        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }

    public static void main(String[] args) {
        int[] arr = { 2, 3, 1, 2, 4, 3 };
        int target = 7;

        System.out.println(minSubArrayLen(target, arr));
    }
}
