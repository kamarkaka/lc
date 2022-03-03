package com.kamarkaka.bloomberg;

import java.util.*;

public abstract class AbstractRateLimiter {
   private final int timeWindowSecond;
   private final int maxCapacity;
   private final Deque<Integer> deque;

   public AbstractRateLimiter(int timeWindowSecond, int maxCapacity) {
      this.timeWindowSecond = timeWindowSecond;
      this.maxCapacity = maxCapacity;
      this.deque = new LinkedList<>();
   }

   public boolean checkAPI(int timestamp) {
      if (!deque.isEmpty() && deque.peekFirst() <= timestamp - timeWindowSecond) {
         deque.pollFirst();
      }

      if (deque.size() >= maxCapacity) return false;

      deque.offerFirst(timestamp);
      return true;
   }
}
