package com.kamarkaka.bloomberg;

import java.util.ArrayDeque;
import java.util.Deque;

/***
 * an api rate limiter of capacity N
 */
public class RateLimiter {
   private final Deque<Integer> deque;
   private final int capacity;

   public RateLimiter(int N) {
      this.deque = new ArrayDeque<>();
      this.capacity = N;
   }

   public boolean checkAPI(int timestamp) {
      if (!deque.isEmpty() && deque.peekFirst() <= timestamp - 1000) {
         deque.pollFirst();
      }

      if (deque.size() >= capacity) return false;

      deque.offerFirst(timestamp);
      return true;
   }
}
