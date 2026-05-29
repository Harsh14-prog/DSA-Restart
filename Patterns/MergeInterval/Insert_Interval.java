package Patterns.MergeInterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Insert_Interval {

    /*
     * This method performs the normal Merge Intervals operation.
     *
     * Example Input:
     * [1,2]
     * [3,5]
     * [4,8]
     * [6,7]
     * [8,10]
     * [12,16]
     *
     * Output:
     * [1,2]
     * [3,10]
     * [12,16]
     */
    public static int[][] mergeInterval(int[][] nums) {

        // Start with first interval
        int s1 = nums[0][0];
        int e1 = nums[0][1];

        // Stores final merged intervals
        List<int[]> list = new ArrayList<>();

        // Traverse remaining intervals
        for (int i = 1; i < nums.length; i++) {

            // Current interval
            int s2 = nums[i][0];
            int e2 = nums[i][1];

            /*
             * Check overlap
             *
             * Example:
             * [3,5]
             * [4,8]
             *
             * Since 5 >= 4
             * overlap exists
             */
            if (e1 >= s2) {

                /*
                 * Merge intervals
                 *
                 * Start remains same
                 * End becomes maximum end value
                 *
                 * [3,5]
                 * [4,8]
                 *
                 * Result:
                 * [3,8]
                 */
                e1 = Math.max(e1, e2);
            }

            /*
             * No overlap
             *
             * Example:
             * [3,10]
             * [12,16]
             */
            else {

                // Store previously merged interval
                list.add(new int[]{s1, e1});

                // Start tracking a new interval
                s1 = s2;
                e1 = e2;
            }
        }

        /*
         * Important:
         * Last merged interval is still not added
         */
        list.add(new int[]{s1, e1});

        /*
         * Convert List<int[]> into int[][]
         */
        int[][] result = new int[list.size()][2];

        int idx = 0;

        for (int[] arr : list) {
            result[idx] = arr;
            idx++;
        }

        return result;
    }

    public static void main(String[] args) {

        /*
         * Given sorted and non-overlapping intervals
         */
        int[][] nums = {
                {1, 2},
                {3, 5},
                {6, 7},
                {8, 10},
                {12, 16}
        };

        /*
         * New interval to insert
         */
        int[] newInterval = {4, 8};

        /*
         * Create new array with one extra space
         * to accommodate the new interval.
         */
        int[][] newArr = new int[nums.length + 1][2];

        int idx = 0;

        // Tracks whether new interval is inserted
        boolean inserted = false;

        /*
         * Insert the new interval into the correct
         * sorted position.
         */
        for (int i = 0; i < nums.length; i++) {

            /*
             * Insert new interval before the first interval
             * whose start is greater than newInterval's start.
             *
             * Example:
             *
             * Current interval = [6,7]
             * New interval     = [4,8]
             *
             * Since 6 > 4,
             * insert [4,8] first.
             */
            if (!inserted && nums[i][0] > newInterval[0]) {

                newArr[idx] = newInterval;

                inserted = true;

                idx++;
            }

            // Copy current interval
            newArr[idx] = nums[i];

            idx++;
        }

        /*
         * If new interval has the largest start value,
         * it was never inserted inside the loop.
         *
         * Example:
         * intervals = [[1,2],[3,4]]
         * newInterval = [10,12]
         *
         * Insert at the end.
         */
        if (!inserted) {

            newArr[idx] = newInterval;

            idx++;
        }

        /*
         * At this point array becomes:
         *
         * [1,2]
         * [3,5]
         * [4,8]
         * [6,7]
         * [8,10]
         * [12,16]
         *
         * Now simply run Merge Intervals.
         */
        int[][] result = mergeInterval(newArr);

        System.out.println("Final Answer:");

        for (int[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }
}