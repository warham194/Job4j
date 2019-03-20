package ru.job4j.vvod;

import java.util.*;

public class Args {
    private final Map<String, String> action = new HashMap<>();
    private final List<String> inputArg = Arrays.asList("-d", "-e", "-o");

    public Args(String[] args) {
        command(args);
    }

    /**
     *  directory.
     * @return string
     */
    public String directory() {
        return action.get("-d");
    }

    /**
     * extensions.
     * @return list string
     */
    public String exclude() {
        return action.get("-e");
    }

    /**
     * Method output.
     * @return string
     */
    public String output() {
        return action.get("-o");
    }

    private void command(String[] args) {
        if (args.length != 6) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < args.length - 1; i++) {
            if (inputArg.contains(args[i])) {
                action.put(args[i], args[i + 1]);
            }
        }
        if (action.size() != inputArg.size()) {
            throw new IllegalArgumentException("Введены неверные аргументы");
        }
    }

    /**
     * @param args вводимые аргументы
     */
    public static void main(String[] args) {
        Args result = new Args(args);
        String directory = result.action.get("-d");
        String exclude = result.action.get("-e");
        String output = result.action.get("-o");
        ArchivedZip archivedZip = new ArchivedZip(directory, exclude, output);
        archivedZip.doZip();
    }
}