package com.kamarkaka;

import java.util.*;

/***
 * 347. Top K Frequent Elements
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 * Example 1:
 *    Input: nums = [1,1,1,2,2,3], k = 2
 *    Output: [1,2]
 *
 * Example 2:
 *    Input: nums = [1], k = 1
 *    Output: [1]
 *
 * Constraints:
 *    1 <= nums.length <= 10^5
 *    k is in the range [1, the number of unique elements in the array].
 *    It is guaranteed that the answer is unique.
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class LC0347 {
   public int[] topKFrequent(int[] nums, int k) {
      Map<Integer, Integer> map = new HashMap<>();
      for (int num : nums) {
         map.put(num, map.getOrDefault(num, 0) + 1);
      }

      ArrayList<Integer>[] buckets = new ArrayList[nums.length + 1];
      for (int num : map.keySet()){
         int frequency = map.get(num);
         if (buckets[frequency] == null) {
            buckets[frequency] = new ArrayList<>();
         }
         buckets[frequency].add(num);
      }

      List<Integer> topK = new ArrayList<>();
      for (int i = buckets.length - 1; i >= 0 && topK.size() < k; i--) {
         if (buckets[i] == null) {
            continue;
         }

         if (buckets[i].size() <= k - topK.size()) {
            topK.addAll(buckets[i]);
         } else {
            topK.addAll(buckets[i].subList(0, k - topK.size()));
         }
      }
      int[] res = new int[k];
      for (int i = 0; i < k; i++) {
         res[i] = topK.get(i);
      }
      return res;
   }
}
