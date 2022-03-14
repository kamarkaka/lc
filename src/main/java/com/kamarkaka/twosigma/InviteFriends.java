package com.kamarkaka.twosigma;

import java.util.*;

// O(3^(N/3)) - N: vertices
public class InviteFriends {
   boolean[][] adj;
   Set<Set<Integer>> visited = new HashSet<>();

   public List<Integer> invite(int N, int[][] friends) {
      Set<Integer> candidateSet = new HashSet<>();
      adj = new boolean[N][N];
      for (int[] friend : friends) {
         adj[friend[0]][friend[1]] = true;
         adj[friend[1]][friend[0]] = true;
         candidateSet.add(friend[0]);
         candidateSet.add(friend[1]);
      }

      // find all maximal independent set
      Set<Set<Integer>> totalSets = new HashSet<>();
      backtrack(new HashSet<>(), candidateSet, totalSets);

      int maxSize = 0;
      List<Integer> res = new ArrayList<>();

      for (Set<Integer> set : totalSets) {
         if (set.size() > maxSize) {
            maxSize = set.size();
            res.clear();
            res.addAll(set);
         }
      }
      return res;
   }

   private void backtrack(Set<Integer> currSet, Set<Integer> candidates, Set<Set<Integer>> totalSets) {
      if (candidates.isEmpty()) {
         System.out.println("add " + currSet + " to res");
         totalSets.add(new HashSet<>(currSet));
         return;
      }

      for (int candidate : candidates) {
         Set<Integer> newCandidates = new HashSet<>(candidates);
         newCandidates.remove(candidate);
         currSet.add(candidate);

         if (visited.contains(currSet)) {
            System.out.println("seen " + currSet + " before!");
         } else {
            visited.add(new HashSet<>(currSet));
            System.out.println("memorize " + currSet);

            for (int i = 0; i < adj.length; i++) {
               if (adj[candidate][i]) newCandidates.remove(i);
            }

            backtrack(currSet, newCandidates, totalSets);
         }

         currSet.remove(candidate);
      }
   }

   public static void run() {
      InviteFriends sol = new InviteFriends();
      System.out.println(sol.invite(5, new int[][] {
         {0,2},{0,3},{0,4},
         {1,2},{1,3},{1,4},
         {2,0},{2,1},
         {3,0},{3,1},
         {4,0},{4,1}
      }));
   }
}
