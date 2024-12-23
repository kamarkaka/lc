package com.kamarkaka.marshallwace;

import java.util.*;

/***
 * There are N given points (numbered from 0 to N-1) on a plane. The K-th point is located at coordinates (X[K], Y[K])
 * and its tag is S[K]. We want to draw a circle centered on coordinates (0, 0). The circle should not contain two
 * points with the same tag. What is the maximum number of points that can lie inside the circle?
 * Write a function that, given a string S of length N and two arrays X, Y consisting of N integers each, returns the
 * maximum number of points inside the circle. The circle may contain only points with distinct tags, and is centered on
 * coordinates (0, 0). Points that are on the border of the circle are included within it.
 *
 * Examples:
 * 1. Given S = "ABDCA", X = [2,-1,-4,-3,3] and Y = [2,-2,4,1,-3], the function should return 3.
 * 2. Given S = "ABB", X = [1,-2,-2] and Y = [1,-2,2], the function should return 1.
 * 3. Given S = "CCD", X = [1,-1,2] and Y = [1,-1,-2], the function should return 0.
 */
public class LargestCircle {
    private class Node {
        public final int dist;
        public final char tag;

        public Node(char tag, int dist) {
            this.tag = tag;
            this.dist = dist;
        }
    }

    public int maxNodes(String S, int[] X, int[] Y) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            char tag = S.charAt(i);
            int dist = X[i] * X[i] + Y[i] * Y[i];
            nodes.add(new Node(tag, dist));
        }

        nodes.sort(Comparator.comparingInt(node -> node.dist));

        Set<Character> tagSet = new HashSet<>();
        int result = 0;
        int radius = 0;
        int nodesOnCircle = 0;
        for (Node node : nodes) {
            if (tagSet.contains(node.tag)) {
                if (node.dist == radius) {
                    return result;
                } else {
                    return result + nodesOnCircle;
                }
            }

            tagSet.add(node.tag);
            if (radius == 0) {
                radius = node.dist;
            }

            if (radius == node.dist) {
                nodesOnCircle++;
            } else if (radius < node.dist) {
                radius = node.dist;
                result += nodesOnCircle;
                nodesOnCircle = 1;
            }
        }
        return result + nodesOnCircle;
    }

    public static void main(String[] args) {
        LargestCircle solution = new LargestCircle();
        System.out.println(solution.maxNodes("ABDCA", new int[]{2,-1,-4,-3,3}, new int[]{2,-2,4,1,-3}));
        System.out.println(solution.maxNodes("ABB", new int[]{1,-2,-2}, new int[]{1,-2,2}));
        System.out.println(solution.maxNodes("CCD", new int[]{1,-1,2}, new int[]{1,-1,-2}));
    }
}
