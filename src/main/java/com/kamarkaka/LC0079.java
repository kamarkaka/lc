package com.kamarkaka;

public class LC0079 {
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0) && existPath(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean existPath(char[][] board, int i, int j, String word, int index, boolean[][] visited) {
        if (index == word.length()) return true;
        if (i < 0 || i >= board.length) return false;
        if (j < 0 || j >= board[i].length) return false;
        if (visited[i][j]) return false;
        if (board[i][j] != word.charAt(index)) return false;

        visited[i][j] = true;
        boolean up = existPath(board, i+1, j, word, index+1, visited);
        boolean down = existPath(board, i-1, j, word, index+1, visited);
        boolean right = existPath(board, i, j+1, word, index+1, visited);
        boolean left = existPath(board, i, j-1, word, index+1, visited);
        visited[i][j] = false;

        return up || down || right || left;
    }

    public static void run() {
        LC0079 solution = new LC0079();
        boolean exist = solution.exist(
            new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}},
            "ABCB");
        System.out.println(exist);
    }
}
