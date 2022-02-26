package com.kamarkaka;

import com.kamarkaka.common.TreeNode;

/***
 * Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values of the nodes in the tree.
 *
 * Example 1:
 *    Input: root = [3,1,4,null,2], k = 1
 *    Output: 1
 *
 * Example 2:
 *    Input: root = [5,3,6,2,4,null,null,1], k = 3
 *    Output: 3
 *
 * Constraints:
 *    The number of nodes in the tree is n.
 *    1 <= k <= n <= 10^4
 *    0 <= Node.val <= 10^4
 *
 * Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?
 */
public class LC0230 {
   int count = 0;
   int val = 0;

   public int kthSmallest(TreeNode root, int k) {
      traverse(root, k);
      return val;
   }

   private void traverse(TreeNode node, int k) {
      if (node == null) return;

      traverse(node.left, k);

      count++;
      if (count == k) {
         val = node.val;
         return;
      }

      traverse(node.right, k);
   }
}