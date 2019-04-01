package ru.job4j.vvod.testSearch;

/**
 * Created by Lenovo2 on 01.04.2019.
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StartSearch {

    private final static String LS = System.lineSeparator();
    private final ValidateParam validateParam;
    private final FileFinder finder;

    public StartSearch(ValidateParam validateParam, FileFinder finder) {
        this.validateParam = validateParam;
        this.finder = finder;
    }

    public void start() {
        if (validateParam.getParam().containsKey("-help")) {
            this.finder.help();
            return;
        }
        if (validateParam.validate()) {
            String logFileName = validateParam.getParam().get("-o");
            writeResult(finder.findFiles(), logFileName);
            System.out.println("Результат записан в файл: " + logFileName);
        } else {
            System.out.println("неправильно");
            this.finder.help();
        }
    }


    public void writeResult(List<File> files, String logName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logName))) {
            bw.write("------------------------------" + LS);
            for (File file : files) {
                bw.write(file.toString() + LS);
            }
            bw.write("------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> loadArgs(String[] args) {
        Map<String, String> argsMap = new HashMap<>();
        String str = null;
        for (String arg : args) {
            if (str != null) {
                argsMap.put(str, arg);
                str = null;
            }
            switch (arg) {
                case "-d": str = "-d"; break;
                case "-n": str = "-n"; break;
                case "-m": argsMap.put(arg, null); break;
                case "-f": argsMap.put(arg, null); break;
                case "-r": argsMap.put(arg, null); break;
                case "-o": str = "-o"; break;
                case "-help": argsMap.put(arg, null); break;
                default: break;
            }
        }
        return argsMap;
    }

    public static void main(String[] args) {
        Map<String, String> param = StartSearch.loadArgs(args);
        for (String arg : args) {
            System.out.println(arg);
        }
        ValidateParam validateParam = new ValidateParam(param);
        FileFinder fileFinder = new FileFinder(param);
        new StartSearch(validateParam, fileFinder).start();
    }
}