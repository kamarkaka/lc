package com.kamarkaka.doordash;

import com.kamarkaka.common.Utilities;

import java.util.*;

public class HighestPointsUnderTimeLimit {
   public int highestPoint;
   public int limit;
   public List<List<Integer>> paths;

   public int getHighestPoint(int[] points, int[][] timeCost, int timeLimit) {
      highestPoint = 0;
      limit = timeLimit;
      paths = new ArrayList<>();

      Map<Integer, Map<Integer, int[]>> srcToDestPointsCost = new HashMap<>();
      for (int i = 0; i < timeCost.length; i++) {
         int src = timeCost[i][0];
         int dest = timeCost[i][1];
         int cost = timeCost[i][2];
         int srcPoint = points[src];
         int destPoint = points[dest];
         int[] srcToDest = new int[]{destPoint, cost};
         int[] destToSrc = new int[]{srcPoint, cost};
         srcToDestPointsCost.computeIfAbsent(src, v -> new HashMap<>()).putIfAbsent(dest, srcToDest);
         srcToDestPointsCost.computeIfAbsent(dest, v -> new HashMap<>()).putIfAbsent(src, destToSrc);
      }

      LinkedList<Integer> currPath = new LinkedList<>();
      currPath.add(0);
      Set<Integer> visited = new HashSet<>();
      visited.add(0);
      dfs(srcToDestPointsCost, 0, points[0], 0, true, currPath, visited);
      return highestPoint;
   }

   private void dfs(Map<Integer, Map<Integer, int[]>> srcToDestPointsCost, int start, int currPoints, int currTime, boolean isStart, LinkedList<Integer> currPath, Set<Integer> visited) {
      if (currTime > limit) return;
      if (start == 0 && !isStart) {
         System.out.println(currPath);
         System.out.println(currPoints);
         paths.add(new LinkedList<>(currPath));
         highestPoint = Math.max(highestPoint, currPoints);
         return;
      }

      if (start == 0) isStart = false;

      visited.add(start);
      Map<Integer, int[]> nextStops = srcToDestPointsCost.get(start);
      for (Integer nextStart : nextStops.keySet()) {
         int[] info = nextStops.get(nextStart);
         int newPoints = currPoints + (visited.contains(nextStart) ? 0 : info[0]);
         int newTime = currTime + info[1];

         currPath.addLast(nextStart);
         dfs(srcToDestPointsCost, nextStart, newPoints, newTime, isStart, currPath, visited);
         currPath.removeLast();
      }
      visited.remove(start);
   }

   public static void run() {
      int[] points = new int[]{5, 10, 3, 20};
      int[][] timeCost = new int[][]{{0, 1, 10}, {0, 2, 4}, {0, 3, 60}, {1, 3, 40}};
      int timeLimit = 110;

      HighestPointsUnderTimeLimit sol = new HighestPointsUnderTimeLimit();
      System.out.println(sol.getHighestPoint(points, timeCost, timeLimit));
   }
}
