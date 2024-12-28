package com.kamarkaka.leetcode;

/***
 * 935. Knight Dialer
 * The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two
 * squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of chess
 * knight are shown in this diagram:
 * A chess knight can move as indicated in the chess diagram below:
 * We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).
 * 1 2 3
 * 4 5 6
 * * 0 #
 * Given an integer n, return how many distinct phone numbers of length n we can dial.
 * You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a
 * number of length n. All jumps should be valid knight jumps.
 * As the answer may be very large, return the answer modulo 10^9 + 7.
 * Example 1:
 *   Input: n = 1
 *   Output: 10
 *   Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is
 *   sufficient.
 * Example 2:
 *   Input: n = 2
 *   Output: 20
 *   Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76,
 *   81, 83, 92, 94]
 * Example 3:
 *   Input: n = 3131
 *   Output: 136006598
 *   Explanation: Please take care of the mod.
 * Constraints:
 *   1 <= n <= 5000
 */
public class LC0935 {
    private static final int MOD = 1_000_000_007;

    public int knightDialer(int N) {
        int[][] possibleMoves = new int[10][3];
        possibleMoves[0] = new int[] {4,6,-1};
        possibleMoves[1] = new int[] {6,8,-1};
        possibleMoves[2] = new int[] {7,9,-1};
        possibleMoves[3] = new int[] {4,8,-1};
        possibleMoves[4] = new int[] {3,9,0};
        possibleMoves[5] = new int[] {-1,-1,-1};
        possibleMoves[6] = new int[] {1,7,0};
        possibleMoves[7] = new int[] {2,6,-1};
        possibleMoves[8] = new int[] {1,3,-1};
        possibleMoves[9] = new int[] {2,4,-1};

        int[] dpCurrent = new int[10];
        int[] dpNext = new int[10];
        for (int i = 0; i < 10; i++) {
            dpCurrent[i] = 1;
        }

        for (int j = 1; j < N; j++) {
            for (int i = 0; i < 10; i++) {
                int[] nextNums = possibleMoves[i];
                for (int num : nextNums) {
                    if (num == -1) continue;
                    dpNext[num] += dpCurrent[i] % MOD;
                    dpNext[num] %= MOD;
                }
            }

            dpCurrent = dpNext;
            dpNext = new int[10];
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += dpCurrent[i] % MOD;
            sum %= MOD;
        }
        return sum;
    }
}
