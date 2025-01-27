package com.kamarkaka.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/***
 * 1797. Design Authentication Manager
 * There is an authentication system that works with authentication tokens. For each session, the user will receive a
 * new authentication token that will expire timeToLive seconds after the currentTime. If the token is renewed, the
 * expiry time will be extended to expire timeToLive seconds after the (potentially different) currentTime.
 * Implement the AuthenticationManager class:
 *   AuthenticationManager(int timeToLive) constructs the AuthenticationManager and sets the timeToLive.
 *   generate(string tokenId, int currentTime) generates a new token with the given tokenId at the given currentTime in
 *   seconds.
 *   renew(string tokenId, int currentTime) renews the unexpired token with the given tokenId at the given currentTime
 *   in seconds. If there are no unexpired tokens with the given tokenId, the request is ignored, and nothing happens.
 *   countUnexpiredTokens(int currentTime) returns the number of unexpired tokens at the given currentTime.
 * Note that if a token expires at time t, and another action happens on time t (renew or countUnexpiredTokens), the
 * expiration takes place before the other actions.
 * Example 1:
 *   Input
 *   ["AuthenticationManager", "renew", "generate", "countUnexpiredTokens", "generate", "renew", "renew",
 *   "countUnexpiredTokens"]
 *   [[5], ["aaa", 1], ["aaa", 2], [6], ["bbb", 7], ["aaa", 8], ["bbb", 10], [15]]
 *   Output
 *   [null, null, null, 1, null, null, null, 0]
 *   Explanation
 *   AuthenticationManager authenticationManager = new AuthenticationManager(5); // Constructs the AuthenticationManager
 *   with timeToLive = 5 seconds.
 *   authenticationManager.renew("aaa", 1); // No token exists with tokenId "aaa" at time 1, so nothing happens.
 *   authenticationManager.generate("aaa", 2); // Generates a new token with tokenId "aaa" at time 2.
 *   authenticationManager.countUnexpiredTokens(6); // The token with tokenId "aaa" is the only unexpired one at time 6,
 *   so return 1.
 *   authenticationManager.generate("bbb", 7); // Generates a new token with tokenId "bbb" at time 7.
 *   authenticationManager.renew("aaa", 8); // The token with tokenId "aaa" expired at time 7, and 8 >= 7, so at time 8
 *   the renew request is ignored, and nothing happens.
 *   authenticationManager.renew("bbb", 10); // The token with tokenId "bbb" is unexpired at time 10, so the renew
 *   request is fulfilled and now the token will expire at time 15.
 *   authenticationManager.countUnexpiredTokens(15); // The token with tokenId "bbb" expires at time 15, and the token
 *   with tokenId "aaa" expired at time 7, so currently no token is unexpired, so return 0.
 * Constraints:
 *   1 <= timeToLive <= 10^8
 *   1 <= currentTime <= 10^8
 *   1 <= tokenId.length <= 5
 *   tokenId consists only of lowercase letters.
 *   All calls to generate will contain unique values of tokenId.
 *   The values of currentTime across all the function calls will be strictly increasing.
 *   At most 2000 calls will be made to all functions combined.
 */
public class LC1797 {
   class AuthenticationManager {
      private final int timeToLive;
      private final PriorityQueue<Pair> pq;
      private final Map<String, Integer> tokenMap;

      class Pair {
         String tokenId;
         int expireTime;

         public Pair(String tokenId, int expireTime) {
            this.tokenId = tokenId;
            this.expireTime = expireTime;
         }
      }

      public AuthenticationManager(int timeToLive) {
         this.timeToLive = timeToLive;
         this.pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.expireTime));
         this.tokenMap = new HashMap<>();
      }

      public void generate(String tokenId, int currentTime) {
         if (!this.tokenMap.containsKey(tokenId)) {
            this.pq.offer(new Pair(tokenId, currentTime + this.timeToLive));
         }
         this.tokenMap.put(tokenId, currentTime + this.timeToLive);

      }

      public void renew(String tokenId, int currentTime) {
         if (this.tokenMap.getOrDefault(tokenId, 0) > currentTime) {
            this.tokenMap.put(tokenId, currentTime + this.timeToLive);
         }
      }

      public int countUnexpiredTokens(int currentTime) {
         while (!this.pq.isEmpty() && this.pq.peek().expireTime <= currentTime) {
            Pair p = this.pq.poll();
            if (this.tokenMap.get(p.tokenId) > p.expireTime) {
               this.pq.offer(new Pair(p.tokenId, this.tokenMap.get(p.tokenId)));
            } else {
               this.tokenMap.remove(p.tokenId);
            }
         }
         return this.pq.size();
      }
   }
}
