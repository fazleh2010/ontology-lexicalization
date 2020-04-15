/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citec.correlation.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 *
 * @author elahi
 */
public class FileFolderUtils {

    public static void createDirectory(String location) throws IOException {
        Path location_path = Paths.get(location);
        /*if (Files.exists(location_path)) {
            Files.delete(location_path);
        }*/
        Files.createDirectories(location_path);

    }

    public static File[] getFiles(String fileDir, String ntriple) {
        File dir = new File(fileDir);
        FileFilter fileFilter = new WildcardFileFilter("*" + ntriple);
        File[] files = dir.listFiles(fileFilter);
        return files;

    }

    public static List<File> getFiles(String fileDir, String category, String extension) {

        String[] files = new File(fileDir).list();
        List<File> selectedFiles = new ArrayList<File>();
        for (String fileName : files) {
            if (fileName.contains(category) && fileName.contains(extension)) {
                selectedFiles.add(new File(fileDir + fileName));
            }
        }

        return selectedFiles;

    }

}
