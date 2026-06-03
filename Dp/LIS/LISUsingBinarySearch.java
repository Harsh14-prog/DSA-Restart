package Dp.LIS;

import java.util.ArrayList;
import java.util.List;

public class LISUsingBinarySearch {

    public static void main(String[] args) {

        int[] nums = {1, 7, 8, 4, 5, 6, -1, 9};

        int n = nums.length;

        /*
         * list stores the smallest possible tail
         * for increasing subsequences of different lengths.
         *
         * Example:
         *
         * list[0] -> smallest tail of LIS length 1
         * list[1] -> smallest tail of LIS length 2
         * list[2] -> smallest tail of LIS length 3
         * ...
         *
         * IMPORTANT:
         * This list is NOT necessarily the actual LIS.
         *
         * It only helps us find the LIS length efficiently.
         */
        List<Integer> list = new ArrayList<>();

        // First element always starts an LIS of length 1
        list.add(nums[0]);

        for (int i = 1; i < n; i++) {

            /*
             * If current element is greater than the
             * largest tail so far, we can extend the LIS.
             */
            if (nums[i] > list.get(list.size() - 1)) {

                list.add(nums[i]);
            }
            else {

                /*
                 * Current element cannot extend the LIS.
                 *
                 * We want to replace the first element
                 * that is >= nums[i].
                 *
                 * This is called LOWER BOUND.
                 */

                int low = 0;
                int high = list.size() - 1;

                int target = nums[i];

                // Stores the position to be replaced
                int ans = -1;

                while (low <= high) {

                    int mid = (low + high) / 2;

                    /*
                     * Found an element >= target.
                     *
                     * It could be our answer,
                     * but there might be an earlier one.
                     *
                     * Therefore search on left side.
                     */
                    if (list.get(mid) >= target) {

                        ans = mid;
                        high = mid - 1;
                    }
                    else {

                        /*
                         * Current element is smaller than target.
                         *
                         * Lower bound cannot exist on left side.
                         *
                         * Search right side.
                         */
                        low = mid + 1;
                    }
                }

                /*
                 * Replace element at lower bound position.
                 *
                 * Example:
                 *
                 * list = [1,7,8]
                 * target = 4
                 *
                 * lower bound = 7
                 *
                 * Replace:
                 *
                 * [1,7,8]
                 *    ↓
                 * [1,4,8]
                 *
                 * Why?
                 *
                 * Smaller tails provide more opportunities
                 * for future elements to extend the LIS.
                 */
                list.set(ans, target);
            }
        }

        /*
         * Size of list equals length of LIS.
         */
        System.out.println("Length of LIS = " + list.size());
    }
}