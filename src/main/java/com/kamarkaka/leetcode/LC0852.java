package com.kamarkaka.leetcode;

/***
 * 852. Peak Index in a Mountain Array
 * You are given an integer mountain array arr of length n where the values increase to a peak element and then
 * decrease.
 * Return the index of the peak element.
 * Your task is to solve it in O(log(n)) time complexity.
 * Example 1:
 *   Input: arr = [0,1,0]
 *   Output: 1
 * Example 2:
 *   Input: arr = [0,2,1,0]
 *   Output: 1
 * Example 3:
 *   Input: arr = [0,10,5,2]
 *   Output: 1
 * Constraints:
 *   3 <= arr.length <= 10^5
 *   0 <= arr[i] <= 10^6
 *   arr is guaranteed to be a mountain array.
 */
public class LC0852 {
    public int peakIndexInMountainArray(int[] A) {
        return findPeak(A, 0, A.length - 1);
    }

    private int findPeak(int[] A, int left, int right) {
        if (left == right) return left;
        if (right - left == 1) return A[left] > A[right] ? left : right;

        int mid = left + (right - left) / 2;
        int peakLeft = findPeak(A, left, mid);
        int peakRight = findPeak(A, mid + 1, right);
        return A[peakLeft] > A[peakRight] ? peakLeft : peakRight;
    }
}
