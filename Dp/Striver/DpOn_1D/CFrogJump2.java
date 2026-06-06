package Dp.Striver.DpOn_1D;

public class CFrogJump2 {

    public static int solve(int[] ht, int n, int k) {

        /*
            BASE CASE

            Frog already at stair 0.
            No energy required.
        */
        if (n == 0) {
            return 0;
        }

        /*
            Store minimum energy answer.

            Initially keep very large value.
        */
        int minEnergy = Integer.MAX_VALUE;

        /*
            Try all jumps from 1 to k
        */
        for (int i = 1; i <= k; i++) {

            /*
                Check if previous stair is valid.

                Example:
                n-i should not become negative.
            */
            if (n - i >= 0) {

                /*
                    Energy required:

                    minimum energy to reach previous stair
                    +
                    jump cost
                */
                int jumpEnergy =
                        solve(ht, n - i, k)
                        + Math.abs(ht[n] - ht[n - i]);

                /*
                    Take minimum among all jumps
                */
                minEnergy = Math.min(minEnergy, jumpEnergy);
            }
        }

        /*
            Return minimum energy
            required to reach stair n
        */
        return minEnergy;
    }

    public static void main(String[] args) {

        /*
            Height of stairs

            Index:
            0   1   2   3
            10  20  30  10
        */
        int[] ht = {10, 20, 30, 10};

        // Total stairs
        int n = ht.length;

        // Frog can jump at most k steps
        int k = 2;

        /*
            Find minimum energy
            to reach last stair
        */
        int minEnergy = solve(ht, n - 1, k);

        // Print final answer
        System.out.println(minEnergy);
    }
}