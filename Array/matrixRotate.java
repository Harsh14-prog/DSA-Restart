package Array;

public class matrixRotate {

    // rotate matrix by 90 degrees clockwise
    public static void rotate(int[][] matrix) {

        int n = matrix.length;

        // Step 1: Transpose (swap across diagonal)
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {   //  j = i
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //  Step 2: Reverse each row
        for (int i = 0; i < n; i++) {

            int left = 0;
            int right = n - 1;

            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;

                left++;
                right--;
            }
        }
    }

    // function to print matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        rotate(matrix);

        System.out.println("\nRotated Matrix (90° Clockwise):");
        printMatrix(matrix);
    }
}