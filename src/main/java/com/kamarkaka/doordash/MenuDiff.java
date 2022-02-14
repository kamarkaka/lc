package com.kamarkaka.doordash;

/***
 * example
 * Existing Menu in our system:
 *             Existing tree
 *             a(1)
 *            /    \
 *          b(2)   c(3)
 *         /   \     \
 *       d(4) e(5)  g(7)
 * New Menu sent by the Merchant:
 *               New tree
 *               a(1)
 *              /    \
 *            b(2)   h(8)
 *        /  /   \  \
 *    e(5) d(4) f(6) g(7)
 * Expected Answer: 5
 */
public class MenuDiff {
   public int countDiff(Node root1, Node root2) {
      if (root1 == null && root2 == null) return 0;
      if (root1 == null || root2 == null) return 1;
      int count = 0;

      if (root1.val != root2.val) count++;
      if (root1.children == null || root1.children.length == 0) {
         count += countNodes(root2) - 1;
         return count;
      }

      int i = 0;
      for (; i < root1.children.length; i++) {
         Node node1 = root1.children[i];
         if (root2.children != null && root2.children.length > i) {
            Node node2 = root2.children[i];
            count += countDiff(node1, node2);
         } else {
            count += countNodes(node1);
         }
      }

      if (root2.children != null && root2.children.length > i) {
         for (; i < root2.children.length; i++) {
            Node node2 = root2.children[i];
            count += countNodes(node2);
         }
      }

      return count;
   }

   private int countNodes(Node node) {
      if (node == null) return 0;
      int count = 1;

      if (node.children == null) return count;

      for (Node child : node.children) {
         count += countNodes(child);
      }

      return count;
   }

   public static void run() {
      MenuDiff diff = new MenuDiff();
      Node root1 = new Node(1);
      Node node1_2 = new Node(2);
      Node node1_3 = new Node(3);
      Node node1_4 = new Node(4);
      Node node1_5 = new Node(5);
      Node node1_7 = new Node(7);
      root1.children = new Node[] {node1_2, node1_3};
      node1_2.children = new Node[] {node1_4, node1_5};
      node1_3.children = new Node[] {node1_7};

      Node root2 = new Node(1);
      Node node2_2 = new Node(2);
      Node node2_8 = new Node(8);
      Node node2_5 = new Node(5);
      Node node2_4 = new Node(4);
      Node node2_6 = new Node(6);
      Node node2_7 = new Node(7);
      root2.children = new Node[] {node2_2, node2_8};
      node2_2.children = new Node[] {node2_5, node2_4, node2_6, node2_7};

      System.out.println(diff.countDiff(root1, root2));
   }
}

class Node {
   int val;
   Node[] children;

   public Node(int val) {
      this.val = val;
      this.children = null;
   }
}

