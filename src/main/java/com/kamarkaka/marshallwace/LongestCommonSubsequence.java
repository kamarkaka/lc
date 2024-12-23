package com.kamarkaka.marshallwace;

public class LongestCommonSubsequence {
    public int solve(String text1, String text2) {
        int m = text1.length(), n = text2.length();

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = text1.charAt(i) == text2.charAt(j) ? 1 : 0;
                } else if (i == 0) {
                    dp[i][j] = Math.max(dp[i][j-1], (text1.charAt(i) == text2.charAt(j) ? 1 : 0));
                } else if (j == 0) {
                    dp[i][j] = Math.max(dp[i-1][j], (text1.charAt(i) == text2.charAt(j) ? 1 : 0));
                } else {
                    int max = dp[i][j];
                    max = Math.max(max, dp[i][j-1]);
                    max = Math.max(max, dp[i-1][j]);
                    max = Math.max(max, dp[i-1][j-1] + (text1.charAt(i) == text2.charAt(j) ? 1 : 0));
                    dp[i][j] = max;
                }
            }
        }

        return dp[m-1][n-1];
    }

/**
    a b c d e
    0 1 2 3 4
a 0 1 1 1 1 1
c 1 1 1 2 2 2
e 2 1 1 2 2 3

 */




    public static void main(String[] args) {
        LongestCommonSubsequence solution = new LongestCommonSubsequence();
        System.out.println(solution.solve("ace", "abcde"));

    }
}
