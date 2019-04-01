package ru.job4j.vvod.testSearch;

import com.google.common.base.Joiner;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Lenovo2 on 01.04.2019.
 */
public class FileFinder {
    private final Map<String, String> param;

    public FileFinder(Map<String, String> param) {
        this.param = param;
    }

    public List<File> findFiles() {
        List<File> result = new ArrayList<>();
        String keyForSearch = getKeyForSearch();
        Queue<File> queueDir = new LinkedList<>();
        queueDir.offer(new File(param.get("-d")));
        while (!queueDir.isEmpty()) {
            for (File file : queueDir.poll().listFiles()) {
                if (file.isDirectory()) {
                    queueDir.offer(file);
                } else {
                    if (checkFile(file, keyForSearch)) {
                        result.add(file);
                    }
                }
            }
        }
        return result;
    }

    private boolean checkFile(File file, String keyForSearch) {
        if (keyForSearch.equals("-m"))
            return checkFileByMask(file, param.get("-n"));
        else if (keyForSearch.equals("-f"))
            return checkFileByFullName(file, param.get("-n"));

        return false;
    }

    private String getKeyForSearch() {
        for (String key : param.keySet()) {
            switch (key) {
                case "-m":
                    return "-m";
                case "-f":
                    return "-f";
                default:
                    break;
            }
        }
        return null;
    }

    private boolean checkFileByFullName(File file, String find) {
        return file.getName().equalsIgnoreCase(find);
    }

    private boolean checkFileByMask(File file, String find) {
        String fileName = file.getName();
        if (!find.contains("*")) {
            return this.checkFileByFullName(file, find);
        } else {
            String[] parts = find.split("\\*");
            for (String part : parts) {
                if (part.isEmpty()) {
                    continue;
                }
                if (!fileName.contains(part)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkFileByRegularExp(File file, String find) {
        return Pattern.matches(file.getName(), find);
    }

    public void help() {
        String forHelp = Joiner.on(System.lineSeparator()).join(
                "Ключи:",
                "-d-директория в которая начинать поиск.",
                "-n-имя файл,маска,либо регулярное выражение.",
                "-m -искать по маске, -f-полное совпадение имени, -r регулярное выражение.",
                "-o -результат записать в файл.",
                "Пример:",
                "java-jar find.jar -d c:/ -n *.txt -m -o log.txt"
        );
        System.out.println(forHelp);
    }
}
