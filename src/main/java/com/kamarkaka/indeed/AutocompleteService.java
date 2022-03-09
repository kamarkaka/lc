package com.kamarkaka.indeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// Space complexity : O(MN) M-average word length, N-number of words
// Time complexity : O(M + V) M-length of the prefix, N-number of node in Trie, V-matches

public class AutocompleteService {
   public final TrieNode root;

   public AutocompleteService(String[] completions) {
      // TODO: Implement
      this.root = new TrieNode();

      for (String completion : completions) {
         this.root.insert(completion, 0);
      }
   }

   public List<String> suggestFor(String partialKeyword) {
      // TODO: implement
      List<String> result = new ArrayList<>();

      TrieNode subTrieNode = root.find(partialKeyword, 0);
      if (subTrieNode == null) return result;

      List<String> outputs = subTrieNode.output();
      for (String output : outputs) {
         result.add(partialKeyword + output);
      }

      // for (String completion ： completions) {
      //     if (completion.startsWith(partialKeword)) {
      //         result.add(completion);
      //     }
      // }
      return result;
   }

   public static void run() {
      AutocompleteService as = new AutocompleteService(new String[] {
         "pyt",
         "pat"
      });

      TrieNode root = as.root;
      System.out.println(root.children.keySet());
      TrieNode node = root.children.get('p');
      System.out.println(node.children.keySet());

      List<String> suggestions = as.suggestFor("p");
      for (String sug : suggestions) {
         System.out.println(sug);
      }

   }
}

// 1. go through all word in completions to check for prefix
// 2. Trie

class TrieNode {
   boolean isEnd;
   Map<Character, TrieNode> children;

   public TrieNode() {
      this.isEnd = false;
      this.children = new HashMap<>();
   }

   // root.insert(word, 0);
   public void insert(String str, int index) {
      if (index == str.length()) {
         this.isEnd = true;
         return;
      }

      char c = str.charAt(index);
      if (!children.containsKey(c)) {
         children.put(c, new TrieNode());
      }

      TrieNode node = children.get(c);
      node.insert(str, index+1);
   }

   // root.find(prefix, 0);
   public TrieNode find(String prefix, int index) {
      if (index == prefix.length()) {
         return this;
      }

      char c = prefix.charAt(index);
      if (!children.containsKey(c)) return null;

      return children.get(c).find(prefix, index + 1);
   }

   // subNode.output()
   public List<String> output() {
      List<String> result = new ArrayList<>();

      StringBuilder sb = new StringBuilder();
      dfs(this, sb, result);

      return result;
   }

   private void dfs(TrieNode node, StringBuilder sb, List<String> result) {
      if (node.isEnd) {
         System.out.println(sb.toString());
         result.add(sb.toString());
      }

      for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
         char c = entry.getKey();
         TrieNode child = entry.getValue();

         StringBuilder newSb = new StringBuilder(sb);
         newSb.append(c);
         dfs(child, newSb, result);
      }
   }
}

//"a", "and"