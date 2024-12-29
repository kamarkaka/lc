package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 267. Palindrome Permutation II
 * Given a string s, return all the palindromic permutations (without duplicates) of it.
 * You may return the answer in any order. If s has no palindromic permutation, return an empty list.
 * Example 1:
 *   Input: s = "aabb"
 *   Output: ["abba","baab"]
 * Example 2:
 *   Input: s = "abc"
 *   Output: []
 * Constraints:
 *   1 <= s.length <= 16
 *   s consists of only lowercase English letters.
 */
public class LC0267 {
    public List<String> generatePalindromes(String s) {
        List<String> result = new ArrayList<>();

        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }

        int oddCount = 0;
        for (int count : charCount) {
            if (count % 2 == 1) {
                oddCount++;
                if (oddCount >= 2) return result;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (charCount[i] % 2 == 1) {
                sb.append((char)('a' + i));
                charCount[i]--;
                break;
            }
        }

        helper(charCount, sb, result);
        return result;
    }

    private void helper(int[] charCount, StringBuilder sb, List<String> result) {
        if (allZeros(charCount)) {
            result.add(sb.toString());
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (charCount[i] > 0) {
                sb.insert(0, (char)('a' + i));
                sb.append((char)('a' + i));
                charCount[i] -= 2;
                helper(charCount, sb, result);
                charCount[i] += 2;
                sb.deleteCharAt(0);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    private boolean allZeros(int[] charCount) {
        for (int count : charCount) {
            if (count > 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LC0267 solution = new LC0267();
        System.out.println(solution.generatePalindromes("aabb"));
        System.out.println(solution.generatePalindromes("aabbc"));
        System.out.println(solution.generatePalindromes("abc"));
    }
}
