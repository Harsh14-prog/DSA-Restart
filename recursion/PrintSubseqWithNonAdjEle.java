package recursion;

import java.util.ArrayList;
import java.util.List;

public class PrintSubseqWithNonAdjEle {

    public static void solve(int[] nums , int index , List<Integer> list){

        if(index >= nums.length){
            System.out.println(list);
            return ;
        }
       
        list.add(nums[index]);
        solve(nums , index + 2 , list);
        list.remove(list.size()-1);
        solve(nums , index + 1 , list) ;

    }
    public static void main(String[] args) {

        int[] nums = {2,1,4,9};
        int index = 0 ;

        solve(nums , index , new ArrayList<>());
    }
}
