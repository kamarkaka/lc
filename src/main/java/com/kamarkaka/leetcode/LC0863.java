package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/***
 * 863. All Nodes Distance K in Binary Tree
 * Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values
 * of all nodes that have a distance k from the target node.
 * You can return the answer in any order.
 * Example 1:
 *   Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 *   Output: [7,4,1]
 *   Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
 * Example 2:
 *   Input: root = [1], target = 1, k = 3
 *   Output: []
 * Constraints:
 *   The number of nodes in the tree is in the range [1, 500].
 *   0 <= Node.val <= 500
 *   All the values Node.val are unique.
 *   target is the value of one of the nodes in the tree.
 *   0 <= k <= 1000
 */
public class LC0863 {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> result = new ArrayList<>();
        findTargetDepth(result, root, target, K, 0);
        return result;
    }

    private int findTargetDepth(List<Integer> result, TreeNode root, TreeNode target, int k, int depth) {
        if (root == null) return -1;

        if (root == target) {
            // collect all nodes under target node within dist k
            collectNodes(result, root, k);
            return depth;
        }

        // search left
        int targetDepth = findTargetDepth(result, root.left, target, k, depth + 1);
        if (targetDepth != -1) {
            // is current node at the dist K from target
            if ((targetDepth - depth) == k) {
                result.add(root.val);
            } else {
                // collect nodes on the right side
                collectNodes(result, root.right, k - (targetDepth - depth) - 1);
            }
            return targetDepth;
        }

        // if not found, search right
        targetDepth = findTargetDepth(result, root.right, target, k, depth + 1);
        if (targetDepth != -1) {
            // is current node at the dist K from target
            if ((targetDepth - depth) == k) {
                result.add(root.val);
            } else {
                // collect nodes on the left side
                collectNodes(result, root.left, k - (targetDepth - depth) - 1);
            }
            return targetDepth;
        }

        // not found
        return -1;
    }

    private void collectNodes(List<Integer> result, TreeNode root, int k) {
        if (root == null || k < 0) return;

        if (k == 0) {
            result.add(root.val);
            return;
        }

        collectNodes(result, root.left, k - 1);
        collectNodes(result, root.right, k - 1);
    }
}
