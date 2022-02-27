package com.kamarkaka.twosigma;

import java.util.*;

/***
 * https://leetcode.com/discuss/interview-question/1389344/interview-question-nearest-neighboring-city
 * https://www.lintcode.com/problem/280/
 *
 * Given a list of points, find the nearest points that shares either an x or y coordinate with the queried point.
 * The distance is denoted on a Euclidean plane: the difference in x plus the difference in y.
 * Input
 *    numOfPoints
 *    points: a list of strings representing the names of each point[i]
 *    xCoordinates
 *    yCoordinates
 *    numOfQueriedPoints
 *    queriedPoints, a list of strings representing the names of the queried points
 * Output
 *    Return a list of strings representing the name of the nearest point that shares either an x or a y coordinate with the queried point.
 * Example 1
 *    input(
 *       3,
 *       ["p1", "p2", "p3"],
 *       [30, 20, 10],
 *       [30, 20, 30],
 *       3,
 *       ["p3, "p2", "p1"]
 *    output: ["p1", NONE, "p3"]
 * Example 2
 *    input(
 *       5,
 *       ["p1", "p2", "p3", "p4", "p5"],
 *       [10, 20, 30, 40, 50],
 *       [10, 20, 30, 40, 50],
 *       5,
 *       ["p1", "p2", "p3", "p4", "p5"]
 *    output: [NONE, NONE, NONE, NONE, NONE]
 *
 */
public class NearestNeighboringCity {
   String[] findNearestNeighbor(int numOfPoints, String[] names, int[] xCoordinates, int[] yCoordinates, int numOfQueriedPoints, String[] queriedName) {
      String[] res = new String[numOfQueriedPoints];

      Map<Integer, List<Point>> xMap = new HashMap<>();
      Map<Integer, List<Point>> yMap = new HashMap<>();
      Map<String, Point> pMap = new HashMap<>();

      for (int i = 0; i < numOfPoints; i++) {
         int x = xCoordinates[i];
         int y = yCoordinates[i];
         Point point = new Point(names[i], x, y);

         pMap.put(names[i], point);

         xMap.putIfAbsent(x, new ArrayList<>());
         xMap.get(x).add(point);

         yMap.putIfAbsent(y, new ArrayList<>());
         yMap.get(y).add(point);
      }

      for (List<Point> list : xMap.values()) {
         list.sort(Comparator.comparingInt(p -> p.y));
      }
      for (List<Point> list : yMap.values()) {
         list.sort(Comparator.comparingInt(p -> p.x));
      }

      for (int i = 0; i < numOfQueriedPoints; i++) {
         String name = queriedName[i];
         Point point = pMap.getOrDefault(name, null);
         if (point == null) {
            res[i] = null;
            continue;
         }

         List<Point> sameXs = xMap.getOrDefault(point.x, new ArrayList<>());
         List<Point> sameYs = yMap.getOrDefault(point.y, new ArrayList<>());
         if (sameXs.size() + sameYs.size() == 0) {
            res[i] = null;
            continue;
         }

         int xIndex = Collections.binarySearch(sameXs, point, Comparator.comparingInt(p -> p.y));
         int yIndex = Collections.binarySearch(sameYs, point, Comparator.comparingInt(p -> p.x));

         int minDiff = Integer.MAX_VALUE;
         Point minPoint = null;
         if (xIndex > 0) {
            Point point1 = sameXs.get(xIndex - 1);
            int diff = Math.abs(point1.y - point.y);
            if (diff < minDiff) {
               minDiff = diff;
               minPoint = point1;
            }
         }
         if (xIndex < sameXs.size() - 1) {
            Point point1 = sameXs.get(xIndex + 1);
            int diff = Math.abs(point1.y - point.y);
            if (diff < minDiff) {
               minDiff = diff;
               minPoint = point1;
            }
         }

         if (yIndex > 0) {
            Point point1 = sameYs.get(yIndex - 1);
            int diff = Math.abs(point1.x - point.x);
            if (diff < minDiff) {
               minDiff = diff;
               minPoint = point1;
            }
         }
         if (yIndex < sameYs.size() - 1) {
            Point point1 = sameYs.get(yIndex + 1);
            int diff = Math.abs(point1.x - point.x);
            if (diff < minDiff) {
               minDiff = diff;
               minPoint = point1;
            }
         }

         if (minPoint == null) {
            res[i] = null;
            continue;
         }

         res[i] = minPoint.name;
      }

      return res;
   }

   private class Point {
      final String name;
      final int x;
      final int y;

      Point(String name, int x, int y) {
         this.name = name;
         this.x = x;
         this.y = y;
      }
   }

   public static void run() {
      NearestNeighboringCity sol = new NearestNeighboringCity();
      String[] res1 = sol.findNearestNeighbor(
         3,
         new String[] {"p1", "p2", "p3"},
         new int[] {30, 20, 10},
         new int[] {30, 20, 30},
         3,
         new String[] {"p3", "p2", "p1"}
      );
      System.out.println(Arrays.asList(res1));

      String[] res2 = sol.findNearestNeighbor(
         5,
         new String[] {"p1", "p2", "p3", "p4", "p5"},
         new int[] {10, 20, 30, 40, 50},
         new int[] {10, 20, 30, 40, 50},
         5,
         new String[] {"p1", "p2", "p3", "p4", "p5"}
      );
      System.out.println(Arrays.asList(res2));
   }
}
