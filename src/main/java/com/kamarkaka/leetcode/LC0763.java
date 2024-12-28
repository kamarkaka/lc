package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 763. Partition Labels
 * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears
 * in at most one part.
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 * Return a list of integers representing the size of these parts.
 * Example 1:
 *   Input: s = "ababcbacadefegdehijhklij"
 *   Output: [9,7,8]
 *   Explanation:
 *   The partition is "ababcbaca", "defegde", "hijhklij".
 *   This is a partition so that each letter appears in at most one part.
 *   A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 * Example 2:
 *   Input: s = "eccbbbbdec"
 *   Output: [10]
 * Constraints:
 *   1 <= s.length <= 500
 *   s consists of lowercase English letters.
 */
public class LC0763 {
    public List<Integer> partitionLabels(String S) {
        List<Integer> result = new ArrayList<>();
        if (S == null || S.length() == 0) return result;

        int[] lastIndex = new int[26];
        for (int i = 0; i < S.length(); i++) {
            lastIndex[S.charAt(i) - 'a'] = i;
        }

        int i = 0, len = S.length();
        while (i < len) {
            int max = lastIndex[S.charAt(i) - 'a'];
            int start = i;
            while (i <= max) {
                char c = S.charAt(i);
                if (lastIndex[c - 'a'] > max) {
                    max = lastIndex[c - 'a'];
                }
                i++;
            }
            result.add(i - start);
        }

        return result;
    }
}
