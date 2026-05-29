package Patterns.MergeInterval ;

import java.util.Arrays;

public class meetingRoom_1 {

    public static boolean canAttendMeetings(int[][] intervals) {

        // Sort according to meeting start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Check adjacent meetings
        for (int i = 1; i < intervals.length; i++) {

            int currentStart = intervals[i][0];

            int previousEnd = intervals[i - 1][1];

            // To attend all meetings: -> there should not be
            // any overlap between 2 meetings
            // so pblm becomes can we detect any overlap?
            // if yes return false ;

            // Overlap exists
            if (currentStart < previousEnd) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int[][] intervals = {
                {0, 30},
                {5, 10},
                {15, 20}
        };

        System.out.println(canAttendMeetings(intervals));
    }
}