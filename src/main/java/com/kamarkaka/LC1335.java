package com.kamarkaka;

import java.util.Arrays;

/***
 * 1335. Minimum Difficulty of a Job Schedule
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
 * You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 *
 * Example 1:
 *    Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 *    Output: 7
 *    Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 *       Second day you can finish the last job, total difficulty = 1.
 *       The difficulty of the schedule = 6 + 1 = 7
 *
 * Example 2:
 *    Input: jobDifficulty = [9,9,9], d = 4
 *    Output: -1
 *    Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 *
 * Example 3:
 *    Input: jobDifficulty = [1,1,1], d = 3
 *    Output: 3
 *    Explanation: The schedule is one job per day. total difficulty will be 3.
 *
 * Constraints:
 *    1 <= jobDifficulty.length <= 300
 *    0 <= jobDifficulty[i] <= 1000
 *    1 <= d <= 10
 */
public class LC1335 {
   public int minDifficulty(int[] jobDifficulty, int d) {
      int n = jobDifficulty.length;
      if (n < d) return -1;

      int[][][] dp = new int[d][n][n];
      for (int i = 0; i < d; i++) {
         for (int j = 0; j < n; j++) {
            Arrays.fill(dp[i][j], -1);
         }
      }

      dp[0][0][0] = jobDifficulty[0];
      for (int i = 1; i < n; i++) {
         dp[0][0][i] = Math.max(jobDifficulty[i], dp[0][0][i-1]);
      }

      for (int day = 1; day < d; day++) {
         for (int start = day; start < n; start++) {
            int minPre = -1;

            for (int preStart = 0; preStart < start; preStart++) {
               if (dp[day-1][preStart][start-1] != -1 && (dp[day-1][preStart][start-1] < minPre || minPre == -1)) {
                  minPre = dp[day-1][preStart][start-1];
               }
            }

            dp[day][start][start] = minPre + jobDifficulty[start];

            for (int end = start + 1; end < n; end++) {
               dp[day][start][end] = Math.max(jobDifficulty[end] + minPre, dp[day][start][end - 1]);
            }
         }
      }

      int min = -1;
      for (int i = 0; i < n; i++) {
         if (dp[d-1][i][n-1] != -1 && (dp[d-1][i][n-1] < min || min == -1)) {
            min = dp[d-1][i][n-1];
         }
      }

      return min;
   }

   public static void run() {
      LC1335 solution = new LC1335();
      System.out.println(solution.minDifficulty(new int[]{6,5,4,3,2,1}, 2));
      System.out.println(solution.minDifficulty(new int[]{186,398,479,206,885,423,805,112,925,656,16,932,740,292,671,360}, 4));
   }
}
