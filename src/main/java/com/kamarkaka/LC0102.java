/***
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 *
 * Example 1:
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: [[3],[9,20],[15,7]]
 *
 * Example 2:
 *   Input: root = [1]
 *   Output: [[1]]
 *
 * Example 3:
 *   Input: root = []
 *   Output: []
 *
 * Constraints:
 *   The number of nodes in the tree is in the range [0, 2000].
 *   -1000 <= Node.val <= 1000
 */

package main.java.com.kamarkaka;

import main.java.com.kamarkaka.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC0102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> valList = new ArrayList<>();
        List<TreeNode> lvlList = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            valList.add(node.val);

            if (node.left != null) lvlList.add(node.left);
            if (node.right != null) lvlList.add(node.right);

            if (queue.isEmpty()) {
                res.add(valList);
                queue.addAll(lvlList);

                valList = new ArrayList<>();
                lvlList = new ArrayList<>();
            }
        }

        return res;
    }
}
