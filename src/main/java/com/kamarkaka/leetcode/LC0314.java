package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 314. Binary Tree Vertical Order Traversal
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Example 1:
 *    Input: root = [3,9,20,null,null,15,7]
 *    Output: [[9],[3,15],[20],[7]]
 *
 * Example 2:
 *    Input: root = [3,9,8,4,0,1,7]
 *    Output: [[4],[9],[3,0,1],[8],[7]]
 *
 * Example 3:
 *    Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
 *    Output: [[4],[9,5],[3,0,1],[8,2],[7]]
 *
 * Constraints:
 *    The number of nodes in the tree is in the range [0, 100].
 *    -100 <= Node.val <= 100
 */
public class LC0314 {
   int minCol = 0;
   int maxCol = 0;
   Map<Integer, List<Integer>> map = new HashMap<>();

   public List<List<Integer>> verticalOrder(TreeNode root) {
      List<List<Integer>> res = new ArrayList<>();
      if (root == null) return res;

      traverse(root, 0);

      for (int i = minCol; i <= maxCol; i++) {
         if (map.containsKey(i)) {
            res.add(map.get(i));
         }
      }

      return res;
   }

   private void traverse(TreeNode node, int col) {
      if (node == null) return;

      traverse(node.left, col - 1);

      if (minCol > col) minCol = col;
      if (maxCol < col) maxCol = col;
      List<Integer> list = map.getOrDefault(col, new ArrayList<>());
      list.add(node.val);
      map.put(col, list);

      traverse(node.right, col + 1);
   }
}
