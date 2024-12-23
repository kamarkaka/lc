package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 1235. Maximum Profit in Job Scheduling
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 *
 * Example 1:
 *    Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 *    Output: 120
 *    Explanation: The subset chosen is the first and fourth job.
 *    Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 *
 * Example 2:
 *    Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 *    Output: 150
 *    Explanation: The subset chosen is the first, fourth and fifth job.
 *    Profit obtained 150 = 20 + 70 + 60.
 *
 * Example 3:
 *    Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 *    Output: 6
 *
 * Constraints:
 *    1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 *    1 <= startTime[i] < endTime[i] <= 10^9
 *    1 <= profit[i] <= 10^4
 */
public class LC1235 {
   public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
      List<int[]> jobs = new ArrayList<>();
      for (int i = 0; i < startTime.length; i++) {
         jobs.add(new int[] {startTime[i], endTime[i], profit[i]});
      }

      jobs.sort(Comparator.comparingInt(a -> a[0]));
      return findMaxProfit(jobs);
   }

   private int findMaxProfit(List<int[]> jobs) {
      int maxProfit = 0;
      PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o1 -> o1[0]));

      for (int i = 0; i < jobs.size(); i++) {
         int[] job = jobs.get(i);
         int start = job[0], end = job[1], profit = job[2];

         while (!pq.isEmpty() && start >= pq.peek()[0]) {
            maxProfit = Math.max(maxProfit, pq.peek()[1]);
            pq.remove();
         }

         pq.add(new int[] {end, maxProfit + profit});
      }

      while (!pq.isEmpty()) {
         maxProfit = Math.max(maxProfit, pq.peek()[1]);
         pq.remove();
      }

      return maxProfit;
   }

   public static void run() {
      LC1235 sol = new LC1235();
      System.out.println(sol.jobScheduling(
         new int[] {1,2,3,3},
         new int[] {3,4,5,6},
         new int[] {5,1,4,7}
      ));
   }
}
