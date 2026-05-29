package Patterns.MergeInterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class merge {

    public static void main(String[] args) {

        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        List<int[]> list = new ArrayList<>();

        // Sort intervals according to start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Start with first interval
        int s1 = intervals[0][0];
        int e1 = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {

            int s2 = intervals[i][0];
            int e2 = intervals[i][1];

            // Overlap exists
            if (e1 >= s2) {

                // Extend the current merged interval
                e1 = Math.max(e1, e2);

            } else {

                // No overlap
                // Store previous merged interval
                list.add(new int[]{s1, e1});

                // Start a new interval
                s1 = s2;
                e1 = e2;
            }
        }

        // VERY IMPORTANT:
        // Add the last merged interval
        list.add(new int[]{s1, e1});

        // Print answer
        for (int[] interval : list) {
            System.out.println(Arrays.toString(interval));
        }
    }
}