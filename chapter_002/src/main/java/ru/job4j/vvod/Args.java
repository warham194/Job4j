package ru.job4j.vvod;

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
    private String extensions = "-e";
    private String output = "-o";
    /**
     * Список аргументов
     */
    private String[] args;

    public Args(String[] args) {
        this.args = args;
    }

    /**
     * Analiz argument
     */
    private String argumentAnalise(String arg) {
        String result = "" ;
        try {
            for (int i = 0; i < args.length; i++) {
                if (args[i].equals(arg) && i < args.length - 1) {
                    result = args[i+1];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
        /* не работает
         String result = "";
        for (int i = 0; i <  args.length; i++) {
            if (args[i].equals(arg)) {
                while (i < args.length - 1) {
                    result = result + args[++i] + " ";
                    if (i + 1 == args.length ||  args[i + 1].contains("-")) {
                        break;
                    }
                }
                break;
            }
        }
        return result.trim();
         */
    }

    /**
     *  directory.
     * @return string
     */
    public String directory() {
        return argumentAnalise(this.directory);
    }

    /**
     * extensions.
     * @return list string
     */
    public List<String> exclude() {
        String result = argumentAnalise(this.extensions);
        return Arrays.asList(result.split(" "));
    }

    /**
     * Method output.
     * @return string
     */
    public String output() {
        return argumentAnalise(this.output());
    }
}
