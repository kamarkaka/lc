package com.kamarkaka.uber;

import java.util.*;

public class ThreadRunning {
   public static int getMinRunTime(int[] jobRuntimes, int x, int y) {
      int opsCount = 0;

      PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(j -> -j));
      pq.addAll(Arrays.stream(jobRuntimes).boxed().toList());

      while (!pq.isEmpty()) {
         List<Integer> list = new ArrayList<>();

         int size = pq.size();
         for (int i = 0; i < size; ++i) {
            int runtime = pq.poll();
            if (i == 0) runtime -= x;
            else runtime -= y;

            if (runtime > 0) list.add(runtime);
         }

         pq.addAll(list);
         opsCount++;
      }
      return opsCount;
   }

   public static void main(String[] args) {
      int minOps = ThreadRunning.getMinRunTime(new int[] {3, 4, 1, 7, 6}, 4, 2);
      System.out.println(minOps);
   }
}
