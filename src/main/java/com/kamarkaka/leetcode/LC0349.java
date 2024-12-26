package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 349. Intersection of Two Arrays
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be
 * unique and you may return the result in any order.
 * Example 1:
 *   Input: nums1 = [1,2,2,1], nums2 = [2,2]
 *   Output: [2]
 * Example 2:
 *   Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 *   Output: [9,4]
 *   Explanation: [4,9] is also accepted.
 * Constraints:
 *   1 <= nums1.length, nums2.length <= 1000
 *   0 <= nums1[i], nums2[i] <= 1000
 */
public class LC0349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[0];
        Map<Integer, Boolean> map = new HashMap<>();
        List<Integer> al = new ArrayList<>();

        for (int num : nums1) {
            map.put(num, false);
        }

        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) == false) {
                al.add(num);
                map.put(num, true);
            }
        }

        int[] arr = new int[al.size()];
        int i = 0;
        for (Integer val :  al) {
            arr[i++] = val;
        }
        return arr;
    }
}
