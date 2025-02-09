package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 543. Diameter of Binary Tree
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may
 * not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 * Example 1:
 *   Input: root = [1,2,3,4,5]
 *   Output: 3
 *   Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 * Example 2:
 *   Input: root = [1,2]
 *   Output: 1
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 10^4].
 *   -100 <= Node.val <= 100
 */
public class LC0543 {
   private int diameter;
   public int diameterOfBinaryTree(TreeNode root) {
      this.diameter = 0;
      longestPath(root);
      return this.diameter;
   }
   private int longestPath(TreeNode node){
      if(node == null) return 0;

      // recursively find the longest path in both left child and right child
      int leftPath = longestPath(node.left);
      int rightPath = longestPath(node.right);

      // update the diameter if left_path plus right_path is larger
      this.diameter = Math.max(this.diameter, leftPath + rightPath);

      // return the longest one between left_path and right_path;
      // remember to add 1 for the path connecting the node and its parent
      return Math.max(leftPath, rightPath) + 1;
   }
}
