package com.kamarkaka.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/***
 * 1047. Remove All Adjacent Duplicates In String
 * You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two
 * adjacent and equal letters and removing them.
 * We repeatedly make duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
 * Example 1:
 *   Input: s = "abbaca"
 *   Output: "ca"
 *   Explanation:
 *   For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only
 *   possible move. The result of this move is that the string is "aaca", of which only "aa" is possible, so the final
 *   string is "ca".
 * Example 2:
 *   Input: s = "azxxzy"
 *   Output: "ay"
 * Constraints:
 *   1 <= s.length <= 10^5
 *   s consists of lowercase English letters.
 */
public class LC1047 {
    public String removeDuplicates(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (deque.isEmpty()) {
                deque.offerLast(c);
                continue;
            }

            if (deque.peekLast() == c) {
                deque.pollLast();
                continue;
            }

            deque.offerLast(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.poll());
        }
        return sb.toString();
    }
}
