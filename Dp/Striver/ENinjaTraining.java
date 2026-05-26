package Dp.Striver;

public class ENinjaTraining {

    public static int solve(int[][] points, int day, int last){

        /*
            BASE CASE

            If we are at day 0,
            choose maximum task
            except the last performed task.
        */
        if(day == 0) {

            int maxi = 0;

            /*
                Try all 3 tasks
            */
            for(int i = 0; i < points[0].length; i++) {

                /*
                    Task should not
                    equal previous task
                */
                if(i != last){

                    maxi = Math.max(maxi, points[0][i]);
                }
            }

            return maxi;
        }

        /*
            Store maximum answer
        */
        int maxi = 0;

        /*
            Try all tasks for current day
        */
        for(int i = 0; i < points[day].length; i++){

            /*
                Current task should not
                equal previous task
            */
            if(i != last){

                /*
                    Current day points
                    +
                    recursive answer
                */
                int point =
                        points[day][i]
                        + solve(points, day - 1, i);

                /*
                    Take maximum
                */
                maxi = Math.max(maxi, point);
            }
        }

        return maxi;
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

        /*
            last = 3

            Means:
            no previous task restriction initially.
        */
        int last = points[0].length;

        /*
            Start from last day

            points.length - 1
            because indexing starts from 0.
        */
        int maxPoints =
                solve(points, points.length - 1, last);

        System.out.println(maxPoints);
    }
}