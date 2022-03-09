package com.kamarkaka.indeed;

import java.util.*;

public class GitCommit {
   public List<GitNode> findAllCommits(GitNode node) {
      List<GitNode> res = new ArrayList<>();
      Set<GitNode> visited = new HashSet<>();
      Queue<GitNode> queue = new LinkedList<>();

      queue.add(node);


      while (!queue.isEmpty()) {
         GitNode currNode = queue.poll();
         visited.add(currNode);
         res.add(currNode);

         for (GitNode parent : currNode.parents) {
            if (!visited.contains(parent)) {
               queue.add(parent);
            }
         }
      }

      return res;
   }

   public GitNode findCommonParent(GitNode node1, GitNode node2) {
      if (node1 == null || node2 == null) return null;

      Set<GitNode> p1 = new HashSet<>();
      Set<GitNode> p2 = new HashSet<>();
      Queue<GitNode> q1 = new LinkedList<>();
      Queue<GitNode> q2 = new LinkedList<>();

      p1.add(node1);
      p2.add(node2);
      q1.add(node1);
      q2.add(node2);

      while (!q1.isEmpty() && !q2.isEmpty()) {
         int size1 = q1.size();
         while (size1-- > 0) {
            GitNode node = q1.poll();
            for (GitNode parent : node.parents) {
               if (p2.contains(parent)) return parent;
               if (!p1.contains(parent)) {
                  p1.add(parent);
                  q1.add(parent);
               }
            }
         }

         int size2 = q2.size();
         while (size2-- > 0) {
            GitNode node = q2.poll();
            for (GitNode parent : node.parents) {
               if (p1.contains(parent)) return parent;
               if (!p2.contains(parent)) {
                  p2.add(parent);
                  q2.add(parent);
               }
            }
         }
      }

      return null;
   }
}

class GitNode {
   int id;
   List<GitNode> parents;

   public GitNode(int id) {
      this.id = id;
      this.parents = new ArrayList<>();
   }
}