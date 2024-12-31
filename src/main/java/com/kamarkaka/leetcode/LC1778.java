package com.kamarkaka.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 1778. Shortest Path in a Hidden Grid
 * This is an interactive problem.
 *
 * There is a robot in a hidden grid, and you are trying to get it from its starting cell to the target cell in this
 * grid. The grid is of size m x n, and each cell in the grid is either empty or blocked. It is guaranteed that the
 * starting cell and the target cell are different, and neither of them is blocked.
 * You want to find the minimum distance to the target cell. However, you do not know the grid's dimensions, the
 * starting cell, nor the target cell. You are only allowed to ask queries to the GridMaster object.
 * Thr GridMaster class has the following functions:
 *   boolean canMove(char direction) Returns true if the robot can move in that direction. Otherwise, it returns false.
 *   void move(char direction) Moves the robot in that direction. If this move would move the robot to a blocked cell or
 *   off the grid, the move will be ignored, and the robot will remain in the same position.
 *   boolean isTarget() Returns true if the robot is currently on the target cell. Otherwise, it returns false.
 * Note that direction in the above functions should be a character from {'U','D','L','R'}, representing the directions
 * up, down, left, and right, respectively.
 * Return the minimum distance between the robot's initial starting cell and the target cell. If there is no valid path
 * between the cells, return -1.
 * Custom testing:
 * The test input is read as a 2D matrix grid of size m x n where:
 *   grid[i][j] == -1 indicates that the robot is in cell (i, j) (the starting cell).
 *   grid[i][j] == 0 indicates that the cell (i, j) is blocked.
 *   grid[i][j] == 1 indicates that the cell (i, j) is empty.
 *   grid[i][j] == 2 indicates that the cell (i, j) is the target cell.
 * There is exactly one -1 and 2 in grid. Remember that you will not have this information in your code.
 * Example 1:
 *   Input: grid = [[1,2],[-1,0]]
 *   Output: 2
 *   Explanation: One possible interaction is described below:
 *   The robot is initially standing on cell (1, 0), denoted by the -1.
 *   - master.canMove('U') returns true.
 *   - master.canMove('D') returns false.
 *   - master.canMove('L') returns false.
 *   - master.canMove('R') returns false.
 *   - master.move('U') moves the robot to the cell (0, 0).
 *   - master.isTarget() returns false.
 *   - master.canMove('U') returns false.
 *   - master.canMove('D') returns true.
 *   - master.canMove('L') returns false.
 *   - master.canMove('R') returns true.
 *   - master.move('R') moves the robot to the cell (0, 1).
 *   - master.isTarget() returns true.
 *   We now know that the target is the cell (0, 1), and the shortest path to the target cell is 2.
 * Example 2:
 *   Input: grid = [[0,0,-1],[1,1,1],[2,0,0]]
 *   Output: 4
 *   Explanation: The minimum distance between the robot and the target cell is 4.
 * Example 3:
 *   Input: grid = [[-1,0],[0,2]]
 *   Output: -1
 *   Explanation: There is no path from the robot to the target cell.
 * Constraints:
 *   1 <= n, m <= 500
 *   m == grid.length
 *   n == grid[i].length
 *   grid[i][j] is either -1, 0, 1, or 2.
 *   There is exactly one -1 in grid.
 *   There is exactly one 2 in grid.
 */

interface GridMaster {
    boolean isTarget();
    boolean canMove(char dir);
    void move(char dir);
}

public class LC1778 {
    public int findShortestPath(GridMaster master) {
        int[][] grid = new int[1001][1001];
        // grid[500][500] = -1;

        // 0: blocked, 1: canMove, 2: target
        buildGrid(grid, master, 500, 500);

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 500, 500 });
        // grid[500][500] = 0;

        int[][] dirs = {
                { -1, 0 },
                { 1, 0 },
                { 0, -1 },
                { 0, 1 },
        };

        int travel = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i += 1) {
                int[] cell = queue.poll();
                for (int[] dir : dirs) {
                    int row = cell[0] + dir[0];
                    int col = cell[1] + dir[1];

                    if (grid[row][col] != 0) {
                        if (grid[row][col] == 2) {
                            return travel + 1;
                        }

                        grid[row][col] = 0;
                        queue.offer(new int[] { row, col });
                    }
                }
            }

            travel += 1;
        }

        return -1;
    }

    private void buildGrid(int[][] grid, GridMaster master, int row, int col) {
        if (master.isTarget()) {
            grid[row][col] = 2;
            return;
        }

        if (col - 1 >= 0 && grid[row][col - 1] == 0 && master.canMove('L')) {
            grid[row][col - 1] = 1;
            master.move('L');
            buildGrid(grid, master, row, col - 1);
            master.move('R');
        }

        if (row - 1 >= 0 && grid[row - 1][col] == 0 && master.canMove('U')) {
            grid[row - 1][col] = 1;
            master.move('U');
            buildGrid(grid, master, row - 1, col);
            master.move('D');
        }

        if (col + 1 < 1001 && grid[row][col + 1] == 0 && master.canMove('R')) {
            grid[row][col + 1] = 1;
            master.move('R');
            buildGrid(grid, master, row, col + 1);
            master.move('L');
        }

        if (row + 1 < 1001 && grid[row + 1][col] == 0 && master.canMove('D')) {
            grid[row + 1][col] = 1;
            master.move('D');
            buildGrid(grid, master, row + 1, col);
            master.move('U');
        }
    }

}
