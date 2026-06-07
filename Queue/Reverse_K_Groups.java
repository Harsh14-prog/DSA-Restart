package Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Reverse_K_Groups {

    public static Queue <Integer> solve(Queue<Integer> q, int k) {

        Stack<Integer> st = new Stack<>();
        Queue<Integer> ans = new LinkedList<>();

        while (!q.isEmpty()) {

            int count = 0;

            while (!q.isEmpty() && count < k) {
                st.push(q.poll());
                count++;
            }

            while (!st.isEmpty()) {
                ans.add(st.pop());
            }
        }

        return ans ;
    }

    public static void main(String[] args) {

        Queue<Integer> q = new LinkedList<>();

        q.offer(10);
        q.offer(20);
        q.offer(30);
        q.offer(40);
        q.offer(50);
        q.offer(60);
        q.offer(70);
        q.offer(80);

        Queue <Integer> ans = solve(q, 3);

        while (!ans.isEmpty()){
            System.out.println(ans.poll());
        }
    }
}
