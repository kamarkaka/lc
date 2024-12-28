package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/***
 * 743. Network Delay Time
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed
 * edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a
 * signal to travel from source to target.
 * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the
 * signal. If it is impossible for all the n nodes to receive the signal, return -1.
 * Example 1:
 *   Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 *   Output: 2
 * Example 2:
 *   Input: times = [[1,2,1]], n = 2, k = 1
 *   Output: 1
 * Example 3:
 *   Input: times = [[1,2,1]], n = 2, k = 2
 *   Output: -1
 * Constraints:
 *   1 <= k <= n <= 100
 *   1 <= times.length <= 6000
 *   times[i].length == 3
 *   1 <= ui, vi <= n
 *   ui != vi
 *   0 <= wi <= 100
 *   All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 */
public class LC0743 {
    public int networkDelayTime(int[][] times, int N, int K) {
        int[] visitedNodes = new int[N];
        HashMap<Integer, List<Integer[]>> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            visitedNodes[i] = -1;
        }

        for (int[] time : times) {
            List<Integer[]> nodes;
            if (!map.containsKey(time[0])) {
                nodes = new ArrayList<>();
            } else {
                nodes = map.get(time[0]);
            }

            Integer[] nodeTime = {time[1], time[2]};
            nodes.add(nodeTime);
            map.put(time[0], nodes);
        }

        LinkedList<Integer> nodes = new LinkedList<>();
        nodes.add(K);
        visitedNodes[K-1] = 0;

        while (nodes.size() > 0) {
            int node = nodes.getFirst();
            nodes.removeFirst();

            if (!map.containsKey(node)) continue;
            List<Integer[]> nodeList = map.get(node);

            for (Integer[] nodeTime : nodeList) {
                int desNode = nodeTime[0];
                int time = nodeTime[1];

                if (desNode == K) continue;
                if (visitedNodes[desNode-1] == -1 || visitedNodes[node-1] + time < visitedNodes[desNode-1]) {
                    visitedNodes[desNode-1] = visitedNodes[node-1] + time;

                    nodes.add(desNode);
                }
            }
        }

        int time = 0;
        for (int i = 0; i < visitedNodes.length; i++) {
            if (i != K - 1 && visitedNodes[i] == -1) return -1;
            time = Math.max(time, visitedNodes[i]);
        }
        return time;
    }
}
