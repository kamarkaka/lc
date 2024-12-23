package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 472. Concatenated Words
 * Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 *
 * Example 1:
 *   Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 *   Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *   Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 *   "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 *   "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 *
 * Example 2:
 *   Input: words = ["cat","dog","catdog"]
 *   Output: ["catdog"]
 *
 * Constraints:
 *   1 <= words.length <= 10^4
 *   0 <= words[i].length <= 1000
 *   words[i] consists of only lowercase English letters.
 *   0 <= sum(words[i].length) <= 10^5
 */
public class LC0472 {
   public List<String> findAllConcatenatedWordsInADict(String[] words) {
      Set<String> set = new HashSet<>();
      Arrays.sort(words, Comparator.comparingInt(String::length));
      List<String> ans = new ArrayList<>();

      for (String word : words) {
         if (word.length() == 0) continue;
         if (solve(word, set)) ans.add(word);
         set.add(word);
      }
      return ans;
   }

   private boolean solve(String word, Set<String> set) {
      boolean[] dp = new boolean[word.length() + 1];
      dp[0] = true;

      for (int i = 1; i < dp.length; i++) {
         for (int j = i - 1; j >= 0; j--) {
            if(dp[j] && set.contains(word.substring(j, i))) {
               dp[i] = true;
               break;
            }
         }
      }

      return dp[word.length()];
   }
}
