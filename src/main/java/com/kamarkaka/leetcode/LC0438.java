package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 438. Find All Anagrams in a String
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer
 * in any order.
 * Example 1:
 *   Input: s = "cbaebabacd", p = "abc"
 *   Output: [0,6]
 *   Explanation:
 *   The substring with start index = 0 is "cba", which is an anagram of "abc".
 *   The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Example 2:
 *   Input: s = "abab", p = "ab"
 *   Output: [0,1,2]
 *   Explanation:
 *   The substring with start index = 0 is "ab", which is an anagram of "ab".
 *   The substring with start index = 1 is "ba", which is an anagram of "ab".
 *   The substring with start index = 2 is "ab", which is an anagram of "ab".
 * Constraints:
 *   1 <= s.length, p.length <= 3 * 10^4
 *   s and p consist of lowercase English letters.
 */
public class LC0438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int start = 0, probe = 0;
        int[] pMap = new int[26];

        for (int i = 0; i < pMap.length; i++) {
            pMap[i] = -1;
        }

        int count = 0;
        for (char cp : p.toCharArray()) {
            if (pMap[cp - 'a'] == -1) pMap[cp - 'a'] = 0;
            pMap[cp - 'a']++;
            count++;
        }

        while (probe < s.length()) {
            char cp = s.charAt(probe);
            if (pMap[cp - 'a'] == -1) {
                if (probe == s.length() - 1) break;

                for (int i = start; i <= probe; i++) {
                    char ci = s.charAt(i);
                    if (pMap[ci - 'a'] != -1) {
                        pMap[ci - 'a']++;
                        count++;
                    }
                }

                probe++;
                start = probe;
            } else if (pMap[cp - 'a'] > 0) {
                pMap[cp - 'a']--;
                count--;

                if (count == 0) {
                    result.add(start);
                    char cs = s.charAt(start);
                    if (pMap[cs - 'a'] != -1) {
                        pMap[cs - 'a']++;
                        count++;
                    }

                    probe++;
                    start++;
                } else {
                    probe++;
                }
            } else {
                while (start <= probe && pMap[cp - 'a'] <= 0) {
                    char cs = s.charAt(start);
                    if (pMap[cs - 'a'] != -1) {
                        pMap[cs - 'a']++;
                        count++;
                    }
                    start++;
                }
            }
        }

        return result;
    }
}
