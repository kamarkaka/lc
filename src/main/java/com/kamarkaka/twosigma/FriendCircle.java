package com.kamarkaka.twosigma;

import java.util.*;

public class FriendCircle {
   private int[] parents;
   public int longestFriendChain(int N, int[][] friends) {
      parents = new int[N];
      for (int i = 0; i < N; i++) {
         parents[i] = i;
      }

      for (int[] friend : friends) {
         union(friend[0], friend[1]);
      }

      int maxChainLen = 0;
      int maxChainProduct = 0;
      Map<Integer, List<Integer>> friendChainMap = new HashMap<>();
      for (int i = 0; i < N; i++) {
         int parent = find(i);
         friendChainMap.putIfAbsent(parent, new ArrayList<>());
         friendChainMap.get(parent).add(i);
      }

      for (List<Integer> chain : friendChainMap.values()) {
         if (chain.size() < maxChainLen) continue;

         maxChainLen = chain.size();
         chain.sort(Comparator.comparingInt(c -> -c));
         maxChainProduct = Math.max(maxChainProduct, chain.get(0) * chain.get(1));
      }

      return maxChainProduct;
   }

   private void union(int p1, int p2) {
      int parent1 = find(p1);
      int parent2 = find(p2);
      parents[parent1] = parents[parent2];
   }

   private int find(int p) {
      if (parents[p] == p) return p;

      return find(parents[p]);
   }

   public static void run() {
      FriendCircle sol = new FriendCircle();
      System.out.println(sol.longestFriendChain(4, new int[][] {
            {0,1},
            {2,3},
            {1,3}
      }));
   }
}
