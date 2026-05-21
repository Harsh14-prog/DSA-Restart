
import java.util.Stack;

public class MaximalRectangle {

    // -------------------------------
    // Largest Rectangle in Histogram
    // -------------------------------
    public static int largestRectangleArea(int[] heights) {

        Stack<Integer> st = new Stack<>();
        int maxArea = 0;
        int n = heights.length;

        for (int i = 0; i <= n; i++) {

            // Dummy 0 height at end
            // to empty remaining stack
            int currentHeight = (i == n) ? 0 : heights[i];

            // Maintain increasing stack
            while (!st.isEmpty() &&
                    heights[st.peek()] >= currentHeight) {

                int elementIndex = st.pop();
                int height = heights[elementIndex];

                // Next Smaller Element
                int nse = i;

                // Previous Smaller Element
                int pse = st.isEmpty() ? -1 : st.peek();

                int width = nse - pse - 1;

                int area = height * width;

                maxArea = Math.max(maxArea, area);
            }

            st.push(i);
        }

        return maxArea;
    }

    // -------------------------------
    // Maximal Rectangle in Matrix
    // -------------------------------
    public static int maximalRectangle(char[][] matrix) {

        if (matrix.length == 0)
            return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] heights = new int[cols];
        int maxArea = 0 ;

        for(int i = 0 ; i < rows ; i++){
            for(int j = 0 ; j < cols ; j++){
                if(matrix[i][j] == '1'){
                    heights[j] += 1 ;
                }
                else{
                    heights[j] = 0 ;
                }
            }
        }

        int area = largestRectangleArea(heights) ;

        maxArea = Math.max(maxArea , area);

        return maxArea ;
    }
    

    // -------------------------------
    // Main Function
    // -------------------------------
    public static void main(String[] args) {

        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };

        int ans = maximalRectangle(matrix);

        System.out.println("Maximum Rectangle Area = " + ans);
    }
}