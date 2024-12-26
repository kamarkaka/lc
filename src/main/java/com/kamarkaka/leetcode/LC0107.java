package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/***
 * 107. Binary Tree Level Order Traversal II
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to
 * right, level by level from leaf to root).
 * Example 1:
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: [[15,7],[9,20],[3]]
 * Example 2:
 *   Input: root = [1]
 *   Output: [[1]]
 * Example 3:
 *   Input: root = []
 *   Output: []
 * Constraints:
 *   The number of nodes in the tree is in the range [0, 2000].
 *   -1000 <= Node.val <= 1000
 */
public class LC0107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(root, 0, result);
        return result;
    }

    public void helper(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) return;

        if (level >= result.size()) {
            result.add(0, new ArrayList<Integer>());
        }

        helper(node.left, level + 1, result);
        helper(node.right, level + 1, result);

        result.get(result.size() - 1 - level).add(node.val);
    }
}
