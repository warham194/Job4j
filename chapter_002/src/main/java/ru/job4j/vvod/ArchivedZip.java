package ru.job4j.vvod;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Lenovo2 on 10.03.2019.
 */
public class ArchivedZip {
    final File path;
    final String output;
    final String exclude;
    final List<File> files;

    /**
     *
     * @param directory
     * @param exclude
     * @param output
     */
    public ArchivedZip(String directory, String exclude, String output) {
        this.path = new File(directory);
        this.output = output;
        this.exclude = exclude;
        this.files = new Search().files(directory, Collections.singletonList(""));
    }


    public void doZip() {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(output))) {
            Queue<File> files = createQueueList();
            for (File file : files) {
                if (!file.getName().contains(exclude)) {
                    FileInputStream fin = new FileInputStream(file.getPath());
                    zout.putNextEntry(new ZipEntry(getPath(file)));
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fin.read(buffer)) != -1) {
                        zout.write(buffer, 0, length);
                    }
                    zout.closeEntry();
                    fin.close();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Queue<File> createQueueList() throws FileNotFoundException {
        Queue<File> result = new LinkedList<>();
            result.offer(path);
            while (!result.isEmpty()) {
                File file = result.poll();
                if (file.isDirectory()) {
                    for (File f : file.listFiles()) {
                        result.offer(f);
                    }
                }
            }
        return result;
    }

    /**
     * @param file
     * @return file name
     */
    private String getPath(File file) {
        String result = file.getAbsolutePath().replace(path.getPath(), "");
        if (result.startsWith(File.separator)) {
            result = result.replaceFirst("[" + File.separator + "/\\Q \\\\E]", "");
        }
        return result;
    }
}
