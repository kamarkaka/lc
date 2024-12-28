package com.kamarkaka.leetcode;

import java.util.List;
import java.util.PriorityQueue;

/***
 * 632. Smallest Range Covering Elements from K Lists
 * You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one
 * number from each of the k lists.
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 * Example 1:
 *   Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
 *   Output: [20,24]
 *   Explanation:
 *   List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 *   List 2: [0, 9, 12, 20], 20 is in range [20,24].
 *   List 3: [5, 18, 22, 30], 22 is in range [20,24].
 * Example 2:
 *   Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
 *   Output: [1,1]
 * Constraints:
 *   nums.length == k
 *   1 <= k <= 3500
 *   1 <= nums[i].length <= 50
 *   -10^5 <= nums[i][j] <= 10^5
 *   nums[i] is sorted in non-decreasing order.
 */
public class LC0632 {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> (a.x - b.x));
        int size = nums.size();
        int[] idxArr = new int[size];
        int max = 0;

        for (int i = 0; i < size; i++) {
            int num = nums.get(i).get(0);
            pq.add(new Point(num, i));
            max = Math.max(max, num);
        }

        int start = -1, end = -1, gap = Integer.MAX_VALUE;
        while (pq.size() == size) {
            Point first = pq.poll();
            int min = first.x, idx = first.y;
            if (max - min < gap) {
                gap = max - min;
                start = min;
                end = max;
            }

            if (++idxArr[idx] < nums.get(idx).size()) {
                first.x = nums.get(idx).get(idxArr[idx]);
                pq.add(first);
                max = Math.max(max, first.x);
            }
        }
        return new int[]{start, end};
    }

    private class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
