package com.kamarkaka.uber;

import java.util.HashMap;
import java.util.Map;

public class CompatibleVersions {
   private int ver = 0;
   private Map<Integer, Integer> hmap = new HashMap<>();

   public void addVersion(int version, boolean compatible) {
      if (!compatible) ver = version;

      hmap.put(version, ver);
   }

   public boolean isCompatible(int sourceVersion, int targetVersion) {
      return hmap.get(sourceVersion) == hmap.get(targetVersion);
   }

   public static void run() {
      CompatibleVersions sol = new CompatibleVersions();
      sol.addVersion(1, false);
      sol.addVersion(2, true);
      sol.addVersion(3, true);
      sol.addVersion(4, false);
      sol.addVersion(5, true);
      sol.addVersion(6, true);

      System.out.println(sol.isCompatible(1,3));
      System.out.println(sol.isCompatible(3,5));
      System.out.println(sol.isCompatible(4,2));
      System.out.println(sol.isCompatible(3,3));
   }
}
