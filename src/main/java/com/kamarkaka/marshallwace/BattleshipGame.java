package com.kamarkaka.marshallwace;

import java.util.ArrayList;
import java.util.List;

/***
 * John plays a game of battleships with his friend Sonia. The game is played on a square map of N rows, numbered from 1
 * to N. Each row contains N cells, labeled with consecutive English upper-case letters (A, B, C, etc.). Each cell is
 * identified by a string composed of its row number followed by its column number: for example, "9C" denotes the third
 * cell in the 9th row, and "15D" denotes the fourth cell in the 15th row.
 * John makes the positions of all his ships on the map (which is not shown to Sonia). Ships are defined by rectangles
 * with maximum area of 4 cells. Sonia picks map cells to hit some ships. A ship is considered to be hit if at least one
 * of its constituent cells is hit. If all of a ship's cells are hit, the ship is sunk.
 * The goal is to count the number of sunk ships and the number of ships that have been hit but not sunk.
 * For example, the picture below shows a map of size N = 4 with two blue ships and five hits marked with the letter
 * 'X':
 */
public class BattleshipGame {
    public String reportDamage(int N, String S, String T) {
        List<Ship> ships = new ArrayList<>();
        String[] shipStrings = S.split(",");
        for (String shipString : shipStrings) {
            String[] shipCoordinates = shipString.split(" ");
            ships.add(new Ship(shipCoordinates[0], shipCoordinates[1]));
        }

        String[] hits = T.split(" ");
        for (String hit : hits) {
            for (Ship ship : ships) {
                if (ship.hit(hit)) {
                    break;
                }
            }
        }

        int shipsSunk = 0;
        int shipsHit = 0;

        for (Ship ship : ships) {
            if (ship.hits == ship.life) {
                shipsSunk++;
            } else if (ship.hits > 0) {
                shipsHit++;
            }
        }

        return shipsSunk + "," + shipsHit;
    }

    private class Ship {
        public int left;
        public int right;
        public int top;
        public int bottom;

        public int life;
        public int hits;

        public Ship(String coordinate1, String coordinate2) {
            int[] coor1 = convertCoordinate(coordinate1);
            int[] coor2 = convertCoordinate(coordinate2);

            this.top = Math.min(coor1[0], coor2[0]);
            this.bottom = Math.max(coor1[0], coor2[0]);
            this.left = Math.min(coor1[1], coor2[1]);
            this.right = Math.max(coor1[1], coor2[1]);

            int height = this.bottom - this.top + 1;
            int width = this.right - this.left + 1;
            this.life = height * width;
            this.hits = 0;
        }

        public boolean hit(String hit) {
            int[] coor = convertCoordinate(hit);

            if (this.top <= coor[0] && coor[0] <= this.bottom && this.left <= coor[1] && coor[1] <= this.right) {
                this.hits++;
                return true;
            }

            return false;
        }
    }

    private static int[] convertCoordinate(String coordinate) {
        int row = Integer.parseInt(coordinate.substring(0, coordinate.length() - 1)) - 1;
        int col = coordinate.charAt(coordinate.length() - 1) - 'A';

        return new int[] {row, col};
    }

    public static void main(String[] args) {
        BattleshipGame solution = new BattleshipGame();
        System.out.println(solution.reportDamage(4, "1B 2C,2D 4D", "2B 2D 3D 4D 4A")); //1,1
        System.out.println(solution.reportDamage(3, "1A 1B,2C 2C", "1B")); //0,1
        System.out.println(solution.reportDamage(12, "1A 2A,12A 12A", "12A")); //1,0
    }
}
