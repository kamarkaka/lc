package main.java.com.kamarkaka;

import java.util.*;

/***
 * 1048. Longest String Chain
 * You are given an array of words where each word consists of lowercase English letters.
 * wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.
 *   For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
 * A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
 * Return the length of the longest possible word chain with words chosen from the given list of words.
 *
 * Example 1:
 *   Input: words = ["a","b","ba","bca","bda","bdca"]
 *   Output: 4
 *   Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
 *
 * Example 2:
 *   Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 *   Output: 5
 *   Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
 *
 * Example 3:
 *   Input: words = ["abcd","dbqca"]
 *   Output: 1
 *   Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
 *   ["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.
 *
 * Constraints:
 *   1 <= words.length <= 1000
 *   1 <= words[i].length <= 16
 *   words[i] only consists of lowercase English letters.
 */
public class LC1048 {
   public int longestStrChain(String[] words) {
      Arrays.sort(words, Comparator.comparingInt(String::length));

      Map<String, Integer> wordMap = new HashMap<>();
      for (int i = 0; i < words.length; i++) {
         String word = words[i];
         wordMap.put(word, 1);
      }

      int maxLen = 1;
      for (int i = 0; i < words.length; i++) {
         String word = words[i];
         int chainLen = findChainLen(word, wordMap);
         maxLen = Math.max(maxLen, chainLen);
      }

      return maxLen;
   }

   private int findChainLen(String word, Map<String, Integer> wordMap) {
      if (word.length() == 1 && wordMap.containsKey(word))
         return 1;

      int max = 1;
      for (int i = 0; i < word.length(); i++) {
         char[] chars = new char[word.length() - 1];
         int pn = 0, po = 0;
         while (pn < chars.length) {
            if (po == i) po++;
            chars[pn++] = word.charAt(po++);
         }

         String potentialWord = String.valueOf(chars);
         if (wordMap.containsKey(potentialWord)) {
            int len = wordMap.get(potentialWord) + 1;
            if (len > max) max = len;
         }
      }

      wordMap.put(word, max);
      return wordMap.get(word);
   }

   public static void run() {
      LC1048 solution = new LC1048();
      System.out.println(solution.longestStrChain(new String[] {"xbc","pcxbcf","xb","cxbc","pcxbc"}));
      System.out.println(solution.longestStrChain(new String[] {"a","b","ab","bac"}));
      System.out.println(solution.longestStrChain(new String[] {"abcd","dbqca"}));
      System.out.println(solution.longestStrChain(new String[] {"a","b","ba","bca","bda","bdca"}));
   }
}
