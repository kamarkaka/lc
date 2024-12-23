package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 721. Accounts Merge
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 * Example 1:
 *    Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 *    Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 *    Explanation:
 *       The first and second John's are the same person as they have the common email "johnsmith@mail.com".
 *       The third John and Mary are different people as none of their email addresses are used by other accounts.
 *       We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 *       ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 *
 * Example 2:
 *    Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 *    Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 *
 * Constraints:
 *    1 <= accounts.length <= 1000
 *    2 <= accounts[i].length <= 10
 *    1 <= accounts[i][j] <= 30
 *    accounts[i][0] consists of English letters.
 *    accounts[i][j] (for j > 0) is a valid email.
 */
public class LC0721 {
   public List<List<String>> accountsMerge(List<List<String>> accounts) {
      int size = accounts.size();
      DisjointSetUnion dsu = new DisjointSetUnion(size);
      Map<String, Integer> emailMap = new HashMap<>();

      for (int i = 0; i < size; i++) {
         for (int j = 1; j < accounts.get(i).size(); j++) {
            String email = accounts.get(i).get(j);
            if (emailMap.containsKey(email)) {
               dsu.union(i, emailMap.get(email));
            } else {
               emailMap.put(email, i);
            }
         }
      }

      Map<Integer, List<String>> components = new HashMap<>();
      for (String email : emailMap.keySet()) {
         int group = emailMap.get(email);
         int parent = dsu.findParent(group);

         components.putIfAbsent(parent, new ArrayList<>());
         components.get(parent).add(email);
      }

      List<List<String>> res = new ArrayList<>();
      for (int group : components.keySet()) {
         List<String> component = components.get(group);
         Collections.sort(component);
         component.add(0, accounts.get(group).get(0));
         res.add(component);
      }
      return res;
   }

   private class DisjointSetUnion {
      int[] parents;
      int[] sizes;

      public DisjointSetUnion(int size) {
         this.parents = new int[size];
         this.sizes = new int[size];

         for (int i = 0; i < size; i++) {
            this.parents[i] = i;
            this.sizes[i] = 1;
         }
      }

      public int findParent(int x) {
         if (x == parents[x]) return x;
         return parents[x] = findParent(parents[x]);
      }

      public void union(int a, int b) {
         int parentA = findParent(a);
         int parentB = findParent(b);
         if (parentA == parentB) return;

         if (sizes[parentA] >= sizes[parentB]) {
            sizes[parentA] += sizes[parentB];
            parents[parentB] = parentA;
         } else {
            sizes[parentB] += sizes[parentA];
            parents[parentA] = parentB;
         }
      }
   }
}
