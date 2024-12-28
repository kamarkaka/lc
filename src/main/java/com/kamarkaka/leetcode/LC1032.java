package com.kamarkaka.leetcode;

/***
 * 1032. Stream of Characters
 * Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a
 * given array of strings words.
 * For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and 'z',
 * your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.
 * Implement the StreamChecker class:
 *   StreamChecker(String[] words) Initializes the object with the strings array words.
 *   boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from
 *   the stream forms a word that is in words.
 * Example 1:
 *   Input
 *   ["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
 *   [[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
 *   Output
 *   [null, false, false, false, true, false, true, false, false, false, false, false, true]
 *   Explanation
 *   StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
 *   streamChecker.query("a"); // return False
 *   streamChecker.query("b"); // return False
 *   streamChecker.query("c"); // return False
 *   streamChecker.query("d"); // return True, because 'cd' is in the wordlist
 *   streamChecker.query("e"); // return False
 *   streamChecker.query("f"); // return True, because 'f' is in the wordlist
 *   streamChecker.query("g"); // return False
 *   streamChecker.query("h"); // return False
 *   streamChecker.query("i"); // return False
 *   streamChecker.query("j"); // return False
 *   streamChecker.query("k"); // return False
 *   streamChecker.query("l"); // return True, because 'kl' is in the wordlist
 * Constraints:
 *   1 <= words.length <= 2000
 *   1 <= words[i].length <= 200
 *   words[i] consists of lowercase English letters.
 *   letter is a lowercase English letter.
 *   At most 4 * 10^4 calls will be made to query.
 */
public class LC1032 {
    class StreamChecker {
        private final TrieNode root;
        private final StringBuilder sb = new StringBuilder();

        public StreamChecker(String[] words) {
            this.root = new TrieNode(false);
            TrieNode node = root;
            for (String s : words) {
                for (int i = s.length() - 1; i >= 0; i--) {
                    char c = s.charAt(i);
                    if (node.tn[c-'a'] == null) node.tn[c-'a'] = new TrieNode(false);
                    node = node.tn[c-'a'];
                }
                node.isWord = true;
                node = root;
            }
        }

        public boolean query(char letter) {
            sb.append(letter);
            TrieNode node = root;
            for (int i = sb.length() - 1; i >= 0; i--) {
                char c = sb.charAt(i);
                if (node.tn[c-'a'] != null) {
                    if (node.tn[c-'a'].isWord) return true;
                    else node = node.tn[c-'a'];
                }
                else return false;
            }
            return false;
        }
    }

    class TrieNode {
        TrieNode[] tn;
        boolean isWord;

        TrieNode(boolean isWord) {
            this.isWord = isWord;
            tn = new TrieNode[26];
        }
    }
}
