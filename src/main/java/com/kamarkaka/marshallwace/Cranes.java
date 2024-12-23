package com.kamarkaka.marshallwace;

import com.sun.source.tree.Tree;

import java.util.*;

/***
 * There are N cranes (numbered from 0 to N-1) arranged in a line along a road. Road begins at position 0. The K-th
 * crane is located at distance P[K] from the beginning of the road and its arm length is equal to A[K]. The cranes
 * cannot change their positions. There is a package, initially localed at position B, that has to be moved by the
 * cranes to position E. The K-th crane can pick up the package only if the distance between its position and the
 * package position is less than or equal to A[K] (the package is within arm range from the crane's position). A package
 * can be moved by a crane to an arbitrary position within the crane's arm reach (between P[K] - A[K] and P[K] + A[K]
 * for the K-th crane).
 * For example, if P[K] = 5 and A[K] = 3, the K-th crane can move packages anywhere between positions 2 and 8, including
 * both of the boundaries.
 * Determine whether it is possible to move the package from position B to position E using some (possibly all) of the
 * cranes. Any crane can be used an arbitrary number of times.
 * Write a function that, given two arrays of integers A and P, both of length N, and two integers B and E, return True
 * if the package can be moved from position B to position E using the cranes described by arrays A adn P and False
 * otherwise.
 *
 * Examples:
 * 1. Given A = [2, 1], P = [5, 1], B = 3, E = 6, your function should return True. The crane number zero can move the
 * package from position 3 to 6 directly.
 */
public class Cranes {
    public boolean canMove(int[] A, int[] P, int B, int E) {
        int[][] ranges = new int[A.length][2];
        for (int i = 0; i < A.length; i++) {
            ranges[i][0] = P[i] - A[i];
            ranges[i][1] = P[i] + A[i];
        }

        Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));

        TreeMap<Integer, Integer> mergedRanges = new TreeMap<>();
        mergedRanges.put(ranges[0][0], ranges[0][1]);

        for (int i = 1; i < ranges.length; i++) {
            int[] range = ranges[i];
            Map.Entry<Integer, Integer> lastEntry = mergedRanges.lastEntry();
            int lastKey = lastEntry.getKey();
            int lastValue = lastEntry.getValue();
            if (lastValue >= range[0]) {
                mergedRanges.put(lastKey, Math.max(lastValue, range[1]));
            } else {
                mergedRanges.put(range[0], range[1]);
            }
        }

        if (B > E) {
            int tmp = B;
            B = E;
            E = tmp;
        }

        int maxReach = mergedRanges.floorEntry(B).getValue();
        return E <= maxReach;
    }

    public static void main(String[] args) {
        Cranes solution = new Cranes();
        System.out.println(solution.canMove(new int[]{2, 1}, new int[]{5, 1}, 3, 6));
    }
}
