package com.kamarkaka;

import com.kamarkaka.common.TreeNode;

import java.util.*;

/***
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
 *
 * Example 1:
 *    Input: root = [3,9,20,null,null,15,7]
 *    Output: [[3],[20,9],[15,7]]
 *
 * Example 2:
 *    Input: root = [1]
 *    Output: [[1]]
 *
 * Example 3:
 *    Input: root = []
 *    Output: []
 *
 * Constraints:
 *    The number of nodes in the tree is in the range [0, 2000].
 *    -100 <= Node.val <= 100
 */
public class LC0103 {
   public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
      List<List<Integer>> res = new ArrayList<>();
      if (root == null) return res;

      int dir = 1;
      Queue<TreeNode> queue = new LinkedList<>();
      queue.add(root);
      List<Integer> valList = new ArrayList<>();
      List<TreeNode> lvlList = new ArrayList<>();
      while (!queue.isEmpty()) {
         TreeNode node = queue.poll();
         valList.add(node.val);

         if (node.left != null) lvlList.add(node.left);
         if (node.right != null) lvlList.add(node.right);

         if (queue.isEmpty()) {
            if (dir == -1) Collections.reverse(valList);
            res.add(valList);
            queue.addAll(lvlList);

            valList = new ArrayList<>();
            lvlList = new ArrayList<>();
            dir *= -1;
         }
      }

      return res;
   }
}
