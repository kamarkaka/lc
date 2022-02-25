package com.kamarkaka.google;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountSquare {
   public int countSquare(List<LineSegment> vertSegments, List<LineSegment> horizSegments) {
      vertSegments.sort((v1, v2) -> {
         if (v1.start.x != v2.start.x) return v1.start.x - v2.start.x;
         return v1.start.y - v2.start.y;
      });

      horizSegments.sort((h1, h2) -> {
         if (h1.start.y != h2.start.y) return h1.start.y - h2.start.y;
         return h1.start.x - h2.start.x;
      });

      vertSegments = merge(vertSegments);
      horizSegments = merge(horizSegments);

      Map<Integer, List<LineSegment>> map = new HashMap<>();
      for (LineSegment h : horizSegments) {
         map.putIfAbsent(h.start.y, new ArrayList<>());
         map.get(h.start.y).add(h);
      }

      List<VLine> vlines = new ArrayList<>();
      for (int i = 0; i < vertSegments.size(); i++) {
         LineSegment l1 = vertSegments.get(i);
         for (int j = i + 1; j < vertSegments.size(); j++) {
            LineSegment l2 = vertSegments.get(j);

            if ((l1.start.y <= l2.start.y && l2.start.y <= l1.end.y) ||
                (l2.start.y <= l1.start.y && l1.start.y <= l2.end.y)) {
               vlines.add(new VLine(l1, l2));
            }
         }
      }

      int count = 0;
      for (LineSegment h : horizSegments) {
         for (VLine vline : vlines) {
            LineSegment v1 = vline.v1;
            LineSegment v2 = vline.v2;
            int len = Math.abs(v1.start.x - v2.start.x);

            if (!intersect(v1, h) || !intersect(v2, h)) continue;

            List<LineSegment> hs = map.get(h.start.y + len);
            for (LineSegment h1 : hs) {
               if (intersect(v1, h1) && intersect(v2, h1)) {
                  count++;
                  break;
               }
            }
         }
      }
      return count;
   }

   private List<LineSegment> merge(List<LineSegment> segments) {
      List<LineSegment> res = new ArrayList<>();

      LineSegment s1 = segments.get(0);
      LineSegment s2 = null;
      LineSegment s3 = null;

      for (int i = 1; i < segments.size(); i++) {
         s2 = segments.get(i);
         s3 = merge(s1, s2);
         if (s3 == null) {
            res.add(s1);
            s1 = s2;
            s2 = null;
         } else {
            s1 = s3;
            s2 = null;
         }
      }

      if (s1 != null) res.add(s1);
      return res;
   }

   private LineSegment merge(LineSegment l1, LineSegment l2) {
      if (l1.isHorizontal() && l2.isHorizontal() &&
         (l1.start.x <= l2.start.x && l2.start.x <= l1.end.x ||
          l2.start.x <= l1.start.x && l1.start.x <= l2.end.x)) {
         return new LineSegment(
            new Point(Math.min(l1.start.x, l2.start.x), l1.start.y),
            new Point(Math.max(l1.end.x, l2.end.x), l1.start.y)
         );
      }

      if (l1.isVertical() && l2.isVertical() &&
            (l1.start.y <= l2.start.y && l2.start.y <= l1.end.y ||
             l2.start.y <= l1.start.y && l1.start.y <= l2.end.y)) {
         return new LineSegment(
               new Point(l1.start.x, Math.min(l1.start.y, l2.start.y)),
               new Point(l1.start.x, Math.max(l1.end.y, l2.end.y))
         );
      }

      return null;
   }

   private boolean intersect(LineSegment v, LineSegment h) {
      return h.start.x <= v.start.x && v.start.x <= h.end.x
         && v.start.y <= h.start.y && h.start.y <= v.end.y;
   }
}

class Point {
   int x;
   int y;

   Point(int x, int y) {
      this.x = x;
      this.y = y;
   }
}

class LineSegment {
   Point start;
   Point end;

   LineSegment(Point p1, Point p2) {
      this.start = p1;
      this.end = p2;
   }

   public boolean isVertical() {
      return start.x == end.x;
   }

   public boolean isHorizontal() {
      return start.y == end.y;
   }
}

class VLine {
   LineSegment v1;
   LineSegment v2;

   VLine(LineSegment v1, LineSegment v2) {
      this.v1 = v1;
      this.v2 = v2;
   }
}