package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 139. Word Break
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 *    Input: s = "leetcode", wordDict = ["leet","code"]
 *    Output: true
 *    Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 *    Input: s = "applepenapple", wordDict = ["apple","pen"]
 *    Output: true
 *    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *       Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 *    Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 *    Output: false
 *
 * Constraints:
 *    1 <= s.length <= 300
 *    1 <= wordDict.length <= 1000
 *    1 <= wordDict[i].length <= 20
 *    s and wordDict[i] consist of only lowercase English letters.
 *    All the strings of wordDict are unique.
 */
public class LC0139 {
   public boolean wordBreak(String s, List<String> wordDict) {
      if (s == null || s.length() == 0) return false;
      HashSet<String> wordSet = new HashSet<>(wordDict);
      if (wordSet.contains(s)) return true;

      boolean[] dp = new boolean[s.length()];
      for (int i = 0; i < s.length(); i++) dp[i] = false;
      dp[0] = wordSet.contains(s.substring(0,1));

      for (int i = 1; i < s.length(); i++) {
         if (wordSet.contains(s.substring(0, i + 1))) {
            dp[i] = true;
            continue;
         }

         int j = i - 1;
         for (; j >= 0; j--) {
            int start = -1;
            if (dp[j]) {
               start = j + 1;
            } else if (j == 0) {
               start = 0;
            }

            if (start > -1) {
               if (wordSet.contains(s.substring(start, i + 1))) {
                  dp[i] = true;
                  break;
               }
            }
         }
      }

      return dp[s.length() - 1];
   }

   public static void run() {
      LC0139 solution =  new LC0139();
      System.out.println(solution.wordBreak("leetcode", new ArrayList<>(Arrays.asList("leet","code"))));
      System.out.println(solution.wordBreak("applepenapple", new ArrayList<>(Arrays.asList("apple","pen"))));
      System.out.println(solution.wordBreak("catsandog", new ArrayList<>(Arrays.asList("cats","dog","sand","and","cat"))));
   }
}
