package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/***
 * 819. Most Common Word
 * Given a string paragraph and a string array of the banned words banned, return the most frequent word that is not banned. It is guaranteed there is at least one word that is not banned, and that the answer is unique.
 * The words in paragraph are case-insensitive and the answer should be returned in lowercase.
 *
 * Example 1:
 *   Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]
 *   Output: "ball"
 *   Explanation:
 *     "hit" occurs 3 times, but it is a banned word.
 *     "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 *     Note that words in the paragraph are not case sensitive,
 *     that punctuation is ignored (even if adjacent to words, such as "ball,"),
 *     and that "hit" isn't the answer even though it occurs more because it is banned.
 *
 * Example 2:
 *   Input: paragraph = "a.", banned = []
 *   Output: "a"
 *
 * Constraints:
 *   1 <= paragraph.length <= 1000
 *   paragraph consists of English letters, space ' ', or one of the symbols: "!?',;.".
 *   0 <= banned.length <= 100
 *   1 <= banned[i].length <= 10
 *   banned[i] consists of only lowercase English letters.
 */
public class LC0819 {
   public String mostCommonWord(String paragraph, String[] banned) {
      if (paragraph == null || paragraph.length() == 0) return "";
      paragraph = paragraph.toLowerCase();

      String res = "";
      int maxCount = 0;
      Map<String, Integer> wordMap = new HashMap<>();
      Set<String> bannedSet = new HashSet<>();
      for (String ban : banned) {
         bannedSet.add(ban);
      }

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < paragraph.length(); i++) {
         char c = paragraph.charAt(i);

         if ('a' <= c && c <= 'z') {
            sb.append(c);
         }

         if (('a' > c || c > 'z' || i == paragraph.length() - 1) && sb.length() > 0) {
            String word = sb.toString();
            sb = new StringBuilder();

            if (bannedSet.contains(word)) continue;

            int count = 1;
            if (wordMap.containsKey(word)) {
               count = wordMap.get(word) + 1;
            }

            if (count > maxCount) {
               maxCount = count;
               res = word;
            }
            wordMap.put(word, count);
         }
      }

      return res;
   }

   public static void run() {
      LC0819 solution = new LC0819();
      System.out.println(solution.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", new String[]{"hit"}));
      System.out.println(solution.mostCommonWord("a", new String[]{}));
      System.out.println(solution.mostCommonWord("Bob", new String[]{}));
   }
}
