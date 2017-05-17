package org.xi.quick.utils.io;

import java.io.File;

/**
 * Created by Xi on 5/17/2017.
 */
public class DirectoryUtil {

    public static boolean exists(String directoryPath) {

        return new File(directoryPath).exists();
    }

    public static void createIfNotExists(String directoryPath) {

        if (!exists(directoryPath)) {
            File Folder = new File(directoryPath);
            Folder.mkdirs();
        }
    }

    public static File[] getAllFiles(String directoryPath) {

        File folder = new File(directoryPath);
        return folder.listFiles();
    }

    public static void delete(String directoryPath) {

        delete(new File(directoryPath));
    }

    public static void delete(File directory) {

        if(!directory.exists()) return;

        File[] files = directory.listFiles();
        if (files != null) {
            for (int i = 0, len = files.length; i < len; i++) {
                if(files[i].isDirectory()){
                    delete(files[i]);
                }
                files[i].delete();
            }
        }
    }
}
