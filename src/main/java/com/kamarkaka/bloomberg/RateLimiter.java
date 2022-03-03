package com.kamarkaka.bloomberg;

/***
 * an api rate limiter of capacity N
 */
public class RateLimiter extends AbstractRateLimiter {
   public RateLimiter(int timeWindowSecond, int maxCapacity) {
      super(timeWindowSecond, maxCapacity);
   }

   public boolean checkAPI(int timestamp) {
      return super.checkAPI(timestamp);
   }
}
