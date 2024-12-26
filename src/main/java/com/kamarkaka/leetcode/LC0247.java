package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 247. Strobogrammatic Number II
 * Given an integer n, return all the strobogrammatic numbers that are of length n. You may return the answer in any
 * order.
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Example 1:
 *   Input: n = 2
 *   Output: ["11","69","88","96"]
 * Example 2:
 *   Input: n = 1
 *   Output: ["0","1","8"]
 * Constraints:
 *   1 <= n <= 14
 */
public class LC0247 {
    public List<String> findStrobogrammatic(int n) {
        return helper(n, true);
    }

    private List<String> helper(int n, boolean isOutside) {
        if (n == 0) {
            return new ArrayList<>();
        } else if (n == 1) {
            return new ArrayList<>(Arrays.asList("0", "1", "8"));
        } else if (n == 2) {
            List<String> list = new ArrayList<>(Arrays.asList("11", "69", "88", "96"));
            if (isOutside) return list;
            else {
                list.add(0, "00");
                return list;
            }
        } else {
            List<String> results = helper(n - 2, false);
            List<String> newResult = new ArrayList<>();
            for (String result : results) {
                if (!isOutside) {
                    newResult.add("0" + result + "0");
                }

                newResult.add("1" + result + "1");
                newResult.add("6" + result + "9");
                newResult.add("8" + result + "8");
                newResult.add("9" + result + "6");
            }

            return newResult;
        }
    }
}
