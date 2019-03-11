package ru.job4j.vvod;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Lenovo2 on 10.03.2019.
 */
public class ArchivedZip {

    /**
     * Path to directory.
     */
    private static String dir;
    /**
     * Path to output file.
     */
    private static String zip;
    /**
     * List of extensions.
     */
    private static List<String> ex;

    public static void main(String[] args) {
        Args arg = new Args(args);
        dir = arg.directory();
        zip = arg.output();
        ex = arg.exclude();
    }

    /**
     * Metod file to zip
     */
    public void zipArchive() throws IOException {
        try(ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(zip))) {
            Search search = new Search();
            List<File> list = search.files(dir, ex);
            byte[] buf = new byte[1024];
            for (File f : list) {
                ZipEntry zipEntry = new ZipEntry(Paths.get(dir).relativize(Paths.get(f.getPath())).toString());
                outputStream.putNextEntry(zipEntry);
                FileInputStream fileInputStream = new FileInputStream(f);
                int bytesRead = fileInputStream.read(buf);
                while (bytesRead > -1) {
                    outputStream.write(buf, 0, bytesRead);
                    bytesRead = fileInputStream.read(buf);
                }
            }
        }
    }

}
