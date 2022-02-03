package com.kamarkaka.twosigma;

import java.util.List;

class TreeSplit {

   /*
    * Complete the 'drainagePartition' function below.
    *
    * The function is expected to return an INTEGER.
    * The function accepts following parameters:
    *  1. INTEGER_ARRAY parent
    *  2. INTEGER_ARRAY inputs
    */

   public static int drainagePartition(List<Integer> parent, List<Integer> inputs) {
      // number of nodes in the tree
      int num = inputs.size();

      // totalFlow[i] denotes the total outflow from node i
      int[] totalFlow = new int[num];

      // build totalFlow, and returns the total outflow of root node
      int rootFlow = buildFlow(parent, inputs, totalFlow);

      // find a node that once cutting it,
      // the difference is the smallest between the outflow of the node itself and the remaining outflow from root
      int minDiff = Integer.MAX_VALUE;
      for (int i = 0; i < num; i++) {
         int diff = Math.abs(rootFlow - totalFlow[i] * 2);
         if (minDiff > diff) minDiff = diff;
      }
      return minDiff;
   }

   private static int buildFlow(List<Integer> parent, List<Integer> inputs, int[] totalFlow) {
      boolean[] visited = new boolean[totalFlow.length];
      int root = -1;
      for (int i = 0; i < parent.size(); i++) {
         if (visited[i]) {
            continue;
         }

         // go up the tree to the node's parent, and
         // update totalFlow array along the way
         int nodeFlow = inputs.get(i);
         int node = i;
         while (parent.get(node) != -1) {
            totalFlow[node] += nodeFlow;
            node = parent.get(node);
         }

         // update root
         totalFlow[node] += nodeFlow;
         if (root == -1) {
            // memorize the root index
            root = node;
         }
         visited[i] = true;
      }

      return totalFlow[root];
   }

}
