package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***
 * 131. Palindrome Partitioning
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 * A palindrome string is a string that reads the same backward as forward.
 *
 * Example 1:
 *    Input: s = "aab"
 *    Output: [["a","a","b"],["aa","b"]]
 *
 * Example 2:
 *    Input: s = "a"
 *    Output: [["a"]]
 *
 * Constraints:
 *    1 <= s.length <= 16
 *    s contains only lowercase English letters.
 */
public class LC0131 {
   public List<List<String>> partition(String s) {
      int len = s.length();
      boolean[][] dp = new boolean[len][len];
      List<List<String>> result = new ArrayList<>();
      dfs(result, s, 0, new LinkedList<>(), dp);
      return result;
   }

   private void dfs(List<List<String>> result, String s, int start, LinkedList<String> currentList, boolean[][] dp) {
      if (start >= s.length()) {
         result.add(new ArrayList<>(currentList));
         return;
      }

      for (int end = start; end < s.length(); end++) {
         if (s.charAt(start) == s.charAt(end) && (end - start <= 2 || dp[start + 1][end - 1])) {
            dp[start][end] = true;
            currentList.addLast(s.substring(start, end + 1));
            dfs(result, s, end + 1, currentList, dp);
            currentList.pollLast();
         }
      }
   }
}
