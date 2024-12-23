package com.kamarkaka.marshallwace;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/***
 * You are given an implementation of a function solution, that, given a building plan, calculates the minimum number of
 * runs required for a cleaning robot to clean all dirty areas in the building. Your task is to find and fix any bug(s)
 * in the implementation, modifying at most two lines.
 * The building plan is represented as a 2D array made of R rows and C columns. Each field contains one of three
 * symbols: a "." (clean floor area), a "*" (dirty floor) or a "#" (wall). A cleaning robot is used to clean the dirty
 * fields. When the robot is run in some room, it cleans the whole floor inside that room. The robot can move in any of
 * four directions: up, down, left, right. It cannot move into a field containing a wall, or outside the area
 * represented by the plan.
 *
 * Examples:
 * 1. For plan = [
 * ..####
 * ..#.*#
 * ###*.#
 * #.####
 * #.#...
 * ###...
 * ], the function should return 1. All dirty fields can be cleaned in a single run.
 * 2. For plan = [
 * ..####
 * ..#.*#
 * ###..#
 * #*####
 * #.#...
 * ###...
 * ], the function should return 2.
 * 3. For plan = [
 * ##########
 * #....*.**#
 * ##########
 * ], the function should return 1.
 */
public class BuildingPlan {
    private static final int[][] DIR = new int[][] {
        new int[]{0, 1},
        new int[]{0, -1},
        new int[]{1, 0},
        new int[]{-1, 0}
    };
    public int numOfRuns(char[][] plan) {
        int R = plan.length;
        int C = plan[0].length;
        List<int[]> dirtySpots = new ArrayList<>();

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (plan[r][c] == '*') {
                    dirtySpots.add(new int[] {r, c});
                }
            }
        }

        int step = 0;
        for (int[] dirtySpot : dirtySpots) {

            if (plan[dirtySpot[0]][dirtySpot[1]] != '*') {
                continue;
            }

            Queue<int[]> spots = new LinkedList<>();
            spots.add(dirtySpot);

            while (!spots.isEmpty()) {
                int[] spot = spots.poll();

                if (plan[spot[0]][spot[1]] == '*') {
                    // clean
                }

                plan[spot[0]][spot[1]] = 'V';

                for (int[] dir : DIR) {
                    int nextSpotR = spot[0] + dir[0];
                    int nextSpotC = spot[1] + dir[1];

                    if (0 <= nextSpotC && nextSpotC < C && 0 <= nextSpotR && nextSpotR < R && plan[nextSpotR][nextSpotC] != '#' && plan[nextSpotR][nextSpotC] != 'V') {
                        spots.add(new int[] {nextSpotR, nextSpotC});
                    }
                }
            }

            step++;
        }

        return step;
    }

    public static void main(String[] args) {
        BuildingPlan solution = new BuildingPlan();
        System.out.println(solution.numOfRuns(new char[][]{
                new char[]{'.', '.', '#', '#', '#', '#'},
                new char[]{'.', '.', '#', '.', '*', '#'},
                new char[]{'#', '#', '#', '*', '.', '#'},
                new char[]{'#', '.', '#', '#', '#', '#'},
                new char[]{'#', '.', '#', '.', '.', '.'},
                new char[]{'#', '#', '#', '.', '.', '.'}}));
        System.out.println(solution.numOfRuns(new char[][]{
                new char[]{'.', '.', '#', '#', '#', '#'},
                new char[]{'.', '.', '#', '.', '*', '#'},
                new char[]{'#', '#', '#', '.', '.', '#'},
                new char[]{'#', '*', '#', '#', '#', '#'},
                new char[]{'#', '.', '#', '.', '.', '.'},
                new char[]{'#', '#', '#', '.', '.', '.'}}));
        System.out.println(solution.numOfRuns(new char[][]{
                new char[]{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                new char[]{'#', '.', '.', '.', '.', '*', '.', '*', '*', '#'},
                new char[]{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}}));
    }
}
