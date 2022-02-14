package main.java.com.kamarkaka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * 1610. Maximum Number of Visible Points
 * You are given an array points, an integer angle, and your location, where location = [posx, posy] and points[i] = [xi, yi] both denote integral coordinates on the X-Y plane.
 * Initially, you are facing directly east from your position. You cannot move from your position, but you can rotate. In other words, posx and posy cannot be changed. Your field of view in degrees is represented by angle, determining how wide you can see from any given view direction. Let d be the amount in degrees that you rotate counterclockwise. Then, your field of view is the inclusive range of angles [d - angle/2, d + angle/2].
 * You can see some set of points if, for each point, the angle formed by the point, your position, and the immediate east direction from your position is in your field of view.
 * There can be multiple points at one coordinate. There may be points at your location, and you can always see these points regardless of your rotation. Points do not obstruct your vision to other points.
 * Return the maximum number of points you can see.
 *
 * Example 1:
 *   Input: points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
 *   Output: 3
 *   Explanation: The shaded region represents your field of view. All points can be made visible in your field of view, including [3,3] even though [2,2] is in front and in the same line of sight.
 *
 * Example 2:
 *   Input: points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
 *   Output: 4
 *   Explanation: All points can be made visible in your field of view, including the one at your location.
 *
 * Example 3:
 *   Input: points = [[1,0],[2,1]], angle = 13, location = [1,1]
 *   Output: 1
 *   Explanation: You can only see one of the two points, as shown above.
 *
 * Constraints:
 *   1 <= points.length <= 10^5
 *   points[i].length == 2
 *   location.length == 2
 *   0 <= angle < 360
 *   0 <= posx, posy, xi, yi <= 100
 */
public class LC1610 {
   public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
      List<Double> angles = new ArrayList<>();
      int countOfSameLoc = 0;
      for (List<Integer> point : points) {
         if (point.get(0) == location.get(0) && point.get(1) == location.get(1)) countOfSameLoc++;
         else angles.add(getAngle(location, point));
      }

      Collections.sort(angles);

      int n = angles.size();
      for(int i = 0; i < n; i++) {
         angles.add(angles.get(i) + 360); // now all angles are CIRCULAR (-150 degree is 210) and (10 degree is now 370)
      }

      int begin = 0; // classic sliding window variable
      int res = 0;

      for(int end = 0; end < angles.size(); end++) {
         while(angles.get(end) - angles.get(begin) > angle) { // this should be 'while' and not 'if' (sliding window always uses 'while')
            begin++;
         }

         res = Math.max(res, end - begin + 1);
      }

      return res + countOfSameLoc;
   }

   private double getAngle(List<Integer> location, List<Integer> point) {
      int x = point.get(0) - location.get(0);
      int y = point.get(1) - location.get(1);

      double piAngle = Math.atan2(y, x);
      if (piAngle >= 0) return piAngle / Math.PI * 180;
      return piAngle / Math.PI * 180 + 360;
   }

   public static void run() {
      LC1610 solution = new LC1610();

      List<Integer> location = new ArrayList<>();
      location.add(1);
      location.add(1);

      List<Integer> point1 = new ArrayList<>();
      point1.add(0);
      point1.add(0);
      List<Integer> point2 = new ArrayList<>();
      point2.add(0);
      point2.add(2);
      List<List<Integer>> points = new ArrayList<>();
      points.add(point1);
      points.add(point2);

      System.out.println(solution.visiblePoints(points, 90, location));
   }
}
