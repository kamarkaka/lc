package com.kamarkaka;

public class LC0887 {
   public int superEggDrop(int k, int n) {
      int step = 0;
      for (int floor = 0; floor <= n; floor++) {
         step = Math.max(step, solve(k, n, floor));
      }
      return step;
   }

   private int solve(int eggCount, int total, int floor) {
      int lo = 1, hi = total;

      int step = 0;
      while (eggCount > 0 && hi > lo) {
         int mid = lo + (hi - lo) / 2;
         if (floor > mid) {
            lo = mid + 1;
         } else {
            eggCount--;
            hi = mid - 1;
         }
         step++;
      }

      return step;
   }
}
