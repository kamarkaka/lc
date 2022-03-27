package com.kamarkaka;

import com.kamarkaka.common.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * 1268. Search Suggestions System
 * You are given an array of strings products and a string searchWord.
 * Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.
 * Return a list of lists of the suggested products after each character of searchWord is typed.
 *
 * Example 1:
 *    Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
 *    Output: [
 *       ["mobile","moneypot","monitor"],
 *       ["mobile","moneypot","monitor"],
 *       ["mouse","mousepad"],
 *       ["mouse","mousepad"],
 *       ["mouse","mousepad"]
 *    ]
 *    Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
 *       After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
 *       After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
 *
 * Example 2:
 *    Input: products = ["havana"], searchWord = "havana"
 *    Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 *
 * Example 3:
 *    Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
 *    Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
 *
 * Constraints:
 *    1 <= products.length <= 1000
 *    1 <= products[i].length <= 3000
 *    1 <= sum(products[i].length) <= 2 * 10^4
 *    All the strings of products are unique.
 *    products[i] consists of lowercase English letters.
 *    1 <= searchWord.length <= 1000
 *    searchWord consists of lowercase English letters.
 */
public class LC1268 {
   public List<List<String>> suggestedProducts(String[] products, String searchWord) {
      TrieNode root = new TrieNode('*');
      for (String product : products) {
         buildTrie(root, product);
      }

      List<List<String>> res = new ArrayList<>();
      TrieNode node = root;
      for (char c : searchWord.toCharArray()) {
         node = findWords(node, c, res);
      }
      return res;
   }

   private void buildTrie(TrieNode root, String str) {
      TrieNode node = root;
      for (char c : str.toCharArray()) {
         if (node.children[c - 'a'] == null) {
            node.children[c - 'a'] = new TrieNode(c);
         }
         node = node.children[c - 'a'];
      }
      node.isEnd = true;
      node.word = str;
   }

   private TrieNode findWords(TrieNode node, char c, List<List<String>> result) {
      List<String> res = new ArrayList<>();

      if (node != null && node.children[c - 'a'] != null) {
         node = node.children[c - 'a'];

         Stack<TrieNode> stack = new Stack<>();
         stack.push(node);

         while (!stack.empty() && res.size() < 3) {
            TrieNode tmpNode = stack.pop();
            if (tmpNode.isEnd) {
               res.add(tmpNode.word);
            }

            for (int i = tmpNode.children.length - 1; i >= 0; i--) {
               if (tmpNode.children[i] != null) {
                  stack.push(tmpNode.children[i]);
               }
            }
         }
      }

      result.add(res);

      if (res.size() == 0) return null;
      else return node;
   }

   class TrieNode {
      char c;
      boolean isEnd;
      String word;
      TrieNode[] children;

      public TrieNode(char c) {
         this.c = c;
         this.isEnd = false;
         this.word = null;
         this.children = new TrieNode[26];
      }
   }

   public static void run() {
      LC1268 solution = new LC1268();
      Utilities.print(solution.suggestedProducts(new String[] {"mobile","mouse","moneypot","monitor","mousepad"}, "mouse"));
      Utilities.print(solution.suggestedProducts(new String[] {"havana"}, "tatina"));
   }
}
