package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/***
 * 373. Find K Pairs with Smallest Sums
 * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 * Example 1:
 *   Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 *   Output: [[1,2],[1,4],[1,6]]
 *   Explanation:
 *   The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 *   Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 *   Output: [[1,1],[1,1]]
 *   Explanation:
 *   The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Constraints:
 *   1 <= nums1.length, nums2.length <= 10^5
 *   -10^9 <= nums1[i], nums2[i] <= 10^9
 *   nums1 and nums2 both are sorted in non-decreasing order.
 *   1 <= k <= 10^4
 *   k <= nums1.length * nums2.length
 */
public class LC0373 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) return result;
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>((l1, l2) -> Integer.compare(l1.get(0) + l1.get(1), l2.get(0) + l2.get(1)));

        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            for (int j = 0; j < Math.min(k, nums2.length); j++) {
                List<Integer> pair = new ArrayList<>();
                pair.add(nums1[i]);
                pair.add(nums2[j]);
                pq.add(pair);
            }
        }

        while (result.size() < k && !pq.isEmpty()) {
            result.add(pq.poll());
        }

        return result;
    }
}
