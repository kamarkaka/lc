package com.kamarkaka.leetcode;

import java.util.HashMap;
import java.util.Map;

/***
 * 211. Design Add and Search Words Data Structure
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 * Implement the WordDictionary class:
 *    WordDictionary() Initializes the object.
 *    void addWord(word) Adds word to the data structure, it can be matched later.
 *    bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 * Example:
 *    Input
 *       ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 *       [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 *    Output
 *       [null,null,null,null,false,true,true,true]
 *    Explanation
 *       WordDictionary wordDictionary = new WordDictionary();
 *       wordDictionary.addWord("bad");
 *       wordDictionary.addWord("dad");
 *       wordDictionary.addWord("mad");
 *       wordDictionary.search("pad"); // return False
 *       wordDictionary.search("bad"); // return True
 *       wordDictionary.search(".ad"); // return True
 *       wordDictionary.search("b.."); // return True
 *
 * Constraints:
 *    1 <= word.length <= 25
 *    word in addWord consists of lowercase English letters.
 *    word in search consist of '.' or lowercase English letters.
 *    There will be at most 3 dots in word for search queries.
 *    At most 10^4 calls will be made to addWord and search.
 */
public class LC0211 {
   class WordDictionary {
      TrieNode trie;

      public WordDictionary() {
         trie = new TrieNode();
      }

      public void addWord(String word) {
         TrieNode node = trie;
         for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
         }
         node.word = true;
      }

      public boolean searchInNode(String word, TrieNode node) {
         for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (node.children.containsKey(ch)) {
               node = node.children.get(ch);
               continue;
            }

            if (ch == '.') {
               for (char x : node.children.keySet()) {
                  TrieNode child = node.children.get(x);
                  if (searchInNode(word.substring(i + 1), child)) {
                     return true;
                  }
               }
            }
            return false;
         }
         return node.word;
      }
   }

   private class TrieNode {
      Map<Character, TrieNode> children = new HashMap();
      boolean word = false;
      public TrieNode() {}
   }
}
