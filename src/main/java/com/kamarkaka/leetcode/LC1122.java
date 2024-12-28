package com.kamarkaka.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/***
 * 1122. Relative Sort Array
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2. Elements that do
 * not appear in arr2 should be placed at the end of arr1 in ascending order.
 * Example 1:
 *   Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 *   Output: [2,2,2,1,4,3,3,9,6,7,19]
 * Example 2:
 *   Input: arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
 *   Output: [22,28,8,6,17,44]
 * Constraints:
 *   1 <= arr1.length, arr2.length <= 1000
 *   0 <= arr1[i], arr2[i] <= 1000
 *   All the elements of arr2 are distinct.
 *   Each arr2[i] is in arr1.
 */
public class LC1122 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        int[] result = new int[arr1.length];
        int p = 0;
        Set<Integer> numSet = new HashSet<>();

        for (int num : arr2) {
            numSet.add(num);
            int index = binarySearch(arr1, num, 0, arr1.length - 1);
            if (index < 0) continue;

            int t = arr1[index];

            while (index < arr1.length && arr1[index] == t) {
                result[p] = t;
                index++;
                p++;
            }
        }

        for (int num : arr1) {
            if (numSet.contains(num)) continue;
            result[p] = num;
            p++;
        }

        return result;
    }

    private int binarySearch(int[] sortedArr, int target, int lo, int hi) {
        if (lo == hi) return target == sortedArr[lo] ? lo : -1;
        if (lo > hi) return -1;

        int mid = lo + (hi - lo) / 2;
        if (target <= sortedArr[mid]) return binarySearch(sortedArr, target, lo, mid);
        else return binarySearch(sortedArr, target, mid + 1, hi);
    }
}
