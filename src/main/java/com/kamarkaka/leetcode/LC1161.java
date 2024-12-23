package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * 1161. Maximum Level Sum of a Binary Tree
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
 * Example 1:
 *   Input: root = [1,7,0,7,-8,null,null]
 *   Output: 2
 *   Explanation:
 *   Level 1 sum = 1.
 *   Level 2 sum = 7 + 0 = 7.
 *   Level 3 sum = 7 + -8 = -1.
 *   So we return the level with the maximum sum which is level 2.
 * Example 2:
 *   Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
 *   Output: 2
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 10^4].
 *   -10^5 <= Node.val <= 10^5
 */
public class LC1161 {
    public int maxLevelSum(TreeNode root) {
        if (root == null) return -1;

        int level = 0;
        int maxSum = Integer.MIN_VALUE;
        int maxLvl = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            level++;
            List<TreeNode> nodes = new ArrayList<>();

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                nodes.add(node);
            }

            int sum = 0;
            for (TreeNode node : nodes) {
                sum += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            if (sum > maxSum) {
                maxSum = sum;
                maxLvl = level;
            }
        }

        return maxLvl;
    }
}
