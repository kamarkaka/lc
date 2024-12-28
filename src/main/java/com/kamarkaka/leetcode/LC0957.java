package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 957. Prison Cells After N Days
 * There are 8 prison cells in a row and each cell is either occupied or vacant.
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 *   If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 *   Otherwise, it becomes vacant.
 * Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.
 * You are given an integer array cells where cells[i] == 1 if the ith cell is occupied and cells[i] == 0 if the ith
 * cell is vacant, and you are given an integer n.
 * Return the state of the prison after n days (i.e., n such changes described above).
 * Example 1:
 *   Input: cells = [0,1,0,1,1,0,0,1], n = 7
 *   Output: [0,0,1,1,0,0,0,0]
 *   Explanation: The following table summarizes the state of the prison on each day:
 *   Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 *   Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 *   Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 *   Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 *   Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 *   Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 *   Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 *   Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 * Example 2:
 *   Input: cells = [1,0,0,1,0,0,1,0], n = 1000000000
 *   Output: [0,0,1,1,1,1,1,0]
 * Constraints:
 *   cells.length == 8
 *   cells[i] is either 0 or 1.
 *   1 <= n <= 10^9
 */
public class LC0957 {
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> seq = new HashMap<>();

        int loopSize = 1, n;

        int num = toInt(cells);
        for (n = 0; n < N; n++) {
            if (!map.containsKey(num)) {
                int next = nextInt(num);
                map.put(num, next);
                seq.put(num, n);
                num = next;
            } else {
                loopSize = map.size() - seq.get(num);
                break;
            }
        }

        if (n < N) {
            N = (N - n) % loopSize;
            for (n = 0; n < N; n++) {
                num = map.get(num);
            }
        }
        return toArray(num);
    }

    private int toInt(int[] cells) {
        int num = 0;
        for (int digit : cells) {
            num = num << 1;
            num = (num | digit);
        }
        return num;
    }

    private int[] toArray(int num) {
        int[] result = new int[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = num & 1;
            num = num >> 1;
        }
        return result;
    }

    private int nextInt(int num) {
        return ~((num << 1) ^ (num >> 1)) & 0x7E;
    }

}
