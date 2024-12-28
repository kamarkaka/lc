package com.kamarkaka.datadog;

import java.util.HashMap;
import java.util.Map;

/***
 * Given a string, find its longest substring that contains only distinct characters.
 */
public class MaxSubstring {
    public String longestSubstringWithDistinctCharacters(String str) {
        Map<Character, Integer> rightIndexMap = new HashMap<>();
        int left = 0, right = 0, maxSize = 1;
        int leftIndex = 0, rightIndex = 0;

        while (right < str.length()) {
            char c = str.charAt(right);
            if (!rightIndexMap.containsKey(c)) {
                rightIndexMap.put(c, right);

                if (maxSize < right - left + 1) {
                    maxSize = right - left + 1;
                    rightIndex = right;
                    leftIndex = left;
                }
            } else {
                left = rightIndexMap.get(c) + 1;
            }
            right++;
        }

        return str.substring(leftIndex, rightIndex + 1);
    }

    public static void main(String[] args) {
        MaxSubstring solution = new MaxSubstring();
        System.out.println(solution.longestSubstringWithDistinctCharacters("a"));
        System.out.println(solution.longestSubstringWithDistinctCharacters("abc"));
        System.out.println(solution.longestSubstringWithDistinctCharacters("abcbde"));
    }
}
