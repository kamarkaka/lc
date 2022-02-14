package com.kamarkaka;

import com.kamarkaka.common.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
