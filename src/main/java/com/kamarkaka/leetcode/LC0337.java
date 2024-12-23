package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/***
 * 337. House Robber III
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
 * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 *
 * Example 1:
 *    Input: root = [3,2,3,null,3,null,1]
 *    Output: 7
 *    Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *
 * Example 2:
 *    Input: root = [3,4,5,1,3,null,1]
 *    Output: 9
 *    Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 *
 * Constraints:
 *    The number of nodes in the tree is in the range [1, 10^4].
 *    0 <= Node.val <= 10^4
 */
public class LC0337 {
   Map<TreeNode, Integer> robResult = new HashMap<>();
   Map<TreeNode, Integer> noRobResult = new HashMap<>();

   public int rob(TreeNode root) {
      return helper(root, false);
   }

   private int helper(TreeNode node, boolean parentRobbed) {
      if (node == null) return 0;

      if (parentRobbed) {
         if (robResult.containsKey(node)) {
            return robResult.get(node);
         }

         int result = helper(node.left, false) + helper(node.right, false);
         robResult.put(node, result);
         return result;
      }

      if (noRobResult.containsKey(node)) {
         return noRobResult.get(node);
      }

      int rob = node.val + helper(node.left, true) + helper(node.right, true);
      int noRob = helper(node.left, false) + helper(node.right, false);
      int result = Math.max(rob, noRob);
      noRobResult.put(node, result);
      return result;
   }
}
