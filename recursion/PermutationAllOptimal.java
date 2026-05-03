package recursion;

import java.util.* ;

public class PermutationAllOptimal {

    public static void swap(int[] nums , int l , int h){
        int temp = nums[l];
        nums[l] = nums[h];
        nums[h] = temp ;
    }

    public static void permut(int[] nums , int index,  List<List<Integer>> ansList){

        if(index == nums.length){

            List <Integer> list = new ArrayList<>();
            
            // we already swapped array so we will add swapped array in list
            for(int i = 0 ; i < nums.length ; i++){
                list.add(nums[i]);
            }

            ansList.add(new ArrayList<>(list));
            return ;
        }
       
        for(int i = index ; i < nums.length ; i++){
            swap(nums , index , i);
            permut(nums , index+1 , ansList);
            swap(nums , index , i);  // backtrack
        }
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> ansList = new ArrayList<>() ;
        int index = 0 ;
        permut(nums ,index, ansList);

        System.out.println(ansList);
    }
}
