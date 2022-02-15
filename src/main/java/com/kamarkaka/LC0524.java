package com.kamarkaka;

import java.util.List;

/***
 * 524. Longest Word in Dictionary through Deleting
 * Given a string s and a string array dictionary, return the longest string in the dictionary that can be formed by deleting some of the given string characters. If there is more than one possible result, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
 *
 * Example 1:
 *    Input: s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 *    Output: "apple"
 *
 * Example 2:
 *    Input: s = "abpcplea", dictionary = ["a","b","c"]
 *    Output: "a"
 *
 * Constraints:
 *    1 <= s.length <= 1000
 *    1 <= dictionary.length <= 1000
 *    1 <= dictionary[i].length <= 1000
 *    s and dictionary[i] consist of lowercase English letters.
 */
public class LC0524 {
   public String findLongestWord(String s, List<String> dictionary) {
      String maxStr = "";
      for (String word : dictionary) {
         if (isSubsequence(word, s)) {
            if (word.length() > maxStr.length() || (word.length() == maxStr.length() && word.compareTo(maxStr) < 0)) {
               maxStr = word;
            }
         }
      }
      return maxStr;
   }

   private boolean isSubsequence(String x, String y) {
      int j = 0;
      for (int i = 0; i < y.length() && j < x.length(); i++) {
         if (x.charAt(j) == y.charAt(i)) j++;
      }

      return j == x.length();
   }
}
