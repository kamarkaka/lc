package com.kamarkaka;

import com.kamarkaka.common.Utilities;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LC0370 {
   public int[] getModifiedArray(int length, int[][] updates) {
      int[] arr = new int[length];
      PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
      for (int[] update : updates) {
         pq.add(new int[] {update[0], update[2]});
         pq.add(new int[] {update[1] + 1, -update[2]});
      }

      int pointer = 0;
      int incre = 0;
      while (pointer < length) {
         while (!pq.isEmpty() && pq.peek()[0] == pointer) {
            int[] op = pq.poll();
            incre += op[1];
         }

         arr[pointer] = incre;
         pointer++;
      }

      return arr;
   }

   public static void run() {
      LC0370 solution = new LC0370();
      Utilities.print(solution.getModifiedArray(5, new int[][] {{1,3,2},{2,4,3},{0,2,-2}}));
   }
}
