package com.kamarkaka.google;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class MaxValueInPath {
   public List<String> findMaxValueInPath(TreeNode root) {
      List<String> res = new ArrayList<>();

      dfs(root, Integer.MIN_VALUE, res);

      return res;
   }

   private void dfs(TreeNode node, int currMax, List<String> res) {
      if (node == null) return;
      if (node.left == null && node.right == null) {
         res.add("(" + node.val + ":" + Math.max(currMax, node.val) + ")");
         return;
      }

      currMax = Math.max(currMax, node.val);
      if (node.left != null) dfs(node.left, currMax, res);
      if (node.right != null) dfs(node.right, currMax, res);
   }

   public static void run() {
      MaxValueInPath sol = new MaxValueInPath();
      TreeNode root = new TreeNode(4);
      root.left = new TreeNode(5);
      root.right = new TreeNode(3);
      root.left.left = new TreeNode(1);
      root.right.left = new TreeNode(2);
      root.right.right = new TreeNode(6);

      System.out.println(sol.findMaxValueInPath(root));
   }
}
