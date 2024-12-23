package com.kamarkaka.leetcode;

import com.kamarkaka.common.Utilities;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/***
 * 489. Robot Room Cleaner
 * You are controlling a robot that is located somewhere in a room. The room is modeled as an m x n binary grid where 0 represents a wall and 1 represents an empty slot.
 * The robot starts at an unknown location in the room that is guaranteed to be empty, and you do not have access to the grid, but you can move the robot using the given API Robot.
 * You are tasked to use the robot to clean the entire room (i.e., clean every empty cell in the room). The robot with the four given APIs can move forward, turn left, or turn right. Each turn is 90 degrees.
 * When the robot tries to move into a wall cell, its bumper sensor detects the obstacle, and it stays on the current cell.
 * Design an algorithm to clean the entire room using the following APIs:
 *    interface Robot {
 *       // returns true if next cell is open and robot moves into the cell.
 *       // returns false if next cell is obstacle and robot stays on the current cell.
 *       boolean move();
 *
 *       // Robot will stay on the same cell after calling turnLeft/turnRight.
 *       // Each turn will be 90 degrees.
 *       void turnLeft();
 *       void turnRight();
 *
 *       // Clean the current cell.
 *       void clean();
 *    }
 * Note that the initial direction of the robot will be facing up. You can assume all four edges of the grid are all surrounded by a wall.
 *
 * Custom testing:
 *    The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". In other words, you must control the robot using only the four mentioned APIs without knowing the room layout and the initial robot's position.
 *
 * Example 1:
 *    Input: room = [[1,1,1,1,1,0,1,1],[1,1,1,1,1,0,1,1],[1,0,1,1,1,1,1,1],[0,0,0,1,0,0,0,0],[1,1,1,1,1,1,1,1]], row = 1, col = 3
 *    Output: Robot cleaned all rooms.
 *    Explanation: All grids in the room are marked by either 0 or 1.
 *       0 means the cell is blocked, while 1 means the cell is accessible.
 *       The robot initially starts at the position of row=1, col=3.
 *       From the top left corner, its position is one row below and three columns right.
 *
 * Example 2:
 *    Input: room = [[1]], row = 0, col = 0
 *    Output: Robot cleaned all rooms.
 *
 * Constraints:
 *    m == room.length
 *    n == room[i].length
 *    1 <= m <= 100
 *    1 <= n <= 200
 *    room[i][j] is either 0 or 1.
 *    0 <= row < m
 *    0 <= col < n
 *    room[row][col] == 1
 *    All the empty cells can be visited from the starting position.
 */
public class LC0489 {
   // moving directions: 0-up, 1-right, 2-down, 3-left
   public static int[][] dirs = new int[][] {{0,-1},{1,0},{0,1},{-1,0}};

   public void cleanRoom(Robot robot) {
      Set<Point> cleaned = new HashSet<>();
      Set<Point> wall = new HashSet<>();
      Stack<Point> stack = new Stack<>();

      stack.push(new Point(0,0,0));

      while (!stack.isEmpty()) {
         Point point = stack.peek();
         if (!cleaned.contains(point)) {
            robot.clean();
            cleaned.add(point);
         }

         boolean moved = false;
         int i = 0;
         while (i < 4) {
            Point nextPoint = new Point(point.x + dirs[point.facing][0], point.y + dirs[point.facing][1], point.facing);
            if (!cleaned.contains(nextPoint) && !wall.contains(nextPoint)) {
               if (robot.move()) {
                  stack.push(nextPoint);
                  moved = true;
                  break;
               } else {
                  wall.add(nextPoint);
               }
            }

            robot.turnRight();
            point.facing = (point.facing + 1) % 4;
            i++;
         }

         if (!moved) {
            stack.pop();
            if (stack.isEmpty()) return;
            Point prevPoint = stack.peek();
            move(point, prevPoint, robot);
            prevPoint.facing = point.facing;
         }
      }
   }

   private void move(Point from, Point to, Robot robot) {
      int dx = to.x - from.x;
      int dy = to.y - from.y;
      int dir = 0;
      if (dx == 0 && dy == -1) dir = 0;
      else if (dx == 1 && dy == 0) dir = 1;
      else if (dx == 0 && dy == 1) dir = 2;
      else dir = 3;

      if ((from.facing + 1) % 4 == dir) {
         robot.turnRight();
      } else if ((from.facing + 2) % 4 == dir) {
         robot.turnRight();
         robot.turnRight();
      } else if ((from.facing + 3) % 4 == dir) {
         robot.turnLeft();
      }

      robot.move();
      from.facing = dir;
   }

   class Point {
      public int x = 0;
      public int y = 0;
      public int facing = 0;

      public Point(int x, int y, int facing) {
         this.x = x;
         this.y = y;
         this.facing = facing;
      }

      @Override
      public boolean equals(Object o) {
         if (o == null || o.getClass() != this.getClass()) return false;
         Point that = (Point) o;
         return this.x == that.x && this.y == that.y;
      }

      @Override
      public int hashCode() {
         return x * 10001 + y;
      }
   }

   public static void run() {
      int[][] room = new int[][] {
         {1,0,1,0,1,1,1},
         {1,1,1,1,1,0,1},
         {1,0,1,0,0,1,1},
         {1,0,0,1,1,0,0},
         {1,1,1,1,1,0,0},
         {0,0,0,1,1,0,0}
      };
      Robot robot = new RobotImpl(room, 0, 0, 0);

      LC0489 solution = new LC0489();
      solution.cleanRoom(robot);
      Utilities.print(room);
   }
}

interface Robot {
   public boolean move();
   public void turnLeft();
   public void turnRight();
   public void clean();
}

class RobotImpl implements Robot {
   public int[][] map;
   private int x;
   private int y;
   private int facing;

   public RobotImpl(int[][] map, int x, int y, int facing) {
      this.map = map;
      this.x = x;
      this.y = y;
   }

   @Override
   public boolean move() {
      int newX = x, newY = y;
      switch (facing) {
         case 0:
            newY -= 1;
            break;
         case 1:
            newX += 1;
            break;
         case 2:
            newY += 1;
            break;
         case 3:
            newX -= 1;
      }

      if (0 <= newX && newX < map[0].length && 0 <= newY && newY < map.length && map[newY][newX] != 0) {
         x = newX;
         y = newY;
         return true;
      }
      return false;
   }

   @Override
   public void turnLeft() {
      facing = (facing + 3) % 4;
   }

   @Override
   public void turnRight() {
      facing = (facing + 1) % 4;
   }

   @Override
   public void clean() {
      map[y][x] = 2;
   }
}