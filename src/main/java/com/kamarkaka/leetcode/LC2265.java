package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 2265. Count Nodes Equal to Average of Subtree
 * Given the root of a binary tree, return the number of nodes where the value of the node is equal to the average of
 * the values in its subtree.
 * Note:
 *   The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
 *   A subtree of root is a tree consisting of root and all of its descendants.
 * Example 1:
 *   Input: root = [4,8,5,0,1,null,6]
 *   Output: 5
 *   Explanation:
 *   For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
 *   For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
 *   For the node with value 0: The average of its subtree is 0 / 1 = 0.
 *   For the node with value 1: The average of its subtree is 1 / 1 = 1.
 *   For the node with value 6: The average of its subtree is 6 / 1 = 6.
 * Example 2:
 *   Input: root = [1]
 *   Output: 1
 *   Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 1000].
 *   0 <= Node.val <= 1000
 */
public class LC2265 {
    public int averageOfSubtree(TreeNode root) {
        Result res = helper(root);
        return res.count;
    }

    private Result helper(TreeNode node) {
        Result res = new Result(0, 0, 0);

        if (node == null) return res;

        Result leftRes = helper(node.left);
        Result rightRes = helper(node.right);

        res.totalNodes = 1 + leftRes.totalNodes + rightRes.totalNodes;
        res.sum = node.val + leftRes.sum + rightRes.sum;
        res.count = leftRes.count + rightRes.count;

        if (node.val == res.sum / res.totalNodes) {
            res.count++;
        }

        return res;
    }

    class Result {
        int totalNodes;
        int sum;
        int count;

        public Result(int totalNodes, int sum, int count) {
            this.totalNodes = totalNodes;
            this.sum = sum;
            this.count = count;
        }
    }
}
