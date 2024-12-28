package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 720. Longest Word in Dictionary
 * Given an array of strings words representing an English Dictionary, return the longest word in words that can be
 * built one character at a time by other words in words.
 * If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there
 * is no answer, return the empty string.
 * Note that the word should be built from left to right with each additional character being added to the end of a
 * previous word.
 * Example 1:
 *   Input: words = ["w","wo","wor","worl","world"]
 *   Output: "world"
 *   Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 * Example 2:
 *   Input: words = ["a","banana","app","appl","ap","apply","apple"]
 *   Output: "apple"
 *   Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is
 *   lexicographically smaller than "apply".
 * Constraints:
 *   1 <= words.length <= 1000
 *   1 <= words[i].length <= 30
 *   words[i] consists of lowercase English letters.
 */
public class LC0720 {
    private TrieNode trie = new TrieNode('*');

    public String longestWord(String[] words) {
        buildTrie(words);

        String result = "";
        for (TrieNode child : trie.children) {
            String str = findLongestWord(child);
            if (str.length() > result.length()) {
                result = str;
            } else if (str.length() == result.length()) {
                if (str.compareTo(result) < 0) {
                    result = str;
                }
            }
        }
        return result;
    }

    private void buildTrie(String[] words) {
        for (String word : words) {
            TrieNode currNode = trie;
            for (char c : word.toCharArray()) {
                if (currNode.children == null) {
                    currNode.children = new ArrayList<>();
                    currNode.children.add(new TrieNode(c));
                    currNode = currNode.children.get(0);
                } else {
                    TrieNode foundNode = findChild(currNode, c);

                    if (foundNode == null) {
                        currNode.children.add(new TrieNode(c));
                        currNode = currNode.children.get(currNode.children.size() - 1);
                    } else {
                        currNode = foundNode;
                    }
                }
            }

            if (currNode.children == null) {
                currNode.children = new ArrayList<>();
            }

            TrieNode endNode = findChild(currNode, '*');
            if (endNode == null) {
                currNode.children.add(new TrieNode('*'));
            }
        }
    }

    private String findLongestWord(TrieNode trieNode) {
        if (trieNode == null) return "";
        if (findChild(trieNode, '*') == null) return "";
        else if (trieNode.children.size() == 1) return Character.toString(trieNode.val);

        String result = "";
        for (TrieNode child : trieNode.children) {
            String str = findLongestWord(child);
            if (str.length() > result.length()) {
                result = str;
            } else if (str.length() == result.length()) {
                if (str.compareTo(result) < 0) {
                    result = str;
                }
            }
        }

        return trieNode.val + result;
    }

    private TrieNode findChild(TrieNode node, char c) {
        if (node == null || node.children == null || node.children.size() == 0) return null;

        for (TrieNode child : node.children) {
            if (child.val == c) {
                return child;
            }
        }

        return null;
    }

    class TrieNode {
        char val;
        List<TrieNode> children;
        TrieNode(char val) {
            this.val = val;
        }
    }
}
