package com.kamarkaka.marshallwace;

/***
 * There are N blocks, numbered from 0 to N-1, arranged in a row. A couple of frogs were sitting together on one block
 * when they had a terrible quarrel. Now they want to jump away from one another so that the distance between them will
 * be as large as possible.
 * The distance between blocks numbered J and K is computed as K - J + 1. The frogs can only jump up, meaning that they
 * can move from one block to another only if the two blocks are adjacent and the second block is of the same or greater
 * height as the first.
 * What is the longest distance that they can possibly create between each other if they also chose to sit on the
 * optimal starting block initially?
 *
 * Write a function that, given an array blocks consisting of N integers denoting the heights of the blocks, returns the
 * longest possible distance that two frogs can make between each other starting from one of the blocks.
 *
 * Examples:
 * 1. blocks = [2,6,8,5] output: 3
 * 2. blocks = [1,5,5,2,6] output: 4
 * 3. blocks = [1, 1] output: 2
 *
 * Constraints:
 * - N is an integer with the range [2, 200,000];
 * - each element of array blocks is an integer within the range [1, 1,000,000,000].
 */
public class Frog {
    public int maxDistance(int[] blocks) {
        int result = 0;

        int index = 0;
        while (index < blocks.length - 1) {
            int dist = calcDist(blocks, index);
            result = Math.max(result, dist - index + 1);

            if (dist == blocks.length - 1) {
                break;
            }

            while (dist > index) {
                if (blocks[dist - 1] == blocks[dist]) {
                    dist--;
                } else {
                    break;
                }
            }

            index = dist;
        }

        return result;
    }

    private int calcDist(int[] blocks, int s) {
        boolean jumpLeft = true;
        int dist = s;
        int i = s;

        while (i < blocks.length - 1) {
            if (jumpLeft) {
                if (blocks[i] >= blocks[i + 1]) {
                    dist++;
                    i++;
                } else {
                    jumpLeft = false;
                }
            } else {
                if (blocks[i] <= blocks[i + 1]) {
                    dist++;
                    i++;
                } else {
                    break;
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        Frog solution = new Frog();
        System.out.println(solution.maxDistance(new int[] {2, 6, 8, 5}));
        System.out.println(solution.maxDistance(new int[] {1, 5, 5, 2, 6}));
        System.out.println(solution.maxDistance(new int[] {1, 1}));
    }
}
