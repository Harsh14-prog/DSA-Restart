package Queue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SlidingWindowMaximum {

    public static void main(String[] args) {

        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int l = 0;

        /*
         * Deque stores INDICES, not values.
         *
         * Important Property:
         * -------------------
         * Indices inside deque are maintained such that
         * their corresponding values are in DECREASING order.
         *
         * Example:
         *
         * dq = [1, 2, 3]
         *
         * values:
         * [3, -1, -3]
         *
         * Front always contains the maximum element's index.
         */
        Deque<Integer> dq = new LinkedList<>();

        List<Integer> ans = new ArrayList<>();

        for (int h = 0; h < nums.length; h++) {

            /*
             * STEP 1:
             * Remove expired indices i.e ele from prev window .
             *
             * Current window starts at 'l'.
             *
             * Any index smaller than l has already gone out
             * of the current window.
             *
             * Since oldest indices are always at the FRONT,
             * remove from FRONT.
             */
            while (!dq.isEmpty() && dq.peekFirst() < l) {

                dq.pollFirst();
            }

            /*
             * STEP 2:
             * Remove useless smaller elements.
             *
             * Current element = nums[h]
             *
             * If current element is greater than elements
             * at the BACK of deque, those elements can never
             * become maximum again.
             *
             * Why?
             *
             * Example:
             *
             * deque values:
             * [3, -1, -3]
             *
             * current = 5
             *
             * Since:
             * 5 > -3
             * 5 > -1
             * 5 > 3
             *
             * all these elements become useless.
             *
             * They are smaller than 5
             * AND
             * they will leave the window before 5.
             *
             * Therefore they can never be maximum
             * in any future window.
             *
             * Remove them from BACK.
             * bez in dq ele are in decre order so remove from smaller ele hence from back remove
             */
            while (!dq.isEmpty() &&
                    nums[dq.peekLast()] <= nums[h]) {

                dq.pollLast();
            }

            /*
             * STEP 3:
             * Insert current index at BACK.
             *
             * After removing smaller elements,
             * deque still remains in decreasing order.
             */
            dq.offerLast(h);

            /*
             * Window size becomes k.
             */
            if (h - l + 1 == k) {

                /*
                 * STEP 4:
                 * Front of deque contains
                 * index of maximum element.
                 */
                ans.add(nums[dq.peekFirst()]);

                /*
                 * Slide window.
                 */
                l++;
            }
        }

        System.out.println(ans);
    }
}