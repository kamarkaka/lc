package main.java.com.kamarkaka;

import main.java.com.kamarkaka.common.TreeNode;

import java.util.*;

/***
 * 1110. Delete Nodes And Return Forest
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
 *
 * Example 1:
 *   Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 *   Output: [[1,2,null,4],[6],[7]]
 *
 * Example 2:
 *   Input: root = [1,2,4,null,3], to_delete = [3]
 *   Output: [[1,2,4]]
 *
 * Constraints:
 *   The number of nodes in the given tree is at most 1000.
 *   Each node has a distinct value between 1 and 1000.
 *   to_delete.length <= 1000
 *   to_delete contains distinct values between 1 and 1000.
 */
public class LC1110 {
   Set<TreeNode> set;
   Set<Integer> deleteSet;

   public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
      set = new HashSet<>();
      set.add(root);

      deleteSet = new HashSet<>();
      for (int num : to_delete) {
         deleteSet.add(num);
      }

      delNodes(root);

      return new ArrayList<>(set);
   }

   private void delNodes(TreeNode node) {
      if (node == null) return;

      if (deleteSet.contains(node.val)) {
         set.remove(node);
         if (node.left != null) set.add(node.left);
         if (node.right != null) set.add(node.right);
      }

      delNodes(node.left);
      delNodes(node.right);

      if (node.left != null && deleteSet.contains(node.left.val)) {
         node.left = null;
      }

      if (node.right != null && deleteSet.contains(node.right.val)) {
         node.right = null;
      }
   }
}
