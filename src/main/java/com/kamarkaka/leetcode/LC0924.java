package com.kamarkaka.leetcode;

import java.util.Arrays;

/***
 * 924. Minimize Malware Spread
 * You are given a network of n nodes represented as an n x n adjacency matrix graph, where the ith node is directly
 * connected to the jth node if graph[i][j] == 1.
 * Some nodes initial are initially infected by malware. Whenever two nodes are directly connected, and at least one of
 * those two nodes is infected by malware, both nodes will be infected by malware. This spread of malware will continue
 * until no more nodes can be infected in this manner.
 * Suppose M(initial) is the final number of nodes infected with malware in the entire network after the spread of
 * malware stops. We will remove exactly one node from initial.
 * Return the node that, if removed, would minimize M(initial). If multiple nodes could be removed to minimize
 * M(initial), return such a node with the smallest index.
 * Note that if a node was removed from the initial list of infected nodes, it might still be infected later due to the
 * malware spread.
 * Example 1:
 *   Input: graph = [[1,1,0],[1,1,0],[0,0,1]], initial = [0,1]
 *   Output: 0
 * Example 2:
 *   Input: graph = [[1,0,0],[0,1,0],[0,0,1]], initial = [0,2]
 *   Output: 0
 * Example 3:
 *   Input: graph = [[1,1,1],[1,1,1],[1,1,1]], initial = [1,2]
 *   Output: 1
 * Constraints:
 *   n == graph.length
 *   n == graph[i].length
 *   2 <= n <= 300
 *   graph[i][j] is 0 or 1.
 *   graph[i][j] == graph[j][i]
 *   graph[i][i] == 1
 *   1 <= initial.length <= n
 *   0 <= initial[i] <= n - 1
 *   All the integers in initial are unique.
 */
public class LC0924 {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int deleteNode = -1, maxInfected = -1;
        boolean[] visited = new boolean[graph.length];
        Arrays.sort(initial);
        for(int i = 0; i < initial.length; i++) {
            if(visited[initial[i]]) continue;
            int count = dfs(graph, initial[i], visited);
            if(count > maxInfected) {
                maxInfected = count;
                deleteNode = initial[i];
            }
        }
        return deleteNode;
    }

    public int dfs(int[][] graph, int node, boolean[] visited) {
        int count = 1;
        visited[node] = true;
        for(int i = 0; i < graph.length; i++) {
            if(visited[i]) continue;
            if(graph[node][i] == 1) {
                count += dfs(graph, i, visited);
            }
        }
        return count;
    }
}
