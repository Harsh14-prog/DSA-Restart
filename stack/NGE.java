package stack;

import java.util.Arrays;
import java.util.Stack;

public class NGE {
    public static void main(String[] args) {
        int[] nums = {4, 5, 2, 10, 8};
        int n = nums.length ;

        int[] nge = new int[n];
        Stack <Integer> st = new Stack<>();

        for(int i = n-1 ; i >= 0 ; i--){

            while(!st.isEmpty() && st.peek() <= nums[i]){
                st.pop();
            }

            if(st.isEmpty()){
                nge[i] = -1 ;
            }
            else{
                nge[i] = st.peek();
            }

            st.push(nums[i]);

        }

        System.out.println(Arrays.toString(nge));
    }
}
