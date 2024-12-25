package com.kamarkaka.leetcode;

/***
 * 208. Implement Trie (Prefix Tree)
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a
 * dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 * Implement the Trie class:
 *   Trie() Initializes the trie object.
 *   void insert(String word) Inserts the string word into the trie.
 *   boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false
 *   otherwise.
 *   boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix
 *   prefix, and false otherwise.
 * Example 1:
 *   Input
 *   ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 *   [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 *   Output
 *   [null, null, true, false, true, null, true]
 *   Explanation
 *   Trie trie = new Trie();
 *   trie.insert("apple");
 *   trie.search("apple");   // return True
 *   trie.search("app");     // return False
 *   trie.startsWith("app"); // return True
 *   trie.insert("app");
 *   trie.search("app");     // return True
 * Constraints:
 *   1 <= word.length, prefix.length <= 2000
 *   word and prefix consist only of lowercase English letters.
 *   At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 */
public class LC0208 {
    class Trie {
        private final TrieNode root;

        public Trie() {
            this.root = new TrieNode('*');
        }

        public void insert(String word) {
            TrieNode node = this.root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] != null) {
                    node = node.children[c - 'a'];
                    continue;
                }

                TrieNode newNode = new TrieNode(c);
                node.children[c - 'a'] = newNode;
                node = newNode;
            }
            node.end = true;
        }

        public boolean search(String word) {
            TrieNode node = this.root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] != null) {
                    node = node.children[c - 'a'];
                    continue;
                }
                return false;
            }

            return node.end;
        }

        public boolean startsWith(String prefix) {
            TrieNode node = this.root;
            for (char c : prefix.toCharArray()) {
                if (node.children[c - 'a'] != null) {
                    node = node.children[c - 'a'];
                    continue;
                }
                return false;
            }

            return true;
        }

        private class TrieNode {
            char c;
            boolean end;
            TrieNode[] children;

            TrieNode(char c) {
                this.c = c;
                this.end = false;
                this.children = new TrieNode[26];
            }
        }
    }
}
