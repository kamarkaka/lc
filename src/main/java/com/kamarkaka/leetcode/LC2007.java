package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 2007. Find Original Array From Doubled Array
 * An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.
 * Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.
 *
 * Example 1:
 *    Input: changed = [1,3,4,2,6,8]
 *    Output: [1,3,4]
 *    Explanation: One possible original array could be [1,3,4]:
 *       - Twice the value of 1 is 1 * 2 = 2.
 *       - Twice the value of 3 is 3 * 2 = 6.
 *       - Twice the value of 4 is 4 * 2 = 8.
 *       Other original arrays could be [4,3,1] or [3,1,4].
 *
 * Example 2:
 *    Input: changed = [6,3,0,1]
 *    Output: []
 *    Explanation: changed is not a doubled array.
 *
 * Example 3:
 *    Input: changed = [1]
 *    Output: []
 *    Explanation: changed is not a doubled array.
 *
 * Constraints:
 * 1 <= changed.length <= 10^5
 * 0 <= changed[i] <= 10^5
 */
public class LC2007 {
   public int[] findOriginalArray(int[] changed) {
      int len = changed.length / 2;
      int[] res = new int[len];

      if (len == 0) return res;

      Map<Integer, Integer> map = new HashMap<>();
      for (int num : changed) {
         map.put(num, map.getOrDefault(num, 0) + 1);
      }

      List<Integer> list = new ArrayList<>(map.keySet());
      Collections.sort(list);

      int idx = 0;
      for (int num : list) {
         if (map.get(num) == 0) continue;

         int count = map.get(num);
         if (!map.containsKey(num * 2) || map.get(num * 2) < count) return new int[0];

         if (num == 0) {
            if (count % 2 == 1) return new int[0];
            for (int i = 0; i < count / 2; i++) {
               res[idx++] = num;
            }
            map.put(num, 0);
            continue;
         }

         map.put(num, 0);
         map.put(num * 2, map.get(num * 2) - count);

         for (int i = 0; i < count; i++) {
            res[idx++] = num;
         }
      }

      return res;
   }
}
