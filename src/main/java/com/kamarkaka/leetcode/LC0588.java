package com.kamarkaka.leetcode;

import java.util.*;

/***
 * 588. Design In-Memory File System
 * Design a data structure that simulates an in-memory file system.
 * Implement the FileSystem class:
 *   FileSystem() Initializes the object of the system.
 *   List<String> ls(String path)
 *   If path is a file path, returns a list that only contains this file's name.
 *   If path is a directory path, returns the list of file and directory names in this directory.
 *   The answer should in lexicographic order.
 *   void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist.
 *   If the middle directories in the path do not exist, you should create them as well.
 *   void addContentToFile(String filePath, String content)
 *   If filePath does not exist, creates that file containing given content.
 *   If filePath already exists, appends the given content to original content.
 *   String readContentFromFile(String filePath) Returns the content in the file at filePath.
 * Example 1:
 *   Input
 *   ["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
 *   [[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
 *   Output
 *   [null, [], null, null, ["a"], "hello"]
 *   Explanation
 *   FileSystem fileSystem = new FileSystem();
 *   fileSystem.ls("/");                         // return []
 *   fileSystem.mkdir("/a/b/c");
 *   fileSystem.addContentToFile("/a/b/c/d", "hello");
 *   fileSystem.ls("/");                         // return ["a"]
 *   fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
 * Constraints:
 *   1 <= path.length, filePath.length <= 100
 *   path and filePath are absolute paths which begin with '/' and do not end with '/' except that the path is just "/".
 *   You can assume that all directory names and file names only contain lowercase letters, and the same names will not
 *   exist in the same directory.
 *   You can assume that all operations will be passed valid parameters, and users will not attempt to retrieve file
 *   content or list a directory or file that does not exist.
 *   1 <= content.length <= 50
 *   At most 300 calls will be made to ls, mkdir, addContentToFile, and readContentFromFile.
 */
public class LC0588 {
    class FileSystem {
        File root;

        public FileSystem() {
            root = new File("");
        }

        public List<String> ls(String path) {
            String[] paths = path.split("/");
            List<String> result = new ArrayList<>();
            File node = root;

            for (String dir : paths) {
                if (dir.isEmpty()) continue;

                if (node.children != null && node.children.containsKey(dir)) {
                    node = node.children.get(dir);
                } else {
                    return new ArrayList<>();
                }
            }

            if (!node.isDirectory) {
                result.add(node.name);
            } else if (node.children != null) {
                for (String child : node.children.keySet()) {
                    result.add(child);
                }

                Collections.sort(result);
            }
            return result;
        }

        public void mkdir(String path) {
            String[] paths = path.split("/");
            File node = root;
            for (String dir : paths) {
                if (dir.isEmpty()) continue;

                if (node.children == null || !node.children.containsKey(dir)) {
                    File directory = new File(dir);
                    directory.isDirectory = true;
                    directory.children = new HashMap<>();

                    if (node.children == null) {
                        node.children = new HashMap<>();
                    }

                    node.children.put(dir, directory);
                }
                node = node.children.get(dir);
            }
        }

        public void addContentToFile(String filePath, String content) {
            String[] paths = filePath.split("/");
            File node = root;
            for (String dir : paths) {
                if (dir.isEmpty()) continue;

                if (node.children == null || !node.children.containsKey(dir)) {
                    File directory = new File(dir);
                    directory.isDirectory = true;
                    directory.children = new HashMap<>();

                    if (node.children == null) {
                        node.children = new HashMap<>();
                    }

                    node.children.put(dir, directory);
                }
                node = node.children.get(dir);
            }

            node.isDirectory = false;
            node.children = null;
            node.content = node.content == null ? content : node.content + content;
        }

        public String readContentFromFile(String filePath) {
            String[] paths = filePath.split("/");
            File node = root;
            for (String dir : paths) {
                if (dir.isEmpty()) continue;
                if (node.children == null || !node.children.containsKey(dir)) {
                    return "";
                }
                node = node.children.get(dir);
            }
            return node.content;
        }

        private class File {
            String name;
            boolean isDirectory;
            String content;
            Map<String, File> children;

            public File(String name) {
                this.name = name;
                this.isDirectory = true;
                this.content = null;
                this.children = null;
            }
        }
    }

}
