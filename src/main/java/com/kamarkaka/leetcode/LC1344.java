package com.kamarkaka.leetcode;

/***
 * 1344. Angle Between Hands of a Clock
 * Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute
 * hand.
 * Answers within 10^-5 of the actual value will be accepted as correct.
 * Example 1:
 *   Input: hour = 12, minutes = 30
 *   Output: 165
 * Example 2:
 *   Input: hour = 3, minutes = 30
 *   Output: 75
 * Example 3:
 *   Input: hour = 3, minutes = 15
 *   Output: 7.5
 * Constraints:
 *   1 <= hour <= 12
 *   0 <= minutes <= 59
 */
public class LC1344 {
    public double angleClock(int hour, int minutes) {
        double ha = 30d * hour + minutes / 2.0d;
        double ma = 6d * minutes;

        double angle = Math.abs(ha - ma);
        if (angle > 180) angle = 360d - angle;
        return angle;
    }
}
