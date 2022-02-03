package com.kamarkaka;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * 140. Word Break II
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 *   Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 *   Output: ["cats and dog","cat sand dog"]
 *
 * Example 2:
 *   Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 *   Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 *   Explanation: Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 *   Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 *   Output: []
 *
 * Constraints:
 *   1 <= s.length <= 20
 *   1 <= wordDict.length <= 1000
 *   1 <= wordDict[i].length <= 10
 *   s and wordDict[i] consist of only lowercase English letters.
 *   All the strings of wordDict are unique.
 */
public class LC0140 {
   public List<String> wordBreak(String s, List<String> wordDict) {
      Set<String> wordSet = new HashSet<>(wordDict);
      List<String> res = new ArrayList<>();
      StringBuilder sb = new StringBuilder();

      wordBreak(s, sb, wordSet, res);

      return res;
   }

   private void wordBreak(String s, StringBuilder sb, Set<String> wordSet, List<String> res) {
      if (wordSet.contains(s)) {
         String tmp = sb.toString();
         if (sb.length() > 0) tmp += " ";
         res.add(tmp + s);
      }

      for (int i = 1; i < s.length(); i++) {
         String word1 = s.substring(0, i);
         if (wordSet.contains(word1)) {
            StringBuilder sb2 = new StringBuilder(sb);
            if (sb2.length() > 0) sb2.append(" ");
            sb2.append(word1);

            String word2 = s.substring(i);
            wordBreak(word2, sb2, wordSet, res);
         }
      }
   }
}
