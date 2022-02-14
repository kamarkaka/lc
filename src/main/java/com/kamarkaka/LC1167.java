package main.java.com.kamarkaka;

import java.util.PriorityQueue;

public class LC1167 {
   public int connectSticks(int[] sticks) {
      if (sticks == null || sticks.length < 2) return 0;
      PriorityQueue<Integer> queue = new PriorityQueue<>();
      for (int stick : sticks) {
         queue.add(stick);
      }

      int cost = 0;
      while (queue.size() > 1) {
         int stick1 = queue.poll();
         int stick2 = queue.poll();
         int newStick = stick1 + stick2;
         queue.add(newStick);
         cost += newStick;
      }

      return cost;
   }
}
