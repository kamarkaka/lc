package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 * Example 1:
 *    Input: root = [1,2,2,3,4,4,3]
 *    Output: true
 *
 * Example 2:
 *    Input: root = [1,2,2,null,3,null,3]
 *    Output: false
 *
 * Constraints:
 *    The number of nodes in the tree is in the range [1, 1000].
 *    -100 <= Node.val <= 100
 *
 * Follow up: Could you solve it both recursively and iteratively?
 */
public class LC0101 {
   public boolean isSymmetric(TreeNode root) {
      if (root == null) return false;
      return isSymmetric(root.left, root.right);
   }

   private boolean isSymmetric(TreeNode left, TreeNode right) {
      if (left == null && right == null) return true;
      if (left == null || right == null) return false;
      if (left.val != right.val) return false;
      return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
   }
}
