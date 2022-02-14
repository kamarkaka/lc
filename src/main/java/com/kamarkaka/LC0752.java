package com.kamarkaka;

import java.util.*;

public class LC0752 {
   public int openLock(String[] deadends, String target) {
      Set<String> dead = new HashSet<>(Arrays.asList(deadends));

      Queue<String> queue = new LinkedList<>();
      queue.offer("0000");
      queue.offer(null);

      Set<String> seen = new HashSet<>();
      seen.add("0000");

      int depth = 0;
      while (!queue.isEmpty()) {
         String lock = queue.poll();
         if (lock == null) {
            depth++;
            if (queue.peek() != null) {
               queue.offer(null);
            }
         } else if (lock.equals(target)) {
            return depth;
         } else if (!dead.contains(lock)) {
            for (int i = 0; i < 4; i++) {
               for (int d = -1; d <= 1; d += 2) {
                  int y = ((lock.charAt(i) - '0') + d + 10) % 10;
                  String nextLock = lock.substring(0, i) + ("" + y) + lock.substring(i+1);
                  if (!seen.contains(nextLock)) {
                     seen.add(nextLock);
                     queue.offer(nextLock);
                  }
               }
            }
         }
      }
      return -1;
   }
}
