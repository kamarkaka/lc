package com.kamarkaka.doordash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/***
 * example
 * Existing Menu in our system:
 *             Existing tree
 *             a(1)
 *            /    \
 *          b(2)   c(3)
 *         /   \     \
 *       d(4) e(5)  g(7)
 * New Menu sent by the Merchant:
 *               New tree
 *               a(1)
 *              /    \
 *            b(2)   h(8)
 *        /  /   \  \
 *    e(5) d(4) f(6) g(7)
 * Expected Answer: 5
 */
public class MenuDiff {
   public int findDiffNodes(TreeNode root1, TreeNode root2) {
      // Both trees are empty, they are the same tree
      if (root1 == null && root2 == null) return 0;

      // Only one of the trees is not null
      // Or if the key of both trees are different, then return
      // the node count of both trees (which could be zero or one of them)
      if ((root1 != null && root2 == null) || (root1 == null && root2 != null) || (root1.key != root2.key)) {
         return countNodes(root1) + countNodes(root2);
      }

      // If their values are different
      // Then we include the current nodes of the two trees as the only diff and then
      // compute the diff of the children
      int diffCount = 0;
      if (root1.value != root2.value) {
         diffCount = 2;
      }

      List<TreeNode> children1 = root1.children;
      List<TreeNode> children2 = root2.children;

      // Compare both children by their keys, to see if they have any intersections
      Map<Integer, TreeNode> childrenKeyMap1 = new HashMap<>();
      for (TreeNode child1 : children1) {
         childrenKeyMap1.put(child1.key, child1);
      }
      Map<Integer, TreeNode> childrenKeyMap2 = new HashMap<>();
      for (TreeNode child2 : children2) {
         childrenKeyMap2.put(child2.key, child2);
      }

      for (Map.Entry<Integer, TreeNode> childEntry1 : childrenKeyMap1.entrySet()) {
         int key1 = childEntry1.getKey();
         TreeNode child1 = childEntry1.getValue();

         // If the same key is not found from the second subtree, then just count the nodes of the current subtree
         if (!childrenKeyMap2.containsKey(key1)) {
            diffCount += countNodes(child1);
         } else {
            // Otherwise, recursively count the diff nodes of the subtrees, remove the entry
            // from other map afterwards
            TreeNode child2 = childrenKeyMap2.get(key1);
            diffCount += findDiffNodes(child1, child2);
            childrenKeyMap2.remove(key1);
         }
      }

      // If the second map still has keys remaining, that means the are extra nodes
      // that are not found in the first subtree, so should add their node count to the result
      for (TreeNode child2 : childrenKeyMap2.values()) {
         diffCount += countNodes(child2);
      }

      return diffCount;
   }

   private int countNodes(TreeNode node) {
      if (node == null) return 0;

      int count = 1;
      for (TreeNode child : node.children) {
         count += countNodes(child);
      }
      return count;
   }

   public static void run() {
      MenuDiff diff = new MenuDiff();
      TreeNode root1 = new TreeNode(1, 4);
      root1.children.add(new TreeNode(2, 1));
      root1.children.add(new TreeNode(3, 5));
      root1.children.get(0).children.add(new TreeNode(4, 3));
      root1.children.get(0).children.add(new TreeNode(5, 5));
      root1.children.get(0).children.add(new TreeNode(6, 8));
      root1.children.get(1).children.add(new TreeNode(7, 12));

      TreeNode root2 = new TreeNode(1, 4);
      root2.children.add(new TreeNode(2, 1));
      root2.children.add(new TreeNode(12, 5));
      root2.children.get(0).children.add(new TreeNode(5, 5));
      root2.children.get(0).children.add(new TreeNode(4, 3));
      root2.children.get(0).children.add(new TreeNode(22, 8));
      root2.children.get(1).children.add(new TreeNode(7, 12));

      System.out.println(diff.findDiffNodes(root1, root2));
   }
}

class TreeNode {
   int key;
   int value;
   List<TreeNode> children;

   public TreeNode(int key, int value) {
      this.key = key;
      this.value = value;
      this.children = new LinkedList<>();
   }
}


