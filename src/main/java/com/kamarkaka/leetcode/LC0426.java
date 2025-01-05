package com.kamarkaka.leetcode;

/***
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 * You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a
 * doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element, and
 * the successor of the last element is the first element.
 * We want to do the transformation in place. After the transformation, the left pointer of the tree node should point
 * to its predecessor, and the right pointer should point to its successor. You should return the pointer to the
 * smallest element of the linked list.
 * Example 1:
 *   Input: root = [4,2,5,1,3]
 *   Output: [1,2,3,4,5]
 *   Explanation: The figure below shows the transformed BST. The solid line indicates the successor relationship, while
 *   the dashed line means the predecessor relationship.
 * Example 2:
 *   Input: root = [2,1,3]
 *   Output: [1,2,3]
 * Constraints:
 *   The number of nodes in the tree is in the range [0, 2000].
 *   -1000 <= Node.val <= 1000
 *   All the values of the tree are unique.
 */
public class LC0426 {
   public Node treeToDoublyList(Node root) {
      if (root == null) return null;

      Node[] result = flatten(root);
      result[1].right = result[0];
      result[0].left = result[1];
      return result[0];
   }

   public Node[] flatten(Node node) {
      if (node == null || (node.right == null && node.left == null)) return new Node[] { node, node };

      Node head = node;
      Node tail = node;
      Node[] left = flatten(node.left);
      Node[] right = flatten(node.right);

      if (left[0] != null) {
         head = left[0];
         left[1].right = node;
         node.left = left[1];
      }

      if (right[0] != null) {
         node.right = right[0];
         right[0].left = node;
         tail = right[1];
      }

      return new Node[] { head, tail };
   }

   private class Node {
      public int val;
      public Node left;
      public Node right;

      public Node() {}

      public Node(int _val) {
         val = _val;
      }

      public Node(int _val,Node _left,Node _right) {
         val = _val;
         left = _left;
         right = _right;
      }
   }
}
