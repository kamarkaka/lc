package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/***
 * 958. Check Completeness of a Binary Tree
 * Given the root of a binary tree, determine if it is a complete binary tree.
 * In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last
 * level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 * Example 1:
 *   Input: root = [1,2,3,4,5,6]
 *   Output: true
 *   Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the
 *   last level ({4, 5, 6}) are as far left as possible.
 * Example 2:
 *   Input: root = [1,2,3,4,5,null,7]
 *   Output: false
 *   Explanation: The node with value 7 isn't as far left as possible.
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 100].
 *   1 <= Node.val <= 1000
 */
public class LC0958 {
    public boolean isCompleteTree(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        nodeList.add(root);
        int levelCount = 1;

        while (nodeList.size() > 0) {
            boolean isLastLevel = true;
            if (levelCount == nodeList.size()) isLastLevel = false;

            List<TreeNode> newNodeList = new ArrayList<>();
            int lastLeafIndex = -1;
            int lastNonLeafIndex = -1;

            for (int i = 0; i < nodeList.size(); i++) {
                TreeNode node = nodeList.get(i);

                if (node.left == null && node.right == null) {
                    lastLeafIndex = i;
                } else {
                    if (lastLeafIndex > -1) return false;
                    if (lastNonLeafIndex > -1) return false;
                    if (isLastLevel) return false;

                    if (node.left != null && node.right != null) {
                        newNodeList.add(node.left);
                        newNodeList.add(node.right);
                    } else if (node.left == null && node.right != null) {
                        return false;
                    } else {
                        newNodeList.add(node.left);
                        lastNonLeafIndex = i;
                    }
                }
            }

            nodeList = newNodeList;
            levelCount *= 2;
        }

        return true;
    }
}
