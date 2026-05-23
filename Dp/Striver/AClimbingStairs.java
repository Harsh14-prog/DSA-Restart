package Dp.Striver;

public class AClimbingStairs {

    /*
        ways(n) means:
        Total number of ways to reach step n

        From any step, we can either:
        1. Take 1 step
        2. Take 2 steps

        Therefore:
        ways(n) = ways(n - 1) + ways(n - 2)

        Why?

        To reach step n:
        - Either we came from step (n - 1)
        - Or we came from step (n - 2)
    */

    public static int ways(int n){

        /*
            BASE CASES

            n == 0
            ----------
            We are already at destination.
            There is exactly 1 valid way:
            "Do nothing"

            n == 1
            ----------
            Only one possible move:
            Take 1 step

            Therefore return 1 for both.
        */
        if(n == 0 || n == 1)
            return 1;

        /*
            RECURSIVE CALLS

            ways(n - 1)
            ----------------
            Total ways if last jump was 1 step

            ways(n - 2)
            ----------------
            Total ways if last jump was 2 steps

            Add both because both are valid possibilities.
        */
        return ways(n - 1) + ways(n - 2);
    }

    public static void main(String[] args) {

        // Total stairs
        int n = 5;

        /*
            Function call:

            ways(5)

            Internally becomes:

            ways(4) + ways(3)

            Then smaller recursive calls continue
            until base cases are reached.
        */

        // Print total number of ways
        System.out.println(ways(n));
    }
}