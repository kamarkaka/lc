package com.kamarkaka.square;

import com.kamarkaka.common.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Socks {
   public List<List<Integer>> findPairs(int[][] socks) {
      List<List<Integer>> res = new ArrayList<>();

      Map<Integer, int[]> map = new HashMap<>();
      for (int[] sock : socks) {
         if (!map.containsKey(sock[1])) {
            map.put(sock[1], new int[] {sock[2], sock[0]});
         } else {
            int[] exitingSock = map.get(sock[1]);
            if (exitingSock[0] + sock[2] == 1) {
               List<Integer> pair = new ArrayList<>();
               pair.add(exitingSock[1]);
               pair.add(sock[0]);
               res.add(pair);
               map.remove(sock[1]);
            }
         }
      }

      return res;
   }

   public static void run() {
      Socks sol = new Socks();
      Utilities.print(sol.findPairs(new int[][] {{1,0,0}, {2,0,1}, {3,1,0}, {4,1,1}}));
   }
}
