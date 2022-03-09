package com.kamarkaka;

/***
 * 245. Shortest Word Distance III
 * Given an array of strings wordsDict and two strings that already exist in the array word1 and word2, return the shortest distance between these two words in the list.
 * Note that word1 and word2 may be the same. It is guaranteed that they represent two individual words in the list.
 *
 * Example 1:
 *    Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 *    Output: 1
 *
 * Example 2:
 *    Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
 *    Output: 3
 *
 * Constraints:
 *    1 <= wordsDict.length <= 10^5
 *    1 <= wordsDict[i].length <= 10
 *    wordsDict[i] consists of lowercase English letters.
 *    word1 and word2 are in wordsDict.
 */
public class LC0245 {
   public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
      int i1 = -1, i2 = -1, minLength = wordsDict.length;
      for (int i = 0; i < wordsDict.length; i++) {
         if (!word1.equals(word2)) {
            if (wordsDict[i].equals(word1)) {
               i1 = i;
            } else if (wordsDict[i].equals(word2)) {
               i2 = i;
            }
            if (i1 != -1 && i2 != -1) {
               minLength = Math.min(minLength, Math.abs(i1 - i2));
            }
         } else {
            if (wordsDict[i].equals(word1)) {
               if (i1 != -1) {
                  minLength = Math.min(minLength, Math.abs(i - i1));
               }
               i1 = i;
            }
         }
      }
      return minLength;
   }
}
