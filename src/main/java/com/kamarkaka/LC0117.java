package com.kamarkaka;

import java.util.ArrayList;
import java.util.List;

/***
 * 117. Populating Next Right Pointers in Each Node II
 * Given a binary tree
 *   struct Node {
 *     int val;
 *     Node *left;
 *     Node *right;
 *     Node *next;
 *   }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 *
 * Example 1:
 *   Input: root = [1,2,3,4,5,null,7]
 *   Output: [1,#,2,3,#,4,5,7,#]
 *   Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 * Example 2:
 *   Input: root = []
 *   Output: []
 *
 * Constraints:
 *   The number of nodes in the tree is in the range [0, 6000].
 *   -100 <= Node.val <= 100
 *
 * Follow-up:
 *   You may only use constant extra space.
 *   The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */
public class LC0117 {
   public Node connect(Node root) {
      List<Node> list = new ArrayList<>();
      list.add(root);

      while (list.size() > 0) {
         List<Node> nextList = new ArrayList<>();
         for (int i = 0; i < list.size(); i++) {
            Node node = list.get(i);
            if (node.left != null) nextList.add(node.left);
            if (node.right != null) nextList.add(node.right);

            if (i + 1 < list.size()) {
               node.next = list.get(i + 1);
            }
         }
         list = nextList;
      }

      return root;
   }

   public Node connect2(Node root) {
      Node realRoot = root;
      Node dummyHead = new Node(0);
      Node pre = dummyHead;
      while (root != null) {
         if (root.left != null) {
            pre.next = root.left;
            pre = pre.next;
         }
         if (root.right != null) {
            pre.next = root.right;
            pre = pre.next;
         }
         root = root.next;
         if (root == null) {
            pre = dummyHead;
            root = dummyHead.next;
            dummyHead.next = null;
         }
      }
      return realRoot;
   }

   class Node {
      public int val;
      public Node left;
      public Node right;
      public Node next;

      public Node() {}

      public Node(int _val) {
         val = _val;
      }

      public Node(int _val, Node _left, Node _right, Node _next) {
         val = _val;
         left = _left;
         right = _right;
         next = _next;
      }
   }
}
