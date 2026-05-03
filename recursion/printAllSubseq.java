package recursion;

import java.util.ArrayList;
import java.util.List;

public class printAllSubseq {

    public static void print(int index , List<Integer> list , int[] nums){
        
        if(index >= nums.length){
            System.out.println(list);
            return ;
        }

        list.add(nums[index]);
        print(index+1 , list , nums);
        list.remove(list.size() - 1);
        print(index+1 , list , nums);
    }
    public static void main(String[] args) {
        int[] nums = {3,1,2};

        int index = 0 ;
        print(index , new ArrayList<>() , nums);
    }
}
