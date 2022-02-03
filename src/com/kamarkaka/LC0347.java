package com.kamarkaka;

import java.util.*;

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
