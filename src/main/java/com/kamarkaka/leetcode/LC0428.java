package com.kamarkaka.leetcode;

import java.util.ArrayList;
import java.util.List;

/***
 * 428. Serialize and Deserialize N-ary Tree
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has
 * no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You
 * just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the
 * original tree structure.
 * For example, you may serialize the following 3-ary tree
 *    1
 *   /|\
 *  3 2 4
 * / \
 * 5 6
 * as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this format.
 * Or you can follow LeetCode's level order traversal serialization format, where each group of children is separated by
 * the null value.
 *      1
 *  /  /\   \
 * 2  3  4   5
 *   /\  |   /\
 *  6 7  8  9 10
 *    |  |  |
 *   11 12 13
 *    |
 *   14
 * For example, the above tree may be serialized as
 * [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].
 * You do not necessarily need to follow the above-suggested formats, there are many more different formats that work so
 * please be creative and come up with different approaches yourself.
 * Example 1:
 *   Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 *   Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Example 2:
 *   Input: root = [1,null,3,2,4,null,5,6]
 *   Output: [1,null,3,2,4,null,5,6]
 * Example 3:
 *   Input: root = []
 *   Output: []
 * Constraints:
 *   The number of nodes in the tree is in the range [0, 10^4].
 *   0 <= Node.val <= 10^4
 *   The height of the n-ary tree is less than or equal to 1000
 *   Do not use class member/global/static variables to store states. Your encode and decode algorithms should be
 *   stateless.
 */
public class LC0428 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };

    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        serialize(root, sb);
        sb.append(']');
        return sb.toString();
    }

    private void serialize(Node node, StringBuilder sb) {
        if (node == null) return;
        sb.append(node.val).append('#');

        if (node.children != null && node.children.size() > 0) {
            sb.append ('[');
            for (Node n : node.children) {
                serialize(n, sb);
            }
            sb.append(']');
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        char[] str = data.toCharArray();
        if (str.length < 3) return null;
        List<Node> nodes = new ArrayList<>();
        deser(str, 1, nodes);
        return nodes.get(0);
    }

    private int deser(char[] str, int index, List<Node> nodes) {
        while (index < str.length && str[index] != ']') {
            int val = 0;
            while (index < str.length && str[index] != '#') {
                val = val * 10 + str[index] - '0';
                index++;
            }
            index++;
            Node node = new Node(val, new ArrayList<>());
            nodes.add(node);
            if (str[index] == '[') {
                index = deser(str, index + 1, node.children);
            }
        }
        return index + 1;
    }
}
