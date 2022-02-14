package com.kamarkaka;

import java.util.*;

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
      System.out.println(solution.wordBreak("leetcode", new ArrayList<>(Arrays.asList(new String[] {"leet","code"}))));
      System.out.println(solution.wordBreak("applepenapple", new ArrayList<>(Arrays.asList(new String[] {"apple","pen"}))));
      System.out.println(solution.wordBreak("catsandog", new ArrayList<>(Arrays.asList(new String[] {"cats","dog","sand","and","cat"}))));
   }
}
