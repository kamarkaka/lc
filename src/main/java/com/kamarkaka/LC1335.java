package main.java.com.kamarkaka;

import java.util.Arrays;

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
