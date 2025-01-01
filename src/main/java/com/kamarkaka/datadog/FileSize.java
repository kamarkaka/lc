package com.kamarkaka.datadog;

import java.io.File;

/***
 * File System，input structure都提供好了，其实就是个N-ary Tree，每个tree node要么是directory with children nodes，
 * 要么是file with size value。需要输出total file sizes。
 * 解答很简单：DFS，遇到directory就继续recursion，否则就increase的total size。
 * follow up了一下：如果给一个inner directory的path string，如何输出total file sizes。
 * 其实也很简单：recursion method里面carry当前的path，和一个当前是否已经进入到了目标子树的flag。
 * 给一个文件夹路径 返回文件夹的大小
 * 求一个文件夹里所有file的size，有的文件是file，有的是folder，他们都是基于相同的base class。
 * 如果你用java的话，你可以像我一样，用instanceof来区分（面试官允许你google）。就是一个最简单的recursion
 * 给一个file system，要你统计文件大小。基本Tree traversal
 * home/
 * |--- me/
 * |. |--- foo.txt : 231
 * |. |--- abs.txt : 443
 * follow up:给你一个制定的dir，你去数一下里面文件的大小，比如 /home/me/
 */
public class FileSize {
    public long calculate(String dirPath) {
        File dir = new File(dirPath);

        if (!dir.exists()) {
            return -1;
        }

        if (!dir.isDirectory()) {
            return dir.length();
        }

        return getTotalSize(dir);
    }

    private long getTotalSize(File dir) {
        long totalSize = 0;

        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    totalSize += file.length();
                } else if (file.isDirectory()) {
                    totalSize += getTotalSize(file);
                }
            }
        }

        return totalSize;
    }

    public static void main(String[] args) {
        FileSize solution = new FileSize();
        System.out.println(solution.calculate("C:\\\\Development\\lc\\"));
    }
}
