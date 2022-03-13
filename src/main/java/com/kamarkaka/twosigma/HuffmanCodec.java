package com.kamarkaka.twosigma;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanCodec {
   private HuffmanNode root;
   private Map<Character, String> huffmanCode;

   public HuffmanCodec() {
      this.root = null;
      this.huffmanCode = new HashMap<>();
   }

   public String encode(String text) {
      buildHuffmanTree(text);

      // Print encoded string
      StringBuilder sb = new StringBuilder();
      for (char c: text.toCharArray()) {
         sb.append(huffmanCode.get(c));
      }

      return sb.toString();
   }

   private void encode(HuffmanNode node, String str) {
      if (node == null) return;

      // Found a leaf node
      if (isLeaf(node)) {
         huffmanCode.put(node.c, str.length() > 0 ? str : "1");
      }

      encode(node.left, str + '0');
      encode(node.right, str + '1');
   }

   public String decode(String encodedText) {
      StringBuilder input = new StringBuilder(encodedText);
      StringBuilder output = new StringBuilder();
      if (isLeaf(root)) {
         // Special case: For input like a, aa, aaa, etc.
         while (root.freq-- > 0) {
            output.append(root.c);
         }
      } else {
         // Traverse the Huffman Tree again and this time, decode the encoded string
         int index = 0;
         while (index < input.length() - 1) {
            index = decode(root, index, input, output);
         }
      }

      return output.toString();
   }

   private int decode(HuffmanNode node, int index, StringBuilder input, StringBuilder output) {
      if (node == null) return index;

      // Found a leaf node
      if (isLeaf(node)) {
         output.append(node.c);
         return index;
      }

      node = (input.charAt(index) == '0') ? node.left : node.right;
      return decode(node, index + 1, input, output);
   }

   private boolean isLeaf(HuffmanNode root) {
      return root.left == null && root.right == null;
   }

   private void buildHuffmanTree(String text) {
      if (text == null || text.length() == 0) return;

      // Count the frequency of appearance of each character and store it in a map
      Map<Character, Integer> freqMap = new HashMap<>();
      for (char c: text.toCharArray()) {
         freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
      }

      // create a priority queue to store live nodes of the Huffman tree.
      PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));

      // create a leaf node for each character and add it to the priority queue.
      for (Map.Entry<Character, Integer> entry: freqMap.entrySet()) {
         pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
      }

      // do till there is more than one node in the queue
      while (pq.size() != 1) {
         // Remove the two nodes of the highest priority (the lowest frequency) from the queue
         HuffmanNode left = pq.poll();
         HuffmanNode right = pq.poll();

         // create a new internal node with these two nodes as children and with a frequency equal to the sum of both nodes' frequencies.
         // Add the new node to the priority queue.
         int freq = left.freq + right.freq;
         pq.add(new HuffmanNode(null, freq, left, right));
      }

      // `root` stores pointer to the root of Huffman Tree
      root = pq.peek();

      // Traverse the Huffman tree and store the Huffman codes in a map
      huffmanCode.clear();
      encode(root, "");
   }

   class HuffmanNode {
      Character c;
      int freq;
      HuffmanNode left;
      HuffmanNode right;

      HuffmanNode(Character c, int freq) {
         this.c = c;
         this.freq = freq;
      }

      HuffmanNode(Character c, int freq, HuffmanNode left, HuffmanNode right) {
         this.c = c;
         this.freq = freq;
         this.left = left;
         this.right = right;
      }
   }

   public static void run() {
      HuffmanCodec codec = new HuffmanCodec();
      String text = "Huffman coding is a data compression algorithm."; //"safdsafd","","a","aa"

      String encoded = codec.encode(text);

      // Print the Huffman codes
      System.out.println("Huffman Codes are: " + codec.huffmanCode);
      System.out.println("original string is: " + text);
      System.out.println("encoded string is: " + encoded);

      String decoded = codec.decode(encoded);
      System.out.println("decoded string is: " + decoded);
   }
}

