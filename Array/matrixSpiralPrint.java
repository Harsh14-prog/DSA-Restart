package Array;

import java.util.*;

public class matrixSpiralPrint {
    public static void main(String[] args) {

        //  matrix (5 rows × 3 columns)
        int mat[][] = {
            {1,2,3},
            {4,5,6},
            {7,8,9},
            {10,11,12},
            {13,14,15}
        };

       
        int r = mat.length;        // number of rows
        int c = mat[0].length;     // number of columns

        
        int top = 0;           // starting row
        int bottom = r - 1;   // ending row
        int left = 0;         // starting column
        int right = c - 1;    // ending column

        
        List<Integer> list = new ArrayList<>();

        
        while(top <= bottom && left <= right){

            // =========================
            // Traverse TOP row (left → right)
            // =========================
            for(int i = left; i <= right; i++){
                list.add(mat[top][i]);
            }
            top++; // move top boundary down (row is done)

            // =========================
            // Traverse RIGHT column (top → bottom)
            // =========================
            for(int i = top; i <= bottom; i++){
                list.add(mat[i][right]);
            }
            right--; // move right boundary left (column is done)

            // =========================
            // Traverse BOTTOM row (right → left)
            // Only if a valid row still exists
            // =========================
            if(top <= bottom){
                for(int i = right; i >= left; i--){
                    list.add(mat[bottom][i]);
                }
                bottom--; // move bottom boundary up
            }

            // =========================
            // Traverse LEFT column (bottom → top)
            // Only if a valid column still exists
            // =========================
            if(left <= right){
                for(int i = bottom; i >= top; i--){
                    list.add(mat[i][left]);
                }
                left++; // move left boundary right
            }
        }

        // Final spiral order output
        System.out.println(list);
    }
}