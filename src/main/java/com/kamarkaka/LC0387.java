package com.kamarkaka;

/***
 * 387. First Unique Character in a String
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 *
 * Example 1:
 *   Input: s = "leetcode"
 *   Output: 0
 *
 * Example 2:
 *   Input: s = "loveleetcode"
 *   Output: 2
 *
 * Example 3:
 *   Input: s = "aabb"
 *   Output: -1
 *
 * Constraints:
 *   1 <= s.length <= 10^5
 *   s consists of only lowercase English letters.
 */
public class LC0387 {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;

        int res = -1;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (count[c - 'a'] == 1) return i;
        }

        return res;
    }

    public static void run() {
        LC0387 solution = new LC0387();
        System.out.println(solution.firstUniqChar("leetcode"));
        System.out.println(solution.firstUniqChar("loveleetcode"));
        System.out.println(solution.firstUniqChar("aabb"));
    }
}
