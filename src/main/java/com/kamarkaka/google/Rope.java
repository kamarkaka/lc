package com.kamarkaka.google;

public class Rope {
   public char getCharAt(CordTreeNode root, int start, int end, int index) {
      if (index >= start && index <= end && root.isLeaf) {
         return root.value.charAt(index - start);
      }

      int leftStart = start;
      int leftEnd = start + root.left.length - 1;

      int rightStart = leftEnd + 1;
      int rightEnd = end;

      if (index >= leftStart && index <= leftEnd) {
         return getCharAt(root.left, leftStart, leftEnd, index);
      }

      return getCharAt(root.right, rightStart, rightEnd, index);
   }

   public String getSubstring(CordTreeNode root, int rangeStart, int rangeEnd, int start, int end) {
      if (start >= rangeStart && start <= rangeEnd && end >= rangeStart && end <= rangeEnd && root.isLeaf) {
         if (end == rangeEnd) return root.value.substring(start - rangeStart);
         else return root.value.substring(start - rangeStart, end - start + 1);
      }

      int leftStart = rangeStart;
      int leftEnd = rangeStart + root.left.length - 1;

      int rightStart = leftEnd + 1;
      int rightEnd = end;


      if (end > leftEnd && start >= leftStart && start <= leftEnd) {
         String left = getSubstring(root.left, leftStart, leftEnd, start, leftEnd);
         String right = getSubstring(root.right, rightStart, rightEnd, rightStart, end);
         return left + right;
      } else if (start >= leftStart && start <= leftEnd && end >= leftStart && end <= leftEnd) {
         return getSubstring(root.left, leftStart, leftEnd, start, end);
      } else {
         return getSubstring(root.right, rightStart, rightEnd, start, end);
      }
   }

   public static void run() {
      Rope sol = new Rope();
      CordTreeNode node = new CordTreeNode(26);
      node.left = new CordTreeNode(5);
      node.right = new CordTreeNode(21);

      node.left.value = "abcde";
      node.left.isLeaf = true;

      node.right.left = new CordTreeNode(10);
      node.right.right = new CordTreeNode(11);

      node.right.left.value = "fghijklmno";
      node.right.left.isLeaf = true;

      node.right.right.value = "pqrstuvwxyz";
      node.right.right.isLeaf = true;

      System.out.println(node.left.length);
      System.out.println(sol.getCharAt(node, 0, node.length-1, 4)) ;
      System.out.println(sol.getCharAt(node, 0, node.length-1, 6) );
      System.out.println(sol.getCharAt(node, 0, node.length-1, 15));
      System.out.println(sol.getCharAt(node, 0, node.length-1, 25));

      System.out.println(sol.getSubstring(node, 0, 25, 2, 25));
   }
}

class CordTreeNode {
   String value;
   boolean isLeaf;
   int length;
   CordTreeNode left;
   CordTreeNode right;

   CordTreeNode(int length) {
      this.length = length;
   }
}

