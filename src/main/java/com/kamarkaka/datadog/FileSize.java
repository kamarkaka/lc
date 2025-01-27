package com.kamarkaka.datadog;

import java.util.List;

/***
 * Calculate total file size
 * Given a file system structure representing directories and files, return the total size of files in the structure.
 * home/
 * ├── me/
 * │   ├── foo.txt : 416
 * │   ├── metrics.txt : 5892
 * │   └── src/
 * │       ├── site.html : 6051
 * │       ├── site.css : 5892
 * │       └── data.csv : 332789
 * └── you/
 *     └── dict.json : 4913364
 * bin/
 * ├── bash: 618416
 * ├── cat: 23648
 * └── ls: 38704
 * var/
 * └── log/
 *     ├── dmesg : 1783894
 *     ├── wifi.log : 924818
 *     └── httpd/
 *         ├── access.log : 17881
 *         └── access.log.0.gz : 4012
 * total_size(file_system) -> 8675777
 *
 * follow up: get total file size under a given path
 * total_size(file_system, "/") // 8675777
 * total_size(file_system, "/home") // 5264404
 * total_size(file_system, "/bin") // 680768
 * total_size(file_system, "/var/") // 2730605
 * total_size(file_system, "/home/me/") // 351040
 * total_size(file_system, "/var/log/wifi.log") // 924818
 */
public class FileSize {
    public static abstract class FSEntry {
        String name;

        private FSEntry(String name) {
            this.name = name;
        }
    }

    public static class Directory extends FSEntry {
        public List<FSEntry> content;

        public Directory(String name, FSEntry... entries) {
            super(name);
            this.content = List.of(entries);
        }
    }

    public static class File extends FSEntry {
        public int size;

        public File(String name, int size) {
            super(name);
            this.size = size;
        }
    }

    public static int getTotalSize(FSEntry entry) {
        if (entry == null) return 0;

        int totalSize = 0;

        if (entry instanceof Directory) {
            List<FSEntry> content = ((Directory) entry).content;
            for (FSEntry subEntry : content) {
                totalSize += getTotalSize(subEntry);
            }
        } else if (entry instanceof File) {
            totalSize = ((File) entry).size;
        }
        return totalSize;
    }

    public static int getTotalSize(FSEntry entry, String path) {
        return dfsHelper(entry, path, "/");
    }

    private static int dfsHelper(FSEntry entry, String path, String currentPath) {
        if (entry == null) return 0;
        int totalSize = 0;
        String xPath = currentPath.equals("/") ? "/" + entry.name : currentPath + "/" + entry.name;

        if (entry instanceof Directory) {
            if (xPath.equals(path) || xPath.startsWith(path)) {
                List<FSEntry> content = ((Directory) entry).content;
                for (FSEntry subEntry : content) {
                    totalSize += getTotalSize(subEntry);
                }
            } else if (path.startsWith(xPath)) {
                // parent of path
                List<FSEntry> content = ((Directory) entry).content;
                for (FSEntry subEntry : content) {
                    totalSize += dfsHelper(subEntry, path, xPath);
                }
            } else {
                return 0;
            }
        } else if (entry instanceof File) {
            if (xPath.startsWith(path)) {
                totalSize = ((File) entry).size;
            }
        }
        return totalSize;
    }

    public static void main(String[] args) {
        Directory root = new Directory(
                "",
                new Directory(
                        "home",
                        new Directory(
                                "me",
                                new File("foo.txt", 416),
                                new File("metrics.txt", 5892),
                                new Directory(
                                        "src",
                                        new File("site.html", 6051),
                                        new File("site.css", 5892),
                                        new File("data.csv", 332789))),
                        new Directory("you",
                                new File("dict.json", 4913364))),
                new Directory("bin",
                        new File("bash", 618416),
                        new File("cat", 23648),
                        new File("ls", 38704)),
                new Directory(
                        "var",
                        new Directory(
                                "log",
                                new File("dmesg", 1783894),
                                new File("wifi.log", 924818),
                                new Directory(
                                        "httpd",
                                        new File("access.log", 17881),
                                        new File("access.log.0.gz", 4012)))));
        FileSize solution = new FileSize();
        System.out.println(solution.getTotalSize(root));
        System.out.println(solution.getTotalSize(root, "/"));
        System.out.println(solution.getTotalSize(root, "/home"));
        System.out.println(solution.getTotalSize(root, "/bin"));
        System.out.println(solution.getTotalSize(root, "/var"));
        System.out.println(solution.getTotalSize(root, "/home/me/"));
        System.out.println(solution.getTotalSize(root, "/var/log/wifi.log"));
    }
}
