package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/***
 * 95. Unique Binary Search Trees II
 * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of
 * unique values from 1 to n. Return the answer in any order.
 * Example 1:
 *   Input: n = 3
 *   Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * Example 2:
 *   Input: n = 1
 *   Output: [[1]]
 * Constraints:
 *   1 <= n <= 8
 */
public class LC0095 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int from, int to) {
        List<TreeNode> result = new ArrayList<>();
        if (from > to) {
            result.add(null);
            return result;
        } else if (from == to) {
            TreeNode node = new TreeNode(from);
            result.add(node);
            return result;
        }

        for (int i = from; i <= to; i++) {
            List<TreeNode> leftSubTrees = generateTrees(from, i - 1);
            List<TreeNode> rightSubTrees = generateTrees(i + 1, to);

            for (TreeNode leftSubTree : leftSubTrees) {
                for (TreeNode rightSubTree : rightSubTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftSubTree;
                    root.right = rightSubTree;
                    result.add(root);
                }
            }
        }
        return result;
    }
}
