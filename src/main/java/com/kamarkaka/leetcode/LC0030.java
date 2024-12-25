package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 30. Substring with Concatenation of All Words
 * You are given a string s and an array of strings words. All the strings of words are of the same length.
 * A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
 * For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all
 * concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of
 * words.
 * Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any
 * order.
 * Example 1:
 *   Input: s = "barfoothefoobarman", words = ["foo","bar"]
 *   Output: [0,9]
 *   Explanation:
 *   The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
 *   The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
 * Example 2:
 *   Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 *   Output: []
 *   Explanation:
 *   There is no concatenated substring.
 * Example 3:
 *   Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 *   Output: [6,9,12]
 *   Explanation:
 *   The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
 *   The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
 *   The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].
 * Constraints:
 *   1 <= s.length <= 104
 *   1 <= words.length <= 5000
 *   1 <= words[i].length <= 30
 *   s and words[i] consist of lowercase English letters.
 */
public class LC0030 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return result;

        int count = words.length, wLen = words[0].length();
        if(s.length()<count*wLen) return result;

        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i < wLen; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int start = i, currCount = 0;
            for (int j = i; j<=s.length() - wLen; j += wLen) {
                String word = s.substring(j, j + wLen);
                if (wordsMap.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    currCount++;
                    if (seen.get(word) > wordsMap.get(word)) {
                        while (seen.get(word) > wordsMap.get(word)) {
                            String curr = s.substring(start, start + wLen);
                            seen.put(curr, seen.get(curr) - 1);
                            currCount--;
                            start += wLen;
                        }
                    }

                    if (currCount == count) {
                        result.add(start);
                        String first = s.substring(start, start + wLen);
                        seen.put(first, seen.get(first) - 1);
                        start += wLen;
                        currCount--;
                    }
                } else {
                    start = j + wLen;
                    currCount = 0;
                    seen.clear();
                }
            }
        }

        return result;
    }
}
