package com.kamarkaka.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/***
 * 857. Minimum Cost to Hire K Workers
 * There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.
 * We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:
 *    Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 *    Every worker in the paid group must be paid at least their minimum wage expectation.
 * Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions. Answers within 10-5 of the actual answer will be accepted.
 *
 * Example 1:
 *    Input: quality = [10,20,5], wage = [70,50,30], k = 2
 *    Output: 105.00000
 *    Explanation: We pay 70 to 0th worker and 35 to 2nd worker.
 *
 * Example 2:
 *    Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
 *    Output: 30.66667
 *    Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.
 *
 * Constraints:
 *    n == quality.length == wage.length
 *    1 <= k <= n <= 10^4
 *    1 <= quality[i], wage[i] <= 10^4
 */
public class LC0857 {
   public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
      int n = quality.length;
      Worker[] workers = new Worker[n];
      for (int i = 0; i < n; i++) {
         workers[i] = new Worker(quality[i], wage[i]);
      }
      Arrays.sort(workers);

      double ans = 1e9;
      int sumq = 0;
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      for (Worker worker : workers) {
         pq.add(-worker.quality);
         sumq += worker.quality;

         if (pq.size() > k) {
            sumq += pq.poll();
         }
         if (pq.size() == k) {
            ans = Math.min(ans, sumq * worker.getRatio());
         }
      }
      return ans;
   }

   private class Worker implements Comparable<Worker> {
      int quality;
      int wage;

      public Worker(int quality, int wage) {
         this.quality = quality;
         this.wage = wage;
      }

      public double getRatio() {
         return (double) wage/ quality;
      }

      public int compareTo(Worker that) {
         return Double.compare(this.getRatio(), that.getRatio());
      }
   }
}
