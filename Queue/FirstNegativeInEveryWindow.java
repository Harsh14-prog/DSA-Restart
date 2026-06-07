package Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FirstNegativeInEveryWindow {
    public static void main(String[] args) {
        
        int[] nums = {12,-1,-7,8,-15,30,16,28} ;
        int n = nums.length ;
        int k = 3 ;

        int l = 0 ;

        Queue <Integer> q = new LinkedList<>() ;

        List <Integer> list = new ArrayList<>() ;

        for(int h = 0 ; h < n ; h++){

            if(nums[h] < 0){
                q.add(h) ;
            }

            if(h-l+1 == k){
               
               while(!q.isEmpty() && q.peek() < l){
                 q.poll() ;
               } 

               if(!q.isEmpty()){
                 list.add(nums[q.peek()]) ;
               }
               else{
                 list.add(0) ;
               }

               l++ ;
            }
        }

        System.out.println(list);
    }
}
