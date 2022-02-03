package com.kamarkaka;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC0212 {
    private static final int[][] DIR = {{-1, 0},{1, 0},{0, -1},{0, 1}};
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) return new ArrayList<>();

        TrieNode root = new TrieNode('\0');
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                boolean found = false;
                List<TrieNode> children = node.children;
                for (TrieNode child : children) {
                    if (child.val == c) {
                        found = true;
                        node = child;
                        break;
                    }
                }
                if (!found) {
                    node = new TrieNode(c);
                    children.add(node);
                }
            }
            node.isEnd = true;
            node.word = word;
        }

        Set<String> result = new HashSet<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findWords(i, j, board, root, result, visited);
            }
        }
        return new ArrayList<>(result);
    }

    private void findWords(int i, int j, char[][] board, TrieNode root, Set<String> result, boolean[][] visited) {
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1) return;
        if (visited[i][j]) return;
        visited[i][j] = true;

        char c = board[i][j];
        List<TrieNode> children = root.children;
        for (TrieNode child : children) {
            if (c == child.val) {
                if (child.isEnd) {
                    result.add(child.word);
                }

                for (int[] dir : DIR) {
                    findWords(i + dir[0], j + dir[1], board, child, result, visited);
                }
            }
        }

        visited[i][j] = false;
    }

    class TrieNode {
        char val;
        String word;
        boolean isEnd;
        List<TrieNode> children;

        public TrieNode(char val) {
            this.val = val;
            this.isEnd = false;
            this.children = new ArrayList<>();
        }
    }
}
