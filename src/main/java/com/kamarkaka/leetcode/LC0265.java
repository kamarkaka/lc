package com.kamarkaka.leetcode;

/***
 * 265. Paint House II
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with
 * a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by an n x k cost matrix costs.
 * For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1
 * with color 2, and so on...
 * Return the minimum cost to paint all houses.
 * Example 1:
 *   Input: costs = [[1,5,3],[2,9,4]]
 *   Output: 5
 *   Explanation:
 *   Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
 *   Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
 * Example 2:
 *   Input: costs = [[1,3],[2,4]]
 *   Output: 5
 * Constraints:
 *   costs.length == n
 *   costs[i].length == k
 *   1 <= n <= 100
 *   2 <= k <= 20
 *   1 <= costs[i][j] <= 20
 */
public class LC0265 {
    public int minCostII(int[][] costs) {
        if (costs.length == 0) return 0;

        for (int i = 1; i < costs.length; i++) {
            for (int j = 0; j < costs[i].length; j++) {
                costs[i][j] += minCost(costs[i - 1], j);
            }
        }
        return minCost(costs[costs.length - 1], -1);
    }

    private int minCost(int[] costs, int exclude) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < costs.length; i++) {
            if (i != exclude) {
                min = Math.min(costs[i], min);
            }
        }
        return min;
    }
}
