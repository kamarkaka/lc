package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.*;

/***
 * 742. Closest Leaf in a Binary Tree
 * Given the root of a binary tree where every node has a unique value and a target integer k, return the value of the
 * nearest leaf node to the target k in the tree.
 * Nearest to a leaf means the least number of edges traveled on the binary tree to reach any leaf of the tree. Also, a
 * node is called a leaf if it has no children.
 * Example 1:
 *   Input: root = [1,3,2], k = 1
 *   Output: 2
 *   Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
 * Example 2:
 *   Input: root = [1], k = 1
 *   Output: 1
 *   Explanation: The nearest leaf node is the root node itself.
 * Example 3:
 *   Input: root = [1,2,3,4,null,null,null,5,null,6], k = 2
 *   Output: 3
 *   Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 1000].
 *   1 <= Node.val <= 1000
 *   All the values of the tree are unique.
 *   There exist some node in the tree where Node.val == k.
 */
public class LC0742 {
   Map<TreeNode, TreeNode> map = new HashMap<>();

   public int findClosestLeaf(TreeNode root, int k) {
      findParent(root, null);

      TreeNode startNode = findNode(root, k);
      Queue<TreeNodeInfo> queue = new LinkedList<>();
      queue.add(new TreeNodeInfo(startNode, 0));

      HashSet<TreeNode> visited = new HashSet<>();
      visited.add(startNode);

      while (!queue.isEmpty()) {
         TreeNodeInfo nodeInfo = queue.poll();
         TreeNode cur = nodeInfo.node;
         int dis = nodeInfo.dis;

         if (cur.left == null && cur.right == null) return cur.val;

         if (cur.left != null && visited.add(cur.left)) {
            queue.add(new TreeNodeInfo(cur.left, dis + 1));
         }

         if (cur.right != null && visited.add(cur.right)) {
            queue.add(new TreeNodeInfo(cur.right, dis + 1));
         }

         if (map.containsKey(cur) && map.get(cur) != null && visited.add(map.get(cur))) {
            queue.add(new TreeNodeInfo(map.get(cur), dis + 1));
         }
      }
      return -1;
   }

   public void findParent(TreeNode root, TreeNode parent) {
      if (root == null) return;

      map.put(root, parent);

      findParent(root.left, root);
      findParent(root.right, root);
   }

   public TreeNode findNode(TreeNode node, int k) {
      if (node == null) return null;
      if (node.val == k) return node;

      TreeNode left = findNode(node.left, k);
      if (left != null) return left;
      return findNode(node.right, k);
   }
}

class TreeNodeInfo {
   TreeNode node;
   int dis;

   TreeNodeInfo(TreeNode node, int dis) {
      this.node = node;
      this.dis = dis;
   }
}
