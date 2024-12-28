package com.kamarkaka.leetcode;

import com.kamarkaka.common.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/***
 * 1001. Grid Illumination
 * There is a 2D grid of size n x n where each cell of this grid has a lamp that is initially turned off.
 * You are given a 2D array of lamp positions lamps, where lamps[i] = [rowi, coli] indicates that the lamp at
 * grid[rowi][coli] is turned on. Even if the same lamp is listed more than once, it is turned on.
 * When a lamp is turned on, it illuminates its cell and all other cells in the same row, column, or diagonal.
 * You are also given another 2D array queries, where queries[j] = [rowj, colj]. For the jth query, determine whether
 * grid[rowj][colj] is illuminated or not. After answering the jth query, turn off the lamp at grid[rowj][colj] and its
 * 8 adjacent lamps if they exist. A lamp is adjacent if its cell shares either a side or corner with grid[rowj][colj].
 * Return an array of integers ans, where ans[j] should be 1 if the cell in the jth query was illuminated, or 0 if the
 * lamp was not.
 * Example 1:
 *   Input: n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
 *   Output: [1,0]
 *   Explanation: We have the initial grid with all lamps turned off. In the above picture we see the grid after turning
 *   on the lamp at grid[0][0] then turning on the lamp at grid[4][4].
 *   The 0th query asks if the lamp at grid[1][1] is illuminated or not (the blue square). It is illuminated, so set
 *   ans[0] = 1. Then, we turn off all lamps in the red square.
 *   The 1st query asks if the lamp at grid[1][0] is illuminated or not (the blue square). It is not illuminated, so set
 *   ans[1] = 0. Then, we turn off all lamps in the red rectangle.
 * Example 2:
 *   Input: n = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,1]]
 *   Output: [1,1]
 * Example 3:
 *   Input: n = 5, lamps = [[0,0],[0,4]], queries = [[0,4],[0,1],[1,4]]
 *   Output: [1,1,0]
 * Constraints:
 *   1 <= n <= 10^9
 *   0 <= lamps.length <= 20000
 *   0 <= queries.length <= 20000
 *   lamps[i].length == 2
 *   0 <= rowi, coli < n
 *   queries[j].length == 2
 *   0 <= rowj, colj < n
 */
public class LC1001 {
    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        Map<Integer, Integer> d1 = new HashMap<>();
        Map<Integer, Integer> d2 = new HashMap<>();
        Set<Pair<Integer, Integer>> status = new HashSet<>();

        for (int[] lamp : lamps) {
            int x = lamp[0], y = lamp[1];
            row.put(x, row.getOrDefault(x, 0) + 1);
            col.put(y, col.getOrDefault(y, 0) + 1);
            d1.put(y - x, d1.getOrDefault(y - x, 0) + 1);
            d2.put(x + y, d2.getOrDefault(x + y, 0) + 1);
            status.add(new Pair<>(x, y));
        }

        int[] ans = new int[queries.length];

        int k = 0;
        for (int[] query : queries) {
            int x = query[0], y = query[1];
            if (row.getOrDefault(x, 0) > 0 || col.getOrDefault(y, 0) > 0 || d1.getOrDefault(y - x, 0) > 0 || d2.getOrDefault(x + y, 0) > 0)
                ans[k++] = 1;
            else ans[k++] = 0;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int xp = x + i, yp = y + j;
                    if (xp >= 0 && yp >= 0 && xp < N && yp < N && status.remove(new Pair<>(xp, yp))) {
                        row.put(xp, row.get(xp) - 1);
                        col.put(yp, col.get(yp) - 1);
                        d1.put(yp - xp, d1.get(yp - xp) - 1);
                        d2.put(xp + yp, d2.get(xp + yp) - 1);
                    }
                }
            }
        }

        return ans;
    }
}
