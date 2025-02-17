package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/***
 * 432. All O`one Data Structure
 * Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum
 * counts.
 * Implement the AllOne class:
 *   AllOne() Initializes the object of the data structure.
 *   inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it
 *   with count 1.
 *   dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove
 *   it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
 *   getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
 *   getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
 * Note that each function must run in O(1) average time complexity.
 * Example 1:
 *   Input
 *   ["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
 *   [[], ["hello"], ["hello"], [], [], ["leet"], [], []]
 *   Output
 *   [null, null, null, "hello", "hello", null, "hello", "leet"]
 *   Explanation
 *   AllOne allOne = new AllOne();
 *   allOne.inc("hello");
 *   allOne.inc("hello");
 *   allOne.getMaxKey(); // return "hello"
 *   allOne.getMinKey(); // return "hello"
 *   allOne.inc("leet");
 *   allOne.getMaxKey(); // return "hello"
 *   allOne.getMinKey(); // return "leet"
 * Constraints:
 *   1 <= key.length <= 10
 *   key consists of lowercase English letters.
 *   It is guaranteed that for each call to dec, key is existing in the data structure.
 *   At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.
 */
public class LC0432 {
    class AllOne {
        private final HashMap<String, Integer> hmap;
        private final TreeMap<Integer, Set<String>> tMap;

        /** Initialize your data structure here. */
        public AllOne() {
            hmap = new HashMap<>();
            tMap = new TreeMap<>();
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            if (hmap.containsKey(key)) {
                int count = hmap.get(key);
                hmap.put(key, count + 1);
                Set<String> set = tMap.get(count);
                set.remove(key);
                if (set.isEmpty()) tMap.remove(count);

                if (tMap.containsKey(count + 1)) {
                    set = tMap.get(count + 1);
                    set.add(key);
                } else {
                    set = new HashSet<>();
                    set.add(key);
                    tMap.put(count + 1, set);
                }
            } else {
                hmap.put(key, 1);
                Set<String> set;
                if (tMap.containsKey(1)) {
                    set = tMap.get(1);
                    set.add(key);
                } else {
                    set = new HashSet<>();
                    set.add(key);
                    tMap.put(1, set);
                }
            }
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            if (!hmap.containsKey(key)) return;

            int count = hmap.get(key);
            if (count == 1) {
                hmap.remove(key);
                Set<String> set = tMap.get(1);
                set.remove(key);
                if (set.isEmpty()) tMap.remove(1);
            } else {
                hmap.put(key, count - 1);
                Set<String> set = tMap.get(count);
                set.remove(key);
                if (set.isEmpty()) tMap.remove(count);

                if (tMap.containsKey(count - 1)) {
                    set = tMap.get(count - 1);
                    set.add(key);
                } else {
                    set = new HashSet<>();
                    set.add(key);
                    tMap.put(count - 1, set);
                }
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            if (tMap.isEmpty()) return "";
            Map.Entry<Integer, Set<String>> entry = tMap.lastEntry();
            Set<String> set = entry.getValue();
            List<String> list = new ArrayList<>(set);
            return list.getFirst();
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            if (tMap.isEmpty()) return "";
            Map.Entry<Integer, Set<String>> entry = tMap.firstEntry();
            Set<String> set = entry.getValue();
            List<String> list = new ArrayList<>(set);
            return list.getFirst();
        }
    }
}
