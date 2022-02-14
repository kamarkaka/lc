/***
 * You are asked to cut off all the trees in a forest for a golf event. The forest is represented as an m x n matrix. In this matrix:
 *   0 means the cell cannot be walked through.
 *   1 represents an empty cell that can be walked through.
 *   A number greater than 1 represents a tree in a cell that can be walked through, and this number is the tree's height.
 * In one step, you can walk in any of the four directions: north, east, south, and west. If you are standing in a cell with a tree, you can choose whether to cut it off.
 * You must cut off the trees in order from shortest to tallest. When you cut off a tree, the value at its cell becomes 1 (an empty cell).
 * Starting from the point (0, 0), return the minimum steps you need to walk to cut off all the trees. If you cannot cut off all the trees, return -1.
 * You are guaranteed that no two trees have the same height, and there is at least one tree needs to be cut off.
 *
 * Example 1:
 *   Input: forest = [[1,2,3],[0,0,4],[7,6,5]]
 *   Output: 6
 *   Explanation: Following the path above allows you to cut off the trees from shortest to tallest in 6 steps.
 * Example 2:
 *   Input: forest = [[1,2,3],[0,0,0],[7,6,5]]
 *   Output: -1
 *   Explanation: The trees in the bottom row cannot be accessed as the middle row is blocked.
 * Example 3:
 *   Input: forest = [[2,3,4],[0,0,5],[8,7,6]]
 *   Output: 6
 *   Explanation: You can follow the same path as Example 1 to cut off all the trees.
 *   Note that you can cut off the first tree at (0, 0) before making any steps.
 *
 * Constraints:
 *   m == forest.length
 *   n == forest[i].length
 *   1 <= m, n <= 50
 *   0 <= forest[i][j] <= 10^9
 */

package main.java.com.kamarkaka;

import java.util.*;

public class LC0675 {
    private int[][] DIR = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};

    public int cutOffTree(List<List<Integer>> forest) {
        if(forest == null || forest.size() == 0) return -1;
        List<int[]> trees = new ArrayList();
        int rows = forest.size();
        int cols = forest.get(0).size();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int height = forest.get(i).get(j);
                if (height > 1) trees.add(new int[]{height, i, j});
            }
        }

        Collections.sort(trees, (o1,o2) -> Integer.compare(o1[0], o2[0]));

        int steps = 0;
        int[] start = new int[] {0,0};
        for (int[] tree : trees) {
            int step = walkToNextTree(forest, rows, cols, start, new int[] {tree[1], tree[2]});
            if (step < 0) return -1;
            steps += step;
            start = new int[] {tree[1], tree[2]};
        }

        return steps;
    }

    private int walkToNextTree(List<List<Integer>> forest, int rows, int cols, int[] start, int[] end) {
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[0] == end[0] && curr[1] == end[1]) {
                    forest.get(end[0]).set(end[1], 1);
                    return step;
                }

                for (int[] diff : DIR) {
                    int tempX = curr[0] + diff[0];
                    int tempY = curr[1] + diff[1];
                    if (tempX >= 0 && tempX < rows && tempY >= 0 && tempY < cols && forest.get(tempX).get(tempY) != 0 && !visited[tempX][tempY]) {
                        visited[tempX][tempY] = true;
                        queue.add(new int[] {tempX, tempY});
                    }
                }
            }
            step++;
        }

        return -1;
    }

    public static void run() {
        LC0675 solution = new LC0675();
        System.out.println(solution.cutOffTree(Arrays.asList(
            Arrays.asList(1,2,3),
            Arrays.asList(0,0,4),
            Arrays.asList(7,6,5)
        )));

        System.out.println(solution.cutOffTree(Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(0,0,0),
                Arrays.asList(7,6,5)
        )));

        System.out.println(solution.cutOffTree(Arrays.asList(
                Arrays.asList(69438,55243,0,43779,5241,93591,73380),
                Arrays.asList(847,49990,53242,21837,89404,63929,48214),
                Arrays.asList(90332,49751,0,3088,16374,70121,25385),
                Arrays.asList(14694,4338,87873,86281,5204,84169,5024),
                Arrays.asList(31711,47313,1885,28332,11646,42583,31460),
                Arrays.asList(59845,94855,29286,53221,9803,41305,60749),
                Arrays.asList(95077,50343,27947,92852,0,0,19731),
                Arrays.asList(86158,63553,56822,90251,0,23826,17478),
                Arrays.asList(60387,23279,78048,78835,5310,99720,0),
                Arrays.asList(74799,48845,60658,29773,96129,90443,14391),
                Arrays.asList(65448,63358,78089,93914,7931,68804,72633),
                Arrays.asList(93431,90868,55280,30860,59354,62083,47669),
                Arrays.asList(81064,93220,22386,22341,95485,20696,13436),
                Arrays.asList(50083,0,89399,43882,0,13593,27847),
                Arrays.asList(0,12256,33652,69301,73395,93440,0),
                Arrays.asList(42818,87197,81249,33936,7027,5744,64710),
                Arrays.asList(35843,0,99746,52442,17494,49407,63016),
                Arrays.asList(86042,44524,0,0,26787,97651,28572),
                Arrays.asList(54183,83466,96754,89861,84143,13413,72921),
                Arrays.asList(89405,52305,39907,27366,14603,0,14104),
                Arrays.asList(70909,61104,70236,30365,0,30944,98378),
                Arrays.asList(20124,87188,6515,98319,78146,99325,88919),
                Arrays.asList(89669,0,64218,85795,2449,48939,12869),
                Arrays.asList(93539,28909,90973,77642,0,72170,98359),
                Arrays.asList(88628,16422,80512,0,38651,50854,55768),
                Arrays.asList(13639,2889,74835,80416,26051,78859,25721),
                Arrays.asList(90182,23154,16586,0,27459,3272,84893),
                Arrays.asList(2480,33654,87321,93272,93079,0,38394),
                Arrays.asList(34676,72427,95024,12240,72012,0,57763),
                Arrays.asList(97957,56,83817,45472,0,24087,90245),
                Arrays.asList(32056,0,92049,21380,4980,38458,3490),
                Arrays.asList(21509,76628,0,90430,10113,76264,45840),
                Arrays.asList(97192,58807,74165,65921,45726,47265,56084),
                Arrays.asList(16276,27751,37985,47944,54895,80706,2372),
                Arrays.asList(28438,53073,0,67255,38416,63354,69262),
                Arrays.asList(23926,75497,91347,58436,73946,39565,10841),
                Arrays.asList(34372,69647,44093,62680,32424,69858,68719),
                Arrays.asList(24425,4014,94871,1031,99852,88692,31503),
                Arrays.asList(24475,12295,33326,37771,37883,74568,25163),
                Arrays.asList(0,18411,88185,60924,29028,69789,0),
                Arrays.asList(34697,75631,7636,16190,60178,39082,7052),
                Arrays.asList(24876,9570,53630,98605,22331,79320,88317),
                Arrays.asList(27204,89103,15221,91346,35428,94251,62745),
                Arrays.asList(26636,28759,12998,58412,38113,14678,0),
                Arrays.asList(80871,79706,45325,3861,12504,0,4872),
                Arrays.asList(79662,15626,995,80546,64775,0,68820),
                Arrays.asList(25160,82123,81706,21494,92958,33594,5243)
        )));


    }
}
