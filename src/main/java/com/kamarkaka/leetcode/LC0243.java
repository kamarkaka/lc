package com.kamarkaka.leetcode;

/***
 * 243. Shortest Word Distance
 * Given an array of strings wordsDict and two different strings that already exist in the array word1 and word2, return the shortest distance between these two words in the list.
 *
 * Example 1:
 *    Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
 *    Output: 3
 *
 * Example 2:
 *    Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 *    Output: 1
 *
 * Constraints:
 *    1 <= wordsDict.length <= 3 * 10^4
 *    1 <= wordsDict[i].length <= 10
 *    wordsDict[i] consists of lowercase English letters.
 *    word1 and word2 are in wordsDict.
 *    word1 != word2
 */
public class LC0243 {
   public int shortestDistance(String[] wordsDict, String word1, String word2) {
      int i1 = -1, i2 = -1;
      int minDistance = wordsDict.length;
      for (int i = 0; i < wordsDict.length; i++) {
         if (wordsDict[i].equals(word1)) {
            i1 = i;
         } else if (wordsDict[i].equals(word2)) {
            i2 = i;
         }

         if (i1 != -1 && i2 != -1) {
            minDistance = Math.min(minDistance, Math.abs(i1 - i2));
         }
      }
      return minDistance;
   }
}
