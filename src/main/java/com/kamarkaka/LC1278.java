package com.kamarkaka;

import java.util.HashMap;
import java.util.Map;

/***
 * 1278. Palindrome Partitioning III
 * You are given a string s containing lowercase letters and an integer k. You need to :
 *    First, change some characters of s to other lowercase English letters.
 *    Then divide s into k non-empty disjoint substrings such that each substring is a palindrome.
 * Return the minimal number of characters that you need to change to divide the string.
 *
 * Example 1:
 *    Input: s = "abc", k = 2
 *    Output: 1
 *    Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
 *
 * Example 2:
 *    Input: s = "aabbc", k = 3
 *    Output: 0
 *    Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
 *
 * Example 3:
 *    Input: s = "leetcode", k = 8
 *    Output: 0
 *
 * Constraints:
 *    1 <= k <= s.length <= 100.
 *    s only contains lowercase English letters.
 */
public class LC1278 {
   Map<Integer, Map<String, Integer>> map = new HashMap<>();
   public int palindromePartition(String s, int k) {
      if (s.length() == k) return 0;

      if (map.containsKey(k) && map.get(k).containsKey(s)) {
         return map.get(k).get(s);
      }

      if (k == 1) {
         int i = 0, j = s.length() - 1;
         int count = 0;
         while (i < j) {
            if (s.charAt(i) != s.charAt(j)) count++;
            i++;
            j--;
         }
         map.putIfAbsent(k, new HashMap<>());
         map.get(k).put(s, count);
         return count;
      }

      int min = Integer.MAX_VALUE;
      for (int i = 1; i < s.length(); i++) {
         String s1 = s.substring(0, i);
         String s2 = s.substring(i);

         if (s2.length() < k - 1) break;

         int s1count = palindromePartition(s1, 1);
         if (s1count >= min) continue;
         min = Math.min(min, s1count + palindromePartition(s2, k - 1));
      }

      map.putIfAbsent(k, new HashMap<>());
      map.get(k).put(s, min);
      return min;
   }

   public int palindromePartition2(String s, int k) {
      int[][] costs = calcCosts(s.toCharArray());
      int[][] cache = new int[costs.length][k];
      for (int i = 0; i < cache.length; i++) {
         for (int j = 0; j < k; j++) {
            cache[i][j] = -1;
         }
      }
      return getMinCost(costs, 0, costs.length - 1, k, cache);
   }

   private int getMinCost(int[][] costs, int startIdx, int endIdx, int k, int[][] cache) {
      if (cache[startIdx][k-1] != -1) return cache[startIdx][k-1];

      if (k == 1) {
         cache[startIdx][k-1] = costs[startIdx][endIdx];
         return costs[startIdx][endIdx];
      }

      int min = Integer.MAX_VALUE;
      for (int i = startIdx; i <= endIdx - k + 1; i++) {
         int cost = costs[startIdx][i] + getMinCost(costs, i + 1, endIdx, k - 1, cache);
         if (cost < min) min = cost;
      }
      cache[startIdx][k-1] = min;
      return min;
   }

   private int[][] calcCosts(char[] chrs) {
      int len = chrs.length;
      int[][] costs = new int[len][len];

      for (int i = 0; i < len; i++) {
         expandCosts(chrs, i, i, costs);
         expandCosts(chrs, i, i + 1, costs);
      }
      return costs;
   }

   private void expandCosts(char[] chrs, int startIdx, int endIdx, int[][] costs) {
      int cost = 0;
      while (startIdx >= 0 && endIdx < chrs.length) {
         if (chrs[startIdx] != chrs[endIdx]) {
            cost++;
         }
         costs[startIdx][endIdx] = cost;
         startIdx--;
         endIdx++;
      }
   }

   public static void run() {
      LC1278 sol = new LC1278();
      System.out.println(sol.palindromePartition("fyhowoxzyrincxivwarjuwxrwealesxsimsepjdqsstfggjnjhilvrwwytbgsqbpnwjaojfnmiqiqnyzijfmvekgakefjaxryyml", 32));
      System.out.println(sol.palindromePartition("leetcode", 8));
      System.out.println(sol.palindromePartition("aabbc", 3));
      System.out.println(sol.palindromePartition("abc", 2));
   }
}
