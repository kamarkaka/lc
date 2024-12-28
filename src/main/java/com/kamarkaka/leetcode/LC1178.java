package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * 1178. Number of Valid Words for Each Puzzle
 * With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
 *   word contains the first letter of puzzle.
 *   For each letter in word, that letter is in puzzle.
 *   For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage", while
 *   invalid words are "beefed" (does not include 'a') and "based" (includes 's' which is not in the puzzle).
 * Return an array answer, where answer[i] is the number of words in the given word list words that is valid with
 * respect to the puzzle puzzles[i].
 * Example 1:
 *   Input: words = ["aaaa","asas","able","ability","actt","actor","access"], puzzles = ["aboveyz","abrodyz","abslute",
 *   "absoryz","actresz","gaswxyz"]
 *   Output: [1,1,3,2,4,0]
 *   Explanation:
 *   1 valid word for "aboveyz" : "aaaa"
 *   1 valid word for "abrodyz" : "aaaa"
 *   3 valid words for "abslute" : "aaaa", "asas", "able"
 *   2 valid words for "absoryz" : "aaaa", "asas"
 *   4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
 *   There are no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.
 * Example 2:
 *   Input: words = ["apple","pleas","please"], puzzles = ["aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy"]
 *   Output: [0,1,3,2,0]
 * Constraints:
 *   1 <= words.length <= 10^5
 *   4 <= words[i].length <= 50
 *   1 <= puzzles.length <= 10^4
 *   puzzles[i].length == 7
 *   words[i] and puzzles[i] consist of lowercase English letters.
 *   Each puzzles[i] does not contain repeated characters.
 */
public class LC1178 {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> result = new ArrayList<>();
        TrieNode root = new TrieNode();

        for (String word : words) build(word, root);

        for (String p : puzzles) {
            char[] temp = p.toCharArray();
            Arrays.sort(temp);
            result.add(search(temp, 0, root, false, p.charAt(0)));
        }

        return result;
    }

    private int search(char[] p, int i, TrieNode root, boolean foundFirst, char f) {
        if (root == null) return 0;

        int count = 0;

        for (int j = i; j < p.length; j++){
            int idx = p[j] - 'a';
            if (f == p[j]) count += search(p, j + 1, root.next[idx], true, f);
            else count += search(p, j + 1, root.next[idx], foundFirst, f);
        }

        if (foundFirst) count += root.count;
        return count;
    }

    private void build(String word, TrieNode root) {
        char[] arr = word.toCharArray();
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            if (i > 0 && arr[i] == arr[i - 1]) continue;

            int idx = arr[i] - 'a';
            if (root.next[idx] == null) root.next[idx] = new TrieNode();
            root = root.next[idx];
        }

        root.count++;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        int count;
    }
}
