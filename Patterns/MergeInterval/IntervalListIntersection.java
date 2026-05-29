package Patterns.MergeInterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalListIntersection {

    public static void main(String[] args) {

        int[][] l1 = {
                {0, 2},
                {5, 10},
                {13, 23},
                {24, 25}
        };

        int[][] l2 = {
                {1, 5},
                {8, 12},
                {15, 24},
                {25, 26}
        };

        int n1 = l1.length;
        int n2 = l2.length;

        List<int[]> list = new ArrayList<>();

        // Pointer for first list
        int i = 0;

        // Pointer for second list
        int j = 0;

        // Traverse both interval lists
        while (i < n1 && j < n2) {

            /*
             * Current intervals
             *
             * l1[i] = [s1,e1]
             * l2[j] = [s2,e2]
             */
            int e1 = l1[i][1];
            int e2 = l2[j][1];

            /*
             * Calculate possible intersection.
             *
             * Overlapping portion starts at:
             * max(start1,start2)
             *
             * Overlapping portion ends at:
             * min(end1,end2)
             */
            int start = Math.max(l1[i][0], l2[j][0]);
            int end = Math.min(e1, e2);

            // we first calculated intersection then checked is it
            // valid or not

            /*
             * If start <= end
             * then a valid intersection exists.
             *
             * Example:
             *
             * [0,2]
             * [1,5]
             *
             * start = max(0,1) = 1
             * end   = min(2,5) = 2
             *
             * Since 1 <= 2
             * intersection = [1,2]
             */
            if (start <= end) {
                list.add(new int[]{start, end});
            }

            /*
             * Move the interval which ends first.
             *
             * Why?
             *
             * Because that interval can never
             * contribute to future intersections.
             *
             * Example:
             *
             * [0,2]
             * [1,5]
             *
             * Interval [0,2] is finished after 2.
             * It cannot intersect anything else.
             *
             * So move i.
             */
            if (e1 <= e2) {
                i++;
            } else {
                j++;
            }
        }

        // Print all intersections
        for (int[] arr : list) {
            System.out.println(Arrays.toString(arr));
        }
    }
}