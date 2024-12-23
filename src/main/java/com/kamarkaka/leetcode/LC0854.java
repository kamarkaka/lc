package com.kamarkaka.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/***
 * 854. K-Similar Strings
 * Strings s1 and s2 are k-similar (for some non-negative integer k) if we can swap the positions of two letters in s1 exactly k times so that the resulting string equals s2.
 * Given two anagrams s1 and s2, return the smallest k for which s1 and s2 are k-similar.
 *
 * Example 1:
 *    Input: s1 = "ab", s2 = "ba"
 *    Output: 1
 *
 * Example 2:
 *    Input: s1 = "abc", s2 = "bca"
 *    Output: 2
 *
 * Constraints:
 *    1 <= s1.length <= 20
 *    s2.length == s1.length
 *    s1 and s2 contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}.
 *    s2 is an anagram of s1.
 */
public class LC0854 {
   public int kSimilarity(String s1, String s2) {
      if (s1.equals(s2)) return 0;

      HashSet<String> visited = new HashSet<>();
      Queue<String> queue = new LinkedList<>();

      visited.add(s1);
      queue.offer(s1);

      int k = 0;
      while (!queue.isEmpty()) {
         k++;
         for (int qSize = queue.size(); qSize > 0; qSize--) {
            String s = queue.poll();
            int i = 0;
            while (s.charAt(i) == s2.charAt(i)) i++;
            for (int j = i + 1; j < s.length(); j++) {
               if (s.charAt(j) == s2.charAt(j) || s.charAt(j) != s2.charAt(i)) continue;
               String temp = swap(s, i, j);
               if (temp.equals(s2)) return k;
               if (visited.add(temp)) queue.offer(temp);
            }
         }
      }
      return k;
   }

   private String swap(String s, int i, int j) {
      char[] charArray = s.toCharArray();
      char temp = charArray[i];
      charArray[i] = charArray[j];
      charArray[j] = temp;
      return new String(charArray);
   }
}
