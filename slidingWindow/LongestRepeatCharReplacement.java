package slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatCharReplacement {

    public static void main(String[] args) {

        String s = "AABABBA";
        int k = 1;

        int n = s.length();
        int l = 0, h = 0;
        int maxLen = 0;

        Map<Character, Integer> mp = new HashMap<>();
        int maxFreq = 0;

        while (h < n) {

            char ch = s.charAt(h);
            mp.put(ch, mp.getOrDefault(ch, 0) + 1);

            // track most frequent character
            maxFreq = Math.max(maxFreq, mp.get(ch));

            // check invalid condition
            while ((h - l + 1) - maxFreq > k) {

                char leftCh = s.charAt(l);
                mp.put(leftCh, mp.get(leftCh) - 1);

                if (mp.get(leftCh) == 0) {
                    mp.remove(leftCh);
                }

                l++;
            }

            // valid window
            maxLen = Math.max(maxLen, h - l + 1);

            h++;
        }

        System.out.println(maxLen); // 4
    }
}
