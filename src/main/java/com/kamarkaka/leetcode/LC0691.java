package com.kamarkaka.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/***
 * 691. Stickers to Spell Word
 * We are given n different types of stickers. Each sticker has a lowercase English word on it.
 * You would like to spell out the given string target by cutting individual letters from your collection of stickers
 * and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of each
 * sticker.
 * Return the minimum number of stickers that you need to spell out target. If the task is impossible, return -1.
 * Note: In all test cases, all words were chosen randomly from the 1000 most common US English words, and target was
 * chosen as a concatenation of two random words.
 * Example 1:
 *   Input: stickers = ["with","example","science"], target = "thehat"
 *   Output: 3
 *   Explanation:
 *   We can use 2 "with" stickers, and 1 "example" sticker.
 *   After cutting and rearrange the letters of those stickers, we can form the target "thehat".
 *   Also, this is the minimum number of stickers necessary to form the target string.
 * Example 2:
 *   Input: stickers = ["notice","possible"], target = "basicbasic"
 *   Output: -1
 *   Explanation:
 *   We cannot form the target "basicbasic" from cutting letters from the given stickers.
 * Constraints:
 *   n == stickers.length
 *   1 <= n <= 50
 *   1 <= stickers[i].length <= 10
 *   1 <= target.length <= 15
 *   stickers[i] and target consist of lowercase English letters.
 */
public class LC0691 {
    public int minStickers(String[] stickers, String target) {
        int n = stickers.length;

        target = sortChars(target);
        for (int i = 0; i < n; ++i) stickers[i] = sortChars(stickers[i]);

        Queue<String> q = new LinkedList();
        q.offer(target);
        int steps = 0;
        Set<String> visited = new HashSet<>();
        while (!q.isEmpty()) {
            steps++;
            int size = q.size();
            while(size-- > 0) {
                String x = q.poll();
                for (int i = 0; i < n; ++i) {
                    String now = filter(x, stickers[i]);
                    if (now.isEmpty()) return steps;
                    if (!now.equals(x) && !visited.contains(now)) {
                        visited.add(now);
                        q.offer(now);
                    }
                }
            }
        }
        return -1;

    }

    private String filter(String a, String b) {
        StringBuilder ret = new StringBuilder();
        int idx = 0;

        for (char c : a.toCharArray()) {
            boolean found = false;
            while (idx < b.length() && b.charAt(idx) <= c) {
                if (b.charAt(idx++) == c) {
                    found = true;
                    break;
                }
            }
            if (!found) ret.append(c);
        }
        return ret.toString();
    }

    private String sortChars(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}