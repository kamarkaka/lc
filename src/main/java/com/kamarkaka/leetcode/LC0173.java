package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/***
 * 173. Binary Search Tree Iterator
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree
 * (BST):
 *   BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of
 *   the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
 *   boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise
 *   returns false.
 *   int next() Moves the pointer to the right, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the
 * smallest element in the BST.
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order
 * traversal when next() is called.
 * Example 1:
 *   Input
 *   ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 *   [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 *   Output
 *   [null, 3, 7, true, 9, true, 15, true, 20, false]
 *   Explanation
 *   BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 *   bSTIterator.next();    // return 3
 *   bSTIterator.next();    // return 7
 *   bSTIterator.hasNext(); // return True
 *   bSTIterator.next();    // return 9
 *   bSTIterator.hasNext(); // return True
 *   bSTIterator.next();    // return 15
 *   bSTIterator.hasNext(); // return True
 *   bSTIterator.next();    // return 20
 *   bSTIterator.hasNext(); // return False
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 10^5].
 *   0 <= Node.val <= 10^6
 *   At most 10^5 calls will be made to hasNext, and next.
 * Follow up:
 *   Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of
 *   the tree?
 */
public class LC0173 {
   class BSTIterator {
      List<Integer> nodesSorted;
      int index;

      public BSTIterator(TreeNode root) {
         this.nodesSorted = new ArrayList<>();
         this.index = -1;
         inorder(root);
      }

      public int next() {
         return nodesSorted.get(++index);
      }

      public boolean hasNext() {
         return index + 1 < nodesSorted.size();
      }

      private void inorder(TreeNode root) {
         if (root == null) return;
         inorder(root.left);
         nodesSorted.add(root.val);
         inorder(root.right);
      }
   }

   class BSTIterator2 {
      Stack<TreeNode> stack;

      public BSTIterator2(TreeNode root) {
         // Stack for the recursion simulation
         this.stack = new Stack<>();

         // Remember that the algorithm starts with a call to the helper function
         // with the root node as the input
         this.leftmostInorder(root);
      }

      private void leftmostInorder(TreeNode root) {
         // For a given node, add all the elements in the leftmost branch of the tree
         // under it to the stack.
         while (root != null) {
            this.stack.push(root);
            root = root.left;
         }
      }

      /**
       * @return the next smallest number
       */
      public int next() {
         // Node at the top of the stack is the next smallest element
         TreeNode topmostNode = this.stack.pop();

         // Need to maintain the invariant. If the node has a right child, call the
         // helper function for the right child
         if (topmostNode.right != null) {
            this.leftmostInorder(topmostNode.right);
         }

         return topmostNode.val;
      }

      /**
       * @return whether we have a next smallest number
       */
      public boolean hasNext() {
         return !this.stack.isEmpty();
      }
   }
}
