package com.kamarkaka.square;

import java.util.HashMap;
import java.util.Map;

public class Decoder {
   Map<String, String> map;

   public Decoder() {
      map = new HashMap<>();
      map.put("0", "A");
      map.put("111", "B");
      map.put("1100", "C");
      map.put("1101", "D");
      map.put("10100", "E");
      map.put("10010", "F");
   }

   public String decode(String str) {
      if (map.containsKey(str)) {
         return map.get(str);
      }

      for (String head : map.keySet()) {
         if (!str.startsWith(head)) continue;

         String res = decode(str.substring(head.length()));
         if (res != null) {
            return map.get(head) + res;
         }
      }

      return null;
   }

   public static void run() {
      Decoder sol = new Decoder();
      System.out.println(sol.decode("11101101"));
   }
}
