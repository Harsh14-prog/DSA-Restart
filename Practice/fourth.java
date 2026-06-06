package Practice ;

import java.util.Stack;

public class fourth {

    public static void main(String[] args) {

        int[] nums = {3,1,2,1};

        int n = nums.length;

        long sum = 0;

        long mod = 1000000007L;

        Stack<Integer> st = new Stack<>();

        // we calculated contribution of each ele only when we
        // pop ele , o/w we push ele in stack
        // so after loop end at i == n stack is not empty 
        // in order to forcefully pop rem ele we used condn i == n

        for(int i = 0; i <= n; i++) {

            while(!st.isEmpty() &&
                  (i == n || nums[st.peek()] >= nums[i])) {

                int mid = st.pop();

                int pse =
                        st.isEmpty() ? -1 : st.peek();

                int nse = i;

                long leftChoices =
                        mid - pse;   // mid used here bez pse and nse are of
                        // popped ele i.e of mid , not of current index i
                        // thats why i not used here

                long rightChoices =
                        nse - mid;

                long contribution =
                        (nums[mid]
                        * leftChoices
                        * rightChoices) % mod;

                sum =
                        (sum + contribution) % mod;
            }

            if(i < n) {
                st.push(i);
            }
        }


        System.out.println(sum);
    }
}