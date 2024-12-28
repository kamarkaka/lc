package com.kamarkaka.datadog;

import java.util.List;

/***
 * Max sum from root to leaf of an n-ary tree
 * Followup: what if there are loops? use set to store visited tree node
 */
public class MaxPathSum {
    class NaryTreeNode {
        int value;
        List<NaryTreeNode> chilren;

        public NaryTreeNode(int value, List<NaryTreeNode> chilren) {
            this.value = value;
            this.chilren = chilren;
        }
    }

    public int maxSum(NaryTreeNode root) {
        if (root.chilren == null || root.chilren.isEmpty()) return root.value;

        int sum = Integer.MIN_VALUE;
        for (NaryTreeNode node : root.chilren) {
            sum = Math.max(sum, maxSum(node));
        }
        return root.value + sum;
    }
}
