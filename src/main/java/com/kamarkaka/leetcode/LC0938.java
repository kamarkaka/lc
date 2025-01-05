package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.Stack;

/***
 * 938. Range Sum of BST
 * Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with
 * a value in the inclusive range [low, high].
 * Example 1:
 *   Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
 *   Output: 32
 *   Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
 * Example 2:
 *   Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 *   Output: 23
 *   Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 2 * 10^4].
 *   1 <= Node.val <= 10^5
 *   1 <= low <= high <= 10^5
 *   All Node.val are unique.
 */
public class LC0938 {
   public int rangeSumBST(TreeNode root, int low, int high) {
      int res = 0;

      Stack<TreeNode> stack = new Stack<>();
      stack.push(root);

      while (!stack.isEmpty()) {
         TreeNode node = stack.pop();
         if (node == null) continue;

         if (low <= node.val && node.val <= high) {
            res += node.val;
         }

         if (low < node.val) stack.push(node.left);
         if (node.val < high) stack.push(node.right);
      }

      return res;
   }
}
