package com.kamarkaka.indeed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoComplete {
   TrieNode root;

   public AutoComplete(List<String> words) {
      this.root = new TrieNode();

      for (String word : words) {
         this.root.insert(word, 0);
      }
   }

   public List<String> find(String prefix) {
      List<String> res = new ArrayList<>();

      TrieNode node = root.find(prefix, 0);
      if (node == null) return res;

      List<String> partialOutput = node.listAll();
      for (String partialWord : partialOutput) {
         res.add(prefix + partialWord);
      }

      return res;
   }

   public static void run() {
      AutoComplete sol = new AutoComplete(Arrays.asList("a","ab","de","abde"));
      System.out.println(sol.find("a"));
      System.out.println(sol.find("ab"));
      System.out.println(sol.find("d"));

   }
}

class TrieNode {
   boolean isEnd;
   TrieNode[] children;

   public TrieNode() {
      this.isEnd = false;
      this.children = new TrieNode[26];
   }

   public void insert(String word, int index) {
      if (index == word.length()) {
         isEnd = true;
         return;
      }

      int idx = word.charAt(index) - 'a';
      if (children[idx] == null) {
         children[idx] = new TrieNode();
      }

      children[idx].insert(word, index+1);
   }

   public TrieNode find(String prefix, int index) {
      if (index == prefix.length()) return this;

      int idx = prefix.charAt(index) - 'a';
      if (children[idx] == null) return null;
      return children[idx].find(prefix, index + 1);
   }

   public List<String> listAll() {
      List<String> res = new ArrayList<>();

      StringBuilder sb = new StringBuilder();
      dfs(this, sb, res);
      return res;
   }

   private void dfs(TrieNode node, StringBuilder sb, List<String> res) {
      if (node.isEnd) {
         res.add(sb.toString());
      }

      for (int i = 0; i < 26; i++) {
         if (node.children[i] == null) continue;

         StringBuilder newSb = new StringBuilder(sb);
         newSb.append((char)('a' + i));
         dfs(node.children[i], newSb, res);
      }
   }
}
