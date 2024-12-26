package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 429. N-ary Tree Level Order Traversal
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by
 * the null value (See examples).
 * Example 1:
 *      1
 *     /|\
 *    3 2 4
 *   / \
 *   5 6
 *   Input: root = [1,null,3,2,4,null,5,6]
 *   Output: [[1],[3,2,4],[5,6]]
 * Example 2:
 *        1
 *    /  /\   \
 *   2  3  4   5
 *     /\  |   /\
 *    6 7  8  9 10
 *      |  |  |
 *     11 12 13
 *      |
 *     14
 *   Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 *   Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 * Constraints:
 *   The height of the n-ary tree is less than or equal to 1000
 *   The total number of nodes is between [0, 10^4]
 */
public class LC0429 {
    class Node {
        public int val;
        public List<Node> children;

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(root, result, 0);
        return result;
    }

    public void helper(Node node, List<List<Integer>> result, int level) {
        if (node == null) return;

        if (level >= result.size()) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(node.val);
            result.add(level, list);
        } else {
            List<Integer> list = result.get(level);
            list.add(node.val);
            result.set(level, list);
        }

        if (node.children != null) {
            level++;
            for (Node cnode : node.children) {
                helper(cnode, result, level);
            }
        }
    }
}
