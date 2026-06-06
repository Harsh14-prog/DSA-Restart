package Dp.Striver.DpOn_1D;

public class ENinjaTrainingTabulation {

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
            DP TABLE

            dp[day][last]

            day  -> current day
            last -> previously performed task
        */
        int[][] dp = new int[n][4];

        /*
            BASE CASE

            Filling " day 0 " manually
        */

        // If last task was 0
        dp[0][0] =
                Math.max(points[0][1], points[0][2]);

        // If last task was 1
        dp[0][1] =
                Math.max(points[0][0], points[0][2]);

        // If last task was 2
        dp[0][2] =
                Math.max(points[0][0], points[0][1]);

        // If no previous task restriction
        dp[0][3] =
                Math.max(
                        points[0][0],
                        Math.max(points[0][1], points[0][2])
                );

        /*
            Fill remaining days
        */
        for(int day = 1; day < n; day++){

            /*
                last = previously performed task
            */
            for(int last = 0; last < 4; last++){

                /*
                    Store maximum answer
                */
                int maxi = 0;

                /*
                    Try all 3 tasks
                */
                for(int task = 0; task < 3; task++){

                    /*
                        Current task should not
                        equal previous task
                    */
                    if(task != last){

                        /*
                            Current points
                            +
                            previous DP answer
                        */
                        int activity =
                                points[day][task]
                                + dp[day - 1][task];

                        /*
                            Take maximum
                        */
                        maxi = Math.max(maxi, activity);
                    }
                }

                /*
                    Store answer
                */
                dp[day][last] = maxi;
            }
        }

        /*
            Final answer

            Last day with no restriction
        */
        System.out.println(dp[n - 1][3]);
    }
}