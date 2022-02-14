package com.kamarkaka;

import java.util.*;

public class LC0056 {
   public int[][] merge(int[][] intervals) {
      List<Integer> start = new ArrayList<>();
      Map<Integer, Integer> map = new HashMap<>();

      for (int[] interval : intervals) {
         start.add(interval[0]);
         map.put(interval[0], Math.max(interval[1], map.getOrDefault(interval[0], Integer.MIN_VALUE)));
      }

      Collections.sort(start);
      List<int[]> res = new ArrayList<>();

      int index = 0;
      int p0 = start.get(index), p1 = map.get(p0);
      index++;
      while (index < intervals.length) {
         int p0new = start.get(index);
         if (p1 < p0new) {
            res.add(new int[] {p0, p1});
            p0 = p0new;
            p1 = map.get(p0new);
         } else {
            p1 = Math.max(p1, map.get(p0new));
         }
         index++;
      }

      res.add(new int[] {p0, p1});
      return res.toArray(new int[0][]);
   }
}
