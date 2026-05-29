package Patterns.MergeInterval;


import java.util.Arrays;

public class meetingRoom_2{

    public static void main(String[] args) {

        int[][] intervals = {
                {1, 10},
                {2, 7},
                {3, 5},
                {6, 8}
        };

        int n = intervals.length;

        /*
         * Create two separate arrays:
         *
         * start[] -> stores all meeting start times
         * end[]   -> stores all meeting end times
         *
         * Example:
         *
         * intervals:
         * [1,10]
         * [2,7]
         * [3,5]
         * [6,8]
         *
         * start = [1,2,3,6]
         * end   = [10,7,5,8]
         */
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {

            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        /*
         * Sort all starting times.
         */
        Arrays.sort(start);

        /*
         * Sort all ending times.
         */
        Arrays.sort(end);

        /*
         * start = [1,2,3,6]
         * end   = [5,7,8,10]
         */

        /*
         * l -> points to next meeting start time
         * h -> points to next meeting end time
         */
        int l = 0;
        int h = 0;

        /*
         * Current rooms occupied.
         */
        int room = 0;

        /*
         * Maximum rooms occupied at any moment.
         *
         * This will be our final answer.
         */
        int maxRoom = 0;

        /*
         * Traverse both arrays.
         */
        while (l < n && h < n) {

            /*
             * Case 1:
             *
             * A new meeting starts BEFORE
             * the earliest meeting ends.
             *
             * Example:
             *
             * start[l] = 3
             * end[h]   = 5
             *
             * 3 < 5
             *
             * Means:
             * Meeting starting at 3 needs
             * a brand new room.
             */
            if (start[l] < end[h]) {

                room++;

                /*
                 * Update maximum rooms used.
                 */
                maxRoom = Math.max(maxRoom, room);

                /*
                 * Move to next meeting start.
                 */
                l++;
            }

            /*
             * Case 2:
             *
             * Earliest meeting has already ended.
             *
             * Example:
             *
             * start[l] = 6
             * end[h]   = 5
             *
             * Means:
             * One room becomes free.
             */
            else {

                room--;

                /*
                 * Move to next ending time.
                 */
                h++;
            }
        }

        System.out.println("Minimum Rooms Required = " + maxRoom);
    }
}