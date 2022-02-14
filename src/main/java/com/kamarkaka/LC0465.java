package com.kamarkaka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 465. Optimal Account Balancing
 * You are given an array of transactions transactions where transactions[i] = [fromi, toi, amounti] indicates that the person with ID = fromi gave amounti $ to the person with ID = toi.
 * Return the minimum number of transactions required to settle the debt.
 *
 * Example 1:
 *    Input: transactions = [[0,1,10],[2,0,5]]
 *    Output: 2
 *    Explanation:
 *       Person #0 gave person #1 $10.
 *       Person #2 gave person #0 $5.
 *       Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 *
 * Example 2:
 *    Input: transactions = [[0,1,10],[1,0,1],[1,2,5],[2,0,5]]
 *    Output: 1
 *    Explanation:
 *       Person #0 gave person #1 $10.
 *       Person #1 gave person #0 $1.
 *       Person #1 gave person #2 $5.
 *       Person #2 gave person #0 $5.
 *       Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 *
 * Constraints:
 *    1 <= transactions.length <= 8
 *    transactions[i].length == 3
 *    0 <= fromi, toi <= 20
 *    fromi != toi
 *    1 <= amounti <= 100
 */
public class LC0465 {
   public int minTransfers(int[][] transactions) {
      Map<Integer, Integer> map = new HashMap<>();

      for (int[] t : transactions) {
         map.put(t[0], map.getOrDefault(t[0], 0) + t[2]);
         map.put(t[1], map.getOrDefault(t[1], 0) - t[2]);
      }

      List<Integer> list = new ArrayList<>();
      for (int v : map.values()) {
         if (v != 0) {
            list.add(v);
         }
      }

      return dfs(0, list);
   }

   private int dfs(int idx, List<Integer> list) {
      if (idx == list.size()) return 0;

      int curr = list.get(idx);
      if (curr == 0) {
         return dfs(idx + 1, list);
      }

      int min = Integer.MAX_VALUE;
      for (int j = idx + 1; j < list.size(); j++) {
         int next = list.get(j);
         if (curr * next < 0) {
            list.set(j, curr + next);
            min = Math.min(min, 1 + dfs(idx + 1, list));
            list.set(j, next);

            if (curr + next == 0) break;
         }
      }
      return min;
   }
}
