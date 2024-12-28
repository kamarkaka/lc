package com.kamarkaka.leetcode;

/***
 * 1007. Minimum Domino Rotations For Equal Row
 * In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino. (A domino is a
 * tile with two numbers from 1 to 6 - one on each half of the tile.)
 * We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.
 * Return the minimum number of rotations so that all the values in tops are the same, or all the values in bottoms are
 * the same.
 * If it cannot be done, return -1.
 * Example 1:
 *   Input: tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
 *   Output: 2
 *   Explanation:
 *   The first figure represents the dominoes as given by tops and bottoms: before we do any rotations.
 *   If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the
 *   second figure.
 * Example 2:
 *   Input: tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
 *   Output: -1
 *   Explanation: In this case, it is not possible to rotate the dominoes to make one row of values equal.
 * Constraints:
 *   2 <= tops.length <= 2 * 10^4
 *   bottoms.length == tops.length
 *   1 <= tops[i], bottoms[i] <= 6
 */
public class LC1007 {
    public int minDominoRotations(int[] A, int[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0 || A.length != B.length) return -1;
        if (A.length == 1) return 0;

        int upperVal = A[0], lowerVal = B[0], upperCount = 0, lowerCount = 0, upperSkip = 0, lowerSkip = 0;
        for (int i = 1; i < A.length; i++) {
            if (upperVal == -1 && lowerVal == -1) break;

            if (upperVal != -1) {
                if (A[i] == upperVal) {
                    if (B[i] == upperVal) upperSkip++;
                } else if (B[i] == upperVal) {
                    upperCount++;
                } else {
                    upperVal = -1;
                }
            }

            if (lowerVal != -1) {
                if (B[i] == lowerVal) {
                    if (A[i] == lowerVal) lowerSkip++;
                } else if (A[i] == lowerVal) {
                    lowerCount++;
                } else {
                    lowerVal = -1;
                }
            }
        }

        if (upperVal == -1 && lowerVal == -1) return -1;
        upperCount = upperVal == -1 ? Integer.MAX_VALUE : Math.min(upperCount, A.length - upperCount - upperSkip);
        lowerCount = lowerVal == -1 ? Integer.MAX_VALUE : Math.min(lowerCount, B.length - lowerCount - lowerSkip);
        return Math.min(upperCount, lowerCount);
    }
}
