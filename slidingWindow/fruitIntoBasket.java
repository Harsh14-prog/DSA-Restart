package slidingWindow;

import java.util.HashMap;

public class fruitIntoBasket {

    public static int totalFruit(int[] fruits) {

        int n = fruits.length;
        int l = 0, h = 0;
        int maxLen = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        while (h < n) {

            // expand window
            map.put(fruits[h], map.getOrDefault(fruits[h], 0) + 1);

            // shrink if more than 2 types
            while (map.size() > 2) {
                int leftFruit = fruits[l];
                map.put(leftFruit, map.get(leftFruit) - 1);

                if (map.get(leftFruit) == 0) {
                    map.remove(leftFruit);
                }
                l++;
            }

            // valid window (≤ 2 types)
            maxLen = Math.max(maxLen, h - l + 1);

            h++;
        }

        return maxLen;
    }

    public static void main(String[] args) {

        int[] fruits = {1, 2, 3, 2, 2};

        System.out.println(totalFruit(fruits)); // 4
    }
}