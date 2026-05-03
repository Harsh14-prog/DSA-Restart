package slidingWindow;

import java.util.* ;

public class LongestSubstringKDistinct {

    public static int longestKSubstr(String s, int k) {
        
        int n = s.length();

        int l = 0, h = 0;
        int maxLength = -1;

        HashMap<Character, Integer> mp = new HashMap<>();

        while (h < n){
           
            char ch = s.charAt(h);
            mp.put(ch , mp.getOrDefault(ch, 0) +1);

            while(mp.size() > k){
                // shrink
                char leftChar = s.charAt(l);
                mp.put(leftChar , mp.get(leftChar)-1);

                if(mp.get(leftChar) == 0){
                    mp.remove(leftChar);
                }
                l++;
            } // l does not move for every h like O(n^2) , so T.C = O(n)

            if(mp.size() == k){  
                // if we remove this check Then you’ll also count: substrings with less than k distinct
                maxLength = Math.max(maxLength , h-l+1);
            }  // we can remove this check for que. "atmost k dist." char
            h++;
        }

        return maxLength == -1 ? 0 : maxLength ;
    }

    public static void main(String[] args) {
        String s = "aabacbebebe";
        int k = 3;

        System.out.println(longestKSubstr(s, k)); // 7
    }
}
