package com.kamarkaka.leetcode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/***
 * 934. Shortest Bridge
 * You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
 * An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands
 * in grid.
 * You may change 0's to 1's to connect the two islands to form one island.
 * Return the smallest number of 0's you must flip to connect the two islands.
 * Example 1:
 *   Input: grid = [[0,1],[1,0]]
 *   Output: 1
 * Example 2:
 *   Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
 *   Output: 2
 * Example 3:
 *   Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 *   Output: 1
 * Constraints:
 *   n == grid.length == grid[i].length
 *   2 <= n <= 100
 *   grid[i][j] is either 0 or 1.
 *   There are exactly two islands in grid.
 */
public class LC0934 {
    private static final int[][] DIR = new int[][] {
        new int[]{ 0, 1},
        new int[]{ 0,-1},
        new int[]{ 1, 0},
        new int[]{-1, 0}
    };
    private int n;
    private Queue<int[]> queue = new ArrayDeque<>();

    public int shortestBridge(int[][] grid) {
        n = grid.length;
        int x = 0, y = 0;
        boolean br = false;

        for (x = 0; x < n && !br; x++) {
            for (y = 0; y < n && !br; y++) {
                if (grid[x][y] == 1) {
                    discoverIsland(grid, x, y);
                    br = true;
                }
            }
        }

        for (int cnt = 0; ; cnt++) {
            for (var i = queue.size(); i > 0; i--) {
                int[] pos = queue.poll();

                for (int[] dir : DIR) {
                    var x2 = pos[0] + dir[0];
                    var y2 = pos[1] + dir[1];

                    if (x2 >= 0 && x2 < n && y2 >= 0 && y2 < n) {
                        if (grid[x2][y2] == 1) return cnt;
                        else if (grid[x2][y2] == 0) {
                            queue.add(new int[] {x2, y2});
                            grid[x2][y2] = -1;
                        }
                    }
                }
            }
        }
    }

    private void discoverIsland(int[][] grid, int x, int y) {
        grid[x][y] = 2;
        queue.add(new int[] {x, y});

        for (int[] dir : DIR) {
            var x2 = x + dir[0];
            var y2 = y + dir[1];

            if (x2 >= 0 && x2 < n && y2 >= 0 && y2 < n) {
                if (grid[x2][y2] == 1) {
                    discoverIsland(grid, x2, y2);
                }
            }
        }
    }

    public static void main(String[] args) {
        LC0934 solution = new LC0934();
        System.out.println(solution.shortestBridge(new int[][]{
                new int[]{1,1,0,0,0},
                new int[]{1,0,0,0,0},
                new int[]{1,0,0,0,0},
                new int[]{0,0,0,1,1},
                new int[]{0,0,0,1,1}
        }));
    }
}
