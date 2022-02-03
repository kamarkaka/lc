package com.kamarkaka;

import java.util.*;

public class LC1152 {
   public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
      Map<String, List<Visit>> map = new HashMap<>();

      for (int i = 0; i < username.length; i++) {
         map.putIfAbsent(username[i], new ArrayList<>());
         map.get(username[i]).add(new Visit(timestamp[i], website[i]));
      }

      Map<String, Integer> count = new HashMap<>();
      String res = "";
      for (String key : map.keySet()) {
         Set<String> set = new HashSet<>();
         List<Visit> list = map.get(key);
         list.sort(Comparator.comparingInt(a -> a.timestamp));

         for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
               for (int k = j + 1; k < list.size(); k++) {
                  String str = list.get(i).website + " " + list.get(j).website + " " + list.get(k).website;
                  if (!set.contains(str)) {
                     count.put(str, count.getOrDefault(str, 0) + 1);
                     set.add(str);
                  }

                  if (res.equals("") || count.get(res) < count.get(str) || (count.get(res) == count.get(str) && res.compareTo(str) > 0)) {
                     res = str;
                  }
               }
            }
         }

      }

      return Arrays.asList(res.split(" "));
   }

   class Visit {
      int timestamp;
      String website;

      public Visit(int timestamp, String website) {
         this.timestamp = timestamp;
         this.website = website;
      }
   }
}
