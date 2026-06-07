package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class InterLeave {

    public static Queue <Integer> interleave (Queue<Integer> q1){

        int size = q1.size() ;

        Queue <Integer> q2 = new LinkedList<>() ;
        Queue <Integer> q3 = new LinkedList<>() ;

        for(int i = 0 ; i < size / 2 ; i++){
           q2.add(q1.poll()) ;
        }

        while (!q1.isEmpty()){
            q3.add(q1.poll()) ;
        }

        while(!q2.isEmpty() && !q3.isEmpty()){
            q1.add(q2.poll()) ;
            q1.add(q3.poll()) ;
        }

        while(!q2.isEmpty()){
            q1.add(q2.poll()) ;
        }

        while(!q3.isEmpty()){
            q1.add(q3.poll()) ;
        }

        return q1 ;

    }
    public static void main(String[] args) {

        Queue<Integer> q = new LinkedList<>();

        q.offer(1);
        q.offer(2);
        q.offer(3);
        q.offer(4);
        q.offer(5);
        q.offer(6);
        q.offer(7);
        q.offer(8);
        q.offer(9);

        Queue <Integer> ans = interleave(q);

        while (!ans.isEmpty()){
            System.out.println(ans.poll());
        }
    }
}
