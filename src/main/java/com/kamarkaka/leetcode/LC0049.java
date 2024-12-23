package com.kamarkaka.leetcode;

import com.kamarkaka.common.Utilities;

import java.util.*;

/***
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 * Example 1:
 *   Input: strs = ["eat","tea","tan","ate","nat","bat"]
 *   Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Example 2:
 *   Input: strs = [""]
 *   Output: [[""]]
 *
 * Example 3:
 *   Input: strs = ["a"]
 *   Output: [["a"]]
 *
 * Constraints:
 *   1 <= strs.length <= 10^4
 *   0 <= strs[i].length <= 100
 *   strs[i] consists of lowercase English letters.
 */
public class LC0049 {
   public List<List<String>> groupAnagrams(String[] strs) {
      List<List<String>> res = new ArrayList<>();
      if (strs == null || strs.length == 0) return res;

      Map<String, List<String>> hashMap = new HashMap<>();

      for(String str : strs){
         char[] arr = str.toCharArray();
         Arrays.sort(arr);
         String signature = new String(arr);

         if (hashMap.containsKey(signature)) {
            hashMap.get(signature).add(str);
         } else {
            List<String> list = new ArrayList<>();
            list.add(str);
            hashMap.put(signature, list);
         }
      }

      for(List<String> list : hashMap.values()) {
         res.add(list);
      }

      return res;
   }

   public static void run() {
      LC0049 solution = new LC0049();
      Utilities.print(solution.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
      Utilities.print(solution.groupAnagrams(new String[]{""}));
      Utilities.print(solution.groupAnagrams(new String[]{"a"}));
   }
}
