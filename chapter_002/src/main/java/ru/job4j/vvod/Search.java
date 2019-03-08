package ru.job4j.vvod;


import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Lenovo2 on 08.03.2019.
 */
public class Search {

    public List<File> files(String parent, List<String> exts) {
        ArrayList<File> result = new ArrayList<>();
        Queue<File> data = new LinkedList<>();

        try {
            for (File file : Arrays.asList(new File(parent).listFiles())) {
                data.add(file);
            }

            File useFile;
            while (!data.isEmpty()) {
                useFile = data.poll();
                if (useFile.isDirectory()) {
                    for (File f : Arrays.asList(useFile.listFiles())) {
                        data.add(f);
                    }
                } else {
                    for (String s : exts) {
                        if (useFile.getName().endsWith(s)) {
                            result.add(useFile);
                        }
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
