package recursion;

import java.util.* ;

public class SubsetSum {

    public static void subSet(int[] nums ,int index, List<Integer> list , int sum){

        if(index == nums.length){
            list.add(sum);
            return ;
        }

        sum += nums[index];
        subSet(nums , index+1 , list, sum);

        sum -= nums[index];
        subSet(nums, index+1 , list, sum);

    }
    public static void main(String[] args) {
        int[] nums = {3,1,2};
        int index = 0 ;

        List<Integer> list = new ArrayList<>();
        int sum = 0 ;

        subSet(nums , index , list , sum);

        Collections.sort(list);
        System.out.println(list);
    }
}
