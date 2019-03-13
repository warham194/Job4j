package ru.job4j.vvod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lenovo2 on 10.03.2019.
 */
public class Args {
    /**
     * Аргументы
     */
    private String directory = "-d";
    private String output = "-o";
    private String extensions = "-e";
    /**
     * Список аргументов
     */
    private List<String> ext = new ArrayList<>();

    public Args(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d")) {
                this.directory = args[++i];
            } else if (args[i].equals("-o")) {
                this.output = args[++i];
            } else if (args[i].equals("-e")) {
                this.ext.add(args[++i]);
            }
        }
    }



    /**
     *  directory.
     * @return string
     */
    public String directory() {
        return this.directory;
    }

    /**
     * extensions.
     * @return list string
     */
    public List<String> exclude() {
        return this.ext;
    }

    /**
     * Method output.
     * @return string
     */
    public String output() {
        return this.output;
    }
}
