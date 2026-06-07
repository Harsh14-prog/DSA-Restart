package Queue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SumOfMaxAndMinOfEachSubArrOfSize_K {
    public static void main(String[] args) {
        
        int[] nums = {2, 5, -1, 7, -3, -1, -2};
        int k = 4;
        int n = nums.length;

        Deque<Integer> dq1 = new LinkedList<>(); // Will store indices for Maximums
        Deque<Integer> dq2 = new LinkedList<>(); // Will store indices for Minimums

        List<Integer> L1 = new ArrayList<>();
        List<Integer> L2 = new ArrayList<>();

        int l = 0;

        for (int h = 0; h < n; h++) {

            // 1. Remove indices that are out of the current window boundary
            while (!dq1.isEmpty() && dq1.peekFirst() < l) {
                dq1.pollFirst();
            }
            while (!dq2.isEmpty() && dq2.peekFirst() < l) {
                dq2.pollFirst();
            }

            // 2. Maintain Max Deque (Monotonic Decreasing): Compare with LAST element
            while (!dq1.isEmpty() && nums[dq1.peekLast()] <= nums[h]) {
                dq1.pollLast();
            }
            dq1.addLast(h);

            // 3. Maintain Min Deque (Monotonic Increasing): Compare with LAST element
            while (!dq2.isEmpty() && nums[dq2.peekLast()] >= nums[h]) {
                dq2.pollLast();
            }
            dq2.addLast(h);

            // 4. If window size reaches 'k', record the max and min, then slide left pointer
            if (h - l + 1 == k) {
                L1.add(nums[dq1.peekFirst()]);
                L2.add(nums[dq2.peekFirst()]);
                l++;
            }
        }

        int sum = 0;
        for (int ele : L1) sum += ele;
        for (int ele : L2) sum += ele;

        System.out.println("Total Sum: " + sum); 
        // Correct output for this array: 14
    }
}