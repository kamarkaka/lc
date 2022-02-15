package com.kamarkaka;

import com.kamarkaka.common.TreeNode;

/***
 * 114. Flatten Binary Tree to Linked List
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *    The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 *    The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 * Example 1:
 *    Input: root = [1,2,5,3,4,null,6]
 *    Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Example 2:
 *    Input: root = []
 *    Output: []
 *
 * Example 3:
 *    Input: root = [0]
 *    Output: [0]
 *
 * Constraints:
 *    The number of nodes in the tree is in the range [0, 2000].
 *    -100 <= Node.val <= 100
 *
 * Follow up: Can you flatten the tree in-place (with O(1) extra space)?
 *
 */
public class LC0114 {
   public void flatten(TreeNode root) {
      flattenTree(root);
   }

   private TreeNode flattenTree(TreeNode node) {

      // Handle the null scenario
      if (node == null) {
         return null;
      }

      // For a leaf node, we simply return the
      // node as is.
      if (node.left == null && node.right == null) {
         return node;
      }

      //Recursively flatten the left subtree
      TreeNode leftTail = this.flattenTree(node.left);

      // Recursively flatten the right subtree
      TreeNode rightTail = this.flattenTree(node.right);

      // If there was a left subtree, we shuffle the connections
      // around so that there is nothing on the left side
      // anymore.
      if (leftTail != null) {
         leftTail.right = node.right;
         node.right = node.left;
         node.left = null;
      }

      // We need to return the "rightmost" node after we are
      // done wiring the new connections.
      return rightTail == null ? leftTail : rightTail;
   }
}
