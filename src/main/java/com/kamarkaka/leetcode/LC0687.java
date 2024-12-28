package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 687. Longest Univalue Path
 * Given the root of a binary tree, return the length of the longest path, where each node in the path has the same
 * value. This path may or may not pass through the root.
 * The length of the path between two nodes is represented by the number of edges between them.
 * Example 1:
 *   Input: root = [5,4,5,1,1,null,5]
 *   Output: 2
 *   Explanation: The shown image shows that the longest path of the same value (i.e. 5).
 * Example 2:
 *   Input: root = [1,4,5,4,4,null,5]
 *   Output: 2
 *   Explanation: The shown image shows that the longest path of the same value (i.e. 4).
 * Constraints:
 *   The number of nodes in the tree is in the range [0, 10^4].
 *   -1000 <= Node.val <= 1000
 *   The depth of the tree will not exceed 1000.
 */
public class LC0687 {
    int maxLen = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        countLen(root);
        return maxLen;
    }

    public int countLen(TreeNode root) {
        if (root == null) return 0;

        int left = countLen(root.left);
        int right = countLen(root.right);

        if (root.left != null && root.val == root.left.val) {
            left++;
        } else {
            left = 0;
        }

        if (root.right != null && root.val == root.right.val) {
            right++;
        } else {
            right = 0;
        }

        maxLen = Math.max(maxLen, left + right);
        return Math.max(left, right);
    }
}
