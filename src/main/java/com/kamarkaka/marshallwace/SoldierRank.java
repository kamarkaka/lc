package com.kamarkaka.marshallwace;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/***
 * In the army, each soldier has an assigned rank. A soldier of rank X has to report to (any) soldier of rank X + 1.
 * Many soldiers can report to the same superior. Write a function that, given an array ranks consisting of soldiers'
 * ranks, returns the number of soldiers who can report to some superior.
 *
 * Examples:
 * 1. Given ranks = [3,4,3,0,2,2,3,0,0], your function should return 5.
 * 2. Given ranks = [4,2,0], your function should return 0.
 * 3. Given ranks = [4,4,3,3,1,0], your function should return 3.
 * Write an efficient algorithm for the following assumptions:
 * - N is an integer within the range [2..100,000];
 * - each element of array ranks is an integer within the range
 */
public class SoldierRank {
    public int reports(int[] ranks) {
        Arrays.sort(ranks);

        int soldiersWithTheSameRank = 0;
        int result = 0;
        for (int i = 0; i < ranks.length; i++) {
            if (soldiersWithTheSameRank == 0 || ranks[i] == ranks[i - 1]) {
                soldiersWithTheSameRank++;
            } else if (ranks[i] - ranks[i - 1] == 1) {
                result += soldiersWithTheSameRank;
                soldiersWithTheSameRank = 1;
            } else {
                soldiersWithTheSameRank = 1;
            }
        }
        return result;
    }

    public int reports2(int[] ranks) {
        Map<Integer, Integer> rankCounts = new HashMap<>();
        for (int rank : ranks) {
            rankCounts.put(rank, rankCounts.getOrDefault(rank, 0) + 1);
        }

        int result = 0;
        for (int rank : rankCounts.keySet()) {
            if (rankCounts.containsKey(rank + 1)) {
                result += rankCounts.get(rank);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SoldierRank solution = new SoldierRank();
        System.out.println(solution.reports2(new int[]{3,4,3,0,2,2,3,0,0}));
        System.out.println(solution.reports2(new int[]{4,2,0}));
        System.out.println(solution.reports2(new int[]{4,4,3,3,1,0}));
    }
}
