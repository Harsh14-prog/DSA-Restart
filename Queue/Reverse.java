package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class Reverse {

    public static void solve(Queue <Integer> q){

        if(q.isEmpty() && q.size() == 1){
            return ;
        }

        int ele = q.poll() ;
        solve(q) ;
        q.add(ele) ;
    }
    public static void main(String[] args) {

        Queue <Integer> q = new LinkedList<>() ;
        q.add(10) ;
        q.add(20) ;
        q.add(30) ;
        q.add(40) ;
        q.add(50) ;
        q.add(60) ;

        solve(q) ;

        while (!q.isEmpty()){
            System.out.println(q.peek());
            q.poll() ;
        }
    }
}
