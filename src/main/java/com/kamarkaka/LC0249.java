package com.kamarkaka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 249. Group Shifted Strings
 * We can shift a string by shifting each of its letters to its successive letter.
 *    For example, "abc" can be shifted to be "bcd".
 * We can keep shifting the string to form a sequence.
 *    For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
 * Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.
 *
 * Example 1:
 *    Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
 *    Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
 *
 * Example 2:
 *    Input: strings = ["a"]
 *    Output: [["a"]]
 *
 * Constraints:
 *    1 <= strings.length <= 200
 *    1 <= strings[i].length <= 50
 *    strings[i] consists of lowercase English letters.
 */
public class LC0249 {
   public List<List<String>> groupStrings(String[] strings) {
      Map<String, List<String>> mapHashToList = new HashMap<>();

      for (String str : strings) {
         String hashKey = getHash(str);
         mapHashToList.putIfAbsent(hashKey, new ArrayList<>());
         mapHashToList.get(hashKey).add(str);
      }

      List<List<String>> res = new ArrayList<>();
      for (List<String> group : mapHashToList.values()) {
         res.add(group);
      }
      return res;
   }

   private String getHash(String s) {
      char[] chars = s.toCharArray();
      StringBuilder hashKey = new StringBuilder();

      for (int i = 1; i < chars.length; i++) {
         hashKey.append((char) ((chars[i] - chars[i - 1] + 26) % 26 + 'a'));
      }

      return hashKey.toString();
   }
}
