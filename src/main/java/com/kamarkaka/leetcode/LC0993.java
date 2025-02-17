package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/***
 * 993. Cousins in Binary Tree
 * Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y, return
 * true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 * Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.
 * Example 1:
 *   Input: root = [1,2,3,4], x = 4, y = 3
 *   Output: false
 * Example 2:
 *   Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
 *   Output: true
 * Example 3:
 *   Input: root = [1,2,3,null,4], x = 2, y = 3
 *   Output: false
 * Constraints:
 *   The number of nodes in the tree is in the range [2, 100].
 *   1 <= Node.val <= 100
 *   Each node has a unique value.
 *   x != y
 *   x and y are exist in the tree.
 */
public class LC0993 {
   public boolean isCousins(TreeNode root, int x, int y) {
      Queue<TreeNode> queue = new LinkedList<>();
      queue.add(root);

      while (!queue.isEmpty()) {
         boolean isSameParent = false;
         boolean isNodeFound = false;

         int nodesWithSameDepth = queue.size();
         for (int i = 0; i < nodesWithSameDepth; i++) {
            TreeNode node = queue.poll();

            if (node == null) {
               isSameParent = false;
            } else {
               if (node.val == x || node.val == y) {
                  if (!isNodeFound) {
                     isNodeFound = true;
                     isSameParent = true;
                  } else {
                     return !isSameParent;
                  }
               }

               if (node.left != null) queue.add(node.left);
               if (node.right != null) queue.add(node.right);
               queue.add(null);
            }

         }

         if (isNodeFound) return false;
      }
      return false;
   }
}
