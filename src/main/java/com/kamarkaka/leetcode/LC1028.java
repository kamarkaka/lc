package com.kamarkaka.leetcode;

import com.kamarkaka.common.TreeNode;

/***
 * 1028. Recover a Tree From Preorder Traversal
 * We run a preorder depth-first search (DFS) on the root of a binary tree.
 * At each node in this traversal, we output D dashes (where D is the depth of this node), then we output the value of
 * this node. If the depth of a node is D, the depth of its immediate child is D + 1. The depth of the root node is 0.
 * If a node has only one child, that child is guaranteed to be the left child.
 * Given the output traversal of this traversal, recover the tree and return its root.
 * Example 1:
 *   Input: traversal = "1-2--3--4-5--6--7"
 *   Output: [1,2,5,3,4,6,7]
 * Example 2:
 *   Input: traversal = "1-2--3---4-5--6---7"
 *   Output: [1,2,5,3,null,6,null,4,null,7]
 * Example 3:
 *   Input: traversal = "1-401--349---90--88"
 *   Output: [1,401,null,349,88,90]
 * Constraints:
 *   The number of nodes in the original tree is in the range [1, 1000].
 *   1 <= Node.val <= 10^9
 */
public class LC1028 {
    int index = 0;
    public TreeNode recoverFromPreorder(String S) {
        return helper(S, 0);
    }

    public TreeNode helper(String s, int depth) {
        int numDash = 0;
        while (index + numDash < s.length() && s.charAt(index + numDash) == '-') {
            numDash++;
        }
        if (numDash != depth) return null;
        int next = index + numDash;
        while (next < s.length() && s.charAt(next) != '-') next++;
        int val = Integer.parseInt(s.substring(index + numDash, next));
        index = next;
        TreeNode root = new TreeNode(val);
        root.left = helper(s, depth + 1);
        root.right = helper(s, depth + 1);
        return root;
    }
}
