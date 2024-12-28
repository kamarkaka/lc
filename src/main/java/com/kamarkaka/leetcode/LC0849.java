package com.kamarkaka.leetcode;

/***
 * 849. Maximize Distance to Closest Person
 * You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat,
 * and seats[i] = 0 represents that the ith seat is empty (0-indexed).
 * There is at least one empty seat, and at least one person sitting.
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * Return that maximum distance to the closest person.
 * Example 1:
 *   Input: seats = [1,0,0,0,1,0,1]
 *   Output: 2
 *   Explanation:
 *   If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
 *   If Alex sits in any other open seat, the closest person has distance 1.
 *   Thus, the maximum distance to the closest person is 2.
 * Example 2:
 *   Input: seats = [1,0,0,0]
 *   Output: 3
 *   Explanation:
 *   If Alex sits in the last seat (i.e. seats[3]), the closest person is 3 seats away.
 *   This is the maximum distance possible, so the answer is 3.
 * Example 3:
 *   Input: seats = [0,1]
 *   Output: 1
 * Constraints:
 *   2 <= seats.length <= 2 * 10^4
 *   seats[i] is 0 or 1.
 *   At least one seat is empty.
 *   At least one seat is occupied.
 */
public class LC0849 {
    public int maxDistToClosest(int[] seats) {
        int maxDist = 0;

        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) continue;
            int distLeft = 1, distRight = 1, j;
            boolean found;

            j = i - 1;
            found = false;
            while (j >= 0) {
                if (seats[j] == 1) {
                    found = true;
                    break;
                } else {
                    distLeft++;
                    j--;
                }
            }

            distLeft = found ? distLeft : Integer.MAX_VALUE;

            j = i + 1;
            found = false;
            while (j < seats.length) {
                if (seats[j] == 1) {
                    found = true;
                    break;
                } else {
                    distRight++;
                    j++;
                }
            }

            distRight = found ? distRight : Integer.MAX_VALUE;

            maxDist = Math.max(maxDist, Math.min(distLeft, distRight));
        }

        return maxDist;
    }
}
