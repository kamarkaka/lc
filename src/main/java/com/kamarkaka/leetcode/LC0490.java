package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 490. The Maze
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.
 * You may assume that the borders of the maze are all walls (see examples).
 *
 * Example 1:
 *    Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
 *    Output: true
 *    Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 *
 * Example 2:
 *    Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
 *    Output: false
 *    Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.
 *
 * Example 3:
 *    Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
 *    Output: false
 *
 * Constraints:
 *    m == maze.length
 *    n == maze[i].length
 *    1 <= m, n <= 100
 *    maze[i][j] is 0 or 1.
 *    start.length == 2
 *    destination.length == 2
 *    0 <= startrow, destinationrow <= m
 *    0 <= startcol, destinationcol <= n
 *    Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
 *    The maze contains at least 2 empty spaces.
 */
public class LC0490 {
   private static final int[][] dirs = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
   public boolean hasPath(int[][] maze, int[] start, int[] destination) {
      Position startPos = new Position(start[0], start[1]);
      Position endPos = new Position(destination[0], destination[1]);
      Set<Position> visited = new HashSet<>();
      Queue<Position> queue = new LinkedList<>();

      queue.add(startPos);
      while (!queue.isEmpty()) {
         Position position = queue.poll();
         if (position.equals(endPos)) return true;
         if (visited.contains(position)) continue;

         visited.add(position);
         Set<Position> nextPositions = findNextPositions(position, maze);
         queue.addAll(nextPositions);
      }
      return false;
   }

   private Set<Position> findNextPositions(Position currPos, int[][] maze) {
      Set<Position> set = new HashSet<>();

      int currY = currPos.y;
      int currX = currPos.x;

      for (int[] dir : dirs) {
         int newY = currY;
         int newX = currX;
         while (
            0 <= newY + dir[0] && newY + dir[0] < maze.length &&
            0 <= newX + dir[1] && newX + dir[1] < maze[0].length &&
            maze[newY + dir[0]][newX + dir[1]] == 0
         ) {
            newY += dir[0];
            newX += dir[1];
         }
         Position nextPos = new Position(newY, newX);
         if (!currPos.equals(nextPos)) {
            set.add(nextPos);
         }
      }

      return set;
   }

   private class Position {
      int y;
      int x;

      public Position(int y, int x) {
         this.y = y;
         this.x = x;
      }

      @Override
      public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         Position that = (Position) o;
         return this.y == that.y && this.x == that.x;
      }

      @Override
      public int hashCode() {
         return Objects.hash(y, x);
      }
   }
}
