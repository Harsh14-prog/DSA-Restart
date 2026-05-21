import java.util.Stack;

public class LargestAreaInRectangle {

    public static void main(String[] args) {

        // Histogram heights
        int[] heights = {2, 1, 5, 6, 2, 3};

        int n = heights.length;

        // Stores maximum area
        int largestArea = 0;

        // Monotonic increasing stack
        // Stores indices
        Stack<Integer> st = new Stack<>();

        /*
           Traverse histogram
        */
        for (int i = 0; i < n; i++) {

            /*
               If current bar is smaller,
               then current index becomes
               NSE for popped elements.
            */
            while (!st.isEmpty()
                    && heights[st.peek()] >= heights[i]) {

                /*
                   Bar whose rectangle
                   is finalized
                */
                int mid = st.pop();

                /*
                   Previous Smaller Element
                */
                int pse;

                if (st.isEmpty()) {
                    pse = -1;
                } else {
                    pse = st.peek();
                }

                /*
                   Current index becomes NSE
                */
                int nse = i;

                /*
                   Width of rectangle

                   Rectangle exists between:
                   pse and nse
                */
                int width = nse - pse - 1;

                /*
                   Area using popped height
                */
                int area = heights[mid] * width;

                largestArea = Math.max(largestArea, area);
            }

            // Push current index
            st.push(i);
        }

        /*
           Remaining elements in stack

           Their NSE = n
        */
        while (!st.isEmpty()) {

            int mid = st.pop();

            int pse;

            if (st.isEmpty()) {
                pse = -1;
            } else {
                pse = st.peek();
            }

            int nse = n;

            int width = nse - pse - 1;

            int area = heights[mid] * width;

            largestArea = Math.max(largestArea, area);
        }

        // Final answer
        System.out.println("Largest Area = " + largestArea);
    }
}