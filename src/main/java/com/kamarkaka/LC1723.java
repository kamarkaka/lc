package com.kamarkaka;

/***
 * 1723. Find Minimum Time to Finish All Jobs
 * You are given an integer array jobs, where jobs[i] is the amount of time it takes to complete the ith job.
 * There are k workers that you can assign jobs to. Each job should be assigned to exactly one worker. The working time of a worker is the sum of the time it takes to complete all jobs assigned to them. Your goal is to devise an optimal assignment such that the maximum working time of any worker is minimized.
 * Return the minimum possible maximum working time of any assignment.
 *
 * Example 1:
 *    Input: jobs = [3,2,3], k = 3
 *    Output: 3
 *    Explanation: By assigning each person one job, the maximum time is 3.
 *
 * Example 2:
 *    Input: jobs = [1,2,4,7,8], k = 2
 *    Output: 11
 *    Explanation: Assign the jobs the following way:
 *       Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
 *       Worker 2: 4, 7 (working time = 4 + 7 = 11)
 *       The maximum working time is 11.
 *
 * Constraints:
 *    1 <= k <= jobs.length <= 12
 *    1 <= jobs[i] <= 10^7
 */
public class LC1723 {
   int min = Integer.MAX_VALUE;

   public int minimumTimeRequired(int[] jobs, int k) {
      backtrack(jobs, jobs.length - 1, new int[k]);
      return min;
   }

   private void backtrack(int[] jobs, int j, int[] sum) {
      int max = getMax(sum);
      if (max > min) return;

      if (j < 0) {
         min = Math.min(max, min);
         return;
      }

      for (int i = 0; i < sum.length; i++) {
         if (i > 0 && sum[i] == sum[i - 1]) continue;
         sum[i] += jobs[j];
         backtrack(jobs, j - 1, sum);
         sum[i] -= jobs[j];
      }

   }

   private int getMax(int[] sum) {
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < sum.length; i++) {
         max = Math.max(max, sum[i]);
      }
      return max;
   }
}
