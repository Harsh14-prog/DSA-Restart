package stack;

public class TrappingRainWater {

    public static void main(String[] args) {

        // Heights of buildings
        int[] height = {3, 0, 2, 0, 4, 1, 2};

        // Size of array
        int n = height.length;

        // Left pointer starts from beginning
        int left = 0;

        // Right pointer starts from end
        int right = n - 1;

        /*
           Stores maximum height seen
           from LEFT side till current point
        */
        int leftMax = 0;

        /*
           Stores maximum height seen
           from RIGHT side till current point
        */
        int rightMax = 0;

        // Stores total trapped water
        int water = 0;

        /*
           Continue until both pointers meet
        */
        while (left < right) {

            /*
               Process the smaller side.

               Why?

               Because smaller boundary
               determines water level.

               If left height is smaller,
               water on left side is fixed.
            */
            if (height[left] < height[right]) {

                /*
                   If current left height is greater
                   than leftMax,
                   update leftMax
                */
                if (height[left] >= leftMax) {

                    leftMax = height[left];
                }

                /*
                   Otherwise water can be trapped.

                   Formula:
                   leftMax - current height
                */
                else {

                    water += leftMax - height[left];
                }

                // Move left pointer forward
                left++;
            }

            /*
               If right side is smaller,
               process right side
            */
            else {

                /*
                   If current right height is greater
                   than rightMax,
                   update rightMax
                */
                if (height[right] >= rightMax) {

                    rightMax = height[right];
                }

                /*
                   Otherwise trap water.

                   Formula:
                   rightMax - current height
                */
                else {

                    water += rightMax - height[right];
                }

                // Move right pointer backward
                right--;
            }
        }

        // Print total trapped water
        System.out.println("Total trapped water = " + water);
    }
}