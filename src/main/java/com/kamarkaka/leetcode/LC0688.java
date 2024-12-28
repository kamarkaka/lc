package com.kamarkaka.leetcode;

/***
 * 688. Knight Probability in Chessboard
 * On an n x n chessboard, a knight starts at the cell (row, column) and attempts to make exactly k moves. The rows and
 * columns are 0-indexed, so the top-left cell is (0, 0), and the bottom-right cell is (n - 1, n - 1).
 * A chess knight has eight possible moves it can make, as illustrated below. Each move is two cells in a cardinal
 * direction, then one cell in an orthogonal direction.
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would
 * go off the chessboard) and moves there.
 * The knight continues moving until it has made exactly k moves or has moved off the chessboard.
 * Return the probability that the knight remains on the board after it has stopped moving.
 * Example 1:
 *   Input: n = 3, k = 2, row = 0, column = 0
 *   Output: 0.06250
 *   Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
 *   From each of those positions, there are also two moves that will keep the knight on the board.
 *   The total probability the knight stays on the board is 0.0625.
 * Example 2:
 *   Input: n = 1, k = 0, row = 0, column = 0
 *   Output: 1.00000
 * Constraints:
 *   1 <= n <= 25
 *   0 <= k <= 100
 *   0 <= row, column <= n - 1
 */
public class LC0688 {
    int N;
    int K;
    double[][][] dp;

    public double knightProbability(int N, int K, int r, int c) {
        this.N = N;
        this.K = K;
        dp = new double[N][N][K+1];
        double prob = search(r, c, 1);
        return prob;
    }

    public double search(int r, int c, int steps){
        double tot = 0;
        if(steps > K){
            return 1;
        }

        if(dp[r][c][steps]!=0){
            return dp[r][c][steps];
        }

        int nextr = 0, nextc = 0;
        for(int i=0; i<4; i++){
            for(int j=0; j<2; j++){
                if(i%2 == 0){
                    nextr = r + 2*(i-1);
                    nextc = c + (j==0 ? 1 : -1);
                }
                else{
                    nextr = r + (j==0 ? 1 : -1);
                    nextc = c + 2*(i-2);
                }
                if(nextr < 0 || nextr > N-1 || nextc < 0 || nextc > N-1){
                    continue;
                }
                tot += search(nextr, nextc, steps+1) / 8;
            }
        }

        dp[r][c][steps] = tot;
        return tot;
    }
}
