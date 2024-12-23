package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 244. Shortest Word Distance II
 * Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.
 * Implement the WordDistance class:
 *    WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
 *    int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict.
 *
 * Example 1:
 *    Input
 *       ["WordDistance", "shortest", "shortest"]
 *       [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
 *    Output
 *       [null, 3, 1]
 *    Explanation
 *       WordDistance wordDistance = new WordDistance(["practice", "makes", "perfect", "coding", "makes"]);
 *       wordDistance.shortest("coding", "practice"); // return 3
 *       wordDistance.shortest("makes", "coding");    // return 1
 *
 * Constraints:
 *    1 <= wordsDict.length <= 3 * 10^4
 *    1 <= wordsDict[i].length <= 10
 *    wordsDict[i] consists of lowercase English letters.
 *    word1 and word2 are in wordsDict.
 *    word1 != word2
 *    At most 5000 calls will be made to shortest.
 */
public class LC0244 {
   class WordDistance {
      Map<String, List<Integer>> locations;

      public WordDistance(String[] wordsDict) {
         this.locations = new HashMap<>();

         for (int i = 0; i < wordsDict.length; i++) {
            this.locations.putIfAbsent(wordsDict[i], new ArrayList<>());
            this.locations.get(wordsDict[i]).add(i);
         }
      }

      public int shortest(String word1, String word2) {
         List<Integer> loc1 = this.locations.get(word1);
         List<Integer> loc2 = this.locations.get(word2);

         int l1 = 0, l2 = 0, minDiff = Integer.MAX_VALUE;
         while (l1 < loc1.size() && l2 < loc2.size()) {
            minDiff = Math.min(minDiff, Math.abs(loc1.get(l1) - loc2.get(l2)));
            if (loc1.get(l1) < loc2.get(l2)) {
               l1++;
            } else {
               l2++;
            }
         }

         return minDiff;
      }
   }
}
