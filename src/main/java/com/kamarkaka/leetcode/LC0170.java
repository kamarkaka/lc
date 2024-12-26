package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 170. Two Sum III - Data structure design
 * Design a data structure that accepts a stream of integers and checks if it has a pair of integers that sum up to a
 * particular value.
 * Implement the TwoSum class:
 *   TwoSum() Initializes the TwoSum object, with an empty array initially.
 *   void add(int number) Adds number to the data structure.
 *   boolean find(int value) Returns true if there exists any pair of numbers whose sum is equal to value, otherwise, it
 *   returns false.
 * Example 1:
 *   Input
 *   ["TwoSum", "add", "add", "add", "find", "find"]
 *   [[], [1], [3], [5], [4], [7]]
 *   Output
 *   [null, null, null, null, true, false]
 *   Explanation
 *   TwoSum twoSum = new TwoSum();
 *   twoSum.add(1);   // [] --> [1]
 *   twoSum.add(3);   // [1] --> [1,3]
 *   twoSum.add(5);   // [1,3] --> [1,3,5]
 *   twoSum.find(4);  // 1 + 3 = 4, return true
 *   twoSum.find(7);  // No two integers sum up to 7, return false
 * Constraints:
 *   -10^5 <= number <= 10^5
 *   -2^31 <= value <= 2^31 - 1
 *   At most 104 calls will be made to add and find.
 */
public class LC0170 {
    private Map<Integer, Integer> map;

    /** Initialize your data structure here. */
    public LC0170() {
        map = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (map.containsKey(number)) {
            map.put(number, map.get(number) + 1);
        } else map.put(number, 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (int num : map.keySet()) {
            int comp = value - num;
            if (map.containsKey(comp)) {
                if (num == comp) {
                    if (map.get(num) >= 2) return true;
                    else continue;
                }
                if (map.get(num) > 0 && map.get(comp) > 0) return true;
            }
        }
        return false;
    }
}
