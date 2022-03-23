package com.kamarkaka.uber;

import java.util.HashMap;
import java.util.Map;

public class CharOrder {
   Map<Character, Integer> charToOrderMap = new HashMap<>();

   public boolean isOrder(String str, String order) {
      for (int i = 0; i < order.length(); i++) {
         char c = order.charAt(i);
         charToOrderMap.put(c, i);
      }

      int preIdx = -1;
      for (int i = 0; i < str.length(); i++) {
         char c = str.charAt(i);
         if (!charToOrderMap.containsKey(c)) continue;

         int idx = charToOrderMap.get(c);
         if (idx < preIdx) return false;
         preIdx = idx;
      }

      return true;
   }

   public static void run() {
      CharOrder sol = new CharOrder();
      System.out.println(sol.isOrder("ABCDEA", "ABC"));
   }
}
