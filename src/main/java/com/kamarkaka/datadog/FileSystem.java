package com.kamarkaka.datadog;

import java.util.List;

/***
 * Given the following API of a file system:
 *   findList(string path): find all sub dir and files in current path
 *   delete(string path): if file or empty dir, delete and return true
 *   isDirectory(string path): if path is dir, return true
 * Implement rm -rf
 * Followup: How to fix if OOM?
 */
interface IFileSystem {
    List<String> findList(String path);
    boolean delete(String path);
    boolean isDirectory(String path);
}

public class FileSystem {
    private final IFileSystem fs;

    public FileSystem(IFileSystem fs) {
        this.fs = fs;
    }

    public void deleteRf(String path) {
        if (!fs.isDirectory(path)) {
            fs.delete(path);
        }

        List<String> subPaths = fs.findList(path);
        for (String subPath : subPaths) {
            deleteRf(subPath);
        }
    }
}
