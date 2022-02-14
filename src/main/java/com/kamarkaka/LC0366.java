package com.kamarkaka;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * 366. Find Leaves of Binary Tree
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 *   Collect all the leaf nodes.
 *   Remove all the leaf nodes.
 *   Repeat until the tree is empty.
 *
 * Example 1:
 *   Input: root = [1,2,3,4,5]
 *   Output: [[4,5,3],[2],[1]]
 *   Explanation:
 *     [[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level it does not matter the order on which elements are returned.
 *
 * Example 2:
 *   Input: root = [1]
 *   Output: [[1]]
 *
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 100].
 *   -100 <= Node.val <= 100
 */
public class LC0366 {
   public List<List<Integer>> findLeaves(TreeNode root) {
      List<List<Integer>> res = new ArrayList<>();

      while (root != null) {
         Set<TreeNode> leaves = removeLeaves(root);
         List<Integer> list = new ArrayList<>();
         for (TreeNode leaf : leaves) {
            list.add(leaf.val);
         }
         res.add(list);

         if (leaves.contains(root)) break;
      }

      return res;
   }

   Set<TreeNode> removeLeaves(TreeNode root) {
      Set<TreeNode> res = new HashSet<>();

      if (root == null) return res;
      if (root.left == null && root.right == null) {
         res.add(root);
         return res;
      }

      Set<TreeNode> lefts = removeLeaves(root.left);
      Set<TreeNode> rights = removeLeaves(root.right);

      if (lefts.contains(root.left)) root.left = null;
      if (rights.contains(root.right)) root.right = null;

      res.addAll(lefts);
      res.addAll(rights);

      return res;
   }
}
