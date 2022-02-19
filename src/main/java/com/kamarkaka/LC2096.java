package com.kamarkaka;

import com.kamarkaka.common.TreeNode;

/***
 * 2096. Step-By-Step Directions From a Binary Tree Node to Another
 * You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
 * Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
 *    'L' means to go from a node to its left child node.
 *    'R' means to go from a node to its right child node.
 *    'U' means to go from a node to its parent node.
 * Return the step-by-step directions of the shortest path from node s to node t.
 *
 * Example 1:
 *    Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
 *    Output: "UURL"
 *    Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
 *
 * Example 2:
 *    Input: root = [2,1], startValue = 2, destValue = 1
 *    Output: "L"
 *    Explanation: The shortest path is: 2 → 1.
 *
 * Constraints:
 *    The number of nodes in the tree is n.
 *    2 <= n <= 10^5
 *    1 <= Node.val <= n
 *    All the values in the tree are unique.
 *    1 <= startValue, destValue <= n
 *    startValue != destValue
 */
public class LC2096 {
   public String getDirections(TreeNode root, int startValue, int destValue) {
      String startPath = findNode(root, startValue, new StringBuilder());
      String destPath = findNode(root, destValue, new StringBuilder());

      StringBuilder sb = new StringBuilder();
      int commonParent = 0;
      for (int i = 0; i < Math.min(startPath.length(), destPath.length()); i++) {
         char dir1 = startPath.charAt(i);
         char dir2 = destPath.charAt(i);
         if (dir1 == dir2) commonParent++;
         else break;
      }

      for (int i = commonParent; i < startPath.length(); i++) {
         sb.append('U');
      }
      for (int i = commonParent; i < destPath.length(); i++) {
         sb.append(destPath.charAt(i));
      }
      return sb.toString();
   }

   private String findNode(TreeNode node, int val, StringBuilder sb) {
      if (node == null) return null;
      if (node.val == val) return sb.toString();

      sb.append('L');
      String left = findNode(node.left, val, sb);
      sb.deleteCharAt(sb.length() - 1);
      if (left != null) return left;

      sb.append('R');
      String right = findNode(node.right, val, sb);
      sb.deleteCharAt(sb.length() - 1);
      return right;

   }

   public static void run() {
      LC2096 sol = new LC2096();

      TreeNode root = new TreeNode(5);
      TreeNode node1 = new TreeNode(1);
      TreeNode node2 = new TreeNode(2);
      TreeNode node3 = new TreeNode(3);
      TreeNode node4 = new TreeNode(4);
      TreeNode node6 = new TreeNode(6);
      TreeNode node7 = new TreeNode(7);
      TreeNode node8 = new TreeNode(8);

      root.left = node8;
      root.right = node3;

      node8.left = node1;
      node3.left = node4;
      node3.right = node7;

      node1.left = node6;
      node7.right = node2;

      System.out.println(sol.getDirections(root, 4, 3));
   }
}
