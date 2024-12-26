package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * 336. Palindrome Pairs
 * You are given a 0-indexed array of unique strings words.
 * A palindrome pair is a pair of integers (i, j) such that:
 *   0 <= i, j < words.length,
 *   i != j, and
 *   words[i] + words[j] (the concatenation of the two strings) is a palindrome
 * Return an array of all the palindrome pairs of words.
 * You must write an algorithm with O(sum of words[i].length) runtime complexity.
 * Example 1:
 *   Input: words = ["abcd","dcba","lls","s","sssll"]
 *   Output: [[0,1],[1,0],[3,2],[2,4]]
 *   Explanation: The palindromes are ["abcddcba","dcbaabcd","slls","llssssll"]
 * Example 2:
 *   Input: words = ["bat","tab","cat"]
 *   Output: [[0,1],[1,0]]
 *   Explanation: The palindromes are ["battab","tabbat"]
 * Example 3:
 *   Input: words = ["a",""]
 *   Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["a","a"]
 * Constraints:
 *   1 <= words.length <= 5000
 *   0 <= words[i].length <= 300
 *   words[i] consists of lowercase English letters.
 */
public class LC0336 {
    public List<List<Integer>> palindromePairs(String[] words) {
        Set<List<Integer>> result = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            int left = -1, right = 0;

            while (left < word.length()) {
                int[] idx = findPalindrome(word, left, right, map);
                if (idx[1] >= 0 && idx[1] != i) {
                    List<Integer> list = new ArrayList<>();
                    if (idx[0] == 0) {
                        list.add(idx[1]);
                        list.add(i);
                    } else {
                        list.add(i);
                        list.add(idx[1]);
                    }
                    result.add(list);
                }
                if (left < right) left++;
                else right++;
            }

            if (map.containsKey("") && !word.equals("") && isPalindrome(word)) {
                List<Integer> list1 = new ArrayList<>();
                list1.add(map.get(""));
                list1.add(i);
                result.add(list1);

                List<Integer> list2 = new ArrayList<>();
                list2.add(i);
                list2.add(map.get(""));
                result.add(list2);
            }
        }

        return new ArrayList<>(result);
    }

    private int[] findPalindrome(String word, int left, int right, Map<String, Integer> map) {
        StringBuilder sb = new StringBuilder();

        while (left >= 0 && right < word.length()) {
            char cl = word.charAt(left);
            char cr = word.charAt(right);
            if (cl == cr) {
                left--;
                right++;
            } else return new int[] {-1, -1};
        }

        int originalWordFirst;
        if (left == -1) {
            originalWordFirst = 0;
            sb.append(word.substring(right));
        } else {
            originalWordFirst = 1;
            sb.append(word.substring(0, left+1));
        }

        String reverseStr = sb.reverse().toString();
        if (map.containsKey(reverseStr)) {
            return new int[] {originalWordFirst, map.get(reverseStr)};
        } else return new int[] {-1, -1};
    }

    private boolean isPalindrome(String word) {
        int left = 0, right = word.length() - 1;
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

}
