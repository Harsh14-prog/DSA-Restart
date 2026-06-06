package Dp.Striver.DpOn_1D;

import java.util.Arrays;

public class ENinjaTrainingMemoization {

    /*
        DP ARRAY

        dp[day][last] means:

        Maximum points ninja can earn
        till 'day'
        when previous task was 'last'.

        last can be:
        0, 1, 2, 3

        0 -> Task 0 was done previously
        1 -> Task 1 was done previously
        2 -> Task 2 was done previously
        3 -> No previous task
    */
    static int[][] dp;

    public static int solve(int[][] points, int day, int last){

        /*
            BASE CASE

            If we are at day 0,
            choose maximum task
            except the last task.
        */
        if(day == 0){

            int maxi = 0;

            /*
                Try all 3 tasks
            */
            for(int task = 0; task < 3; task++){

                /*
                    Task should not
                    equal previous task
                */
                if(task != last){

                    maxi = Math.max(maxi, points[0][task]);
                }
            }

            return maxi;
        }

        /*
            MEMOIZATION CHECK

            If answer already calculated,
            directly return stored value.
        */
        if(dp[day][last] != -1){
            return dp[day][last];
        }

        /*
            Store maximum answer
        */
        int maxi = 0;

        /*
            Try all tasks for current day
        */
        for(int task = 0; task < 3; task++){

            /*
                Current task should not
                equal previous task
            */
            if(task != last){

                /*
                    Current task points
                    +
                    recursive answer
                */
                int activity =
                        points[day][task]
                        + solve(points, day - 1, task);

                /*
                    Take maximum
                */
                maxi = Math.max(maxi, activity);
            }
        }

        /*
            Store answer in DP table
        */
        dp[day][last] = maxi;

        /*
            Return final answer
        */
        return dp[day][last];
    }

    public static void main(String[] args) {

        /*
            points[day][task]

            Tasks:
            0 -> Task A
            1 -> Task B
            2 -> Task C
        */
        int[][] points = {

                {2, 1, 3},
                {3, 4, 6},
                {10, 1, 6},
                {8, 3, 7}
        };

        // Total days
        int n = points.length;

        /*
            Create DP table

            Rows -> days
            Columns -> last task

            We need 4 columns because:
            0,1,2,3
        */
        dp = new int[n][4];

        /*
            Fill DP table with -1

            Meaning:
            answer not calculated yet
        */
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        /*
            Start from last day

            last = 3 means:
            no previous task restriction
        */
        int maxPoints =
                solve(points, n - 1, 3);

        System.out.println(maxPoints);
    }
}