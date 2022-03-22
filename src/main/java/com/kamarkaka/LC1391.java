package com.kamarkaka;

import java.util.*;

/***
 * 1391. Check if There is a Valid Path in a Grid
 * You are given an m x n grid. Each cell of grid represents a street. The street of grid[i][j] can be:
 *    1 which means a street connecting the left cell and the right cell.
 *    2 which means a street connecting the upper cell and the lower cell.
 *    3 which means a street connecting the left cell and the lower cell.
 *    4 which means a street connecting the right cell and the lower cell.
 *    5 which means a street connecting the left cell and the upper cell.
 *    6 which means a street connecting the right cell and the upper cell.
 * You will initially start at the street of the upper-left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.
 * Notice that you are not allowed to change any street.
 * Return true if there is a valid path in the grid or false otherwise.
 *
 * Example 1:
 *    Input: grid = [[2,4,3],[6,5,2]]
 *    Output: true
 *    Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).
 *
 * Example 2:
 *    Input: grid = [[1,2,1],[1,2,1]]
 *    Output: false
 *    Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)
 *
 * Example 3:
 *    Input: grid = [[1,1,2]]
 *    Output: false
 *    Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).
 *
 * Constraints:
 *    m == grid.length
 *    n == grid[i].length
 *    1 <= m, n <= 300
 *    1 <= grid[i][j] <= 6
 */
public class LC1391 {
   private Map<Integer, List<int[]>> dirMap = new HashMap<>();
   private Map<Integer, List<Set<Integer>>> nextMap = new HashMap<>();

   private void init() {
      dirMap.putIfAbsent(1, new LinkedList<>());
      dirMap.putIfAbsent(2, new LinkedList<>());
      dirMap.putIfAbsent(3, new LinkedList<>());
      dirMap.putIfAbsent(4, new LinkedList<>());
      dirMap.putIfAbsent(5, new LinkedList<>());
      dirMap.putIfAbsent(6, new LinkedList<>());

      dirMap.get(1).add(new int[] {0, -1});
      dirMap.get(1).add(new int[] {0, 1});
      dirMap.get(2).add(new int[] {-1, 0});
      dirMap.get(2).add(new int[] {1, 0});
      dirMap.get(3).add(new int[] {0, -1});
      dirMap.get(3).add(new int[] {1, 0});
      dirMap.get(4).add(new int[] {0, 1});
      dirMap.get(4).add(new int[] {1, 0});
      dirMap.get(5).add(new int[] {0, -1});
      dirMap.get(5).add(new int[] {-1, 0});
      dirMap.get(6).add(new int[] {0, 1});
      dirMap.get(6).add(new int[] {-1, 0});

      nextMap.putIfAbsent(1, new LinkedList<>());
      nextMap.putIfAbsent(2, new LinkedList<>());
      nextMap.putIfAbsent(3, new LinkedList<>());
      nextMap.putIfAbsent(4, new LinkedList<>());
      nextMap.putIfAbsent(5, new LinkedList<>());
      nextMap.putIfAbsent(6, new LinkedList<>());

      nextMap.get(1).add(new HashSet<>(Arrays.asList(1,4,6)));
      nextMap.get(1).add(new HashSet<>(Arrays.asList(1,3,5)));
      nextMap.get(2).add(new HashSet<>(Arrays.asList(2,3,4)));
      nextMap.get(2).add(new HashSet<>(Arrays.asList(2,5,6)));
      nextMap.get(3).add(new HashSet<>(Arrays.asList(1,4,6)));
      nextMap.get(3).add(new HashSet<>(Arrays.asList(2,5,6)));
      nextMap.get(4).add(new HashSet<>(Arrays.asList(1,3,5)));
      nextMap.get(4).add(new HashSet<>(Arrays.asList(2,5,6)));
      nextMap.get(5).add(new HashSet<>(Arrays.asList(1,4,6)));
      nextMap.get(5).add(new HashSet<>(Arrays.asList(2,3,4)));
      nextMap.get(6).add(new HashSet<>(Arrays.asList(1,3,5)));
      nextMap.get(6).add(new HashSet<>(Arrays.asList(2,3,4)));
   }

   public boolean hasValidPath(int[][] grid) {
      init();

      int rows = grid.length, cols = grid[0].length;
      boolean[][] visited = new boolean[rows][cols];

      Queue<int[]> queue = new LinkedList<>();
      queue.add(new int[] {0,0});

      while (!queue.isEmpty()) {
         int[] pos = queue.poll();
         int row = pos[0];
         int col = pos[1];
         int road = grid[row][col];
         visited[row][col] = true;
         if (row == rows - 1 && col == cols - 1) return true;

         List<int[]> dirs = dirMap.get(road);
         for (int i = 0; i < dirs.size(); i++) {
            int[] dir = dirs.get(i);
            int nr = row + dir[0];
            int nc = col + dir[1];
            if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
            if (visited[nr][nc]) continue;

            int nextRoad = grid[nr][nc];
            Set<Integer> connectedRoads = nextMap.get(road).get(i);
            if (!connectedRoads.contains(nextRoad)) continue;

            queue.add(new int[] {nr, nc});
         }
      }

      return false;
   }

   public static void run() {
      LC1391 sol = new LC1391();
      System.out.println(sol.hasValidPath(new int[][] {
         {1,1,1,1,1,3}
      }));
   }
}
