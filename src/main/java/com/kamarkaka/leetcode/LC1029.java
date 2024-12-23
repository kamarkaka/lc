package com.kamarkaka.leetcode;

import java.util.PriorityQueue;

/***
 * 1029. Two City Scheduling
 * A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti], the cost of flying the ith person to city a is aCosti, and the cost of flying the ith person to city b is bCosti.
 * Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.
 *
 * Example 1:
 *   Input: costs = [[10,20],[30,200],[400,50],[30,20]]
 *   Output: 110
 * Explanation:
 *   The first person goes to city A for a cost of 10.
 *   The second person goes to city A for a cost of 30.
 *   The third person goes to city B for a cost of 50.
 *   The fourth person goes to city B for a cost of 20.
 *   The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 *
 * Example 2:
 *   Input: costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
 *   Output: 1859
 *
 * Example 3:
 *   Input: costs = [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,359],[631,42]]
 *   Output: 3086
 *
 * Constraints:
 *   2 * n == costs.length
 *   2 <= costs.length <= 100
 *   costs.length is even.
 *   1 <= aCosti, bCosti <= 1000
 */
public class LC1029 {
   public int twoCitySchedCost(int[][] costs) {
      int n = costs.length / 2;
      int countA = 0, countB = 0, minCost = 0;
      PriorityQueue<Integer> pqA = new PriorityQueue<>();
      PriorityQueue<Integer> pqB = new PriorityQueue<>();

      for (int[] cost : costs) {
         if (cost[0] < cost[1]) {
            countA++;
            minCost += cost[0];
            pqA.add(cost[1] - cost[0]);
         } else {
            countB++;
            minCost += cost[1];
            pqB.add(cost[0] - cost[1]);
         }
      }

      if (countA == n) return minCost;

      if (countA > n) {
         for (int i = 0; i < n - countB; i++) {
            minCost += pqA.poll();
         }
      } else {
         for (int i = 0; i < n - countA; i++) {
            minCost += pqB.poll();
         }
      }

      return minCost;
   }

   public static void run() {
      LC1029 solution = new LC1029();
      System.out.println(solution.twoCitySchedCost(new int[][] {
         {259,770},{448,54},{926,667},{184,139},{840,118},{577,469}
      }));
   }
}
