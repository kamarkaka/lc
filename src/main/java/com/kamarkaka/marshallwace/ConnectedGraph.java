package com.kamarkaka.marshallwace;

import java.sql.Array;
import java.util.*;

/***
 * You are given an undirected graph consisting of N vertices, numbered from 0 to N-1, connected with M edges. The graph
 * is described by two arrays, A and B, both of length M. A pair (A[K], B[K]), for K from 0 to M-1, describes an edge
 * between vertex A[K] and vertex B[K].
 * Each second, every vertex with at most one edge connected to it disappears. Every edge which is connected to one of
 * the disappearing vertices also disappears.
 * After how many seconds will the vertices stop disappearing?
 * Consider a graph with N = 7 vertices and following 6 edges: (0,1), (1,2), (2,0), (1,4), (4,5) and (4,6)
 * 6 - 4 - 1 - 2
 *     |    \ /
 *     5     0   3
 * After the first second, vertices 3, 5, and 6 will disappear:
 *     4 - 1 - 2
 *          \ /
 *           0
 * After the next second vertex 4 will disappear and only vertices 0, 1 and 2 will be left
 */
public class ConnectedGraph {
    public int numberOfSeconds(int N, int[] A, int[] B) {
        int result = 0;
        List<Set<Integer>> graph = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            graph.add(i, new HashSet<>());
        }

        for (int i = 0; i < A.length; i++) {
            graph.get(A[i]).add(B[i]);
            graph.get(B[i]).add(A[i]);
        }

        Set<Integer> nodesToRemove;
        Set<Integer> candidateNodes = new HashSet<>();
        for (int i = 0; i < N; i++) {
            candidateNodes.add(i);
        }

        while (!candidateNodes.isEmpty()) {
            nodesToRemove = new HashSet<>();
            for (int i : candidateNodes) {
                if (graph.get(i).size() <= 1) {
                    nodesToRemove.add(i);
                }
            }

            if (nodesToRemove.isEmpty()) {
                break;
            } else {
                result++;

                candidateNodes.removeAll(nodesToRemove);
                for (int node: nodesToRemove) {
                    for (int v : graph.get(node)) {
                        graph.get(v).remove(node);
                    }

                    candidateNodes.remove(node);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ConnectedGraph solution = new ConnectedGraph();
        System.out.println(solution.numberOfSeconds(7, new int[]{0,1,2,1,4,4}, new int[]{1,2,0,4,5,6}));
    }
}
