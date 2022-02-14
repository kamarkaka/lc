package main.java.com.kamarkaka;

import java.util.LinkedList;
import java.util.Queue;

public class LC0733 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int m = image.length, n = image[0].length;
        int oldColor = image[sr][sc];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sr, sc});

        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            if (image[s[0]][s[1]] == newColor) continue;

            if (s[0] - 1 >= 0 && image[s[0] - 1][s[1]] == oldColor)
                queue.add(new int[]{s[0] - 1, s[1]});

            if (s[0] + 1 < m && image[s[0] + 1][s[1]] == oldColor)
                queue.add(new int[]{s[0] + 1, s[1]});

            if (s[1] - 1 >= 0 && image[s[0]][s[1] - 1] == oldColor)
                queue.add(new int[]{s[0], s[1] - 1});

            if (s[1] + 1 < n && image[s[0]][s[1] + 1] == oldColor)
                queue.add(new int[]{s[0], s[1] + 1});

            image[s[0]][s[1]] = newColor;
        }

        return image;
    }
}
